package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

import java.util.List;
import java.util.Objects;

/**
 * @description: 设备信号量
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-03-24 22:20
 **/
public class DeviceSignalMessage extends BaseTuyaMessage {

    private List<Item> reportData;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        JSONArray statusArray = messageBody.getJSONArray("reportData");
        if (Objects.nonNull(statusArray)) {
            this.reportData = statusArray.toJavaList(Item.class);
        }
    }

    public List<Item> getReportData() {
        return reportData;
    }

    public void setReportData(List<Item> reportData) {
        this.reportData = reportData;
    }

    public static class Item {
        private Long memory;
        private Integer rssi;
        private Long t;

        public Long getMemory() {
            return memory;
        }

        public void setMemory(Long memory) {
            this.memory = memory;
        }

        public Integer getRssi() {
            return rssi;
        }

        public void setRssi(Integer rssi) {
            this.rssi = rssi;
        }

        public Long getT() {
            return t;
        }

        public void setT(Long t) {
            this.t = t;
        }
    }
}
