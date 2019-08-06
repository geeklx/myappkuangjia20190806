package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SHuibenDetailBean3 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String desc;
    private String imgUrl;
    private String urlForFriend;// 小程序
    private String urlForMoments;// url

    public SHuibenDetailBean3() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrlForFriend() {
        return urlForFriend;
    }

    public void setUrlForFriend(String urlForFriend) {
        this.urlForFriend = urlForFriend;
    }

    public String getUrlForMoments() {
        return urlForMoments;
    }

    public void setUrlForMoments(String urlForMoments) {
        this.urlForMoments = urlForMoments;
    }
}
