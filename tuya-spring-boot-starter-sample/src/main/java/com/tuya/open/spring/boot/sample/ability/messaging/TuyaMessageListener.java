package com.tuya.open.spring.boot.sample.ability.messaging;

import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import com.tuya.connector.open.messaging.event.LinkageExecuteMessage;
import com.tuya.connector.open.messaging.event.NameUpdateMessage;
import com.tuya.connector.open.messaging.event.StatusReportMessage;
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

    @EventListener
    public void updateStatusEvent(StatusReportMessage message) {
        log.info("StatusReport event happened: {}", message);
    }

    @EventListener
    public void nameUpdateMessage(NameUpdateMessage message) {
        log.info("NameUpdate event happened: {}", message);
    }

    @EventListener
    public void linkageMessage(LinkageExecuteMessage message){
        //linkage rule run msg
        log.info("Linkage exec event: {}",message);
    }

}
