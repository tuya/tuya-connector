package com.tuya.open.spring.boot.sample;

import com.tuya.connector.spring.annotations.ConnectorScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@ConnectorScan(basePackages = "com.tuya.open.spring.boot.sample.ability.api")
//@EnableMessaging(msgPaths = {"com.tuya.open.spring.boot.sample.ability.messaging.msg"})
@SpringBootApplication
public class TuyaSpringBootStarterSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuyaSpringBootStarterSampleApplication.class, args);
    }

}
