package com.library.controller;

import com.library.entity.Book;
import com.library.entity.BookCart;
import com.library.entity.BookCartItem;
import com.library.entity.Reader;
import com.library.service.BookCartItemService;
import com.library.service.BookCartService;
import com.library.service.BookService;
import com.library.service.ReaderService;
import com.library.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by 王彦鑫 on 2018/9/16
 */
@Controller
@RequestMapping(value = "/cart")
public class BookCartController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BookCartService bookCartService;

    @Autowired
    private BookCartItemService bookCartItemService;

    /**
     * 添加图书到购物车
     * @param bookIds
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cart",method = RequestMethod.POST)
    public String addToBookCart(@RequestBody Integer[] bookIds, HttpServletRequest request,HttpServletResponse response){
        List<Book> bookList= bookService.getByIds(bookIds);
        //获取当前用户
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        //获取当前用户的BookCart
        BookCart bookCart = CartUtils.getBookCart(request,reader);
        bookCartService.addToBookCart(bookList,reader,bookCart,request,response);
        return null;
    }

    /**
     * 跳转到购物车页面
     * @return
     */
    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String getCart(Map<String,Object> map){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        if(reader != null){
            map.put("reader",reader);
            return "projectPages/BookCartDisplay";
        }else {
            return "redirect:/";
        }
    }

    /**
     * jqGrid获取购物车数据
     * @param params
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/getcart",method = RequestMethod.GET)
    @ResponseBody
    public R getCart(@RequestParam Map<String, Object> params,HttpServletRequest request){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        BookCart bookCart = CartUtils.getBookCart(request,reader);
        //System.out.println(bookCart.getReaderName());
        //1.添加购物车BookCart
        //上边从cookie获取的bookCart没有id，先根据username查询出id，id存在则更新，不存在则新增购物车
        Integer id = null;
        try { id = bookCartService.getByUsername(bookCart.getReaderUsername()).getId();
        }catch(Exception ignored){

        }
        if(id == null){
            bookCartService.save(bookCart);
        }else {
            bookCart.setId(id);
            bookCartService.update(bookCart);
        }
        //2.添加具体购物明细到数据库
        //要从cookie中分页获取map
        List<BookCartItem> bookCartItemsList = MapUtils.transMapToList(bookCart.getMap());
        bookCartItemService.saveOrUpdate(bookCartItemsList,bookCart);

        Query query = new Query(params);
        List paggingList = MapUtils.paggingList(bookCartItemsList,query);
        PageUtils pageUtil = new PageUtils(paggingList, bookCartItemsList.size(),query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cart",method = RequestMethod.DELETE)
    public String clearCart(HttpServletRequest request, HttpServletResponse response){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        BookCart bookCart = CartUtils.getBookCart(request,reader);
        String cookieName = reader.getUsername();
        bookCartService.clearBookCart(bookCart,cookieName+"cart",request,response);
        //++++清空数据库
        return "redirect:/cart/cart";
    }

    /**
     * 删除图书
     * @param bookIds
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deletecart",method = RequestMethod.POST)
    public String deleteCartItem(@RequestBody Integer[] bookIds,HttpServletRequest request, HttpServletResponse response){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        BookCart bookCart = CartUtils.getBookCart(request,reader);
        String cookieName = reader.getUsername()+"cart";
        bookCartService.deleteFromBookCart(bookIds,bookCart,cookieName,request,response);
        System.out.println(Arrays.toString(bookIds));
        return "redirect:/cart/cart";
    }

    /**
     * 更新图书数量
     * @param bookId
     * @param count
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cart/{bookId}",method = RequestMethod.POST)
    public void updateCartItem(@PathVariable("bookId") Integer bookId,@RequestBody Integer count,
                                 HttpServletRequest request, HttpServletResponse response){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        BookCart bookCart = CartUtils.getBookCart(request,reader);
        String cookieName = reader.getUsername()+"cart";
        bookCartService.updateBookCart(bookId,bookCart,count,cookieName,request,response);
    }

}
