package com.tuya.open.spring.boot.sample.ability.messaging.msg;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;
import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import lombok.Getter;

@Getter
public class DeviceNameUpdate extends BaseTuyaMessage {

    private String devId;
    private String produceId;
    private String uid;
    private String name;
    private String uuid;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.devId = messageBody.getString("devId");
        this.produceId = messageBody.getString("produceId");
        this.uid = messageBody.getString("uid");
        this.name = messageBody.getString("name");
        this.uuid = messageBody.getString("uuid");
    }

    @Override
    public String type() {
        return "deviceNameUpdate";
    }
}
