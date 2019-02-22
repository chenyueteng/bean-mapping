/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.test.base.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户视图对象
 * @author binbin.hou
 * date 2019/2/19
 * @since 0.0.1
 */
public class UserVo implements Serializable {

    private static final long serialVersionUID = -6208693483592896223L;

    private String name;

    private int age;

    private Date birthday;

    private Address address;

    private List<String> stringList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", address=" + address +
                ", stringList=" + stringList +
                '}';
    }
}
