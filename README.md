[English](README.md) | [中文版](README_zh.md)

[![License: Apache 2](https://img.shields.io/badge/license-Apache%202-green)](https://github.com/tuya/tuya-connector/blob/master/LICENSE 'License')
![Version: 1.0.0](https://img.shields.io/badge/version-1.0.0-blue)

`tuya-connector` helps you efficiently create cloud development projects regarding the OpenAPI or message subscription capabilities. You can put all the focus on business logic without taking care of server-side programming nor relational databases.


## Quick start
### Integrate Spring Boot
#### Dependency

```xml
<dependency>
  <groupId>com.tuya</groupId>
  <artifactId>tuya-spring-boot-starter</artifactId>
  <version>#{latest.version}</version>
</dependency>

<!-- Specify the Maven repository URL -->
<repository>
    <id>tuya-maven</id>
    <url>https://maven-other.tuya.com/repository/maven-public/</url>
</repository>
```

#### Configuration
```
# ClientId & SecretKey generated on the Tuya Cloud Development Platform
connector.ak=***
connector.sk=***
```
#### Usage
##### **Call OpenAPI operations**

1. Create the `Connector` interface, which is the mapping class of OpenAPI.
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

2. Set `@ConnectorScan` for the class of the Spring Boot application. You can set `@EnableMessaging` to enable the message subscription capability.
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

3. The `Connector` interface will be scanned and injected into the Spring container.
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

##### **Subscribe to message events**
```java
/**
 * device status data report event
 */
@EventListener
public void statusReportMessage(StatusReportMessage event) {
    log.info("### StatusReport event happened, eventInfo: {}", event);
}
```

## How it works: implement extensions based on the [connector](https://github.com/tuya/connector) framework.
### Extension points of OpenAPI

- ErrorProcessor

You can define the implementation class of `ErrorProcessor` to handle different error responses. For example, if a token expires, it can be automatically refreshed. The API operation will be tried again with the refreshed token. `TokenInvalidErrorProcessor` is the built-in implementation class of `ErrorProcessor`.
> **The extended `ErrorProcessor` must be injected into the Spring container to take effect.**


- ContextManager

The `connector` framework supports `TuyaContextManager` on which the automatic token refreshing depends. `TuyaContextManager` can prepare the context before API operations, and manage information including data source connection, tokens, and multilingual text.

- TokenManager

`TuyaTokenManager` is the default token management mechanism and implements the `TokenManager` interface in the `connector` framework. The token information is cached on the premises.
> **To manage the token on the premises, you can extend `TokenManager` and inject it into the Spring container.**


- HeaderProcessor

`TuyaHeaderProcessor` implements the processing logic of the header for OpenAPI operations, including the required attribute values and signatures.<br />


### Extension points of messages

- MessageDispatcher

`TuyaMessageDispatcher` implements `MessageDispatcher` interface for message dispatching in the `connector` framework. The dispatcher features message ordering and data decryption. It allows you to create specific message types and publish messages based on Spring's event mechanism.<br />

- MessageEvent

You can add `ApplicationListener` to listen for required events. The `connector` framework includes all the Tuya's message event types. The message data contains ciphertext messages and plaintext messages.

| **Message event** | **BizCode** | **Description** |
| --- | --- | --- |
| StatusReportMessage | statusReport | Report data to the cloud. |
| OnlineMessage | online | A device is online. |
| OfflineMessage | offline | A device is offline. |
| NameUpdateMessage | nameUpdate | Modify the device name. |
| DpNameUpdateMessage | dpNameUpdate | Modify the name of a data point. |
| DeleteMessage | delete | Remove a device. |
| BindUserMessage | bindUser | Bind the device to a user account. |
| UpgradeStatusMessage | upgradeStatus | The update status. |
| AutomationExternalActionMessage | automationExternalAction | Automate an external action. |
| SceneExecuteMessage | sceneExecute | Execute a scene. |
