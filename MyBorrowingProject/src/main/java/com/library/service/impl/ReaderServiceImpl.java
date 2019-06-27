package com.library.service.impl;

import com.library.dao.ReaderDao;
import com.library.entity.Reader;
import com.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王彦鑫 on 2018/10/15
 */
@Service
public class ReaderServiceImpl extends BaseServiceImpl<Reader> implements ReaderService {

    @Autowired
    private ReaderDao readerDao;

    @Override
    public Reader queryByUsername(String username) {
        return readerDao.queryByUsername(username);
    }

    public String loginFlag(String username, String password){
        String flag = "";
        Reader reader = queryByUsername(username);
        if (username == null) {
            flag = "usernameIsNull";
        }
        if (!password.equals(reader.getPassword())) {
            flag = "passwordIsWrong";
        }
        return flag;
    }
}
