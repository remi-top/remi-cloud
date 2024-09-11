# 工程简介
瑞米平台公共的日志模块

## 操作日志
主要面向用户的，要求简单易懂，反映出用户所做的动作。

### 核心功能
- 要实现以下功能：

  - 自定义spring boot starter
  - 定义日志注解
  - AOP拦截日志注解方法
  - 定义日志动态内容模板

- 模板中又需要实现：
  - 动态模板表达式解析：用强大的SpEL来解析表达式
  - 自定义函数：支持目标方法前置/后置的自定义函数

## 

### SQL脚本
```sql
DROP TABLE IF EXISTS t_system_log;
CREATE TABLE t_system_log
(
    id                VARCHAR(32) NOT NULL COMMENT '日志编号',
    user_id           VARCHAR(32) COMMENT '用户ID',
    user_name         VARCHAR(255) COMMENT '用户名称',
    app_code          VARCHAR(255) COMMENT '应用编码',
    app_name          VARCHAR(255) COMMENT '应用名称',
    business_no       VARCHAR(255) COMMENT '业务流水号',
    business_type     VARCHAR(255) COMMENT '业务类型（新增、修改、删除、查询、导入、导出、其他）',
    log_content           LONGTEXT COMMENT '日志模板内容',
    log_extras            LONGTEXT COMMENT '额外的日志内容',
    request_url       VARCHAR(255) COMMENT '请求地址',
    request_ip        VARCHAR(255) COMMENT '请求IP',
    request_type      VARCHAR(255) COMMENT '请求类型（POST、PUT、OPTIONS、DELETE）',
    content_type      VARCHAR(255) COMMENT '请求报文类型（map、json）',
    request_body      LONGTEXT COMMENT '请求报文',
    response_body     LONGTEXT COMMENT '响应报文',
    request_time      DATETIME COMMENT '请求时间',
    response_time     DATETIME COMMENT '响应时间',
    execute_time       BIGINT COMMENT '请求耗时',
    trace_id          VARCHAR(255) COMMENT '链路追踪ID',
    error_info        LONGTEXT COMMENT '异常信息',
    user_agent        VARCHAR(255) COMMENT '浏览器、操作系统、UI引擎标识',
    revision          INTEGER(11) COMMENT '乐观锁',
    is_deleted        INTEGER(1) COMMENT '逻辑删除;0-未删除 1-已删除',
    group_tenant_id   VARCHAR(32) COMMENT '部门租户ID',
    company_tenant_id VARCHAR(32) COMMENT '公司租户ID',
    created_by        VARCHAR(32) COMMENT '创建人',
    created_at        DATETIME COMMENT '创建时间',
    updated_by        VARCHAR(32) COMMENT '更新人',
    updated_at        DATETIME COMMENT '更新时间',
    PRIMARY KEY (id)
) COMMENT = '系统日志表';
```
## 系统日志
主要用于开发者调试排查系统问题的，不要求固定格式和可读性。

### 

## 延伸阅读
https://tech.meituan.com/2021/09/16/operational-logbook.html

https://juejin.cn/post/7075184311397253128

http://mysql.taobao.org/monthly/2017/09/08/

