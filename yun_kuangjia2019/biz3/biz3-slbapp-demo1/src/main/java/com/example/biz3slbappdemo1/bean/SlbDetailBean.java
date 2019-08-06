package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbDetailBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SlbHotBean1 booksHot;

    public SlbDetailBean() {
    }

    public SlbDetailBean(SlbHotBean1 booksHot) {
        this.booksHot = booksHot;
    }

    public SlbHotBean1 getBooksHot() {
        return booksHot;
    }

    public void setBooksHot(SlbHotBean1 booksHot) {
        this.booksHot = booksHot;
    }
}
