package com.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author 王
 */
@Entity //指明该类是一个实体bean
@Table(name = "book")  //指定了实体所要映射的数据库表
public class Book {

	 private Integer id;
	 private String title;
	 private String author;

	/**
	 *	无参的
	 */
		public Book() {
			super();
		}
	/**
	 *带参的构造器
	 */
		public Book(Integer id, String title, String author) {
			super();
			this.id = id;
			this.title = title;
			this.author = author;
		}
	 
	@Id
	//Column注解将属性映射到列
	@Column(name = "id", nullable = false, length=32 ,unique = true)
	//Hibernate的Generator生成id,strategy是生成策略
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "title", nullable = false, length = 32)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "author", nullable = false, length = 32)
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	 @Override
		public String toString() {
			return "Book [id=" + id + ", title=" + title + ", author=" + author
					+ "]";
		}
	 
}
