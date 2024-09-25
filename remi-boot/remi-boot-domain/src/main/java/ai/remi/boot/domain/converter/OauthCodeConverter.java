package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.OauthCodePostDTO;
import ai.remi.boot.domain.dto.put.OauthCodePutDTO;
import ai.remi.boot.domain.entity.OauthCode;
import ai.remi.boot.domain.query.OauthCodeQuery;
import ai.remi.boot.domain.vo.OauthCodeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 授权码表 OauthCodeConverter转换接口
 */
@Mapper
public interface OauthCodeConverter {

    OauthCodeConverter INSTANT = Mappers.getMapper(OauthCodeConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OauthCode postDtoToEntity(OauthCodePostDTO oauthCodeDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OauthCode putDtoToEntity(OauthCodePutDTO oauthCodeDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    OauthCode queryToEntity(OauthCodeQuery oauthCodeQuery);

    OauthCodeVO entityToVO(OauthCode oauthCode);

}
