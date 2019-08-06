package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbMoreBooksBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String bookName;
    private String bookSmallPicture;
    private String bookBigPicture;
    private String classifyName;
    private String bookClassifyId;

    public SlbMoreBooksBean2() {
    }

    public SlbMoreBooksBean2(int id, String bookName, String bookSmallPicture, String bookBigPicture, String classifyName, String bookClassifyId) {
        this.id = id;
        this.bookName = bookName;
        this.bookSmallPicture = bookSmallPicture;
        this.bookBigPicture = bookBigPicture;
        this.classifyName = classifyName;
        this.bookClassifyId = bookClassifyId;
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

    public String getBookClassifyId() {
        return bookClassifyId;
    }

    public void setBookClassifyId(String bookClassifyId) {
        this.bookClassifyId = bookClassifyId;
    }
}
