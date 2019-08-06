package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

// 支付宝
public class SMyMedalDetailShareBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String avatar;
    private String imgUrl;
    private String nickName;
    private String shareUrl;
    private String tips;

    public SMyMedalDetailShareBean1() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
