package com.tuya.connector.open.messaging.event;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: jinyun.zhou@tuya.com
 **/
public enum EventType {
    STATUS_REPORT(4, "statusReport", "数据上报"),

    ONLINE(20, "online", "设备上线"),
    OFFLINE(20, "offline", "设备离线"),
    NAME_UPDATE(20, "nameUpdate", "设备改名称"),
    DP_NAME_UPDATE(20, "dpNameUpdate", "修改设备功能点名称 "),
    DELETE(20, "delete", "设备解绑(设备删除)"),
    BIND_USER(20, "bindUser", "设备绑定"),
    UPGRADE_STATUS(20, "upgradeStatus", "设备升级状态"),
    SHARE(20, "share", "设备分享"),
    DEVICE_SIGNAL(20, "deviceSignal", "设备信号量"),
    DEVICE_DP_COMMAND(20, "deviceDpCommand", "设备控制"),

    USER_REGISTER(21, "userRegister", "用户注册"),
    USER_UPDATE(21, "userUpdate", "用户更新"),
    USER_DELETE(21, "userDelete", "用户注销"),

    AUTOMATION_EXTERNAL_ACTION(22, "automationExternalAction", "自动化外部动作"),

    SCENE_EXECUTE(25, "sceneExecute", "场景执行"),

    ROOM_NAME_UPDATE(34, "roomNameUpdate", "房间更名"),
    ROOM_SORT(34, "roomSort", "房间排序"),

    HOME_CREATE(35, "homeCreate", "家庭创建"),
    HOME_UPDATE(35, "homeUpdate", "家庭更新"),
    HOME_DELETE(35, "homeDelete", "家庭删除"),
    ROOM_CREATE(35, "roomCreate", "房间创建"),
    ROOM_DELETE(35, "roomDelete", "房间删除"),

    // extend other type message here
    UNKNOWN_MESSAGE(null, "unknownMessage", "未定义消息"),
    ;

    private Integer protocol;
    private final String type;
    private String description;

    private static final Map<String, EventType> CACHE;

    static {
        CACHE = Arrays.stream(EventType.values()).collect(Collectors.toMap(
            item -> (item.getProtocol() + item.getType()), Function.identity()));
    }

    EventType(Integer protocol, String type, String description) {
        this.type = type;
        this.protocol = protocol;
        this.description = description;
    }

    public static EventType of(Integer protocol, String bizCode) {
        //数据上报特有逻辑
        if (protocol == 4 && bizCode == null) {
            bizCode = STATUS_REPORT.type;
        }
        return CACHE.getOrDefault(protocol + bizCode, UNKNOWN_MESSAGE);
    }

    public Integer getProtocol() {
        return protocol;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}