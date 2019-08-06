package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

// "userInfo": {
//         "name": "访客",
//         "avatar": "https://edu-admin.oss-cn-hangzhou.aliyuncs.com/20181128/6136b09a2cc441d5b9b30bbec9046b7f.png",
//         "readBookCount": 15,
//         "readDuration": 1.2
//         }
public class SAppUserInfoBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String avatar;
    private String readBookCount;
    private String readDuration;

    public SAppUserInfoBean1() {
    }

    public SAppUserInfoBean1(String name, String avatar, String readBookCount, String readDuration) {
        this.name = name;
        this.avatar = avatar;
        this.readBookCount = readBookCount;
        this.readDuration = readDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getReadBookCount() {
        return readBookCount;
    }

    public void setReadBookCount(String readBookCount) {
        this.readBookCount = readBookCount;
    }

    public String getReadDuration() {
        return readDuration;
    }

    public void setReadDuration(String readDuration) {
        this.readDuration = readDuration;
    }
}
