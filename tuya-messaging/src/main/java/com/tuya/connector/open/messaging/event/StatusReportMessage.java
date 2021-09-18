package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

import java.util.List;
import java.util.Objects;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/24 3:34 下午
 */
public class StatusReportMessage extends BaseTuyaMessage {
    private String dataId;
    private List<Item> status;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.dataId = messageBody.getString("dataId");
        JSONArray statusArray = messageBody.getJSONArray("status");
        if (Objects.nonNull(statusArray)) {
            this.status = statusArray.toJavaList(Item.class);
        }
    }

    @Override
    public EventType getEventType() {
        return EventType.STATUS_REPORT;
    }

    public static class Item {
        private String code;
        private Long t;
        private Object value;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Long getT() {
            return t;
        }

        public void setT(Long t) {
            this.t = t;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    @Override
    public String type() {
        return getEventType().getType();
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public List<Item> getStatus() {
        return status;
    }

    public void setStatus(List<Item> status) {
        this.status = status;
    }
}
