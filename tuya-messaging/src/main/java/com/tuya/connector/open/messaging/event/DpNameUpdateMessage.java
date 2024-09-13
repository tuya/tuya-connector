package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 修改设备功能点名称
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 21:46
 **/
public class DpNameUpdateMessage extends BaseTuyaMessage {

    public static final String DEV_ID = "devId";
    public static final String DP_ID = "dpId";
    public static final String NAME = "name";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public String type() {
        return "dpNameUpdate";
    }
}
