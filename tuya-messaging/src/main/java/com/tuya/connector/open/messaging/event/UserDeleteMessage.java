package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 用户注销
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:31
 **/
public class UserDeleteMessage extends BaseTuyaMessage {

    public static final String UID = "uid";
    public static final String SCHEMA = "schema";
    public static final String TIME = "time";

    public UserDeleteMessage(SourceMessage sourceMessage, JSONObject messageBody) {
        super(sourceMessage, messageBody);
    }

    @Override
    public String type() {
        return EventType.USER_DELETE.getType();
    }
}
