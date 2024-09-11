package ai.remi.comm.util.page;


import java.util.ArrayList;
import java.util.List;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc PageUtils 分页工具箱
 */
public class PageUtils {
    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少数据
     * @param <T>
     * @return
     */
    public static <T> List<T> startPage(List<T> list, int pageNum, int pageSize){
        if(null == list){
            return new ArrayList<T>();
        }
        if(list.size() == 0){
            return list;
        }
        if(pageNum == 0){
            pageNum = 1;
        }
        int count = list.size(); //记录总数
        int pageCount = 0; //页数
        if(count % pageSize == 0){
            pageCount = count / pageSize;
        }else{
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; //开始索引
        int toIndex = 0; //结束索引
        if(pageNum > pageCount){
            return new ArrayList<T>();
        }

        if(pageNum != pageCount){
            fromIndex = (pageNum -1) * pageSize;
            toIndex = fromIndex + pageSize;
        }else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }
        return list.subList(fromIndex,toIndex);
    }
}
