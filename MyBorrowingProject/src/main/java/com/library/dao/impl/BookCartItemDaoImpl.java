package com.library.dao.impl;

import com.library.dao.BookCartItemDao;
import com.library.entity.BookCartItem;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/11/12
 */
@Repository //用于标注数据访问组件，即DAO组件
@Transactional
public class BookCartItemDaoImpl extends BaseDaoImpl<BookCartItem> implements BookCartItemDao {
    @Override
    public List<BookCartItem> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<BookCartItem> findAllByBookCartId(Integer bookCartId) {
        String sql = "FROM BookCartItem where bookCartId = "+bookCartId;
        return getSession().createQuery(sql).list();
    }

    @Override
    public List<BookCartItem> getByIds(Integer[] ids, String readerUsername) {
        return getSession().createQuery(
                "FROM BookCartItem WHERE bookId IN (:ids) AND readerUsername= '"+readerUsername+"'")
                .setParameterList("ids",ids)
                .list();
    }
}
