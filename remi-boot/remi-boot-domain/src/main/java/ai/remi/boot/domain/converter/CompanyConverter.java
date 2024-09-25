package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.CompanyPostDTO;
import ai.remi.boot.domain.dto.put.CompanyPutDTO;
import ai.remi.boot.domain.entity.Company;
import ai.remi.boot.domain.query.CompanyQuery;
import ai.remi.boot.domain.vo.CompanyVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 公司表 CompanyConverter转换接口
 */
@Mapper
public interface CompanyConverter {
    
    CompanyConverter INSTANT = Mappers.getMapper(CompanyConverter.class);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company postDtoToEntity(CompanyPostDTO companyDTO);
    
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company putDtoToEntity(CompanyPutDTO companyDTO);
    
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company queryToEntity(CompanyQuery companyQuery);
    
    CompanyVO entityToVO(Company company);
    
}
