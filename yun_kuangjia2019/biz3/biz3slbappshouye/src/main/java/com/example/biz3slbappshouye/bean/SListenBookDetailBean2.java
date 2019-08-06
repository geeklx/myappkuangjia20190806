package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

//{
//        "id":"200",
//        "createTime":"2018-11-29 13:53",
//        "updateTime":"",
//        "delFlag":0,
//        "bookItemId":"21",
//        "bookId":"13",
//        "orders":1,
//        "dltName":"01 (1)",
//        "pic":"https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/13/21/01 (1).jpg",
//        "audio":"https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/13/21/01 (1).mp3",
//        "subtitle":"",
//        "subtitleTime":"",
//        "remark":""
//        },
public class SListenBookDetailBean2 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  id;
    private String  bookId;
    private String  bookItemId;
    private String  bookCode;
    private String  itemName;
    private String  coverImgA;
    private String  coverImgB;
    private String  audioUrl;
    private boolean  collect;

    public SListenBookDetailBean2() {
    }

    public SListenBookDetailBean2(String id, String bookId, String bookItemId, String bookCode, String itemName, String coverImgA, String coverImgB, String audioUrl, boolean collect) {
        this.id = id;
        this.bookId = bookId;
        this.bookItemId = bookItemId;
        this.bookCode = bookCode;
        this.itemName = itemName;
        this.coverImgA = coverImgA;
        this.coverImgB = coverImgB;
        this.audioUrl = audioUrl;
        this.collect = collect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(String bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCoverImgA() {
        return coverImgA;
    }

    public void setCoverImgA(String coverImgA) {
        this.coverImgA = coverImgA;
    }

    public String getCoverImgB() {
        return coverImgB;
    }

    public void setCoverImgB(String coverImgB) {
        this.coverImgB = coverImgB;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }
}
