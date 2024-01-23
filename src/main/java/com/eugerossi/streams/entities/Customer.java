package com.eugerossi.streams.entities;

public class Customer {

    private String name;

    private Integer age;

    public Customer(String name, Integer age) {
        this.name = name;
        this.age =  age;
    }

    public Integer getAge() {
        return age;
    }
}
