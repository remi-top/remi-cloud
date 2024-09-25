package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.CompanyDeptPostDTO;
import ai.remi.boot.domain.dto.put.CompanyDeptPutDTO;
import ai.remi.boot.domain.entity.CompanyDept;
import ai.remi.boot.domain.query.CompanyDeptQuery;
import ai.remi.boot.domain.vo.CompanyDeptVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 公司部门表 CompanyDeptConverter转换接口
 */
@Mapper
public interface CompanyDeptConverter {
    
    CompanyDeptConverter INSTANT = Mappers.getMapper(CompanyDeptConverter.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CompanyDept postDtoToEntity(CompanyDeptPostDTO companyDeptDTO);
    
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CompanyDept putDtoToEntity(CompanyDeptPutDTO companyDeptDTO);
    
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CompanyDept queryToEntity(CompanyDeptQuery companyDeptQuery);
    
    CompanyDeptVO entityToVO(CompanyDept companyDept);
    
}
