package com.library.dao;

import com.library.entity.BookCart;

/**
 * Created by 王彦鑫 on 2018/11/12
 */
public interface BookCartDao extends BaseDao<BookCart> {

    /**
     * 通过读者姓名查询购物车
     * @param username
     * @return
     */
    BookCart getByUsername(String username);
}
