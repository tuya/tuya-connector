package com.tuya.connector.open.common.constant;

/**
 * @Classname Region
 * @Description Region Enum
 * @Date 2021/4/2
 * @Author 哲也
 */

public enum TuyaRegion {

    /**
     * China
     */
    CN("https://openapi.tuyacn.com", "pulsar+ssl://mqe.tuyacn.com:7285/"),
    /**
     * US WEST
     */
    US("https://openapi.tuyaus.com", "pulsar+ssl://mqe.tuyaus.com:7285/"),
    /**
     * US EAST
     */
    US_EAST("https://openapi-ueaz.tuyaus.com", "pulsar+ssl://mqe.tuyaus.com:7285/"),
    /**
     * European
     */
    EU("https://openapi.tuyaeu.com", "pulsar+ssl://mqe.tuyaeu.com:7285/"),
    /**
     * Europe West
     */
    EU_WEST("https://openapi-weaz.tuyaeu.com", "pulsar+ssl://mqe.tuyaeu.com:7285/"),
    /**
     * India
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

