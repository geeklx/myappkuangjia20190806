package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbHotModel22 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int bookclassifyCode;

    public SlbHotModel22() {
    }

    public SlbHotModel22(int bookclassifyCode) {
        this.bookclassifyCode = bookclassifyCode;
    }

    public int getBookclassifyCode() {
        return bookclassifyCode;
    }

    public void setBookclassifyCode(int bookclassifyCode) {
        this.bookclassifyCode = bookclassifyCode;
    }
}
