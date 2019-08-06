package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbHotModel33 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int bookcaseUserId;

    public SlbHotModel33() {
    }

    public SlbHotModel33(int bookcaseUserId) {
        this.bookcaseUserId = bookcaseUserId;
    }

    public int getBookcaseUserId() {
        return bookcaseUserId;
    }

    public void setBookcaseUserId(int bookcaseUserId) {
        this.bookcaseUserId = bookcaseUserId;
    }
}
