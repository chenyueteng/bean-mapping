/*
 * Copyright (c)  2019. binbin.hou Inc.
 * bean-mapping All rights reserved.
 */

package com.github.houbb.bean.mapping.test.model;

/**
 * @author binbin.hou
 * date 2019/2/19
 */
public class Address {

    /**
     * 国家
     */
    private String country;

    /**
     * 街道
     */
    private String street;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
