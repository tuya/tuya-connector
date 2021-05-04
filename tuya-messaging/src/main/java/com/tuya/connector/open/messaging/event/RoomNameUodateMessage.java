package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 房间更名
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 23:05
 **/
public class RoomNameUodateMessage extends BaseTuyaMessage {

    public static final String HOME_ID = "homeId";
    public static final String ROOM_NAME = "roomName";
    public static final String UID = "uid";
    public static final String TIME = "time";

    public RoomNameUodateMessage(SourceMessage sourceMessage, JSONObject messageBody) {
        super(sourceMessage, messageBody);
    }

    @Override
    public String type() {
        return EventType.ROOM_NAME_UPDATE.getType();
    }
}
