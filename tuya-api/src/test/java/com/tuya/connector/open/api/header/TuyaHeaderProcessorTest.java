package com.tuya.connector.open.api.header;

import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.model.HttpRequest;
import com.tuya.connector.open.api.header.TuyaHeaderProcessor;
import com.tuya.connector.open.api.token.TuyaToken;
import com.tuya.connector.open.api.token.TuyaTokenManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author benguan.zhou@tuya.com
 * @description
 * @date 2021/06/25
 */
public class TuyaHeaderProcessorTest {
    static TuyaHeaderProcessor processor;
    @BeforeAll
    public static void setup(){
        Configuration config = new Configuration();
        ApiDataSource apiDataSource = ApiDataSource.builder()
                .ak("testClientId")
                .sk("testSecret")
                .tokenManager(new TuyaTokenManager(config) {
                    @Override
                    public TuyaToken getToken() {
                        return TuyaToken.builder()
                                .access_token("testToken").build();
                    }

                    @Override
                    public TuyaToken refreshToken() {
                        return TuyaToken.builder()
                                .access_token("testToken").build();
                    }

                    @Override
                    public Configuration getConfiguration() {
                        return null;
                    }
                })
                .build();
        config.setApiDataSource(apiDataSource);
        processor = new TuyaHeaderProcessor(config);
    }
    @Test
    public void testSign() throws MalformedURLException {
        Map<String,String> headers = processor.value(HttpRequest.builder()
                .url(new URL("https://openapi.tuyacn.com/v1.0/home"))
                .headers(zip(Arrays.asList("client_id","t","access_token","nonce")
                        ,Arrays.asList("testClientId","1624528478091","testToken","testNonce").stream()
                                .map(it->Arrays.asList(it)).collect(Collectors.toList())))
                .body("{\"name\":\"my_home\"}".getBytes(StandardCharsets.UTF_8))
                .httpMethod("post")
                .build());
        System.out.println(headers);
        Assertions.assertEquals("12BF3EFE99C23967319B1AABD398E7458F3B28E70E67456F2014015ABE177778",headers.get("sign"));
    }

    private <K,V> Map<K,V> zip(List<K> ks, List<V> vs){
        Map<K,V> map = new HashMap<>();
        Iterator<K> iterK = ks.iterator();
        Iterator<V> iterV = vs.iterator();
        while(iterK.hasNext()){
            if(iterV.hasNext()){
                map.put(iterK.next(),iterV.next());
            }else{
                map.put(iterK.next(),null);
            }
        }
        return map;
    }
}
