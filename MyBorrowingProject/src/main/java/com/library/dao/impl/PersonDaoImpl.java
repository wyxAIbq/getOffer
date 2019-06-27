package com.library.dao.impl;

import com.library.dao.PersonDao;
import com.library.entity.Person;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author 王
 * @date 2018-9-11
 */
@Repository //用于标注数据访问组件，即DAO组件
@Transactional
public class PersonDaoImpl extends BaseDaoImpl<Person> implements PersonDao {

    @Override
    public List<Person> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

}