package com.library.service;

import com.library.entity.Book;
import com.library.entity.BookCart;
import com.library.entity.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 王彦鑫 on 2018/10/22
 */
public interface BookCartService extends BaseService<BookCart>{

    /**
     * 操作cookie
     * 添加图书到购物车
     * @param bookList
     * @param reader
     * @param bookCart
     * @param request
     * @param response
     */
    void addToBookCart(List<Book> bookList, Reader reader, BookCart bookCart, HttpServletRequest request, HttpServletResponse response);

    /**
     * 操作cookie，从购物车中删除图书
     * @param bookIds
     * @param bookCart
     * @param cookieName
     * @param request
     * @param response
     */
    void deleteFromBookCart(Integer[] bookIds,BookCart bookCart,String cookieName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 操作cookie，清空购物车
     * @param bookCart
     * @param cookieName
     * @param request
     * @param response
     */
    void clearBookCart(BookCart bookCart,String cookieName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 操作cookie，更新cookie中购物车
     * @param bookId
     * @param bookCart
     * @param count
     * @param cookieName
     * @param request
     * @param response
     */
    void updateBookCart(Integer bookId,BookCart bookCart,int count,String cookieName, HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过读者姓名查询购物车
     * @param username
     * @return
     */
    BookCart getByUsername(String username);
}
