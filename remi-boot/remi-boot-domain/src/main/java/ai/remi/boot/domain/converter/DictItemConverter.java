package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.DictItemPostDTO;
import ai.remi.boot.domain.dto.put.DictItemPutDTO;
import ai.remi.boot.domain.entity.DictItem;
import ai.remi.boot.domain.query.DictItemQuery;
import ai.remi.boot.domain.vo.DictItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 字典项表 DictItemConverter转换接口
 */
@Mapper
public interface DictItemConverter {

    DictItemConverter INSTANT = Mappers.getMapper(DictItemConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DictItem postDtoToEntity(DictItemPostDTO dictItemDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DictItem putDtoToEntity(DictItemPutDTO dictItemDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DictItem queryToEntity(DictItemQuery dictItemQuery);

    DictItemVO entityToVO(DictItem dictItem);

}
