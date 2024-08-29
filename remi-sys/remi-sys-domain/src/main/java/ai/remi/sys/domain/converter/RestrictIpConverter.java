package ai.remi.sys.domain.converter;


import ai.remi.sys.domain.dto.post.RestrictIpPostDTO;
import ai.remi.sys.domain.dto.put.RestrictIpPutDTO;
import ai.remi.sys.domain.entity.RestrictIp;
import ai.remi.sys.domain.query.RestrictIpQuery;
import ai.remi.sys.domain.vo.RestrictIpVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 黑白名单表 RestrictIpConverter转换接口
 */
@Mapper
public interface RestrictIpConverter {

    RestrictIpConverter INSTANT = Mappers.getMapper(RestrictIpConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RestrictIp postDtoToEntity(RestrictIpPostDTO restrictIpDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RestrictIp putDtoToEntity(RestrictIpPutDTO restrictIpDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RestrictIp queryToEntity(RestrictIpQuery restrictIpQuery);

    RestrictIpVO entityToVO(RestrictIp restrictIp);

}
