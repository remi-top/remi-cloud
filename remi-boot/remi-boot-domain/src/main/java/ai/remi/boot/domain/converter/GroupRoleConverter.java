package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.GroupRolePostDTO;
import ai.remi.boot.domain.dto.put.GroupRolePutDTO;
import ai.remi.boot.domain.entity.GroupRole;
import ai.remi.boot.domain.query.GroupRoleQuery;
import ai.remi.boot.domain.vo.GroupRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 用户组角色表 GroupRoleConverter转换接口
 */
@Mapper
public interface GroupRoleConverter {

    GroupRoleConverter INSTANT = Mappers.getMapper(GroupRoleConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GroupRole postDtoToEntity(GroupRolePostDTO groupRoleDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GroupRole putDtoToEntity(GroupRolePutDTO groupRoleDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GroupRole queryToEntity(GroupRoleQuery groupRoleQuery);

    GroupRoleVO entityToVO(GroupRole groupRole);

}
