package ai.remi.boot.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import ai.remi.comm.domain.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户表 UserDTO对象
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName("remi_user")
public class User extends BaseEntity {
    
    
    /**
     * 用户编码/员工工号
     */
    private String userCode;
    
    /**
     * 登录账号
     */
    private String loginName;
    
    /**
     * 用户名称
     */
    private String userName;
    
    /**
     * 用户英文名称
     */
    private String userNameEn;
    
    /**
     * 用户首名（东方为姓，西方为名）
     */
    private String firstName;
    
    /**
     * 用户中名（欧美人常用）
     */
    private String middleName;
    
    /**
     * 用户末名（东方为名，西方为姓）
     */
    private String lastName;
    
    /**
     * 用户昵称/别号
     */
    private String nickName;
    
    /**
     * 用户描述
     */
    private String userRemark;
    
    /**
     * 登录密码
     */
    private String password;
    
    /**
     * 出生日期
     */
    private LocalDate birthday;
    
    /**
     * 证件号码
     */
    private String identity;
    
    /**
     * 电话号码
     */
    private String phone;
    
    /**
     * 电子邮箱
     */
    private String email;
    
    /**
     * 用户性别（0女性 1男性）
     */
    private Integer sex;
    
    /**
     * 头像地址
     */
    private String headPic;
    
    /**
     * 最后登录IP
     */
    private String loginIp;
    
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;
    
    /**
     * 是否启用（0停用 1启用）
     */
    private Integer status;
    
    
}
