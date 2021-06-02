[English](README.md) | [中文版](README_zh.md)

[![license: Apache 2](https://img.shields.io/badge/license-Apache%202-green)](https://github.com/tuya/tuya-connector/blob/master/LICENSE 'License')
![Version: 1.0.0](https://img.shields.io/badge/version-1.0.0-blue)


`tuya-connector`可以使得开发者在涂鸦云云对接（OpenAPI或者消息订阅）项目过程中，就如同本地开发一样，无需关注跟云端的连接和处理过程，从而帮助开发者更加聚焦在自身的业务逻辑上。
#### [演示视频](https://www.bilibili.com/video/BV1E5411g7H1?share_source=copy_web)


## 快速开始
### SpringBoot集成
#### 依赖

```xml
<dependency>
  <groupId>com.tuya</groupId>
  <artifactId>tuya-spring-boot-starter</artifactId>
  <version>#{latest.version}</version>
</dependency>

<!-- 添加 maven 仓库地址 -->
<repository>
    <id>tuya-maven</id>
    <url>https://maven-other.tuya.com/repository/maven-public/</url>
</repository>
```

#### 配置
```
# 涂鸦IoT平台云开发应用 ClientId & SecretKey
connector.ak=***
connector.sk=***
```
#### 使用
##### **调用OpenAPI**

1. 创建`Connector`接口（OpenAPI的映射类）
```java
public interface DeviceConnector {
    /**
     * query device info by device_id
     * @param deviceId
     * @return
     */
    @GET("/v1.0/devices/{device_id}")
    Device getById(@Path("device_id") String deviceId);
}
```

2. Spring应用启动类添加`@ConnectorScan`扫描路径，如果需要消息订阅，可以通过`@EnableMessaging`开启。
```java
@ConnectorScan(basePackages = "com.xxx.connectors")
@EnableMessaging
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

3. 直接注入`Connector`即可调用
```java
@Service
public class DeviceService {
    @Autowired
    private DeviceConnector device;

    public Device getById(String deviceId) {
        return device.getById(deviceId);
    }
}
```

##### **订阅消息事件**
```java
/**
 * device status data report event
 */
@EventListener
public void statusReportMessage(StatusReportMessage event) {
    log.info("### StatusReport event happened, eventInfo: {}", event);
}
```

## 原理：基于 [connector](https://github.com/tuya/connector) 框架的涂鸦云平台扩展实现
### OpenAPI扩展点实现

- ErrorProcessor

当请求OpenAPI后的响应结果返回错误码时，可以通过自定义`ErrorProcessor`的实现类来针对不同的错误码进行相应的处理，比如Token失效时，自动刷新token后重新请求；`TokenInvalidErrorProcessor`是内置的`ErrorProcessor`。
> **扩展的ErrorProcessor需要注入到Spring容器才能生效。**


- ContextManager

`connector`框架支持扩展上下文管理器，每次API请求时都会提前准备好上下文信息，`TuyaContextManager`提供了数据源连接、当前token以及多语言等信息的管理，框架支持的token自动刷新机制依赖上下文管理器。<br />

- TokenManager

`TuyaTokenManager`实现了`connector`框架的`TokenManager`接口，作为框架默认的token管理机制，token信息会缓存在本地内存。
> **如果开发者需要自行管理token，可以扩展TokenManager并注入到Spring容器。**


- HeaderProcessor

`TuyaHeaderProcessor`实现了涂鸦云OpenAPI请求时的header处理逻辑，包括需要添加的属性值以及签名。<br />


### 消息扩展点实现

- MessageDispatcher

`TuyaMessageDispatcher`实现了`connector`框架`MessageDispatcher`消息分发接口，支持顺序订阅云端消息、数据解密、构建精确的具体消息类型并通过Spring事件机制分发出去。<br />

- MessageEvent

开发者需要针对需要订阅的事件添加相应的`ApplicationListener`即可，框架内置了涂鸦所有云端消息事件类型，订阅的消息数据包括原始加密消息数据以及解密后的结构化的消息数据。

| **消息事件** | **BizCode** | **描述** |
| --- | --- | --- |
| StatusReportMessage | statusReport | 数据上报 |
| OnlineMessage | online | 设备上线 |
| OfflineMessage | offline | 设备离线 |
| NameUpdateMessage | nameUpdate | 修改设备名称 |
| DpNameUpdateMessage | dpNameUpdate | 修改设备功能点名称 |
| DeleteMessage | delete | 删除设备 |
| BindUserMessage | bindUser | 设备绑定用户 |
| UpgradeStatusMessage | upgradeStatus | 设备升级状态 |
| AutomationExternalActionMessage | automationExternalAction | 自动化外部动作 |
| SceneExecuteMessage | sceneExecute | 场景执行 |
