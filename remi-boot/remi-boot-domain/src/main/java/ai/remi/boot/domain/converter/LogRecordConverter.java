package ai.remi.boot.domain.converter;


import ai.remi.boot.domain.dto.post.LogRecordPostDTO;
import ai.remi.boot.domain.dto.put.LogRecordPutDTO;
import ai.remi.boot.domain.entity.LogRecord;
import ai.remi.boot.domain.query.LogRecordQuery;
import ai.remi.boot.domain.vo.LogRecordVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author DianJiu
 * @email dianjiuxyz@gmail.com
 * @desc 日志记录表 LogRecordConverter转换接口
 */
@Mapper
public interface LogRecordConverter {

    LogRecordConverter INSTANT = Mappers.getMapper(LogRecordConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LogRecord postDtoToEntity(LogRecordPostDTO logRecordDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LogRecord putDtoToEntity(LogRecordPutDTO logRecordDTO);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "revision", ignore = true)
    @Mapping(target = "deptTenantId", ignore = true)
    @Mapping(target = "companyTenantId", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    LogRecord queryToEntity(LogRecordQuery logRecordQuery);

    LogRecordVO entityToVO(LogRecord logRecord);

}
