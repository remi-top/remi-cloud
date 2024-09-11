package ai.remi.comm.log.filter;

import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import lombok.NoArgsConstructor;

/**
 * 排除JSON敏感属性
 */
@NoArgsConstructor
public class PropertyPreExcludeFilter extends SimplePropertyPreFilter {

    public PropertyPreExcludeFilter addExcludes(String... filters) {
        for (String filter : filters) {
            this.getExcludes().add(filter);
        }
        return this;
    }
}
