package com.tuya.connector.open.api.token;

import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/4 5:53 下午
 */
public interface TokenConnector {

    @GET("/v1.0/token")
    TuyaToken getToken(@Query("grant_type") int grantType);

    @GET("/v1.0/token/{refresh_token}")
    TuyaToken refreshToken(@Path("refresh_token") String refreshToken);

}
