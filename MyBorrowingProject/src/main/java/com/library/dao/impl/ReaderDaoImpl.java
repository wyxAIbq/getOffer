package com.library.dao.impl;

import com.library.dao.ReaderDao;
import com.library.entity.Reader;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/10/15
 */
@Repository //用于标注数据访问组件，即DAO组件
@Transactional
public class ReaderDaoImpl extends BaseDaoImpl<Reader> implements ReaderDao {

    public Reader queryByUsername(String username){
        String sql = "FROM Reader where username = '"+username+"'";
        return (Reader) getSession().createQuery(sql).uniqueResult();
    }
    @Override
    public List<Reader> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

}
