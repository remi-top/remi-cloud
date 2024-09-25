```yaml
# XXL-JOB
xxl-job:
  enabled: false
  # 调度中心部署跟地址
  adminAddresses: http://127.0.0.1:8080/xxl-job-admin
  # 执行器通讯TOKEN
  accessToken:
  # 执行器
  executor:
    # 执行器AppName
    appname: remi-ids-job
    # 执行器注册地址
    address:
    # 执行器IP
    ip:
    # 执行器端口号
    port:
    # 执行器运行日志文件存储磁盘路径
    logPath: /app/logs/remi-ids-job
    # 执行器日志文件保存天数
    logRetentionDays: 30
```