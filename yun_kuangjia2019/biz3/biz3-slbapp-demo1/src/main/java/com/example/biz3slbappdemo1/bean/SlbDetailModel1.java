package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbDetailModel1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookUnique;

    public SlbDetailModel1() {
    }

    public String getBookUnique() {
        return bookUnique;
    }

    public void setBookUnique(String bookUnique) {
        this.bookUnique = bookUnique;
    }
}
