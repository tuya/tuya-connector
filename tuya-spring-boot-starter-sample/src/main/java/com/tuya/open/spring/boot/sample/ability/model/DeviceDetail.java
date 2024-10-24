package com.tuya.open.spring.boot.sample.ability.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DeviceDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -1389103173186462562L;

    private String id; // 设备 ID
    private Long activeTime; // 设备的激活时间（时间戳秒数）
    private String category; // 设备的产品品类
    private Long createTime; // 设备的初次配网时间（时间戳秒数）
    private Long updateTime; // 设备的更新时间（时间戳秒数）
    private String customName; // 设备的自定义名称
    private String icon; // 设备的图标
    private String ip; // 设备的 IP 地址
    private Boolean isOnline; // 设备的在线状态
    private Boolean online; // 设备的在线状态
    private String lat; // 设备的纬度
    private String localKey; // 设备的局域网加密后的唯一密钥
    private String lon; // 设备的经度
    private String name; // 设备的名称
    private String productId; // 设备的产品 ID
    private String productName; // 设备的产品名称
    private Boolean sub; // 是否为子设备
    private String timeZone; // 设备的时区
    private String uuid; // 设备的 UUID
    private String bindSpaceId; // 空间 Id
    private String gatewayId; // 网关 ID
    private String nodeId; // 设备的节点 ID
    private String assetId; // 设备的资产 ID
    private String model; // 设备的型号
    private String sn; // 设备的序列号

}
