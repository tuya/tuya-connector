package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * @description: 自动化外部动作
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-04-01 15:50
 **/
public class AutomationExternalActionMessage extends BaseTuyaMessage {

    public static final String CODE = "code";
    public static final String VALUE = "value";
    public static final String OPERATOR = "operator";

    private String automationId;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.automationId = messageBody.getString("automationId");
    }

    @Override
    public EventType getEventType() {
        return EventType.AUTOMATION_EXTERNAL_ACTION;
    }

    public String getAutomationId() {
        return automationId;
    }

    public void setAutomationId(String automationId) {
        this.automationId = automationId;
    }

    @Override
    public String type() {
        return getEventType().getType();
    }
}
