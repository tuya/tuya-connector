package com.tuya.connector.open.api.connectors.device;

import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.config.Logging;
import com.tuya.connector.api.core.ConnectorFactory;
import com.tuya.connector.api.core.DefaultConnectorFactory;
import com.tuya.connector.api.header.HeaderProcessor;
import com.tuya.connector.open.api.SecurityInfo;
import com.tuya.connector.open.api.context.TuyaContextManager;
import com.tuya.connector.open.api.header.TuyaHeaderProcessor;
import com.tuya.connector.open.api.model.Device;
import com.tuya.connector.open.api.token.TuyaTokenManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/6 12:43 下午
 */
@Slf4j
public class ConnectorTest {

    static DeviceConnector deviceConnector;
    String DEVICE_ID = "87707085bcddc23a5fa3";

    @BeforeAll
    static void init() {
        Configuration config = new Configuration();
        ApiDataSource dataSource = ApiDataSource.DEFAULT_BUILDER.build();
        dataSource.setBaseUrl("https://openapi.tuyacn.com");
        dataSource.setAk(SecurityInfo.AK);
        dataSource.setSk(SecurityInfo.SK);
        dataSource.setLoggingStrategy(Logging.Strategy.BASIC);
        dataSource.setAutoSetHeader(true);
        HeaderProcessor headerProcessor = new TuyaHeaderProcessor(config);
        dataSource.setHeaderProcessor(headerProcessor);

        dataSource.setContextManager(new TuyaContextManager(config));

        dataSource.setAutoRefreshToken(true);
        dataSource.setTokenManager(new TuyaTokenManager(config));

        config.setApiDataSource(dataSource);
        config.init();

        ConnectorFactory connectorFactory = new DefaultConnectorFactory(config);
        deviceConnector = connectorFactory.loadConnector(DeviceConnector.class);
    }

    @Test
    void getById() {
        Device device = deviceConnector.getById(DEVICE_ID);
        log.error("DEVICE: {}", device);
        Assertions.assertEquals(device.getUuid(), DEVICE_ID);
    }

}
