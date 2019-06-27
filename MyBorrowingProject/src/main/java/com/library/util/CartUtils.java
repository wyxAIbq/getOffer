package com.library.util;

import com.library.entity.BookCart;
import com.library.entity.Reader;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by 王彦鑫 on 2018/10/23
 */
public final class CartUtils {

    /**
     * 获取BookCart
     **/
    public static BookCart getBookCart(HttpServletRequest request, Reader reader){
        //从数据库中获取BookCart，也装成map，id+BookCartItem形式
        HashMap map = StringUtiles.transStrToMap(request,reader.getUsername()+"cart");
        BookCart bookCart = new BookCart(reader,new Date());
        bookCart.setMap(map);
        bookCart.setCount(bookCart.getCount());
        if("".equals(CookieUtils.getCookieValue(request,reader.getUsername()+"cart",true))){
            return new BookCart();
        }else {
            return bookCart;
        }
    }
}
