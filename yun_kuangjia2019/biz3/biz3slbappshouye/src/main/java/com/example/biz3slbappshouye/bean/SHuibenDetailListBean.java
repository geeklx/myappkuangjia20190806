package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SHuibenDetailListBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SListCommBean1> bookViewList;
    private String screenImg;

    public SHuibenDetailListBean() {
    }

    public List<SListCommBean1> getBookViewList() {
        return bookViewList;
    }

    public void setBookViewList(List<SListCommBean1> bookViewList) {
        this.bookViewList = bookViewList;
    }

    public String getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(String screenImg) {
        this.screenImg = screenImg;
    }
}
