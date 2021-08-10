package com.tuya.connector.open.api.errorprocessor;

import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.config.Logging;
import com.tuya.connector.api.config.Timeout;
import com.tuya.connector.api.core.ConnectorFactory;
import com.tuya.connector.api.core.DefaultConnectorFactory;
import com.tuya.connector.api.header.HeaderProcessor;
import com.tuya.connector.open.api.SecurityInfo;
import com.tuya.connector.open.api.connectors.device.DeviceConnector;
import com.tuya.connector.open.api.context.TuyaContextManager;
import com.tuya.connector.open.api.header.TuyaHeaderProcessor;
import com.tuya.connector.open.api.model.Device;
import com.tuya.connector.open.api.token.TuyaToken;
import com.tuya.connector.open.api.token.TuyaTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/6 1:41 下午
 */
@Slf4j
public class TokenValidErrorProcessorTest {
    static DeviceConnector deviceConnector;
    String DEVICE_ID = "*********";

    @BeforeAll
    static void init() {
        Configuration config = new Configuration();
        ApiDataSource dataSource = ApiDataSource.DEFAULT_BUILDER.build();
        dataSource.setTimeout(Timeout.builder().callTimeout(60).connectTimeout(60).readTimeout(60).writeTimeout(60).build());
        dataSource.setBaseUrl("https://openapi.tuyacn.com");
        dataSource.setAk(SecurityInfo.AK);
        dataSource.setSk(SecurityInfo.SK);
        dataSource.setLoggingStrategy(Logging.Strategy.BASIC);
        dataSource.setAutoSetHeader(true);
        HeaderProcessor headerProcessor = new TuyaHeaderProcessor(config);
        dataSource.setHeaderProcessor(headerProcessor);

        dataSource.setContextManager(new TuyaContextManager(config));

        dataSource.setAutoRefreshToken(true);
        TuyaTokenManager tuyaTokenManager = new TuyaTokenManager(config);
        tuyaTokenManager.setCachedToken(
                TuyaToken.builder()
                        .access_token("*********")
                        .refresh_token("*********")
                        .build()
        );
        dataSource.setTokenManager(tuyaTokenManager);

        dataSource.getErrorProcessorRegister().register(new TokenInvalidErrorProcessor());
        dataSource.setAutoRefreshToken(true);

        config.setApiDataSource(dataSource);
        config.init();

        ConnectorFactory connectorFactory = new DefaultConnectorFactory(config);
        deviceConnector = connectorFactory.loadConnector(DeviceConnector.class);
    }

    /**
     * 可以手动debug在RetrofitDelegate.execute()方法内设置 response code 为token expired(1010)
     */
    @Test
    void autoRefreshToken() {
        Device device = deviceConnector.getById(DEVICE_ID);
        log.error("DEVICE: {}", device);
        Assertions.assertEquals(device.getUuid(), DEVICE_ID);
    }


}
