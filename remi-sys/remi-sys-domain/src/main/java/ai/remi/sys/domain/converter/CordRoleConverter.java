package ai.remi.sys.domain.converter;


import ai.remi.sys.domain.dto.post.CordRolePostDTO;
import ai.remi.sys.domain.dto.put.CordRolePutDTO;
import ai.remi.sys.domain.entity.CordRole;
import ai.remi.sys.domain.query.CordRoleQuery;
import ai.remi.sys.domain.vo.CordRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 组织角色表 CordRoleConverter转换接口
 */
@Mapper
public interface CordRoleConverter {

    CordRoleConverter INSTANT = Mappers.getMapper(CordRoleConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CordRole postDtoToEntity(CordRolePostDTO deptRoleDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CordRole putDtoToEntity(CordRolePutDTO deptRoleDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CordRole queryToEntity(CordRoleQuery deptRoleQuery);

    CordRoleVO entityToVO(CordRole deptRole);

}
