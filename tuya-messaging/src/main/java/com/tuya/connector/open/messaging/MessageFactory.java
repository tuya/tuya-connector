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
        return MessageFactory.generate(EventType.of(sourceMessage,messageBody), sourceMessage, messageBody);
    }

    @SneakyThrows
    public static BaseTuyaMessage generate(EventType eventType, SourceMessage sourceMessage, JSONObject messageBody) {
        Class<? extends BaseTuyaMessage> orDefault = messageHandler.getOrDefault(eventType, UnknownMessage.class);
        BaseTuyaMessage baseTuyaMessage = orDefault.newInstance();
        baseTuyaMessage.defaultBuild(sourceMessage, messageBody);
        return baseTuyaMessage;
    }
}
