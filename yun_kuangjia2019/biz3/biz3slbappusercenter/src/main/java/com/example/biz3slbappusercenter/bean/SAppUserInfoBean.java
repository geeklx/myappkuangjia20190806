package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

// "userInfo": {
//         "name": "访客",
//         "avatar": "https://edu-admin.oss-cn-hangzhou.aliyuncs.com/20181128/6136b09a2cc441d5b9b30bbec9046b7f.png",
//         "readBookCount": 15,
//         "readDuration": 1.2
//         }
public class SAppUserInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SAppUserInfoBean1 userInfo;

    public SAppUserInfoBean() {
    }

    public SAppUserInfoBean(SAppUserInfoBean1 userInfo) {
        this.userInfo = userInfo;
    }

    public SAppUserInfoBean1 getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(SAppUserInfoBean1 userInfo) {
        this.userInfo = userInfo;
    }
}
