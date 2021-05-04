package com.tuya.connector.open.api.header;


import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.header.HeaderProcessor;
import com.tuya.connector.open.api.token.TuyaToken;
import com.tuya.connector.open.api.token.TuyaTokenManager;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/3 7:05 下午
 */
@Slf4j
public class TuyaHeaderProcessor implements HeaderProcessor {

    private final Configuration configuration;

    public TuyaHeaderProcessor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Map<String, String> value(URL url) {
        ApiDataSource dataSource = configuration.getApiDataSource();
        TuyaTokenManager tokenManager = (TuyaTokenManager) dataSource.getTokenManager();
        String ak = dataSource.getAk();
        String accessToken = null;
        boolean withToken = isWithToken(url);
        if (withToken) {
            TuyaToken token = tokenManager.getCachedToken();
            accessToken = token.getAccessToken();
        }
        Map<String, String> map = new HashMap<>();
        long t = System.currentTimeMillis();
        map.put("client_id", ak);
        map.put("t", String.valueOf(t));
        map.put("sign_method", "HMAC-SHA256");
        map.put("lang", "zh");
        map.put("sign", sign(withToken ? ak + accessToken + t : ak + t));
        if (withToken) {
            map.put("access_token", accessToken);
        }

        return map;
    }

    @Override
    public String sign(String content) {
        ApiDataSource dataSource = configuration.getApiDataSource();
        String sk = dataSource.getSk();
        Mac sha256HMAC = null;
        try {
            sha256HMAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey secretKey = new SecretKeySpec(sk.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        try {
            sha256HMAC.init(secretKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] digest = sha256HMAC.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return new HexBinaryAdapter().marshal(digest).toUpperCase();
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    private boolean isWithToken(URL url) {
        String path = url.getPath();
        log.info("URL PATH: {}", path);

        return !path.contains("/v1.0/token");
    }

}
