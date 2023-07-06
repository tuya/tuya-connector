package com.tuya.connector.open.messaging;

import com.alibaba.fastjson.JSON;
import com.tuya.connector.messaging.MessageDataSource;
import com.tuya.connector.messaging.MessageDispatcher;
import com.tuya.connector.open.messaging.autoconfig.TuyaMessageDataSource;
import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.shade.org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/3 11:57 上午
 */
@Slf4j
@SuppressWarnings("rawtypes")
public class TuyaMessageDispatcher implements MessageDispatcher, ApplicationContextAware {
    private boolean switchFlag = true;

    static ApplicationContext ctx;

    private final TuyaMessageDataSource tuyaMessageDataSource;
    private final static ExecutorService EXECUTORS = Executors.newSingleThreadExecutor();

    public TuyaMessageDispatcher(TuyaMessageDataSource tuyaMessageDataSource) {
        log.debug("###STEP 1: start init tuya message dispatcher");
        this.tuyaMessageDataSource = tuyaMessageDataSource;
    }

    @Override
    @SneakyThrows
    @PostConstruct
    public void dispatch() {
        final String ak = tuyaMessageDataSource.getAk();
        final String sk = tuyaMessageDataSource.getSk();
        String url = tuyaMessageDataSource.getUrl();

        PulsarClient client = PulsarClient.builder()
            .loadConf(tuyaMessageDataSource.clientLoadConf())
            .serviceUrl(url)
            .authentication(new Authentication() {
                private static final long serialVersionUID = -826735355004851795L;

                @Override
                public String getAuthMethodName() {
                    return "auth1";
                }

                @Override
                public AuthenticationDataProvider getAuthData() throws PulsarClientException {
                    return new AuthenticationDataProvider() {
                        private static final long serialVersionUID = 3721578352443318652L;

                        @Override
                        public boolean hasDataFromCommand() {
                            return true;
                        }

                        @Override
                        public String getCommandData() {
                            return String.format("{\"username\":\"%s\",\"password\":\"%s\"}",
                                ak, DigestUtils.md5Hex(ak + DigestUtils.md5Hex(sk)).substring(8, 24));
                        }
                    };
                }

                @Override
                public void configure(Map<String, String> authParams) {}

                @Override
                public void start() throws PulsarClientException {
                    log.debug("pulsar client start auth");
                }

                @Override
                public void close() throws IOException {
                    log.debug("pulsar client close auth");
                }
            })
            .build();
        log.debug("###STEP 2: pulsar client build success, client config: {}",
            JSON.toJSONString(tuyaMessageDataSource.clientLoadConf()));

        String topic = String.format("%s/out/event", ak);
        String subscriptionName = String.format("%s-sub", ak);
        Consumer consumer = client.newConsumer()
            .loadConf(tuyaMessageDataSource.consumerLoadConf())
            .topic(topic)
            .subscriptionName(subscriptionName)
            .subscribe();
        log.debug("###STEP 3: pulsar consumer create success, topic: {}, subscriptionName: {}, consumer config: {}",
            topic, subscriptionName, JSON.toJSONString(tuyaMessageDataSource.consumerLoadConf()));

        EXECUTORS.execute(worker(consumer, sk));
    }

    @Override
    public boolean stop() {
        switchFlag = false;
        EXECUTORS.shutdownNow();
        return true;
    }

    @Override
    public MessageDataSource getMsgDataSource() {
        return tuyaMessageDataSource;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @SuppressWarnings("all")
    private Runnable worker(Consumer consumer, String sk) {
        log.debug("###STEP 4: start create tuya message dispatcher worker");
        Thread worker = new Thread(
            () -> {
                log.debug("###STEP 6: tuya message dispatcher worker is running...");
                boolean consumerFlag = true;
                while (switchFlag) {
                    Message message = null;
                    BaseTuyaMessage baseTuyaMessage = null;
                    MessageId messageId = null;
                    String tid = null;
                    try {
                        log.debug("###Consume STEP 1: receive message");
                        message = consumer.receive();
                        if (Objects.isNull(message)) {
                            continue;
                        }
                        messageId = message.getMessageId();
                        tid = message.getProperty("tid");
                        log.debug("###Consume STEP 2: message received, messageId: {}, tid: {}", messageId, tid);
                        SourceMessage sourceMessage = JSON.parseObject(new String(message.getData()), SourceMessage.class);
                        BaseTuyaMessage msg = MessageFactory.extract(sourceMessage, sk);
                        log.debug("###Consume STEP 3: message parsed, messageId: {}, tid: {}, event type: {}", messageId, tid, msg.getEventType());
                        ctx.publishEvent(msg);
                        log.debug("###Consume STEP 4: publish message to spring event , messageId: {}, tid: {}, event type: {}", messageId, tid, msg.getEventType());
                        consumerFlag = true;
                    } catch (Exception e) {
                        consumerFlag = false;
                        log.debug(String.format("Consume tuya message error, messageId: %s, tid: %s ", messageId, tid), e);
                    } finally {
                        if (Objects.nonNull(message)) {
                            try {
                                log.debug("###Consume STEP 5: start message ack, messageId: {}, tid: {}", messageId, tid);
                                if (consumerFlag) {
                                    consumer.acknowledge(message);
                                    log.debug("###Consume STEP 6: message ack success, messageId: {}, tid: {}", messageId, tid);
                                } else {
                                    consumer.negativeAcknowledge(message);
                                    log.debug("###Consume STEP 6: message ack negative, messageId: {}, tid: {}", messageId, tid);
                                }
                            } catch (PulsarClientException e) {
                                log.error(String.format("Ack tuya message error, messageId: %s, tid: %s ", messageId, tid), e);
                            }
                        }
                    }
                }
            }
        );
        worker.setDaemon(true);
        log.debug("###STEP 5: create tuya message dispatcher worker success");
        return worker;
    }

}
