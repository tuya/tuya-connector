package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 家庭删除
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:50
 **/
public class HomeDeleteMessage extends BaseTuyaMessage {

    public static final String HOME_ID = "homeId";
    public static final String HOME_NAME = "homeName";
    public static final String TIME = "time";
    public static final String MEMBER_IDS = "memberIds";
    public static final String DEVICES = "devices";
    public static final String REASON = "reason";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

}
