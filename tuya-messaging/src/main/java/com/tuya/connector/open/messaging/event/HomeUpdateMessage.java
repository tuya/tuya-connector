package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 家庭更新
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:46
 **/
public class HomeUpdateMessage extends BaseTuyaMessage {

    public static final String HOME_ID = "homeId";
    public static final String HOME_NAME = "homeName";
    public static final String UID = "uid";
    public static final String TIME = "time";
    public static final String LON = "lon";
    public static final String LAT = "lat";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public String type() {
        return "homeUpdate";
    }
}
