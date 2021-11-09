package com.tuya.open.spring.boot.sample;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.open.messaging.autoconfig.MessageProperties;
import com.tuya.connector.spring.boot.autoconfigure.ConnectorProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(classes = {TuyaSpringBootStarterSampleApplication.class})
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

    public ExecutorService executor = Executors.newFixedThreadPool(1);

    @Test
    public void threadTest() throws InterruptedException {
        executor = TtlExecutors.getTtlExecutorService(executor);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(() ->{
            configuration.getApiDataSource().setAk("1");
            configuration.getApiDataSource().setSk("1");
            log.info("t1:{} ak:{}, sk:{}", Thread.currentThread(), configuration.getApiDataSource().getAk(), configuration.getApiDataSource().getSk());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.execute(()->
                    log.info("t1 child:{} ak:{}, sk:{}", Thread.currentThread(), configuration.getApiDataSource().getAk(), configuration.getApiDataSource().getSk()));
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() ->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            configuration.getApiDataSource().setAk("2");
            configuration.getApiDataSource().setSk("2");
            log.info("t2:{} ak:{}, sk{}",Thread.currentThread(), configuration.getApiDataSource().getAk(), configuration.getApiDataSource().getSk());
            executor.execute(() ->
                    log.info("t2 child:{} ak:{}, sk:{}", Thread.currentThread(), configuration.getApiDataSource().getAk(), configuration.getApiDataSource().getSk()));
            countDownLatch.countDown();
        });

        t1.start();
        t2.start();
        countDownLatch.await();
    }

}
