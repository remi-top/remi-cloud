package ai.remi.comm.jdbc.handler;

import ai.remi.comm.jdbc.config.InterceptConfig;
import ai.remi.comm.jdbc.enums.InterceptTableStrategy;

import java.util.Set;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc SQL拦截器抽象类
 */
public abstract class AbstractSqlHandler {

    protected InterceptConfig interceptConfig;

    public AbstractSqlHandler(InterceptConfig interceptConfig) {
        this.interceptConfig = interceptConfig;
    }

    protected boolean defaultIgnoreStrategy(String tableName, InterceptConfig interceptConfig) {
        if (customIgnore()) {
            return true;
        }
        InterceptTableStrategy interceptTableStrategy = interceptConfig.getInterceptTableStrategy();
        Set<String> tables = interceptConfig.getTables();
        if (InterceptTableStrategy.EXCLUDE.equals(interceptTableStrategy)) {
            return tables.contains(tableName);
        } else if (InterceptTableStrategy.INCLUDE.equals(interceptTableStrategy)) {
            return !tables.contains(tableName);
        }else {
            throw new RuntimeException("未指定表拦截策略");
        }
    }

    /**
     * 如果传入的字段名为空串，则获取默认的字段
     * @param column
     * @return
     */
    protected String handleColumn(String column) {
        if (isNotBlank(column)){
            return column;
        }
        column = getDefaultColumn();
        if (isNotBlank(column)){
            return column;
        }
        throw new RuntimeException(this.getClass()+"未指定填充字段,请检查");
    }

    public static boolean isNotBlank(String str) {
        return str!=null&&str.trim().length() > 0;
    }

    /**
     * 子类实现指定 字段名
     * @return
     */
    protected String getDefaultColumn(){
        return "";
    }

    /**
     * 子类需要实现自定义忽略策略
     * @return
     */
    protected abstract boolean customIgnore();

}
