package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbHotBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String bookName;
    private String bookSmallPicture;
    private String bookBigPicture;
    private String classifyName;
    private String bookClassifyCode;
    private boolean isRetweet;

    public SlbHotBean2() {
    }

    public SlbHotBean2(int id, String bookName, String bookSmallPicture, String bookBigPicture, String classifyName, String bookClassifyCode, boolean isRetweet) {
        this.id = id;
        this.bookName = bookName;
        this.bookSmallPicture = bookSmallPicture;
        this.bookBigPicture = bookBigPicture;
        this.classifyName = classifyName;
        this.bookClassifyCode = bookClassifyCode;
        this.isRetweet = isRetweet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookSmallPicture() {
        return bookSmallPicture;
    }

    public void setBookSmallPicture(String bookSmallPicture) {
        this.bookSmallPicture = bookSmallPicture;
    }

    public String getBookBigPicture() {
        return bookBigPicture;
    }

    public void setBookBigPicture(String bookBigPicture) {
        this.bookBigPicture = bookBigPicture;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getBookClassifyCode() {
        return bookClassifyCode;
    }

    public void setBookClassifyCode(String bookClassifyCode) {
        this.bookClassifyCode = bookClassifyCode;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
