package com.tuya.connector.open.messaging.autoconfig;

import com.tuya.connector.messaging.MessageDataSource;
import lombok.Getter;
import lombok.Setter;
import org.apache.pulsar.client.api.SubscriptionType;

import java.util.HashMap;
import java.util.Map;

public class TuyaMessageDataSource extends MessageDataSource {

    @Getter @Setter
    private String subNameSuffix;

    // pulsar client loadConf
    private Map<String, Object> clientLoadConfMap = new HashMap<>();

    // pulsar consumer loadConf
    private Map<String, Object> consumerLoadConfMap = new HashMap<>();


    public TuyaMessageDataSource(String url, String ak, String sk) {
        super(url, ak, sk);
    }

    public TuyaMessageDataSource(String url, String ak, String sk, String subNameSuffix) {
        super(url, ak, sk);
        this.subNameSuffix = subNameSuffix;
    }

    public Map<String, Object> clientLoadConf() {
        // Whether the Pulsar client accepts untrusted TLS certificate from broker
        clientLoadConfMap.put("tlsAllowInsecureConnection", true);

        return clientLoadConfMap;
    }

    public Map<String, Object> consumerLoadConf() {
        // Multiple consumers can attach to the same subscription, yet only the first consumer is active, and others are
        // standby. When the active consumer is disconnected, messages will be dispatched to one of standby consumers,
        // and the standby consumer then becomes active consumer.
        consumerLoadConfMap.put("subscriptionType", SubscriptionType.Failover);

        return consumerLoadConfMap;
    }
}
