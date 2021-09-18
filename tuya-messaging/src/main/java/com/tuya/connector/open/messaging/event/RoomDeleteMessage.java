package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 房间删除
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 23:01
 **/
public class RoomDeleteMessage extends BaseTuyaMessage {

    public static final String HOME_ID = "homeId";
    public static final String TIME = "time";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public EventType getEventType() {
        return EventType.ROOM_DELETE;
    }

    @Override

    public String type() {
        return getEventType().getType();
    }
}
