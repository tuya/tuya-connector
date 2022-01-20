package com.tuya.connector.open.spring.boot;


import com.tuya.connector.open.api.config.TuyaRegionConfig;
import com.tuya.connector.open.api.errorprocessor.TokenInvalidErrorProcessor;
import com.tuya.connector.spring.annotations.ConnectorScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p> TODO
 *
 * @author qiufeng.yu@tuya.com
 * @since 2021/1/25 5:51 下午
 */

@Slf4j
@Configuration
@ImportAutoConfiguration(TuyaRegionConfig.class)
@PropertySource("classpath:/_tuya-default.properties")
@ConnectorScan(basePackages = {"com.tuya.connector.open.ability"})
public class TuyaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TokenInvalidErrorProcessor tokenInvalidErrorProcessor() {
        return new TokenInvalidErrorProcessor();
    }
}
