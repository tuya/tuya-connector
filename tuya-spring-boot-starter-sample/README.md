本文介绍如何基于涂鸦云开发开放的能力和 SDK，开发一个场景联动的程序，实现设备联动。

## 前提条件

- [已完成云项目创建](https://developer.tuya.com/cn/docs/iot/config-cloud-project?id=Kat2eytbffx3v)
- [已添加设备](https://developer.tuya.com/cn/docs/iot/link-devices?id=Kat2fh417hhz2)
- 已配好开发环境，特别注意，SDK代码分支切换为xxxx，参考[设备控制第一步、第二步](https://developer.tuya.com/cn/docs/iot/device-control-best-practice-java?id=Kat2uf0cwn9k9)

### 第一步：打开SDK工程
按照上述步骤打开工程，查看测试代码，接口定义在 `tuya-ability` 模块，测试代码 `tuya-spring-boot-starter-sample` 模块，如下图所示。
- 接口定义代码参考 `LinkageConnector.java ` 
- 业务应用代码参考 `LinkageController.java ` 
- 消息推送代码参考 `TuyaMessageListener.java` 其中`LinkageExecuteMessage.java` 为场景联动pulsar消息

     ![image.png](https://airtake-public-data-1254153901.cos.ap-shanghai.myqcloud.com/content-platform/hestia/1667360200fcdcdb47303.png)

### 第二步：开始测试
接下来，利用`tuya-spring-boot-starter-sample` 工程中的测试接口`LinkageController.java ` ，我们创建一个真实的场景联动，**开关A打开触发开关B打开**
首先，确保你已经有2个支持开关的设备。

- 查询设备支持联动的指令和状态，确保设备支持开关
  调用接口 `GET:localhost:8080/linkage/device/xxxdeviceId/specifications`
  **返回结果：**
     ```json
    {
	"functions": [{
			"code": "switch_1",
			"name": "开关1",
			"type": "Boolean",
			"values": {}
		},
		{
			"code": "repeat_value",
			"name": "repeat_value",
			"type": "Integer",
			"values": {
				"unit": "",
				"min": 1.0,
				"max": 100.0,
				"scale": 0.0,
				"step": 1.0
			}
		}
	],
	"status": [{
			"code": "switch_1",
			"name": "开关1",
			"type": "Boolean",
			"values": {}
		},
		{
			"code": "repeat_value",
			"name": "repeat_value",
			"type": "Integer",
			"values": {
				"unit": "",
				"min": 1.0,
				"max": 100.0,
				"scale": 0.0,
				"step": 1.0
			}
		}
	]
    }
  ```

- 新建联动
   调用接口 `POST:localhost:8080/linkage` 
   注意参数中的functionCode和statusCode分别对应第一步中请求返回的指令code
   **请求内容：**
  ```json
  {
    "spaceId": "62855019",
    "name": "设备A打开联动设备B打开",
    "type": "automation",
    "decisionExpr": "or",
    "conditions": [
        {
            "code": 1,
            "entityId": "设备A设备ID",
            "entityType": "device_report",
            "expr": {
                "statusCode": "switch_1",
                "comparator": "==",
                "statusValue": true
            }
        }
    ],
    "actions": [
        {
            "entityId": "设备B设备ID",
            "actionExecutor": "device_issue",
            "executorProperty": {
                "functionCode": "switch_1",
                "functionValue": true
            }
        }
    ]
  }
  ```

- 查询联动内容，确认联动规则新建正确
   调用接口 `GET:localhost:8080/linkage/xxxid
    **返回结果：**
     ```json
    {
    "id": null,
    "status": "enable",
    "runningMode": "cloud",
    "spaceId": null,
    "name": "设备A打开联动设备B打开",
    "type": null,
    "decisionExpr": "or",
    "conditions": [
        {
            "entityId": "设备A",
            "entityType": "device_report",
            "code": 1,
            "expr": {
                "statusCode": "switch_1",
                "comparator": "==",
                "statusValue": "true"
            }
        }
    ],
    "actions": [
        {
            "entityId": "设备B",
            "actionExecutor": "device_issue",
            "executorProperty": {
                "functionCode": "switch_1",
                "functionValue": true
            }
        }
    ],
    "effectiveTime": null
   }
    ```
- 开关A打开，观察现象，以及消息推送结果
   现象：设备B被打开，服务日志中看到 **联动执行** pulsar消息
   同时也可以看到**设备上报**的日志
  ```json
   2022-11-16 14:45:30.823  INFO 9535 --- [pool-2-thread-1] c.t.o.s.b.s.a.m.TuyaMessageListener      : StatusReport event happened: {"dataId":"AAXtkNGStaXJkt9B9ANQkALD","devId":"xxxx","eventType":"STATUS_REPORT","productKey":"3scsoziioegwvclm","sourceMessage":{"data":"9FYTKVxH5kfusyKiuV4mQlTuK+JucNAfc2aZfvELS2JCmyaEfIborumoiueCL2Mp1ZX90eXZhOp5+wQ9J2fju7xGpwD/JlZZBAfq7xRafwK3EoECzdmu7KRyWpHElyMo1XyDdVVhcKL1zpLhZNOiyUi/EDUSDu+AUoHcC3ZBtjgA4+i815ocSIZxHvB+8csg8ZT/GixGBfG/bGk/doPaI9EgeDUnN3CyDfkFlYkWYiQ=","protocol":4,"pv":"2.0","sign":"7b10ee3682c517cf62c68dd46b12ff6d","t":1668581130851},"status":[{"code":"switch_1","t":1668581130679,"value":true}]}

   2022-11-16 14:45:30.823  INFO 9535 --- [pool-2-thread-1] c.t.o.s.b.s.a.m.TuyaMessageListener      : Linkage exec event: xxxx
  ```