package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;
import java.util.List;

public class SlbMoreBooksBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SlbMoreBooksBean1> moreBooks;

    public SlbMoreBooksBean() {
    }

    public List<SlbMoreBooksBean1> getMoreBooks() {
        return moreBooks;
    }

    public void setMoreBooks(List<SlbMoreBooksBean1> moreBooks) {
        this.moreBooks = moreBooks;
    }
}
