package com.library.dao;

import com.library.entity.BookCartItem;

import java.util.List;

/**
 * Created by 王彦鑫 on 2018/11/12
 */
public interface BookCartItemDao extends BaseDao<BookCartItem> {

    /**
     *从数据库中找出该BookCartId对应的所有BookCartItem
     * @param bookCartId
     * @return
     */
    List<BookCartItem> findAllByBookCartId(Integer bookCartId);

    List<BookCartItem> getByIds(Integer [] ids,String readerUsername);
}
