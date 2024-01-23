package com.eugerossi.collections.maps.entities;

public class Product implements Comparable<Product> {
    private Integer code;
    private String name;

    public Product(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Product o) {
        return (int) (this.code - o.getCode());
    }
}
