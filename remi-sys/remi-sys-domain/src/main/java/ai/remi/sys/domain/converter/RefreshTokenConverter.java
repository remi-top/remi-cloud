package ai.remi.sys.domain.converter;


import ai.remi.sys.domain.dto.post.RefreshTokenPostDTO;
import ai.remi.sys.domain.dto.put.RefreshTokenPutDTO;
import ai.remi.sys.domain.entity.RefreshToken;
import ai.remi.sys.domain.query.RefreshTokenQuery;
import ai.remi.sys.domain.vo.RefreshTokenVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 刷新令牌表 RefreshTokenConverter转换接口
 */
@Mapper
public interface RefreshTokenConverter {

    RefreshTokenConverter INSTANT = Mappers.getMapper(RefreshTokenConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RefreshToken postDtoToEntity(RefreshTokenPostDTO refreshTokenDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RefreshToken putDtoToEntity(RefreshTokenPutDTO refreshTokenDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RefreshToken queryToEntity(RefreshTokenQuery refreshTokenQuery);

    RefreshTokenVO entityToVO(RefreshToken refreshToken);

}
