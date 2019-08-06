package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;
import java.util.List;

public class SlbMoreBooksBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String classifyName;
    private List<SlbMoreBooksBean2> list;

    public SlbMoreBooksBean1() {
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<SlbMoreBooksBean2> getList() {
        return list;
    }

    public void setList(List<SlbMoreBooksBean2> list) {
        this.list = list;
    }
}
