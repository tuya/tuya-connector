package com.tuya.connector.open.messaging.autoconfig;

import com.tuya.connector.messaging.MessageDataSource;
import com.tuya.connector.open.messaging.TuyaMessageDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/25 8:32 下午
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class MessageAutoConfiguration {

    private final MessageProperties messageProperties;

    public MessageAutoConfiguration(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    @Bean
    public TuyaMessageDispatcher tuyaMessageDispatcher() {
        MessageDataSource messageDataSource = MessageDataSource.builder()
            .url(messageProperties.getUrl())
            .ak(messageProperties.getAk())
            .sk(messageProperties.getSk())
            .build();
        return new TuyaMessageDispatcher(messageDataSource);
    }

}
