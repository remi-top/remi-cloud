<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">Startdis Cloud</h1>

## 平台简介

Startdis Cloud 即 Startdis Dev Tools 启迪快速开发平台的多模块微服务开发框架。由[江苏点九科技有限公司](https://dianjiu.cc)提供技术支持。

### 分支说明

- main: 暂不维护
- dev: 2.0 和 3.0 的开发分支 测试稳定后会合并到对应分支
- 2.0.0: java17 + springboot 2.7 + springcloud 2021
- 3.0.0: java21 + springboot 3.3 + springcloud 2023

### 文档视频

https://www.yuque.com/dianjiuxyz/vpdfr9

### 体验地址

https://sdt.startdis.com

startdis/startdis2022

### 其他产品

- 👉🏻 [自研 IAM 统一身份引擎](https://iam.startdis.com)

- 👉🏻 [自研 BPM 统一流程引擎](https://bpm.startdis.com)

- 👉🏻 [自研 LCE 低代码引擎](https://lce.startdis.com)

## 快速开始

### 模块说明
~~~
startdis-boot     
├── docs                                              // 说明文档
└── sdt-comm                                          // 公共模块
     ├── comm-auth                                    // 权限控制
     ├── comm-core                                    // 核心模块
     ├── comm-domain                                  // 领域模型
     ├── comm-jdbc                                    // 数据配置
     ├── comm-datascope                               // 数据权限
     ├── comm-datasource                              // 多数据源
     ├── comm-exception                               // 异常模块
     ├── comm-feign                                   // 通信模块
     ├── comm-job                                     // 任务模块
     ├── comm-minio                                   // MinIo 文件存储
     ├── comm-s3                                      // S3 对象存储
     ├── comm-fastdfs                                 // FastDFS文件存储
     ├── comm-activemq                                // ActiveMQ
     ├── comm-rabbitmq                                // RabbitMQ
     ├── comm-rocketmq                                // RocketMQ
     ├── comm-redis                                   // 缓存服务
     ├── comm-utils                                   // 工具类
     ├── comm-thread                                  // 线程池
     ├── comm-safe                                    // 安全控制
     ├── comm-saprfc                                  // Sap Rfc
     ├── comm-web                                     // Web
     ├── comm-webservice                              // WebService
     ├── comm-websocket                               // WebSocket
     ├── comm-netty                                   // Netty
     ├── comm-security                                // 安全模块
     ├── comm-swagger                                 // 系统接口
     ├── pom.xml                                      // 公共依赖
└── sdt-iam                                           // 身份引擎（统一身份管理）
└── sdt-bpm                                           // 流程引擎（统一流程管理）
└── sdt-xpe                                           // 插件引擎（插件拓展平台）
└── sdt-lce                                           // 代码引擎（低代码平台）
~~~

