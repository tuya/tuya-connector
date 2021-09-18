package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 设备升级状态
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:09
 **/
public class UpgradeStatusMessage extends BaseTuyaMessage {

    public static final String DEV_ID = "devId";
    public static final String UPGRADE_STATUS = "upgradeStatus";
    public static final String DESCRIPTION = "description";
    public static final String MODULE_TYPE = "moduleType";


    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
    }

    @Override
    public EventType getEventType() {
        return EventType.UPGRADE_STATUS;
    }

    @Override
    public String type() {
        return getEventType().getType();
    }
}
