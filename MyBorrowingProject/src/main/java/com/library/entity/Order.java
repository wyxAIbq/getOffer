package com.library.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by 王彦鑫 on 2018/11/19
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {

    private static final long serialVersionUID = 156884643940100899L;
    private Integer id;
    //订单序列号，由下单时间+学号组成
    private String orderSn;
    private Integer readerId;
    private String readerUsername;
    private String phone;
    //下单时间
    private Date orderTime;
    //完成时间
    private Date completionTime;
    //订单状态
    //订单相关状态字段设计，采用单个字段表示全部的订单状态
    //1xx 表示订单取消和删除等状态 100订单下单成功，　101订单已取消，　102订单已删除
    //2xx 表示订单物流相关状态　200订单已配送， 201用户确认收货
    private Integer orderStatus;
    //发货状态 商品配送情况;0未发货,1已发货,2已收货
    private Integer shippingStatus;
    //工作人员编号
    private Integer staffId;
    //工作人员姓名
    private String staffName;
    //工作人员手机号
    private String staffPhone;


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

    @Column(name = "order_sn", nullable = false, length = 32)
    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
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

    @Column(name = "phone", nullable = false, length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "order_time", nullable = false, length = 32)
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Column(name = "completion_time", nullable = false, length = 32)
    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    @Column(name = "order_status", nullable = false, length = 32)
    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "shipping_status", nullable = false, length = 32)
    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    @Column(name = "staff_id", nullable = false, length = 32)
    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    @Column(name = "staff_name", nullable = false, length = 32)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "staff_phone", nullable = false, length = 32)
    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone;
    }
}
