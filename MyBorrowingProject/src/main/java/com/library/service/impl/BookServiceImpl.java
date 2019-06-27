package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.entity.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王彦鑫 on 2018/9/13
 */

@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
    @Autowired
    private BookDao bookDao;

}
