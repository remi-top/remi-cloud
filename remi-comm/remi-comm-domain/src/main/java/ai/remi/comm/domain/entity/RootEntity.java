package ai.remi.comm.domain.entity;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc RootEntity
 */
public interface RootEntity extends Serializable {

    public String getId();

    public RootEntity setId(String id);

    public Integer getRevision();

    public RootEntity setRevision(Integer revision);

    public Integer getDeleted();

    public RootEntity setDeleted(Integer deleted);

    public String getDeptTenantId();

    public RootEntity setDeptTenantId(String deptTenantId);

    public String getCompanyTenantId();

    public RootEntity setCompanyTenantId(String companyTenantId);

    public String getCreatedBy();

    public RootEntity setCreatedBy(String createdBy);

    public LocalDateTime getCreatedAt();

    public RootEntity setCreatedAt(LocalDateTime createdAt);

    public String getUpdatedBy();

    public RootEntity setUpdatedBy(String updatedBy);

    public LocalDateTime getUpdatedAt();

    public RootEntity setUpdatedAt(LocalDateTime updatedAt);

}
