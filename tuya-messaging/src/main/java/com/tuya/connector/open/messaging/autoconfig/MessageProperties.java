package com.tuya.connector.open.messaging.autoconfig;

import com.tuya.connector.open.common.constant.EnvConstant;
import com.tuya.connector.open.common.constant.TuyaRegion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/1/25 5:52 下午
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = MessageProperties.MESSAGE_PREFIX)
public class MessageProperties implements EnvironmentAware {
    protected static final String MESSAGE_PREFIX = "connector.message";

    String url;

    String ak;

    String sk;

    /**
     * pulsar 消息订阅名称后缀，订阅名称完整格式为：clientId-{subNameSuffix}
     * 默认值为：sub
     */
    String subNameSuffix = "sub";

    @Override
    public void setEnvironment(Environment env) {
        if (StringUtils.isEmpty(ak)) {
            this.ak = env.getProperty(EnvConstant.ENV_AK, "");
        }

        if (StringUtils.isEmpty(sk)) {
            this.sk = env.getProperty(EnvConstant.ENV_SK, "");
        }

        if (StringUtils.isEmpty(url)) {
            String region = env.getProperty(EnvConstant.ENV_REGION);
            this.url = StringUtils.isEmpty(region) ? TuyaRegion.CN.getMsgUrl() : TuyaRegion.valueOf(region).getMsgUrl();
        }
    }
}
