package ai.remi.comm.util.number;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc BigDecimalUtils
 */
public class BigDecimalUtils {
    
    /**
     * 中位数
     */
    public static BigDecimal median(List<BigDecimal> list) {
        
        if (null == list) {
            return null;
        }
        Collections.sort(list);
        int size = list.size();
        if (size % 2 == 1) {
            
            return list.get((size - 1) / 2);
        } else {
            
            return (list.get(size / 2 - 1).add(list.get(size / 2))).divide(new BigDecimal(2), 3);
        }
    }
    
    /**
     * 排序
     */
    
    public static List<BigDecimal> sort(List<BigDecimal> list) {
        
        if (null == list) {
            return null;
        }
        Collections.sort(list);
        return list;
        
    }
    
    /**
     * 最大值
     */
    public static BigDecimal max(List<BigDecimal> list) {
        
        if (null == list) {
            return null;
        }
        //        BigDecimal max = Collections.max(list);
        
        BigDecimal sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            
            sum = sum.max(list.get(i));
        }
        return sum;
    }
    
    /**
     * 最小值
     */
    public static BigDecimal min(List<BigDecimal> list) {
        
        if (null == list) {
            return null;
        }
        //        BigDecimal min = Collections.min(list);
        BigDecimal sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            
            sum = sum.min(list.get(i));
        }
        return sum;
    }
    
    /**
     * 平均值
     *
     * @return
     */
    public static BigDecimal avg(List<BigDecimal> list) {
        
        if (null == list) {
            return null;
        }
        BigDecimal sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            
            sum = sum.add(list.get(i));
        }
        BigDecimal sum1 = new BigDecimal(list.size());
        BigDecimal avg = sum.divide(sum1, 2);
        System.out.println(avg);
        return avg;
    }
    
}
