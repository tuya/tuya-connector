package com.tuya.connector.open.messaging;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import com.tuya.connector.open.messaging.event.EventType;
import com.tuya.connector.open.messaging.event.UnknownMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/24 3:56 下午
 */
@Slf4j
public class MessageFactory {

    private static Map<EventType, Class<? extends BaseTuyaMessage>> messageHandler = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.tuya.connector.open.messaging.event");
        Set<Class<? extends BaseTuyaMessage>> classSet = reflections.getSubTypesOf(BaseTuyaMessage.class);
        classSet.forEach(handler -> {
            try {
                BaseTuyaMessage baseTuyaMessage = handler.newInstance();
                EventType eventType = baseTuyaMessage.getEventType();
                messageHandler.put(eventType, handler);
            } catch (Exception ignore) {
                log.error("ignore {} handler.", handler.getSimpleName());
            }
        });
    }

    @SneakyThrows
    public static BaseTuyaMessage extract(SourceMessage sourceMessage, String sk) {
        String data;
        if (sourceMessage.getEncryptPayload() != null) {
            // problem left over by history
            data = sourceMessage.getEncryptPayload();
        } else {
            data = sourceMessage.getData();
        }
        String decryptData = AESBase64Utils.decrypt(data, sk.substring(8, 24));
        JSONObject messageBody = JSON.parseObject(decryptData);
        String bizCode = null;
        if (Objects.nonNull(messageBody) && messageBody.size() > 0) {
            bizCode = messageBody.getString("bizCode");
        }
        return generate(EventType.of(sourceMessage.getProtocol(), bizCode), sourceMessage, messageBody);
    }

    @SneakyThrows
    public static BaseTuyaMessage generate(EventType eventType, SourceMessage sourceMessage, JSONObject messageBody) {
        Class<? extends BaseTuyaMessage> msgHandler = messageHandler.getOrDefault(eventType, UnknownMessage.class);
        BaseTuyaMessage tuyaMessage = msgHandler.newInstance();
        tuyaMessage.defaultBuild(sourceMessage, messageBody);
        return tuyaMessage;
    }
}
