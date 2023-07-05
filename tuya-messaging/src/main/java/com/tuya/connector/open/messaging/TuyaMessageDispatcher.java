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
        final String ak = tuyaMessageDataSource.getAk();
        final String sk = tuyaMessageDataSource.getSk();
        String url = tuyaMessageDataSource.getUrl();

        PulsarClient client = PulsarClient.builder()
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
            .loadConf(tuyaMessageDataSource.clientLoadConf())
            .build();
        Consumer consumer = client.newConsumer()
            .topic(String.format("%s/out/%s", ak, "event"))
            .subscriptionName(String.format("%s-sub", ak))
            .loadConf(tuyaMessageDataSource.consumerLoadConf())
            .subscribe();
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

    private Runnable worker(Consumer consumer, String sk) {
        Thread worker = new Thread(
            () -> {
                while (switchFlag) {
                    Message message = null;
                    BaseTuyaMessage baseTuyaMessage = null;
                    try {
                        message = consumer.receive();
                        if (Objects.isNull(message)) {
                            continue;
                        }
                        SourceMessage sourceMessage = JSON.parseObject(new String(message.getData()), SourceMessage.class);
                        BaseTuyaMessage msg = MessageFactory.extract(sourceMessage, sk);
                        ctx.publishEvent(msg);
                    } catch (Exception e) {
                        log.error(String.format("Consume tuya message error, source message: %s ", baseTuyaMessage), e);
                    } finally {
                        if (Objects.nonNull(message)) {
                            try {
                                consumer.acknowledge(message);
                            } catch (PulsarClientException e) {
                                log.error(String.format("Ack tuya message error, pulsar message: %s ", message), e);
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
