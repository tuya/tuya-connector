package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 房间排序
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 23:07
 **/
public class RoomSortMessage extends BaseTuyaMessage {

    public static final String HOME_ID = "homeId";
    public static final String ROOMS = "rooms";
    public static final String UID = "uid";
    public static final String TIME = "time";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }


}
