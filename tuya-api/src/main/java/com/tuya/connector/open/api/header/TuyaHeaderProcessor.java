package com.tuya.connector.open.api.header;


import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.header.HeaderProcessor;
import com.tuya.connector.api.model.HttpRequest;
import com.tuya.connector.open.api.token.TuyaToken;
import com.tuya.connector.open.api.token.TuyaTokenManager;
import com.tuya.connector.open.common.util.Sha256Util;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/3 7:05 下午
 */
@Slf4j
public class TuyaHeaderProcessor implements HeaderProcessor {
    private static final String EMPTY_HASH = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";
    private static final String SING_HEADER_NAME = "Signature-Headers";
    private static final String NONCE_HEADER_NAME = "nonce";

    private final Configuration configuration;

    public TuyaHeaderProcessor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Map<String, String> value(HttpRequest request) {
        ApiDataSource dataSource = configuration.getApiDataSource();
        TuyaTokenManager tokenManager = (TuyaTokenManager) dataSource.getTokenManager();
        String ak = dataSource.getAk();
        String accessToken = null;
        boolean withToken = isWithToken(request.getUrl());
        if (withToken) {
            TuyaToken token = tokenManager.getCachedToken();
            accessToken = token.getAccessToken();
        }

        Map<String, String> flattenHeaders = flattenHeaders(request.getHeaders());
        Map<String, String> map = new HashMap<>();
        String t = flattenHeaders.get("t");
        if(!StringUtils.hasText(t)){
            t = System.currentTimeMillis()+"";
        }
        map.put("client_id", ak);
        map.put("t", t);
        map.put("sign_method", "HMAC-SHA256");
        map.put("lang", "zh");
        String signHeaderName = flattenHeaders.getOrDefault(SING_HEADER_NAME,"");
        map.put(SING_HEADER_NAME, signHeaderName);
        String nonce = flattenHeaders.getOrDefault(NONCE_HEADER_NAME, "");
        map.put(NONCE_HEADER_NAME, nonce);
        String str;
        if (withToken) {
            str = ak + accessToken + t + nonce + stringToSign(request, flattenHeaders);
        } else {
            str = ak + t + nonce + stringToSign(request, flattenHeaders);
        }
        map.put("sign", sign(str));
        if (withToken) {
            map.put("access_token", accessToken);
        }
        return map;
    }

    private Map<String, String> flattenHeaders(Map<String, List<String>> headers) {
        Map<String, String> newHeaders = new HashMap<>();
        headers.forEach((name, values) -> {
            if (values == null || values.isEmpty()) {
                newHeaders.put(name, "");
            } else {
                newHeaders.put(name, values.get(0));
            }
        });
        return newHeaders;
    }

    @SneakyThrows
    private String stringToSign(HttpRequest request, Map<String, String> headers) {
        List<String> lines = new ArrayList<>(16);
        lines.add(request.getHttpMethod().toUpperCase());
        String bodyHash = EMPTY_HASH;
        if (request.getBody() != null && request.getBody().length > 0) {
            bodyHash = Sha256Util.encryption(request.getBody());
        }
        String signHeaders = headers.get(SING_HEADER_NAME);
        String headerLine = "";
        if (signHeaders != null) {
            String[] sighHeaderNames = signHeaders.split("\\s*:\\s*");
            headerLine = Arrays.stream(sighHeaderNames).map(it -> it.trim())
                    .filter(it -> it.length() > 0)
                    .map(it->it+":"+headers.get(it))
                    .collect(Collectors.joining("\n"));
        }
        lines.add(bodyHash);
        lines.add(headerLine);
        URL url = request.getUrl();
        String paramSortedPath = getPathAndSortParam(url);
        lines.add(paramSortedPath);
        return String.join("\n", lines);
    }

    @SneakyThrows
    private String getPathAndSortParam(URL url) {
        String query = url.getQuery();
        String path = url.getPath();
        if(!StringUtils.hasText(query)){
            return path;
        }
        Map<String,String> kvMap = new TreeMap<>();
        String[] kvs = query.split("\\&");
        for(String kv: kvs){
            String[] kvArr = kv.split("=");
            if(kvArr.length>1){
                kvMap.put(kvArr[0], decode(kvArr[1]));
            }else{
                kvMap.put(kvArr[0],"");
            }
        }
        return path + "?" + kvMap.entrySet().stream().map(it->it.getKey()+"="+ encode(it.getValue()))
                .collect(Collectors.joining("&"));
    }

    @SneakyThrows
    private String decode(String data) {
        return URLDecoder.decode(data,"utf-8");
    }

    @SneakyThrows
    private String encode(String data){
        return data;
        //return URLEncoder.encode(data,"utf-8");
    }

    @Override
    public String sign(String content) {
        ApiDataSource dataSource = configuration.getApiDataSource();
        String sk = dataSource.getSk();
        return Sha256Util.sign(content,sk);
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
