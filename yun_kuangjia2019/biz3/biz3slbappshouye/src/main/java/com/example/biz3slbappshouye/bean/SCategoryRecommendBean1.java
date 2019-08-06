package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SCategoryRecommendBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String classfyName;
    private List<SListCommBean1> bookViewList;

    public SCategoryRecommendBean1() {
    }

    public SCategoryRecommendBean1(String classfyName, List<SListCommBean1> bookViewList) {
        this.classfyName = classfyName;
        this.bookViewList = bookViewList;
    }

    public String getClassfyName() {
        return classfyName;
    }

    public void setClassfyName(String classfyName) {
        this.classfyName = classfyName;
    }

    public List<SListCommBean1> getBookViewList() {
        return bookViewList;
    }

    public void setBookViewList(List<SListCommBean1> bookViewList) {
        this.bookViewList = bookViewList;
    }
}
