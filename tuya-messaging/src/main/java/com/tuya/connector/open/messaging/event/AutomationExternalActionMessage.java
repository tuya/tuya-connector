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

    public AutomationExternalActionMessage(SourceMessage sourceMessage, JSONObject messageBody) {
        super(sourceMessage, messageBody);
        this.automationId = messageBody.getString("automationId");
    }

    public String getAutomationId() {
        return automationId;
    }

    public void setAutomationId(String automationId) {
        this.automationId = automationId;
    }

    @Override
    public String type() {
        return EventType.AUTOMATION_EXTERNAL_ACTION.getType();
    }
}
