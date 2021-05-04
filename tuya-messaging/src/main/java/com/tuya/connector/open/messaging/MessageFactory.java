package com.tuya.connector.open.messaging;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.event.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.tuya.connector.open.messaging.event.EventType.STATUS_REPORT;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/24 3:56 下午
 */
@Slf4j
public class MessageFactory {
    /**
     * 消息类型唯一标识
     */
    private static final String MESSAGE_TYPE_KEY = "bizCode";

    @SneakyThrows
    public static BaseTuyaMessage extract(SourceMessage sourceMessage, String sk) {
        String data = sourceMessage.getData();
        String decryptData = AESBase64Utils.decrypt(data, sk.substring(8, 24));
        JSONObject messageBody = JSON.parseObject(decryptData);
        String type = messageBody.getString(MESSAGE_TYPE_KEY);
        if (sourceMessage.getProtocol() == 4) {
            // problem left over by history
            type = STATUS_REPORT.getType();
        }
        return MessageFactory.generate(type, sourceMessage, messageBody);
    }

    public static BaseTuyaMessage generate(String type, SourceMessage sourceMessage, JSONObject messageBody) {
        boolean isUnknown = Objects.isNull(type) || type.isEmpty();
        if (!isUnknown) {
            EventType eventType = EventType.of(type);
            if (Objects.isNull(eventType)) {
                return unknownMessage(type, sourceMessage, messageBody);
            }
            switch (eventType) {
                case STATUS_REPORT:
                    return new StatusReportMessage(sourceMessage, messageBody);
                case ONLINE:
                    return new OnlineMessage(sourceMessage, messageBody);
                case OFFLINE:
                    return new OfflineMessage(sourceMessage, messageBody);
                case NAME_UPDATE:
                    return new NameUpdateMessage(sourceMessage, messageBody);
                case DP_NAME_UPDATE:
                    return new DpNameUpdateMessage(sourceMessage, messageBody);
                case DELETE:
                    return new DeleteMessage(sourceMessage, messageBody);
                case BIND_USER:
                    return new BindUserMessage(sourceMessage, messageBody);
                case UPGRADE_STATUS:
                    return new UpgradeStatusMessage(sourceMessage, messageBody);
                case SHARE:
                    return new ShareMessage(sourceMessage, messageBody);
                case DEVICE_SIGNAL:
                    return new DeviceSignalMessage(sourceMessage, messageBody);
                case USER_REGISTER:
                    return new UserRegisterMessage(sourceMessage, messageBody);
                case USER_UPDATE:
                    return new UserUpdateMessage(sourceMessage, messageBody);
                case USER_DELETE:
                    return new UserDeleteMessage(sourceMessage, messageBody);
                case HOME_CREATE:
                    return new HomeCreateMessage(sourceMessage, messageBody);
                case HOME_UPDATE:
                    return new HomeUpdateMessage(sourceMessage, messageBody);
                case HOME_DELETE:
                    return new HomeDeleteMessage(sourceMessage, messageBody);
                case ROOM_CREATE:
                    return new RoomCreateMessage(sourceMessage, messageBody);
                case ROOM_DELETE:
                    return new RoomDeleteMessage(sourceMessage, messageBody);
                case ROOM_NAME_UPDATE:
                    return new RoomNameUodateMessage(sourceMessage, messageBody);
                case ROOM_SORT:
                    return new RoomSortMessage(sourceMessage, messageBody);
                case DEVICE_DP_COMMAND:
                    return new DeviceDpCommandMessage(sourceMessage, messageBody);
                case AUTOMATION_EXTERNAL_ACTION:
                    return new AutomationExternalActionMessage(sourceMessage, messageBody);
                case SCENE_EXECUTE:
                    return new SceneExecuteMessage(sourceMessage, messageBody);
                // extend other type message here

                default:
                    return unknownMessage(type, sourceMessage, messageBody);
            }
        } else {
            return unknownMessage(type, sourceMessage, messageBody);
        }
    }

    private static BaseTuyaMessage unknownMessage(String type, SourceMessage sourceMessage, JSONObject messageBody) {
        log.warn("no message extractor found for type[{}]", type);
        return new BaseTuyaMessage(sourceMessage, messageBody) {
            @Override
            public String type() {
                return type;
            }
        };
    }
}
