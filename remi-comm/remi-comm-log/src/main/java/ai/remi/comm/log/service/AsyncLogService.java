package ai.remi.comm.log.service;

import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 */
@Service
public class AsyncLogService {

    /**
     * 通过OpenFeign客户端注入
     */
    //@Resource
    //private SysClientApi sysClientApi;

    /**
     * 保存系统日志记录
     */
    //@Async
    //public void saveOperateLog(OperateLog operateLog) {
        //CompanyAuthInfo companyAuthInfo = new CompanyAuthInfo();
        //companyAuthInfo.setDeptTenantId(operateLog.getDeptTenantId());
        //companyAuthInfo.setCompanyTenantId(operateLog.getCompanyTenantId());
        //RequestHolder.add(companyAuthInfo);
        //LogRecordPostDTO logRecordPostDTO = BeanCopyUtils.copyPropertiesThird(operateLog, LogRecordPostDTO.class);
        //sysClientApi.addLogRecord(logRecordPostDTO);
    //}
}
