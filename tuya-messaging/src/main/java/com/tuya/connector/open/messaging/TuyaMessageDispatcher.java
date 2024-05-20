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
        this.tuyaMessageDataSource = tuyaMessageDataSource;
    }

    @Override
    @SneakyThrows
    @PostConstruct
    public void dispatch() {
        log.debug("###TUYA_PULSAR_MSG => start initial pulsar consumer");
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
                public void start() throws PulsarClientException {}

                @Override
                public void close() throws IOException {}
            })
            .build();
        String topic = String.format("%s/out/event", ak);
        String subscriptionName = String.format("%s-sub", ak);
        Consumer consumer = client.newConsumer()
            .loadConf(tuyaMessageDataSource.consumerLoadConf())
            .topic(topic)
            .subscriptionName(subscriptionName)
            .subscribe();
        log.debug("###TUYA_PULSAR_MSG => pulsar consumer initial success");
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
        Thread worker = new Thread(
            () -> {
                boolean consumerFlag = true;
                while (switchFlag) {
                    log.debug("###TUYA_PULSAR_MSG => start receive next message");
                    Message message = null;
                    BaseTuyaMessage baseTuyaMessage = null;
                    MessageId msgId = null;
                    String tid = null;
                    long publishTime = -1L;
                    try {
                        message = consumer.receive();
                        if (Objects.isNull(message)) {
                            continue;
                        }
                        msgId = message.getMessageId();
                        tid = message.getProperty("tid");
                        publishTime = message.getPublishTime();
                        log.debug("###TUYA_PULSAR_MSG => message received, msgId={}, publishTime={}, tid={}", msgId, publishTime, tid);
                        String payload = new String(message.getData());
                        SourceMessage sourceMessage = JSON.parseObject(payload, SourceMessage.class);
                        BaseTuyaMessage msg = MessageFactory.extract(sourceMessage, sk);
                        log.debug("###TUYA_PULSAR_MSG => start process message, messageId={}, publishTime={}, tid={}, payload={}",
                            msgId, publishTime, tid, payload);
                        ctx.publishEvent(msg);
                        log.debug("###TUYA_PULSAR_MSG => finish process message, messageId={}, publishTime={}, tid={}",
                            msgId, publishTime, tid);
                        consumerFlag = true;
                    } catch (Exception e) {
                        consumerFlag = false;
                        log.error(String.format("###TUYA_PULSAR_MSG => Consume tuya message error, msgId: %s, tid: %s ", msgId, tid), e);
                    } finally {
                        if (Objects.nonNull(message)) {
                            try {
                                log.debug("###TUYA_PULSAR_MSG => start message ack, messageId={}, publishTime={}, tid={}", msgId, publishTime, tid);
                                if (consumerFlag) {
                                    consumer.acknowledge(message);
                                } else {
                                    consumer.negativeAcknowledge(message);
                                }
                                log.debug("###TUYA_PULSAR_MSG => message ack success, ackFlag={}, messageId={}, publishTime={}, tid={}", consumerFlag, msgId, publishTime, tid);
                            } catch (PulsarClientException e) {
                                log.error(String.format("###TUYA_PULSAR_MSG => Ack tuya message error, msgId: %s, tid: %s ", msgId, tid), e);
                            }
                        }
                    }
                }
            }
        );
        worker.setDaemon(true);
        return worker;
    }

}
