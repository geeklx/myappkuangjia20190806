package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SHuibenDetailBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SHuibenDetailBean1 bookItem;
    private SHuibenDetailBean3 shareInfo;
    private SHuibenDetailBean4 nextBookItem;
    private List<SHuibenDetailBean2> bookItemDetails;

    public SHuibenDetailBean() {
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

    public SHuibenDetailBean4 getNextBookItem() {
        return nextBookItem;
    }

    public void setNextBookItem(SHuibenDetailBean4 nextBookItem) {
        this.nextBookItem = nextBookItem;
    }

    public List<SHuibenDetailBean2> getBookItemDetails() {
        return bookItemDetails;
    }

    public void setBookItemDetails(List<SHuibenDetailBean2> bookItemDetails) {
        this.bookItemDetails = bookItemDetails;
    }

}
