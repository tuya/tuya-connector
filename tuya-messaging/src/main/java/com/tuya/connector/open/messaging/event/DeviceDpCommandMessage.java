package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

import java.util.List;
import java.util.Objects;

/**
 * @description: 设备控制
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 23:09
 **/
public class DeviceDpCommandMessage extends BaseTuyaMessage {

    private List<Item> command;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        JSONArray object = messageBody.getJSONArray("command");
        if (Objects.nonNull(object)) {
            this.command = object.toJavaList(Item.class);
        }
    }

    @Override
    public String type() {
        return "deviceDpCommand";
    }

    public List<Item> getCommand() {
        return command;
    }

    public void setCommand(List<Item> command) {
        this.command = command;
    }

    public static class Item {
        private Long dpId;
        private Boolean value;

        public Long getDpId() {
            return dpId;
        }

        public void setDpId(Long dpId) {
            this.dpId = dpId;
        }

        public Boolean getValue() {
            return value;
        }

        public void setValue(Boolean value) {
            this.value = value;
        }
    }
}
