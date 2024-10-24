package com.tuya.open.spring.boot.sample.ability.messaging.msg;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;
import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter @Setter
public class DevicePropertyMessage extends BaseTuyaMessage {

    private String dataId;
    private String devId;
    private String productId;
    private List<PropertyItem> properties;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.dataId = messageBody.getJSONObject("bizData").getString("dataId");
        this.devId = messageBody.getJSONObject("bizData").getString("devId");
        this.productId = messageBody.getJSONObject("bizData").getString("productId");
        JSONArray jsonArray = messageBody.getJSONObject("bizData").getJSONArray("properties");
        if (jsonArray != null) {
            properties = jsonArray.stream().map(
                item -> {
                    JSONObject jsonObject = (JSONObject) item;
                    PropertyItem propertyItem = new PropertyItem();
                    propertyItem.setCode(jsonObject.getString("code"));
                    propertyItem.setValue(jsonObject.get("value"));
                    propertyItem.setDpId(jsonObject.getString("dpId"));
                    propertyItem.setTime(jsonObject.getLong("time"));
                    return propertyItem;
                }
            ).toList();
        }
    }

    @Override
    public String type() {
        return "devicePropertyMessage";
    }

    @Getter @Setter
    class PropertyItem implements Serializable {

        @Serial
        private static final long serialVersionUID = -5316969945618066530L;

        private String code;
        private Object value;
        private String dpId;
        private Long time;
    }
}
