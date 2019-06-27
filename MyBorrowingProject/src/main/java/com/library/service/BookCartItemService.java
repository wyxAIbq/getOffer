package com.library.service;

import com.library.entity.BookCart;
import com.library.entity.BookCartItem;

import java.util.List;

/**
 * Created by 王彦鑫 on 2018/11/12
 */
public interface BookCartItemService extends BaseService<BookCartItem> {

    /**
     *新增或更新购物车明细表
     * @param bookCartItemList
     * @param bookCart
     */
    void saveOrUpdate(List<BookCartItem> bookCartItemList, BookCart bookCart);

    List<BookCartItem> getByIds(Integer [] ids, String readerUsername);
}
