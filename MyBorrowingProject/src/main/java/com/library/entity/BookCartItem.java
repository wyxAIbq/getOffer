package com.library.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 王彦鑫 on 2018/9/16
 */

@Entity
@Table(name = "cart_details")
public class BookCartItem implements Serializable {

    private static final long serialVersionUID = 156884643940100899L;
    private Integer id;
    private Integer bookCartId;
    private Integer bookId;
    private String bookTitle;
    private String bookAuthor;
    private Integer readerId;
    private String readerUsername;
    private String readerName;
    private Integer count;

    /**
     *	无参的
     */
    public BookCartItem() {
        super();
    }

    public BookCartItem(Book book, Reader reader, Integer count) {
        this.bookId = book.getId();
        this.bookTitle = book.getTitle();
        this.bookAuthor = book.getAuthor();
        this.readerId = reader.getId();
        this.readerUsername = reader.getUsername();
        this.readerName = reader.getName();
        this.count = count;
    }

    @Id
    @Column(name = "id", nullable = false, length=32 ,unique = true)  //Column注解将属性映射到列
    @GenericGenerator(name = "generator", strategy = "native")          //Hibernate的Generator生成id,strategy是生成策略
    @GeneratedValue(generator = "generator")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "bookcart_id", nullable = false, length = 32)
    public Integer getBookCartId() {
        return bookCartId;
    }

    public void setBookCartId(Integer bookCartId) {
        this.bookCartId = bookCartId;
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

    @Column(name = "reader_id", nullable = false, length = 32)
    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId){this.readerId = readerId;}

    @Column(name = "reader_username", nullable = false, length = 32)
    public String getReaderUsername() {
        return readerUsername;
    }

    public void setReaderUsername(String readerUsername) {
        this.readerUsername = readerUsername;
    }

    @Column(name = "reader_name", nullable = false, length = 32)
    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    @Column(name = "count", nullable = false, length = 32)
    public Integer getCount() { return count; }

    public void setCount(Integer count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "BookCartItem{" +
                "bookId='" + bookId + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", readerId='" + readerId + '\'' +
                ", readerUsername='" + readerUsername + '\'' +
                ", readerName='" + readerName + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
