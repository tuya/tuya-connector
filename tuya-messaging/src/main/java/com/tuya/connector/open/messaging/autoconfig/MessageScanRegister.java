package com.tuya.connector.open.messaging.autoconfig;

import com.tuya.connector.open.messaging.MessageRegister;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MessageScanRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableMessaging.class.getName()));
        Set<String> pkgPaths = new HashSet<>();
        if (attributes != null) {
            String[] paths = attributes.getStringArray("msgPaths");
            pkgPaths.addAll(Arrays.asList(paths));
        }
        MessageRegister.init(pkgPaths);
    }
}
