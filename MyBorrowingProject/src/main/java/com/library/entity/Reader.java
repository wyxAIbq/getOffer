package com.library.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 王彦鑫 on 2018/9/17
 */

@Entity
@Table(name = "reader")
public class Reader {

    private Integer id;

    private String username;

    private String password;

    private String name;

    private String academy;

    private String specialty;

    private String gender;

    public Reader(String username, String password, String name, String academy, String specialty, String gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.academy = academy;
        this.specialty = specialty;
        this.gender = gender;
    }

    public Reader() {

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

    @Column(name = "username", nullable = false, length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "name", nullable = false, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "academy", nullable = false, length = 32)
    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    @Column(name = "specialty", nullable = false, length = 32)
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Column(name = "gender", nullable = false, length = 32)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", academy='" + academy + '\'' +
                ", specialty='" + specialty + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
