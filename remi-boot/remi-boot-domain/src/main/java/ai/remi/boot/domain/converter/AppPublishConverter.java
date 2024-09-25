package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.AppPublishPostDTO;
import ai.remi.boot.domain.dto.put.AppPublishPutDTO;
import ai.remi.boot.domain.entity.AppPublish;
import ai.remi.boot.domain.query.AppPublishQuery;
import ai.remi.boot.domain.vo.AppPublishVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 应用发布表 AppPublishConverter转换接口
 */
@Mapper
public interface AppPublishConverter {

    AppPublishConverter INSTANT = Mappers.getMapper(AppPublishConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AppPublish postDtoToEntity(AppPublishPostDTO appPublishDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AppPublish putDtoToEntity(AppPublishPutDTO appPublishDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AppPublish queryToEntity(AppPublishQuery appPublishQuery);

    AppPublishVO entityToVO(AppPublish appPublish);

}
