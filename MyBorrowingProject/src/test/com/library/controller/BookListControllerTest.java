package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 王彦鑫 on 2018/10/1
 */
public class BookListControllerTest {

    @Autowired
    private BookService bookService;
    @Test
    public void addlist() {
        //Integer [] idss = {1,2,3};
        List<Book> books = bookService.findAll();
        System.out.println(books.size());
    }
}