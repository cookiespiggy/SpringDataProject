package com.imooc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 雇员实体类:
 * JPA先开发实体类 ===> 自动生成数据表,和原来的有区别,
 * 原来是先把数据库和表建立好
 * 注意,原来的sql语句需要我们自己维护
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 20)
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
