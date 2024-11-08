package com.tuya.open.spring.boot.sample.ability.messaging.msg;

import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;
import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import lombok.Getter;

@Getter
public class DeviceNameUpdate extends BaseTuyaMessage {

    private String devId;
    private String productId;
    private String uid;
    private String name;
    private String uuid;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.devId = messageBody.getJSONObject("bizData").getString("devId");
        this.productId = messageBody.getJSONObject("bizData").getString("productId");
        this.uid = messageBody.getJSONObject("bizData").getString("uid");
        this.name = messageBody.getJSONObject("bizData").getString("name");
        this.uuid = messageBody.getJSONObject("bizData").getString("uuid");
    }

    @Override
    public String type() {
        return "deviceNameUpdate";
    }
}
