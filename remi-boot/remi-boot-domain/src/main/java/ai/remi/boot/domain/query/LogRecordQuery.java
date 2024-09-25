package ai.remi.boot.domain.query;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 日志记录表 LogRecordQuery对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "日志记录表")
public class LogRecordQuery implements Serializable {
    private static final long serialVersionUID = -49900855695220251L;
    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
    /**
     * 应用编码
     */
    @Schema(description = "应用编码")
    private String appCode;
    /**
     * 操作方法名称或描述
     */
    @Schema(description = "操作方法名称或描述")
    private String content;
    /**
     * 操作人类别（1管理端用户 2移动端用户 99其他）
     */
    @Schema(description = "操作人类别（1管理端用户 2移动端用户 99其他）")
    private Integer operatorType;
    /**
     * 业务操作类型（1新增 2修改 3删除 4导入 5导出 6下载 7上传 8授权 9登录 10登出 11强退 12修改状态 13生成代码 14清空数据 15更新缓存 99其他）
     */
    @Schema(description = "业务操作类型（1新增 2修改 3删除 4导入 5导出 6下载 7上传 8授权 9登录 10登出 11强退 12修改状态 13生成代码 14清空数据 15更新缓存 99其他）")
    private Integer businessType;
    /**
     * 业务流水号
     */
    @Schema(description = "业务流水号")
    private String businessNo;
    /**
     * 链路追踪ID
     */
    @Schema(description = "链路追踪ID")
    private String traceId;
    /**
     * 操作人Id
     */
    @Schema(description = "操作人Id")
    private String userId;
    /**
     * 操作人编码
     */
    @Schema(description = "操作人编码")
    private String userCode;
    /**
     * 操作人员名称
     */
    @Schema(description = "操作人员名称")
    private String userName;
    /**
     * 操作人员英文名称
     */
    @Schema(description = "操作人员英文名称")
    private String userNameEn;
    /**
     * 用户系统语言
     */
    @Schema(description = "用户系统语言")
    private String userLanguage;
    /**
     * 请求地址
     */
    @Schema(description = "请求地址")
    private String requestUrl;
    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String requestMethod;
    /**
     * 请求类型（POST、PUT、OPTIONS、DELETE）
     */
    @Schema(description = "请求类型（POST、PUT、OPTIONS、DELETE）")
    private String requestType;
    /**
     * 请求报文类型（map、json）
     */
    @Schema(description = "请求报文类型（map、json）")
    private String contentType;
    /**
     * 请求报文
     */
    @Schema(description = "请求报文")
    private String requestBody;
    /**
     * 请求时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "请求时间")
    private LocalDateTime requestTime;
    /**
     * 响应报文
     */
    @Schema(description = "响应报文")
    private String responseBody;
    /**
     * 响应时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "响应时间")
    private LocalDateTime responseTime;
    /**
     * 异常信息
     */
    @Schema(description = "异常信息")
    private Integer errorInfo;
    /**
     * 请求耗时，单位：ms
     */
    @Schema(description = "请求耗时，单位：ms")
    private Long executeTime;
    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String platform;
    /**
     * 浏览器
     */
    @Schema(description = "浏览器")
    private String browser;
    /**
     * 操作人IP
     */
    @Schema(description = "操作人IP")
    private String userIp;
    /**
     * IP归属地址
     */
    @Schema(description = "IP归属地址")
    private String ipAddress;
    /**
     * 是否成功（0失败 1成功）
     */
    @Schema(description = "是否成功（0失败 1成功）")
    private Integer status;

}

