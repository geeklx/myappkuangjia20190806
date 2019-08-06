package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbBannerModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private SlbBannerModel1 keyword;

    public SlbBannerModel() {
    }

    public SlbBannerModel1 getKeyword() {
        return keyword;
    }

    public void setKeyword(SlbBannerModel1 keyword) {
        this.keyword = keyword;
    }
}
