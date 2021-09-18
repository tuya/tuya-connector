package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.messaging.MessageEvent;
import com.tuya.connector.open.messaging.SourceMessage;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/24 3:33 下午
 */
public abstract class BaseTuyaMessage implements MessageEvent, Serializable {
    private static final long serialVersionUID = 8025704099210695269L;

    private SourceMessage sourceMessage;

    private String bizCode;
    private String devId;
    private String productKey;
    private Long ts;
    private String uuid;

    private Map<String, Object> bizData;

    public abstract EventType getEventType();

    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        this.sourceMessage = sourceMessage;
        if (Objects.nonNull(messageBody) && messageBody.size() > 0) {
            this.bizCode = messageBody.getString("bizCode");
            this.devId = messageBody.getString("devId");
            this.productKey = messageBody.getString("productKey");
            this.ts = messageBody.getLong("ts");
            this.bizData = messageBody.getJSONObject("bizData");
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SourceMessage getSourceMessage() {
        return sourceMessage;
    }

    public void setSourceMessage(SourceMessage sourceMessage) {
        this.sourceMessage = sourceMessage;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Map<String, Object> getBizData() {
        return bizData;
    }

    public void setBizData(Map<String, Object> bizData) {
        this.bizData = bizData;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
