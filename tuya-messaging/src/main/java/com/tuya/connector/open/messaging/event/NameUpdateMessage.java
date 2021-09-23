package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 设备改名称
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 21:46
 **/
public class NameUpdateMessage extends BaseTuyaMessage {

    public static final String DEV_ID = "devId";
    public static final String UID = "uid";
    public static final String NAME = "name";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public EventType getEventType() {
        return EventType.NAME_UPDATE;
    }
}
