package com.tuya.connector.open.messaging;

import com.tuya.connector.messaging.MessageDataSource;
import com.tuya.connector.open.messaging.event.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/24 2:41 下午
 */
@Slf4j
public class MessageTest {
    static AnnotationConfigApplicationContext ctx;

    @BeforeAll
    static void init() {
        ctx = new AnnotationConfigApplicationContext();
        ctx.register(MessageConfig.class);
        ctx.refresh();
        ctx.start();
    }

    @Test
    void msgTest() {
        while (true) {}
    }


    @Configuration
    static class MessageConfig {
        @EventListener
        public void online(OnlineMessage onlineMessageEvent) {
            log.info("### online event happened, event: {}", onlineMessageEvent);
        }

        @EventListener
        public void offline(OfflineMessage offlineMessageEvent) {
            log.info("### offline event happened, event: {}", offlineMessageEvent);
        }

        @EventListener
        public void nameUpdate(NameUpdateMessage event) {
            log.info("### nameUpdate event happened, event: {}", event);

        }

        @EventListener
        public void delete(DeleteMessage event) {
            log.info("### delete event happened, event: {}", event);
        }

        @EventListener
        public void bindUser(BindUserMessage event) {
            log.info("### bindUser event happened, event: {}", event);
        }

        @EventListener
        public void statusReportMessage(StatusReportMessage event) {
            log.info("### statusReport event happened, event: {}", event);
        }

        @EventListener
        public void unknownMessage(UnknownMessage event) {
            log.info("### unknown event happened, event: {}", event);
        }

        @Bean
        public TuyaMessageDispatcher tuyaMessageDispatcher(MessageDataSource messageDataSource) {
            return new TuyaMessageDispatcher(messageDataSource);
        }

        @Bean
        public MessageDataSource messageDataSource() {
            return MessageDataSource.builder()
                    .url("pulsar+ssl://mqe.tuyacn.com:7285/")
                    .ak(SecurityInfo.AK)
                    .sk(SecurityInfo.SK)
                    .build();
        }
    }

}
