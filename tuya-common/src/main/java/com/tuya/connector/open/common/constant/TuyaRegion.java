package com.tuya.connector.open.common.constant;

/**
 * @Classname Region
 * @Description 地区枚举类
 * @Date 2021/4/2
 * @Author 哲也（张梓濠 zheye.zhang@tuya.com）
 */

public enum TuyaRegion {

    /**
     * 中国大陆
     */
    CN("https://openapi.tuyacn.com", "pulsar+ssl://mqe.tuyacn.com:7285/"),
    /**
     * 美洲区
     */
    US("https://openapi.tuyaus.com", "pulsar+ssl://mqe.tuyaus.com:7285/"),
    /**
     * 欧洲区
     */
    EU("https://openapi.tuyaeu.com", "pulsar+ssl://mqe.tuyaeu.com:7285/"),
    /**
     * 印度区
     */
    IN("https://openapi.tuyain.com", "pulsar+ssl://mqe.tuyain.com:7285/");

    private final String apiUrl;

    private final String msgUrl;

    TuyaRegion(String apiUrl, String msgUrl) {
        this.apiUrl = apiUrl;
        this.msgUrl = msgUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getMsgUrl() {
        return msgUrl;
    }
}

