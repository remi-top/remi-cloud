package ai.remi.boot.domain.dto.post;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户表 UserDTO对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Schema(description = "用户表")
public class UserPostDTO implements Serializable {

    private static final long serialVersionUID = -48342054464737753L;

    /**
     * 所属公司ID
     */
    @Schema(description = "所属公司ID")
    private String companyId;

    /**
     * 所属公司编码
     */
    @Schema(description = "所属公司编码")
    private String companyCode;

    /**
     * 所属部门ID
     */
    @Schema(description = "所属部门ID")
    private String deptId;

    /**
     * 所属部门编码
     */
    @Schema(description = "所属部门编码")
    private String deptCode;

    /**
     * 用户编码/员工工号
     */
    @Schema(description = "用户编码/员工工号")
    private String userCode;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String userName;

    /**
     * 用户英文名称
     */
    @Schema(description = "用户英文名称")
    private String userNameEn;

    /**
     * 用户首名（东方为姓，西方为名）
     */
    @Schema(description = "用户首名（东方为姓，西方为名）")
    private String firstName;

    /**
     * 用户中名（欧美人常用）
     */
    @Schema(description = "用户中名（欧美人常用）")
    private String middleName;

    /**
     * 用户末名（东方为名，西方为姓）
     */
    @Schema(description = "用户末名（东方为名，西方为姓）")
    private String lastName;

    /**
     * 用户昵称/别号
     */
    @Schema(description = "用户昵称/别号")
    private String nickName;

    /**
     * 登录账号
     */
    @Schema(description = "登录账号")
    private String loginName;

    /**
     * 登录密码
     */
    @Schema(description = "登录密码")
    private String password;

    /**
     * 职位ID
     */
    @Schema(description = "职位ID")
    private String positionId;

    /**
     * 职位编码
     */
    @Schema(description = "职位编码")
    private String positionCode;

    /**
     * 职级ID
     */
    @Schema(description = "职级ID")
    private String rankId;

    /**
     * 职级编码
     */
    @Schema(description = "职级编码")
    private String rankCode;

    /**
     * 用户描述
     */
    @Schema(description = "用户描述")
    private String userRemark;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "出生日期")
    private LocalDate birthday;

    /**
     * 证件号码
     */
    @Schema(description = "证件号码")
    private String identity;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String phone;

    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    private String email;

    /**
     * 用户性别（0女性 1男性）
     */
    @Schema(description = "用户性别（0女性 1男性）")
    private Integer sex;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String headPic;

    /**
     * 最后登录IP
     */
    @Schema(description = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

    /**
     * 是否启用（0停用 1启用）
     */
    @Schema(description = "是否启用（0停用 1启用）")
    private Integer status;


}
