package com.library.service.impl;

import com.library.dao.BookCartItemDao;
import com.library.entity.BookCart;
import com.library.entity.BookCartItem;
import com.library.service.BookCartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 王彦鑫 on 2018/11/12
 */
@Service
public class BookCartItemServiceImpl extends BaseServiceImpl<BookCartItem> implements BookCartItemService {

    @Autowired
    private BookCartItemDao bookCartItemDao;

    @Override
    public void saveOrUpdate(List<BookCartItem> bookCartItemList, BookCart bookCart) {
        //1.找出购物车明细表中对应购物车ID的明细项
        List<BookCartItem> bookCartItemList1 = bookCartItemDao.findAllByBookCartId(bookCart.getId());
        //逻辑比较复杂,使用一个map集合,先把数据库中取出的明细表key存入BookCartItem,value存入1,然后和改变后的明细比较,修改value值
        Map<BookCartItem,Integer> map = new HashMap<BookCartItem,Integer>(bookCartItemList.size()+bookCartItemList1.size());
        for(BookCartItem bookCartItem1 :bookCartItemList1){
            map.put(bookCartItem1,1);
        }
        for(BookCartItem bookCartItem :bookCartItemList){
            Set<BookCartItem> set = map.keySet();
            //flag用来找出哪些是新增的图书
            boolean flag = true;
            for(BookCartItem bookSetItem : set){
                if (bookSetItem.getBookId().equals(bookCartItem.getBookId())) {
                    if (bookSetItem.getCount().equals(bookCartItem.getCount())) {
                        map.put(bookSetItem, 2);
                    } else {
                        bookCartItem.setBookCartId(bookCart.getId());
                        map.put(bookCartItem, 3);
                    }
                    flag = false;
                    break;
                }
            }
            if(flag){
                bookCartItem.setBookCartId(bookCart.getId());
                map.put(bookCartItem,3);
            }
        }
        for (Map.Entry<BookCartItem, Integer> entry : map.entrySet()) {
            //1是删除的图书和改变了数量之前的旧书,2是没有任何改变的图书，不做处理,3是新增图书和改变了数量之后的新书
            if(entry.getValue() == 1){
                BookCartItem bookCartItem = entry.getKey();
                bookCartItemDao.delete(bookCartItem.getId());
            }else if(entry.getValue() == 3){
                BookCartItem bookCartItem = entry.getKey();
                bookCartItemDao.save(bookCartItem);
            }
        }
        }

    @Override
    public List<BookCartItem> getByIds(Integer[] ids, String readerUsername) {
        return bookCartItemDao.getByIds(ids,readerUsername);
    }
}
