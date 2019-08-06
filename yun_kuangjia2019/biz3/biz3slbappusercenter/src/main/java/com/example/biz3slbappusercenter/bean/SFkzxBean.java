package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SFkzxBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String tel;
    private String wechat;
    private String qq;

    public SFkzxBean() {
    }

    public SFkzxBean(String tel, String wechat, String qq) {
        this.tel = tel;
        this.wechat = wechat;
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
