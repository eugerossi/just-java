package com.eugerossi.collections.maps.entities;

import java.util.Objects;

public class MutableKey {
    private String name;

    // standard constructor, getter and setter
    public MutableKey(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutableKey that = (MutableKey) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
