package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;
import java.util.List;

public class SlbHotBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SlbHotBean1 booksHot;

    public SlbHotBean() {
    }

    public SlbHotBean(SlbHotBean1 booksHot) {
        this.booksHot = booksHot;
    }

    public SlbHotBean1 getBooksHot() {
        return booksHot;
    }

    public void setBooksHot(SlbHotBean1 booksHot) {
        this.booksHot = booksHot;
    }
}
