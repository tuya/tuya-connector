package com.tuya.open.spring.boot.sample.ability.messaging;

import com.tuya.connector.open.messaging.event.NameUpdateMessage;
import com.tuya.connector.open.messaging.event.StatusReportMessage;
import com.tuya.open.spring.boot.sample.ability.messaging.msg.DeviceNameUpdate;
import com.tuya.open.spring.boot.sample.ability.messaging.msg.DeviceOfflineMessage;
import com.tuya.open.spring.boot.sample.ability.messaging.msg.DeviceOnlineMessage;
import com.tuya.open.spring.boot.sample.ability.messaging.msg.DevicePropertyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * <p> TODO
 *
 * @author qiufeng.yu@tuya.com
 * @since 2021/4/1 10:11 下午
 */
@Slf4j
@Component
public class TuyaMessageListener {

//    @EventListener
    public void updateStatusEvent(StatusReportMessage message) {
        log.info("StatusReport event happened: {}", message);
    }

//    @EventListener
    public void nameUpdateMessage(NameUpdateMessage message) {
        log.info("NameUpdate event happened: {}", message);
    }

    @EventListener
    public void deviceNameUpdateMsg(DeviceNameUpdate message) {
        log.info("deviceNameUpdateMsg event happened: {}", message);
    }

    @EventListener
    public void deviceOfflineMsg(DeviceOfflineMessage msg) {
        log.warn("pulsar msg, DeviceOfflineMessage:{}", msg);
    }

    @EventListener
    public void deviceOnlineMsg(DeviceOnlineMessage msg) {
        log.warn("pulsar msg, DeviceOnlineMessage:{}", msg);
    }

    @EventListener
    public void devicePropertyMsg(DevicePropertyMessage msg) {
        log.warn("pulsar msg, DevicePropertyMessage:{}", msg);
    }


}
