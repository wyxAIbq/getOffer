package com.library.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 王彦鑫 on 2018/11/20
 */
@Entity
@Table(name = "order_books")
public class OrderBooks implements Serializable {

    private static final long serialVersionUID = 156884643940100899L;

    private Integer id;
    //订单ID
    private Integer orderId;
    //图书ID
    private Integer bookId;
    //图书标题
    private String bookTitle;
    //图书作者
    private String bookAuthor;
    //图书数量
    private Integer count;

    @Id
    @Column(name = "id",nullable = false, length=32 ,unique = true)
    @GenericGenerator(name = "generator", strategy = "native")
    @GeneratedValue(generator = "generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "order_id", nullable = false, length = 32)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column(name = "book_id", nullable = false, length = 32)
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Column(name = "book_title", nullable = false, length = 32)
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Column(name = "book_author", nullable = false, length = 32)
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Column(name = "count", nullable = false, length = 32)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
