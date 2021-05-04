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

    public RoomDeleteMessage(SourceMessage sourceMessage, JSONObject messageBody) {
        super(sourceMessage, messageBody);
    }

    @Override

    public String type() {
        return EventType.ROOM_DELETE.getType();
    }
}
