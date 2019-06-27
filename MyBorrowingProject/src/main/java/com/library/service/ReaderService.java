package com.library.service;

import com.library.entity.Reader;

/**
 * Created by 王彦鑫 on 2018/10/15
 */
public interface ReaderService extends BaseService<Reader> {

    Reader queryByUsername(String username);
    String loginFlag(String username,String password);
}
