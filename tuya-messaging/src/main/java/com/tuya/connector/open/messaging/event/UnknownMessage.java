package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

/**
 * Description: TODO
 *
 * @author Chyern
 * @since 2021/9/17
 */
public class UnknownMessage extends BaseTuyaMessage {

    private JSONObject messageBody;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.messageBody = messageBody;
    }

    @Override
    public String type() {
        return "unknown";
    }
}
