package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbBannerModel1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookColumn;

    public SlbBannerModel1() {
    }

    public String getBookColumn() {
        return bookColumn;
    }

    public void setBookColumn(String bookColumn) {
        this.bookColumn = bookColumn;
    }
}
