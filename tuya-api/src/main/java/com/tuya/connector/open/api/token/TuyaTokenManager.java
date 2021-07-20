package com.tuya.connector.open.api.token;

import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.core.DefaultConnectorFactory;
import com.tuya.connector.api.token.TokenManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author benguan.zhou
 * @since 2021/7/19
 */
@Slf4j
public class TuyaTokenManager implements TokenManager<TuyaToken> {

    private final static int TOKEN_GRANT_TYPE = 1;
    private final TokenConnector connector;
    private final Map<String, TuyaToken> cachedTokenMap = new ConcurrentHashMap<>();
    private final Configuration configuration;
    private final Map<String, Lock> tokenRefreshLocks = new ConcurrentHashMap<>();
    /** 1 seconds
     * */
    private final long WAIT_MILLIS = 1000;
    /** 3 minutes
     * */
    private static final long REFRESH_BEFORE_EXPIRED_SECONDS = 60*3;

    public TuyaTokenManager(Configuration configuration) {
        this.configuration = configuration;
        connector = new DefaultConnectorFactory(configuration).loadConnector(TokenConnector.class);
    }

    @Override
    public TuyaToken getCachedToken() {
        return refreshToken();
    }

    @Override
    @SneakyThrows
    public TuyaToken getToken() {
        return refreshToken();
    }

    @Override
    @SneakyThrows
    public TuyaToken refreshToken() {
        refreshTokenIfNeed();
        String ak = configuration.getApiDataSource().getAk();
        return cachedTokenMap.get(ak);
    }

    /**一个方法的结果，要么通过返回值体现，要么通过副作用体现*/
    @SneakyThrows
    private void refreshTokenIfNeed(){
        String ak = configuration.getApiDataSource().getAk();
        Objects.requireNonNull(ak,"ak can't be null");
        TuyaToken cachedToken = cachedTokenMap.get(ak);
        if(cachedToken!=null && !isTokenExpired(cachedToken)){
            return;
        }
        //use a new lock for every ak. create if lock isn't exists.
        Lock lock = tokenRefreshLocks.get(ak);
        if(lock == null){
            synchronized (ak){
                lock = tokenRefreshLocks.get(ak);
                if(lock == null){
                    lock = new ReentrantLock();
                    tokenRefreshLocks.put(ak,lock);
                }
            }
        }

        //get token from openapi, then put it into cache.
        if(lock.tryLock(WAIT_MILLIS, TimeUnit.MILLISECONDS)){
            try{
                TuyaToken token = connector.getToken(TOKEN_GRANT_TYPE);
                Objects.requireNonNull(token,"Refreshed token required not null.");
                token.setExpire_at(System.currentTimeMillis()/1000+token.getExpire_time());
                cachedTokenMap.put(ak,token);
                return;
            }finally {
                lock.unlock();
            }
        }else{
            throw new RuntimeException("wait lock timeout!");
        }
    }

    /**
     * not actually expired
     * */
    private static boolean isTokenExpired(TuyaToken token){
        return token.getExpire_at() - REFRESH_BEFORE_EXPIRED_SECONDS < System.currentTimeMillis()/1000;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * warn: just for test, do not use this method!!!
     * */
    public void setCachedToken(TuyaToken token) {
        String ak = configuration.getApiDataSource().getAk();
        Objects.requireNonNull(token,"Refreshed token required not null.");
        token.setExpire_at(System.currentTimeMillis()+token.getExpire_time());
        cachedTokenMap.put(ak,token);
    }
}
