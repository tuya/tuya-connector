package com.tuya.open.spring.boot.sample.ability.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Device {
    String id; // 设备 ID
    String gatewayId; // 网关 ID, 非网关子设备时为空
    String nodeId; // 节点 ID, 非网关子设备时为空
    String uuid; // 设备 UUID
    String category; // 产品品类
    String categoryName; // 产品品类名称
    String name; // 设备名称
    String productId; // 产品 ID
    String productName; // 产品名称
    String localKey; // 密钥
    Boolean sub; // 是否为子设备
    String assetId; // 资产 ID
    String ownerId; // 家庭 ID
    String ip; // 设备 IP
    String lon; // 经度
    String lat; // 纬度
    String model; // 产品型号
    String timeZone; // 时区
    Long activeTime; // 激活时间
    Long updateTime; // 更新时间
    Long createTime; // 初次配网时间
    Boolean online; // 在线状态
    String icon; // 设备图标
}
