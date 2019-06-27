package com.library.util;

import com.library.entity.BookCartItem;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 王彦鑫 on 2018/10/23
 */
public final class StringUtiles {

    /**
     * 将cookie中获取的StringCart转换为HashMap<Integer,BookCartItem>
     * @param request
     * @return
     */
    public static HashMap<Integer,BookCartItem> transStrToMap(HttpServletRequest request,String cookieName){
        HashMap map = new HashMap();
        String[] strs;
        String c;
        JSONObject jsonObject;
        BookCartItem bookCartItem;
        String bookStrCart = CookieUtils.getCookieValue(request,cookieName,true);
        if(bookStrCart != null){
            strs = bookStrCart.split("_");
            for (String str:strs) {
                if(!("".equals(str))){
                    c = str.substring(str.indexOf("{"), str.lastIndexOf("}")+1);
                    c=c.replace("=", ":");
                    c=c.replace(",", ",");
                    jsonObject=JSONObject.fromObject(c);
                    bookCartItem = (BookCartItem) JSONObject.toBean(jsonObject, BookCartItem.class);

                    map.put(bookCartItem.getBookId(),bookCartItem);
                }
            }
            return map;
        }else {
            return new HashMap<>();
        }
    }

    public static String transMapToStr(HashMap map){
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
        {
            entry = (java.util.Map.Entry)iterator.next();
            sb.append(entry.getKey().toString()).append( "+" ).append(null==entry.getValue()?"":
                    entry.getValue().toString()).append (iterator.hasNext() ? "_" : "");
        }
        return sb.toString();
    }

}
