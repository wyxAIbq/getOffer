package com.library.dao.impl;

import com.library.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.sql.Statement;
import java.util.List;

/**
 * Created by 王彦鑫 on 2018/9/12
 * @author 王
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    @Resource
    private SessionFactory sessionFactory;
    private Class<T> clazz;

    public BaseDaoImpl(){
        //使用反射技术得到T的真实类型
        //获取当前new的对象的泛型的父类
        ParameterizedType pt =(ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个类型参数的真实类型，只有一个泛型参数，所以写0
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
        System.out.println("clazz--->"+clazz);
    }

    /**
     * 获取当前可用的Session对象,用protected修饰方便子类得到session
     * @return
     */
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        //不需要自己关事务了，spring框架已经帮我们做了，我们用它的事务管理
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(Integer id) {
        Object obj = getById(id);
        if(obj!=null){
            getSession().delete(obj);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getSession().createQuery(
                "FROM "+clazz.getSimpleName())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(Integer id) {
        return (T)getSession().get(clazz, id);
    }


    @Override
    @SuppressWarnings("unchecked")
    //sql语句记得空格！！！！！
    public List<T> getByIds(Integer[] ids) {
        return getSession().createQuery(
                "FROM " +clazz.getSimpleName()+" WHERE id IN (:ids)")
                .setParameterList("ids",ids)
                .list();
    }


}
