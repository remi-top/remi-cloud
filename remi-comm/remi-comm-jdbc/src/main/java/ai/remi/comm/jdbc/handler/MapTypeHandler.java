package ai.remi.comm.jdbc.handler;

import java.util.Map;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc Map类型转换器
 */
public class MapTypeHandler extends JsonTypeHandler<Map> {
    public MapTypeHandler() {
        super(Map.class);
    }
}
