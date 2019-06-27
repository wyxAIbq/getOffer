package com.library.service.impl;

import com.library.dao.BookCartDao;
import com.library.dao.BookDao;
import com.library.dao.ReaderDao;
import com.library.entity.Book;
import com.library.entity.BookCart;
import com.library.entity.BookCartItem;
import com.library.entity.Reader;
import com.library.service.BookCartService;
import com.library.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 王彦鑫 on 2018/10/22
 */
@Service
public class BookCartServiceImpl extends BaseServiceImpl<BookCart> implements BookCartService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private ReaderDao readerDao;
    @Autowired
    private BookCartDao bookCartDao;

    /**
     * 添加到购物车
     * 添加到cookie
     */
    @Override
    public void addToBookCart(List<Book> bookList, Reader reader, BookCart bookCart, HttpServletRequest request,
                              HttpServletResponse response) {
        bookCart.add(bookList,reader);
        //如果是重复添加的图书，这里加入cookie要判断
        CookieUtils.addBookToCookie(request,response,reader.getUsername()+"cart",true,bookCart);
    }

    @Override
    public void deleteFromBookCart(Integer[] bookIds, BookCart bookCart,String cookieName, HttpServletRequest request, HttpServletResponse response) {
        if(bookCart == null){
            System.out.println("查找不到购物车");
        }else {
            HashMap map = bookCart.getMap();
            for(Integer bookId : bookIds){
                map.remove(bookId);
            }
            //将删了某些图书的map添加到cookie
            CookieUtils.addBookToCookie(request,response,cookieName,true,bookCart);
            //++++双出数据库中对应图书
        }
    }

    @Override
    public void clearBookCart(BookCart bookCart,String cookieName, HttpServletRequest request, HttpServletResponse response) {
        if(bookCart == null){
            System.out.println("查找不到购物车");
        }else {
            bookCart.getMap().clear();
            CookieUtils.deleteCookie(request,response,cookieName);
        }
    }

    @Override
    public void updateBookCart(Integer bookId, BookCart bookCart, int count,String cookieName, HttpServletRequest request, HttpServletResponse response) {
        if(bookCart == null){
            System.out.println("查找不到购物车");
        }else {
            BookCartItem bookListItem = bookCart.getMap().get(bookId);
            bookListItem.setCount(count);
            CookieUtils.addBookToCookie(request,response,cookieName,true,bookCart);
            //++++更新数据库
        }
    }

    @Override
    public BookCart getByUsername(String username) {
        return bookCartDao.getByUsername(username);
    }
}
