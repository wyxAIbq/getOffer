package com.library.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 王彦鑫 on 2018/10/22
 * 一个用户一张list表
 */
@Entity
@Table(name = "book_cart")
public class BookCart implements Serializable {

    private static final long serialVersionUID = 156884643940100899L;
    private Integer id;
    private Integer readerId;
    private String readerUsername;
    private String readerName;
    private Integer count;
    private Date createDate;
    private HashMap<Integer, BookCartItem> map = new HashMap<>();

    public BookCart() {
    }

    public BookCart(Reader reader, Date createDate) {
        this.readerId = reader.getId();
        this.readerUsername = reader.getUsername();
        this.readerName = reader.getName();
        this.createDate = createDate;
    }

    /**

     * 向list中添加book和reader信息
     * @param book
     * @param reader
     */
    public void add(List<Book> bookList, Reader reader) {
        for(Book book : bookList){
            BookCartItem item = map.get(book.getId());
            if(item == null) {
                //若不存在相同类型书籍，则创建新的购物项
                item = new BookCartItem(book,reader,1);
                // 将购物项添加到购物车中
                map.put(book.getId(), item);
            } else {
                // 若存在相同类型书籍，则将购物项个数加一
                item.setCount(item.getCount()+1);
            }
        }
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

    @Column(name = "reader_id", nullable = false, length = 32)
    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

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
    public Integer getCount() {
        Integer count = 0;
        for(HashMap.Entry<Integer, BookCartItem> me : map.entrySet()) {
            BookCartItem item = me.getValue();
            count += item.getCount();
        }
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Column(name = "create_date", nullable = false, length = 32)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Transient
    public HashMap<Integer, BookCartItem> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, BookCartItem> map) {
        this.map = map;
    }
}
