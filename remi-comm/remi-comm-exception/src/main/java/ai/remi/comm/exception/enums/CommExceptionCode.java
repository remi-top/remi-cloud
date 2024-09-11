package ai.remi.comm.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 错误码设计描述
 * <p>
 * 面向日志的错误码定义共十三位（十位有意义，三位连接符），并且应该具有如下分类： 应用标识，表示错误属于哪个应用，三位数字。 功能域标识，表示错误属于应用中的哪个功能模块，三位数字。 错误类型，表示错误属于那种类型，一位字母。
 * 错误编码，错误类型下的具体错误，三位数字。
 * <p>
 * 面向外部传递的错误码共六位，并且有如下分类： 错误类型，表示错误来源，1位字母。 应用标识，表示错误属于哪个应用，2位数字。 错误编码，错误类型下的具体错误，3位数字。
 * <p>
 * 错误类型： A-表示错误来源于用户 B-表示错误来源于系统 C-表示错误来源于第三方接口
 */
@Getter
@AllArgsConstructor
public enum CommExceptionCode implements ExceptionCode {

    /**
     * 面向外部传递的错误码共六位，并且有如下分类：
     * 错误类型，表示错误来源，1位字母。
     * 应用标识，表示错误属于哪个应用，2位数字。
     * 错误编码，错误类型下的具体错误，3位数字。
     * <p>
     * 错误类型： A-表示错误来源于用户 B-表示错误来源于系统 C-表示错误来源于第三方接口
     */
    /****************************客户端异常****************************/
    BUSINESS_ERROR("A01001", "business.error"),
    ILLEGAL_ARGUMENT("A01002", "illegal.argument"),
    NOT_LOGGED_IN("A01003", "not.logged.in"),
    /****************************系统异常****************************/
    SYSTEM_ERROR("B01001", "system.error"),
    ASSERT_ERROR("B01002", "assert.error"),
    /****************************三方异常****************************/
    OTHER_SYSTEM_ERROR("C01001", "other.system.error"),
    CALL_FEIGN_ERROR("C01002", "call.feign.error"),
    CALL_SQL_ERROR("C01003", "call.sql.error"),
    CALL_REDIS_ERROR("C01004", "call.redis.error"),
    CALL_MINIO_ERROR("C01005", "call.minio.error"),
    CALL_NACOS_ERROR("C01006", "call.nacos.error"),
    CALL_JOB_ERROR("C01007", "call.job.error"),
    CALL_MQ_ERROR("C01008", "call.mq.error"),
    CALL_ES_ERROR("C01009", "call.es.error");

    private String code;

    private String key;

}
