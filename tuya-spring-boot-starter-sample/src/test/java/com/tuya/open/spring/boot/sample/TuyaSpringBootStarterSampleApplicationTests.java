package com.tuya.open.spring.boot.sample;

import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.open.messaging.autoconfig.MessageProperties;
import com.tuya.connector.spring.boot.autoconfigure.ConnectorProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TuyaSpringBootStarterSampleApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Configuration configuration;

    @Autowired
    private Environment environment;

    @Autowired
    private MessageProperties messageProperties;

    @Test
    void contextLoads() {
        ConnectorProperties bean = applicationContext.getBean(ConnectorProperties.class);
        Boolean auto = environment.getRequiredProperty("connector.api.auto-set-header", Boolean.class);
        System.out.println(configuration.getApiDataSource().getBaseUrl());
        System.out.println(messageProperties.getUrl());
        assertNotNull(bean.getApi().getContextManager());
        assertTrue(auto);
    }

}
