package com.tuya.connector.open.api.context;

import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.context.Context;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/6 11:22 上午
 */
public class TuyaContext implements Context {

    private ApiDataSource apiDataSource;
    private String lang;

    public void setApiDataSource(ApiDataSource apiDataSource) {
        this.apiDataSource = apiDataSource;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public ApiDataSource getApiDataSource() {
        return apiDataSource;
    }

    @Override
    public String getLang() {
        return lang;
    }
}
