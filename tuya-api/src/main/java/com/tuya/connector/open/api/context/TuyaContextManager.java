package com.tuya.connector.open.api.context;

import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.context.ContextManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/6 11:22 上午
 */
@Slf4j
public class TuyaContextManager implements ContextManager<TuyaContext> {
    private static final ThreadLocal<TuyaContext> ctx = new InheritableThreadLocal<>();

    private final Configuration configuration;
    public TuyaContextManager(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TuyaContext start() {
        TuyaContext tuyaCtx = new TuyaContext();
        tuyaCtx.setApiDataSource(configuration.getApiDataSource());
        tuyaCtx.setLang("zh");// TODO
        ctx.set(tuyaCtx);
        return tuyaCtx;
    }

    @Override
    public void clear() {
        ctx.remove();
    }

    @Override
    public TuyaContext get() {
        return ctx.get();
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
