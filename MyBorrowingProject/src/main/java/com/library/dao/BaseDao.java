package com.library.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/9/12
 * @author 王
 */
public interface BaseDao<T> {

    /**
     * 保存实体
     * @param entity
     */
    void save(T entity);

    /**
     * 删除实体
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新实体
     * @param entity
     */
    void update(T entity);

    /**
     * 按id查询
     * @param id
     * @return
     */
    T getById(Integer id);


    /**
     * 按id查询
     * @param ids
     * @return
     */
    List<T> getByIds(Integer [] ids);

    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    List<T> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

}
