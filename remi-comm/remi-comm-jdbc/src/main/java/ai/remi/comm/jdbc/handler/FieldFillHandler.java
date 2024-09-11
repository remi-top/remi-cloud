package ai.remi.comm.jdbc.handler;

import net.sf.jsqlparser.expression.Expression;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc 字段填充拦截器
 */
public interface FieldFillHandler {

    /**
     * 获取自动填充值表达式
     */
    Expression getFieldFillValue();

    /**
     * 获取自动填充的字段名
     */
    String getFieldFillColumn();

    /**
     * 在创建的时候根据表名判断是否忽略拼接字段
     *
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接
     */
    default boolean createIgnore(String tableName){
        return false;
    }

    /**
     * 在更新的时候根据表名判断是否忽略拼接字段
     *
     * @param tableName 表名
     * @return 是否忽略, true:表示忽略，false:需要解析并拼接
     */
    default boolean updateIgnore(String tableName) {
        return false;
    }


}
