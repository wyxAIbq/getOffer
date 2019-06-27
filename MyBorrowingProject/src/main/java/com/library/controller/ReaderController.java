package com.library.controller;

import com.library.entity.Reader;
import com.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/10/15
 */
@Controller
@RequestMapping(value = "/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;
    /**
     * 新增读者
     * @param reader
     * @return
     */
    @RequestMapping(value = "/reader", method = RequestMethod.POST)
    public String save(Reader reader){
        readerService.save(reader);
        return "redirect:/reader/readers";
    }

    /**
     * 显示添加的页面
     */
    @RequestMapping(value = "/reader", method = RequestMethod.GET)
    public String toAddReader(Map<String, Object> map) {
        map.put("reader", new Reader());
        return "projectPages/addOrUpdateReader";
    }

    /**
     * 删除读者
     * @param id
     * @return
     */
    @RequestMapping(value = "/reader/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        readerService.delete(id);
        System.out.println("删除成功" + id);
        return "redirect:/reader/readers";
    }

    /**
     * 显示更新页面
     */
    @RequestMapping(value = "/reader/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id, Map<String, Object> map) {
        Reader reader = readerService.getById(id);
        map.put("reader", reader);
        return "projectPages/addOrUpdateReader";
    }

    /**
     * 更新读者信息
     * @param book
     * @return
     */
    @RequestMapping(value = "/reader", method = RequestMethod.PUT)
    public String update(Reader reader) {
        readerService.update(reader);
        return "redirect:/reader/readers";
    }

    /**
     * 查看全部读者
     * @param map
     * @return
     */
    @RequestMapping(value = "/readers", method = RequestMethod.GET)
    public String findAll(Map<String, Object> map) {
        List<Reader> readers = readerService.findAll();
        map.put("readers", readers);
        return "projectPages/ReaderDisplay";
    }
}
