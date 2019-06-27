package com.library.util;

import com.library.entity.BookCartItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/10/30
 */
public final class MapUtils {

    /**
     * 将map转为L
     * @param map
     * @return
     */
    public static List<BookCartItem> transMapToList(Map map){
        List list;
        Collection collection = map.values();
        if (collection instanceof List){
            list = (List)collection;
        }
        else{
            list = new ArrayList(collection);
        }
        return list;
    }

    /**
     * 将List分页显示
     * @param list
     * @param map
     * @return
     */
    public static List paggingList(List list,Map<String, Object> map){
        List newList;
        int total = list.size();
        int offset = (int) map.get("offset");
        int limit = (int)map.get("limit");
        newList=list.subList(offset, offset+limit > total?total:(offset+limit));
        return newList;
    }


}
