package com.tuya.connector.open.api.config;

import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.exceptions.ConnectorException;
import com.tuya.connector.open.common.constant.EnvConstant;
import com.tuya.connector.open.common.constant.TuyaRegion;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @Classname TuyaRegionConfig
 * @Description TODO
 * @Date 2021/4/2
 * @Author 哲也（张梓濠 zheye.zhang@tuya.com）
 */
@AutoConfigureAfter(Configuration.class)
public class TuyaRegionConfig implements EnvironmentAware, InitializingBean {

    private Configuration configuration;

    private Environment environment;

    public TuyaRegionConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void setEnvironment(Environment env) {
        this.environment = env;
    }

    @Override
    public void afterPropertiesSet() {
        if (StringUtils.isEmpty(configuration.getApiDataSource().getBaseUrl())) {
            String evnRegion = environment.getProperty(EnvConstant.ENV_REGION, TuyaRegion.CN.name());
            try {
                TuyaRegion tuyaRegion = TuyaRegion.valueOf(evnRegion);
                configuration.getApiDataSource().setBaseUrl(tuyaRegion.getApiUrl());
            } catch (IllegalArgumentException e) {
                throw new ConnectorException("Connector region must in legal scope!");
            }
        }
    }
}
