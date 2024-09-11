package ai.remi.comm.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author dianjiu【】
 * @email dianjiuxyz@gmail.com
 * @desc BaseEntity
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 乐观锁
     */
    @Version
    @TableField("revision")
    private Integer revision;

    /**
     * 逻辑删除 （0正常 1删除）
     * value = ""  默认的原值，默认为 0；
     * delval = "" 删除后的值，默认为1；
     */
//    @TableLogic(value="0",delval="1")
    @TableField("deleted")
    private Integer deleted;
    
    /**
     * 部门租户ID
     */
    @TableField("dept_tenant_id")
    private String deptTenantId;

    /**
     * 公司租户ID
     */
    @TableField("company_tenant_id")
    private String companyTenantId;

    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    /**
     * 更新人
     */
    @TableField("updated_by")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

}
