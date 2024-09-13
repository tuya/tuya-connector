package com.tuya.connector.open.messaging.event;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.SourceMessage;

import java.util.List;
import java.util.Objects;

/**
 * @description: 场景执行事件
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-04-01 15:26
 **/
public class SceneExecuteMessage extends BaseTuyaMessage {
    /**
     * private field
     */
    private Long gid;
    private String uid;

    /**
     * bizData
     */
    public static String NAME = "name";
    public static String ID = "id";
    private List<Item> actions;

    @Override
    public void defaultBuild(SourceMessage sourceMessage, JSONObject messageBody) {
        super.defaultBuild(sourceMessage, messageBody);
        this.gid = messageBody.getLong("gid");
        this.uid = messageBody.getString("uid");

        JSONArray statusArray = messageBody.getJSONArray("actions");
        if (Objects.nonNull(statusArray)) {
            this.actions = statusArray.toJavaList(Item.class);
        }
    }

    @Override
    public String type() {
        return "sceneExecute";
    }

    public List<Item> getActions() {
        return actions;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setActions(List<Item> actions) {
        this.actions = actions;
    }

    public static class Item {
        private String entityId;
        private Integer execStatus;
        private Long executeTime;
        private String id;

        public String getEntityId() {
            return entityId;
        }

        public void setEntityId(String entityId) {
            this.entityId = entityId;
        }

        public Integer getExecStatus() {
            return execStatus;
        }

        public void setExecStatus(Integer execStatus) {
            this.execStatus = execStatus;
        }

        public Long getExecuteTime() {
            return executeTime;
        }

        public void setExecuteTime(Long executeTime) {
            this.executeTime = executeTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
