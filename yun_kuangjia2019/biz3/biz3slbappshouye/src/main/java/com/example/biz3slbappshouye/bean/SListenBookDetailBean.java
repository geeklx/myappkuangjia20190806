package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SListenBookDetailBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SHuibenDetailBean1 bookItem;
    private SHuibenDetailBean3 shareInfo;
    private List<SListenBookDetailBean2> list;

    public SListenBookDetailBean() {
    }

    public SHuibenDetailBean1 getBookItem() {
        return bookItem;
    }

    public void setBookItem(SHuibenDetailBean1 bookItem) {
        this.bookItem = bookItem;
    }

    public SHuibenDetailBean3 getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(SHuibenDetailBean3 shareInfo) {
        this.shareInfo = shareInfo;
    }

    public List<SListenBookDetailBean2> getList() {
        return list;
    }

    public void setList(List<SListenBookDetailBean2> list) {
        this.list = list;
    }
}
