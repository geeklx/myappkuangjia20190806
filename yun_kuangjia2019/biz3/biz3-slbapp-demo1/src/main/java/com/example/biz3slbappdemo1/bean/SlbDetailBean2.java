package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbDetailBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String bookUnique;
    private int detailPagination;
    private String detailPicUrl;
    private String detailMp3Url;

    public SlbDetailBean2() {
    }

    public SlbDetailBean2(int id, String bookUnique, int detailPagination, String detailPicUrl, String detailMp3Url) {
        this.id = id;
        this.bookUnique = bookUnique;
        this.detailPagination = detailPagination;
        this.detailPicUrl = detailPicUrl;
        this.detailMp3Url = detailMp3Url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookUnique() {
        return bookUnique;
    }

    public void setBookUnique(String bookUnique) {
        this.bookUnique = bookUnique;
    }

    public int getDetailPagination() {
        return detailPagination;
    }

    public void setDetailPagination(int detailPagination) {
        this.detailPagination = detailPagination;
    }

    public String getDetailPicUrl() {
        return detailPicUrl;
    }

    public void setDetailPicUrl(String detailPicUrl) {
        this.detailPicUrl = detailPicUrl;
    }

    public String getDetailMp3Url() {
        return detailMp3Url;
    }

    public void setDetailMp3Url(String detailMp3Url) {
        this.detailMp3Url = detailMp3Url;
    }
}
