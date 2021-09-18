package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 设备分享
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:14
 **/
public class ShareMessage extends BaseTuyaMessage {

    public static final String RECEIVER = "receiver";
    public static final String SHARE = "share";
    public static final String GROUP_ID = "groupId";
    public static final String ADD_DEV_IDS = "addDevIds";
    public static final String REMOVE_DDEV_IDS = "removedDevIds";

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public EventType getEventType() {
        return EventType.SHARE;
    }

    @Override
    public String type() {
        return getEventType().getType();
    }
}
