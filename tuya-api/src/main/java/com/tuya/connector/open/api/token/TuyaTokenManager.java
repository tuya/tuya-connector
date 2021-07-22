package com.tuya.connector.open.api.token;

import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.core.DefaultConnectorFactory;
import com.tuya.connector.api.token.TokenManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/6 11:31 上午
 */
@Slf4j
public class TuyaTokenManager implements TokenManager<TuyaToken> {

    private final static int TOKEN_GRANT_TYPE = 1;
    private final static ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private final TokenConnector connector;
    private final Map<String, TuyaToken> cachedTokenMap = new ConcurrentHashMap<>();
    private final Configuration configuration;

    public TuyaTokenManager(Configuration configuration) {
        this.configuration = configuration;
        connector = new DefaultConnectorFactory(configuration).loadConnector(TokenConnector.class);
    }

    @Override
    public TuyaToken getCachedToken() {
        String currentAk = configuration.getApiDataSource().getAk();
        if (Objects.isNull(cachedTokenMap.get(currentAk))) {
            cachedTokenMap.put(currentAk, getToken());
        }
        return cachedTokenMap.get(currentAk);
    }

    public void setCachedToken(TuyaToken cachedToken) {
        cachedTokenMap.put(configuration.getApiDataSource().getAk(), cachedToken);
    }

    @Override
    @SneakyThrows
    public TuyaToken getToken() {
        TuyaToken token = connector.getToken(TOKEN_GRANT_TYPE);
        if (Objects.isNull(token)) {
            log.error("Get token required not null.");
        }
        setCachedToken(token);
        log.info("Get token success, token: {}", token);
        return token;
    }

    @Override
    @SneakyThrows
    public TuyaToken refreshToken() {
        String ak = configuration.getApiDataSource().getAk();
        String sk = configuration.getApiDataSource().getSk();
        Future<TuyaToken> future = EXECUTOR.submit(() -> {
            try {
                configuration.getApiDataSource().setAk(ak);
                configuration.getApiDataSource().setSk(sk);
                return connector.getToken(TOKEN_GRANT_TYPE);
            } catch (Exception e) {
                log.error("refresh token error", e);
                return null;
            } finally {
                configuration.getApiDataSource().clear();
            }
        });
        TuyaToken refreshedToken = future.get();
        if (Objects.isNull(refreshedToken)) {
            log.error("Refreshed token required not null.");
        }
        cachedTokenMap.put(configuration.getApiDataSource().getAk(), refreshedToken);
        return refreshedToken;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }
}
