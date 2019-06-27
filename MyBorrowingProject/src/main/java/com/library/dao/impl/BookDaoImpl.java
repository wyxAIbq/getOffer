package com.library.dao.impl;

import com.library.dao.BookDao;
import com.library.entity.Book;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/9/12
 */
@Repository //用于标注数据访问组件，即DAO组件
@Transactional
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

    @Override
    public List<Book> queryList(Map<String, Object> map) {
        Query query = this.getSession().createQuery("FROM Book ");
        query.setFirstResult((int) map.get("offset")); //设置查询起始位置
        query.setMaxResults((int) map.get("limit"));   //设置最大查询数目
        return query.list();
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        String hqlString = "select count(*) from Book";
        Query query = this.getSession().createQuery(hqlString);
        return ((Number)query.uniqueResult()).intValue();
    }
}
