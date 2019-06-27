package com.library.dao;

import com.library.entity.Reader;

/**
 * Created by 王彦鑫 on 2018/10/15
 */
public interface ReaderDao extends BaseDao<Reader>{

    Reader queryByUsername(String username);
}
