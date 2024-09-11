package ai.remi.comm.util.merge;

import java.util.List;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc MergeUtils
 */
public class MergeUtils {
    /**
     *
     * @param listA 需要合并的A对象集合
     * @param listB 需要合并的B对象集合
     * @param compare 对比A和B对象，如果true 就执行 merge方法
     * @param merge 合并A和B对象，需要用户自己实现
     * @param <R> 返回值的对象
     * @param <T> 被合并的对象
     */
    public static <R,T> void merge(List<R> listA, List<T> listB, Merged.Compare<R,T> compare, Merged<R, T> merge){
        merge.apply(listA,listB,compare);
    }
}
