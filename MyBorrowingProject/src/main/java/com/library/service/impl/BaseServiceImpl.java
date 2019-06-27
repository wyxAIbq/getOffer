package com.library.service.impl;

import com.library.dao.BaseDao;
import com.library.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/9/13
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void delete(Integer id) {
        baseDao.delete(id);
    }

    @Override
    public T getById(Integer id) {
        return baseDao.getById(id);
    }


    @Override
    public List<T> getByIds(Integer [] ids) {
        return baseDao.getByIds(ids);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public List findAll() {
        return baseDao.findAll();
    }

    @Override
    public List queryList(Map<String, Object> map){
        return baseDao.queryList(map);
    }
    @Override
    public int queryTotal(Map<String, Object> map){
        return baseDao.queryTotal(map);
    }

}
