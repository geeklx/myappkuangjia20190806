package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;


public class SPaySuccessBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String authority;
    private String avatar;
    private String endDateString;
    private String nickName;
    private String status;

    public SPaySuccessBean() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
