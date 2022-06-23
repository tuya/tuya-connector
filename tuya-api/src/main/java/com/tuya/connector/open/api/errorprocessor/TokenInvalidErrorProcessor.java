package com.tuya.connector.open.api.errorprocessor;

import com.tuya.connector.api.context.Context;
import com.tuya.connector.api.error.ErrorInfo;
import com.tuya.connector.api.error.ErrorProcessor;
import com.tuya.connector.api.exceptions.ConnectorException;
import com.tuya.connector.api.plugin.Invocation;
import com.tuya.connector.api.token.TokenManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/5 11:07 上午
 */
@Slf4j
public class TokenInvalidErrorProcessor implements ErrorProcessor {

    @Override
    @SneakyThrows
    public Object process(ErrorInfo errorInfo, Invocation invocation, Context context) {
        log.warn("token error processor : {}", errorInfo);
        if (context.getApiDataSource().isAutoRefreshToken()) {
            log.warn("token invalid error processor: refresh token and auto retry call request.");
            TokenManager<?> tokenManager = context.getApiDataSource().getTokenManager();
            tokenManager.refreshToken();
            return invocation.proceed();
        }
        throw new ConnectorException(errorInfo.toString());
    }

    @Override
    public String getErrorCode() {
        return "1010";
    }
}
