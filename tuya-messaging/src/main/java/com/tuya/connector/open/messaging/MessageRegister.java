package com.tuya.connector.open.messaging;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.connector.open.messaging.event.BaseTuyaMessage;
import com.tuya.connector.open.messaging.event.UnknownMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/3/24 3:56 下午
 */
@Slf4j
public class MessageRegister {

    private final static String INNER_MSG_PATH = "com.tuya.connector.open.messaging.event";
    private static boolean isInit = false;
    private static final Map<String, Class<? extends BaseTuyaMessage>> MSG = new HashMap<>();

    public static void init(Set<String> pkgPaths) {
        if (isInit) {
            return;
        }
        log.info("###TUYA_PULSAR_MSG => start initial message register, pkgPaths: {}", pkgPaths);
        Set<String> paths = new HashSet<>();
        paths.add(INNER_MSG_PATH);
        if (!CollectionUtils.isEmpty(pkgPaths)) {
            paths.addAll(pkgPaths);
        }
        Reflections reflections = new Reflections(paths);
        Set<Class<? extends BaseTuyaMessage>> classSet = reflections.getSubTypesOf(BaseTuyaMessage.class);
        classSet.forEach(handler -> {
            try {
                BaseTuyaMessage baseTuyaMessage = handler.newInstance();
                MSG.put(baseTuyaMessage.type(), handler);
            } catch (Exception ignore) {
                log.error("ignore {} handler.", handler.getSimpleName());
            }
        });
        isInit = true;
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
        return generate(bizCode, sourceMessage, messageBody);
    }

    @SneakyThrows
    public static BaseTuyaMessage generate(String bizCode, SourceMessage sourceMessage, JSONObject messageBody) {
        Class<? extends BaseTuyaMessage> msgHandler = MSG.getOrDefault(bizCode, UnknownMessage.class);
        BaseTuyaMessage tuyaMessage = msgHandler.newInstance();
        tuyaMessage.defaultBuild(sourceMessage, messageBody);
        return tuyaMessage;
    }
}
