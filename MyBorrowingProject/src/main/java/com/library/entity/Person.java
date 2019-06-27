package com.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 个人信息实体类
 * 注解方式配置对应数据表
 * @author 王
 */
@Entity //指明该类是一个实体bean
@Table(name = "person")  //指定了实体所要映射的数据库表
public class Person {

	/**
	 * id
	 */
	private Integer id;
	private String name;// 姓名
	private String idCard;// 身份证号
	private String phone;// 手机号
	private String address;// 地址

	//无参的
	public Person() {
		super();
	}
    //带参的构造器
	public Person(String name, String idCard, String phone, String address) {
		super();
		this.name = name;
		this.idCard = idCard;
		this.phone = phone;
		this.address = address;
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

	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "id_card", nullable = false, length = 32)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "phone", nullable = false, length = 32)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	@Column(name = "address", nullable = false, length = 32)
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", idCard=" + idCard + ", phone=" + phone + ", address="
				+ address + "]";
	}

}
