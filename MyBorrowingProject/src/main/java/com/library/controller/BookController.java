package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import com.library.util.PageUtils;
import com.library.util.Query;
import com.library.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/9/13
 */

@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 新增书本
     * @param book
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String save(Book book){
        bookService.save(book);
        return "redirect:/book/books";
    }

    /**
     * 显示添加的页面
     */
    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String toAddBook(Map<String, Object> map) {
        map.put("book", new Book());
        return "projectPages/addOrUpdateBook";
    }

    /**
     * 删除书本
     * @param id
     * @return
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        bookService.delete(id);
        System.out.println("删除成功" + id);
        return "redirect:/book/books";
    }

    /**
     * 显示更新页面
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id, Map<String, Object> map) {
        Book book = bookService.getById(id);
        map.put("book", book);
        return "projectPages/addOrUpdateBook";
    }

    /**
     * 更新书本信息
     * @param book
     * @return
     */
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public String update(Book book) {
        bookService.update(book);
        return "redirect:/book/books";
    }

    /**
     * 查看全部书籍
     * @param map
     * @return
     */
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String findAll(Map<String, Object> map) {
        List<Book> books = bookService.findAll();
        map.put("books", books);
        return "projectPages/BookDisplay";
    }

    @RequestMapping(value = "/jqbooks", method = RequestMethod.GET)
    @ResponseBody
    public R booklist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        //分页查询
        List<Book> bookList = bookService.queryList(query);
        int total = bookService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(bookList, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

}
