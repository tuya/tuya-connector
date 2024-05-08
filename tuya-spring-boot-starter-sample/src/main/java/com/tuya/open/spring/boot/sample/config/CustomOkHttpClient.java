package com.tuya.open.spring.boot.sample.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class CustomOkHttpClient implements ApplicationContextAware {
    private static ApplicationContext ctx;


    @PostConstruct
    @DependsOn("tuyaConfiguration")
    public void customSetOkHttpClient() {
        log.info("自定义OkHttpClient...");
        com.tuya.connector.api.config.Configuration configuration = ctx.getBean(com.tuya.connector.api.config.Configuration.class);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(20);
        OkHttpClient myOkHttpClient = new OkHttpClient.Builder().dispatcher(dispatcher).build();
        configuration.getApiDataSource().setSpecificClient(myOkHttpClient);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
