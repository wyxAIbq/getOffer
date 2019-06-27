package com.library.controller;

import com.library.entity.BookCartItem;
import com.library.entity.Reader;
import com.library.service.BookCartItemService;
import com.library.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彦鑫 on 2018/11/15
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private BookCartItemService bookCartItemService;

    /**
     * 1.从session中获取用户名密码
     * 2.把购物车勾选的图书id获取到，查询id获取图书itemIdentifier（跳转到订单页面才确认订单）
     * 3.循环调用checkOut借阅图书，借阅失败/成功返回flag
     * @return
     */
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public String getBookIds(@RequestParam Integer[] bookIds,Map<String,Object> map){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        if(reader != null){
            map.put("bookIds", Arrays.toString(bookIds));
            return "projectPages/OrderDisplay";
        }else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/getorder",method = RequestMethod.GET)
    @ResponseBody
    public R getOrder(@RequestParam Map<String, Object> params){
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);

        String a = ((String) params.get("bookIds")).replace("\"","").replace(" ","");
        a = a.substring(a.indexOf("[")+1,a.indexOf("]"));
        String [] b = a.split(",");
        Integer [] bookIds = new Integer[b.length];
        for(int i=0;i<b.length;i++)
        {
            bookIds[i] = Integer.parseInt(b[i]);
        }

        List<BookCartItem> bookCartItemList = bookCartItemService.getByIds(bookIds,reader.getUsername());
        Query query = new Query(params);
        List paggingList = MapUtils.paggingList(bookCartItemList,query);
        PageUtils pageUtil = new PageUtils(paggingList, bookCartItemList.size(),query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping(value = "/submit")
    public String submit(@RequestBody Integer expressBox){
        System.out.println(expressBox);
        Reader reader = (Reader) SessionUtils.getSessionAttribute(Constant.CURRENT_USER);
        //List<BookCartItem> bookCartItemList = bookCartItemService.getByIds(bookIds,reader.getUsername());
        //for(BookCartItem bookCartItem :bookCartItemList){
        //    CheckOutSample.checkOut("201722180225","201722180225","30803876");
        //}
        return "redirect:/cart/cart";
    }
}
