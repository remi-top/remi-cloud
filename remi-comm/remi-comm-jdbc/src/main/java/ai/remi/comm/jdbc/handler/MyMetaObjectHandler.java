package ai.remi.comm.jdbc.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc MyMetaObjectHandler 配置类
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        this.setFieldValByName("createdAt", LocalDateTime.now(), metaObject);
        //this.setFieldValByName("createdBy", AuthInfoUtils.getUniqueId(), metaObject);
        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
        //this.setFieldValByName("updatedBy", AuthInfoUtils.getUniqueId(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName("updatedAt", LocalDateTime.now(), metaObject);
    }
}
