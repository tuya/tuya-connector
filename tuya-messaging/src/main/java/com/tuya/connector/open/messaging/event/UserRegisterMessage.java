package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 用户注册
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:30
 **/
public class UserRegisterMessage extends BaseTuyaMessage {

    public static final String UID = "uid";
    public static final String SCHEMA = "schema";
    public static final String TIME = "time";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public String type() {
        return "userRegister";
    }
}
