package ai.remi.comm.util.merge;


import java.util.List;

/**
 *
 * @param <R> 返回值的对象类型
 * @param <T> 需要被合并的类型
 */
@FunctionalInterface
public interface Merged<R,T> {

    void merge(R r, T t);

    default void apply(List<R> listA, List<T> listB,Compare<R,T> compare){
        listA.forEach(a-> listB.forEach(b -> {
            if(compare.compare(a,b)){
                merge(a,b);
            }
        }));
    }
    @FunctionalInterface
    interface Compare<R,T>{
        boolean compare(R r,T t);
    }
}
