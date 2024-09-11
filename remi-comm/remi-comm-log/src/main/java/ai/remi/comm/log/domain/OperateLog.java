package ai.remi.comm.log.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author 点九
 * @since 2022-07-20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class OperateLog implements Serializable {

    private static final long serialVersionUID = -85509022761738038L;

    /**
     * 业务模块，区分不同业务模块的应用ID
     */
    private String appId;

    /**
     * 业务模块，区分不同业务模块的应用编码
     */
    private String appCode;

    /**
     * 操作方法名称或描述
     */
    private String content;

    /**
     * 请求状态 成功 or 失败
     */
    private String status;

    /**
     * 操作人类别
     */
    private Integer operatorType;

    /**
     * 业务操作类型
     */
    private Integer businessType;

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
     * 响应报文
     */
    private String responseBody;

    /**
     * 异常信息
     */
    private String errorInfo;

    /**
     * 请求时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime requestTime;

    /**
     * 响应时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime responseTime;

    /**
     * 操作花费的时间 单位：ms
     */
    private Long executeTime;

    /**
     * 用户系统语言
     */
    private String userLanguage;

    /**
     * 业务流水号
     */
    private String businessNo;

    /**
     * 链路追踪ID
     */
    private String traceId;

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
     * 公司租户ID
     */
    private String companyTenantId;

    /**
     * 部门租户ID
     */
    private String deptTenantId;

}
