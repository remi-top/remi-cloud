package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.DeptUserPostDTO;
import ai.remi.boot.domain.dto.put.DeptUserPutDTO;
import ai.remi.boot.domain.entity.DeptUser;
import ai.remi.boot.domain.query.DeptUserQuery;
import ai.remi.boot.domain.vo.DeptUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 部门用户表 DeptUserConverter转换接口
 */
@Mapper
public interface DeptUserConverter {

    DeptUserConverter INSTANT = Mappers.getMapper(DeptUserConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeptUser postDtoToEntity(DeptUserPostDTO deptUserDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeptUser putDtoToEntity(DeptUserPutDTO deptUserDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DeptUser queryToEntity(DeptUserQuery deptUserQuery);

    DeptUserVO entityToVO(DeptUser deptUser);

}
