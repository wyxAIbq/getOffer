package com.library.dao.impl;

import com.library.dao.BookCartDao;
import com.library.entity.BookCart;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/11/12
 */
@Repository //用于标注数据访问组件，即DAO组件
@Transactional
public class BookCartDaoImpl extends BaseDaoImpl<BookCart> implements BookCartDao {
    @Override
    public List<BookCart> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public BookCart getByUsername(String username) {
        String sql = "FROM BookCart where readerUsername = '"+username+"'";
        return (BookCart) getSession().createQuery(sql).uniqueResult();
    }
}
