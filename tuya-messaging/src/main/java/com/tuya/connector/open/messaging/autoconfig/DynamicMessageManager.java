package com.tuya.connector.open.messaging.autoconfig;

import com.tuya.connector.open.common.constant.EnvConstant;
import com.tuya.connector.open.common.constant.TuyaRegion;
import com.tuya.connector.open.messaging.TuyaMessageDispatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/25 8:32 下午
 */

@Slf4j
@Configuration
public class DynamicMessageManager implements EnvironmentAware, ApplicationContextAware {

    static Environment env;
    static ApplicationContext ctx;
    static Map<String, TuyaMessageDispatcher> messageMap = new ConcurrentHashMap<>();

    /**
     * add new message dispatcher
     * @param ak
     * @param sk
     * @param url
     * @return
     */
    public static TuyaMessageDispatcher addMessageDispatcher(String ak, String sk, String url) {
        if (Objects.isNull(messageMap.get(ak))) {
            log.warn("message dispatcher has exists,url:{}",url);
        }
        if (StringUtils.isEmpty(url)) {
            String region = env.getProperty(EnvConstant.ENV_REGION);
            url = StringUtils.isEmpty(region) ? TuyaRegion.CN.getMsgUrl() : TuyaRegion.valueOf(region).getMsgUrl();
        }
        TuyaMessageDataSource tuyaMessageDataSource = new TuyaMessageDataSource(url, ak, sk);
        TuyaMessageDispatcher tuyaMessageDispatcher = new TuyaMessageDispatcher(tuyaMessageDataSource);
        tuyaMessageDispatcher.setApplicationContext(ctx);
        tuyaMessageDispatcher.dispatch();
        messageMap.put(ak, tuyaMessageDispatcher);
        return tuyaMessageDispatcher;
    }

    public static Boolean stopMessageDispatcher(String ak, String sk, String url) {
        TuyaMessageDispatcher tuyaMessageDispatcher = messageMap.get(ak);
        if (Objects.isNull(tuyaMessageDispatcher)) {
            log.warn("message dispatcher not exists,url:{}", url);
            return true;
        }
        boolean result = tuyaMessageDispatcher.stop();
        log.info("stop message dispatcher {},url:{}", result,  url);
        if (result) {
            messageMap.remove(ak);
        }
        return result;
    }


    @Override
    public void setEnvironment(Environment environment) {
        env = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
