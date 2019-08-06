package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbIndexMyBooksBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SlbIndexMyBooksBean1 booksCollection;

    public SlbIndexMyBooksBean() {
    }

    public SlbIndexMyBooksBean1 getBooksCollection() {
        return booksCollection;
    }

    public void setBooksCollection(SlbIndexMyBooksBean1 booksCollection) {
        this.booksCollection = booksCollection;
    }
}
