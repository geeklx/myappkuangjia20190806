package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;
import java.util.List;

public class SlbMoreBooksCategoryBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SlbMoreBooksCategoryBean1> booksClassifyAll;

    public SlbMoreBooksCategoryBean() {
    }

    public List<SlbMoreBooksCategoryBean1> getBooksClassifyAll() {
        return booksClassifyAll;
    }

    public void setBooksClassifyAll(List<SlbMoreBooksCategoryBean1> booksClassifyAll) {
        this.booksClassifyAll = booksClassifyAll;
    }
}
