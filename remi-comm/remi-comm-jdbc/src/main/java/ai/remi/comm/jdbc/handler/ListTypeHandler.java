package ai.remi.comm.jdbc.handler;

import java.util.List;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc List类型转换器
 */
public class ListTypeHandler extends JsonTypeHandler<List> {
    public ListTypeHandler() {
        super(List.class);
    }
}
