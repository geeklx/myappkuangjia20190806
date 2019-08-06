package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbMoreBooksCategoryBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String classifyCode;
    private String classifyName;
    private String classifyPicture;

    public SlbMoreBooksCategoryBean1() {
    }

    public SlbMoreBooksCategoryBean1(String classifyCode, String classifyName, String classifyPicture) {
        this.classifyCode = classifyCode;
        this.classifyName = classifyName;
        this.classifyPicture = classifyPicture;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyPicture() {
        return classifyPicture;
    }

    public void setClassifyPicture(String classifyPicture) {
        this.classifyPicture = classifyPicture;
    }
}
