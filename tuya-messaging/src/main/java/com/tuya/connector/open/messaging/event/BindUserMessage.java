package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 设备绑定
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:02
 **/
public class BindUserMessage extends BaseTuyaMessage {

    public static final String DEV_ID = "devId";
    public static final String UID = "uid";
    public static final String OWNER_ID = "ownerId";
    public static final String UUID = "uuid";
    public static final String TOKEN = "token";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }
}
