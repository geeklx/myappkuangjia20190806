package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SLoginUserInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SLoginUserInfoBean2 pbUser;
    private String tempKey;
    private String token;
    private String medalCount;
    public SLoginUserInfoBean() {
    }

    public String getMedalCount() {
        return medalCount;
    }

    public void setMedalCount(String medalCount) {
        this.medalCount = medalCount;
    }

    public SLoginUserInfoBean2 getPbUser() {
        return pbUser;
    }

    public void setPbUser(SLoginUserInfoBean2 pbUser) {
        this.pbUser = pbUser;
    }

    public String getTempKey() {
        return tempKey;
    }

    public void setTempKey(String tempKey) {
        this.tempKey = tempKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
