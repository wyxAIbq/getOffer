package com.library.service;

import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/9/13
 */
public interface BaseService<T> {
    void save(T entity);

    void delete(Integer id);

    /**
     * 根据主键查询实体
     * @param id
     * @return
     */
    T getById(Integer id);


    List<T> getByIds(Integer [] ids);


    void update(T entity);

    List<T> findAll();
    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<T> queryList(Map<String, Object> map);
    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);
}
