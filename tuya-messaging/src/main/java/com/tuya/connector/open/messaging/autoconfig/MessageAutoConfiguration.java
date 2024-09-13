package com.tuya.connector.open.messaging.autoconfig;

import com.tuya.connector.open.messaging.MessageRegister;
import com.tuya.connector.open.messaging.TuyaMessageDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/25 8:32 下午
 */

@Slf4j
@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class MessageAutoConfiguration implements ImportBeanDefinitionRegistrar {

    private final MessageProperties messageProperties;

    public MessageAutoConfiguration(MessageProperties messageProperties) {
        this.messageProperties = messageProperties;
    }

    @Bean
    public TuyaMessageDispatcher tuyaMessageDispatcher(TuyaMessageDataSource tuyaMessageDataSource) {
        if (StringUtils.isEmpty(tuyaMessageDataSource.getUrl())) {
            tuyaMessageDataSource.setUrl(messageProperties.getUrl());
        }
        if (StringUtils.isEmpty(tuyaMessageDataSource.getAk())) {
            tuyaMessageDataSource.setUrl(messageProperties.getAk());
        }
        if (StringUtils.isEmpty(tuyaMessageDataSource.getSk())) {
            tuyaMessageDataSource.setUrl(messageProperties.getSk());
        }
        if (StringUtils.isEmpty(tuyaMessageDataSource.getSubNameSuffix())) {
            tuyaMessageDataSource.setUrl(messageProperties.getSubNameSuffix());
        }
        return new TuyaMessageDispatcher(tuyaMessageDataSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public TuyaMessageDataSource tuyaMessageDataSource() {
        return new TuyaMessageDataSource(
            messageProperties.getUrl(),
            messageProperties.getAk(),
            messageProperties.getSk(),
            messageProperties.getSubNameSuffix()
        );
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableMessaging.class.getName()));
        Set<String> pkgPaths = new HashSet<>();
        if (attributes != null) {
            String[] paths = attributes.getStringArray("msgPaths");
            for (String path : paths) {
                pkgPaths.add(path);
            }
        }
        MessageRegister.init(pkgPaths);
    }
}
