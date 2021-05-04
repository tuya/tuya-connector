package com.tuya.connector.open.messaging.event;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: jinyun.zhou@tuya.com
 **/
public enum EventType {
    /**
     * 设备消息类型枚举
     */
    ONLINE("online", "设备上线"),
    OFFLINE("offline", "设备离线"),
    NAME_UPDATE("nameUpdate", "设备改名称"),
    DP_NAME_UPDATE("dpNameUpdate", "修改设备功能点名称 "),
    DELETE("delete", "设备解绑(设备删除)"),
    BIND_USER("bindUser", "设备绑定"),
    UPGRADE_STATUS("upgradeStatus", "设备升级状态"),
    SHARE("share", "设备分享"),
    DEVICE_SIGNAL("deviceSignal", "设备信号量"),
    USER_REGISTER("userRegister", "用户注册"),
    USER_UPDATE("userUpdate", "用户更新"),
    USER_DELETE("userDelete", "用户注销"),
    HOME_CREATE("homeCreate", "家庭创建"),
    HOME_UPDATE("homeUpdate", "家庭更新"),
    HOME_DELETE("homeDelete", "家庭删除"),
    ROOM_CREATE("roomCreate", "房间创建"),
    ROOM_DELETE("roomDelete", "房间删除"),
    ROOM_NAME_UPDATE("roomNameUpdate", "房间更名"),
    ROOM_SORT("roomSort", "房间排序"),
    DEVICE_DP_COMMAND("deviceDpCommand", "设备控制"),
    STATUS_REPORT("statusReport", "数据上报"),
    AUTOMATION_EXTERNAL_ACTION("automationExternalAction", "自动化外部动作"),
    SCENE_EXECUTE("sceneExecute", "场景执行")
    // extend other type message here

    ;

    private final String type;
    private String description;

    private static final Map<String, EventType> CACHE;

    static {
        CACHE = Arrays.stream(EventType.values()).collect(Collectors.toMap(
            EventType::getType, Function.identity()));
    }

    EventType(String type) {
        this.type = type;
    }

    EventType(String type, String description) {
        this(type);
        this.description = description;
    }

    public static EventType of(String type) {
        return CACHE.get(type);
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}