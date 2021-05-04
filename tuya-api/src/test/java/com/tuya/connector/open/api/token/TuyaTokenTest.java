package com.tuya.connector.open.api.token;

import com.tuya.connector.api.config.ApiDataSource;
import com.tuya.connector.api.config.Configuration;
import com.tuya.connector.api.config.Logging;
import com.tuya.connector.api.core.ConnectorFactory;
import com.tuya.connector.api.core.DefaultConnectorFactory;
import com.tuya.connector.api.header.HeaderProcessor;
import com.tuya.connector.open.api.SecurityInfo;
import com.tuya.connector.open.api.context.TuyaContextManager;
import com.tuya.connector.open.api.header.TuyaHeaderProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/5 4:26 下午
 */
@Slf4j
public class TuyaTokenTest {

    static TokenConnector connector;

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
        connector = connectorFactory.loadConnector(TokenConnector.class);
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    void getTokenTest(int grantType) {
        TuyaToken tuyaToken = connector.getToken(grantType);
        log.info("TOKEN: {}", tuyaToken.toString());
        assertTrue(tuyaToken.getExpire_time() > 0 && tuyaToken.getUid().equals("bay1606653099465k7rn"));
    }

}
