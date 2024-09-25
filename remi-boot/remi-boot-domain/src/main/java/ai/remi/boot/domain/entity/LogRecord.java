package ai.remi.boot.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import ai.remi.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 日志记录表 LogRecordDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_log_record")
public class LogRecord extends BaseEntity {


    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 操作方法名称或描述
     */
    private String content;

    /**
     * 操作人类别（1管理端用户 2移动端用户 99其他）
     */
    private Integer operatorType;

    /**
     * 业务操作类型（1新增 2修改 3删除 4导入 5导出 6下载 7上传 8授权 9登录 10登出 11强退 12修改状态 13生成代码 14清空数据 15更新缓存 99其他）
     */
    private Integer businessType;

    /**
     * 业务流水号
     */
    private String businessNo;

    /**
     * 链路追踪ID
     */
    private String traceId;

    /**
     * 操作人Id
     */
    private String userId;

    /**
     * 操作人编码
     */
    private String userCode;

    /**
     * 操作人员名称
     */
    private String userName;

    /**
     * 操作人员英文名称
     */
    private String userNameEn;

    /**
     * 用户系统语言
     */
    private String userLanguage;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求类型（POST、PUT、OPTIONS、DELETE）
     */
    private String requestType;

    /**
     * 请求报文类型（map、json）
     */
    private String contentType;

    /**
     * 请求报文
     */
    private String requestBody;

    /**
     * 请求时间
     */
    private LocalDateTime requestTime;

    /**
     * 响应报文
     */
    private String responseBody;

    /**
     * 响应时间
     */
    private LocalDateTime responseTime;

    /**
     * 异常信息
     */
    private Integer errorInfo;

    /**
     * 请求耗时，单位：ms
     */
    private Long executeTime;

    /**
     * 操作系统
     */
    private String platform;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作人IP
     */
    private String userIp;

    /**
     * IP归属地址
     */
    private String ipAddress;

    /**
     * 是否成功（0失败 1成功）
     */
    private Integer status;


}
