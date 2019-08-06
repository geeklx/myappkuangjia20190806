package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SBannerBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SBannerBean1> bannerList;

    public SBannerBean() {
    }

    public List<SBannerBean1> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<SBannerBean1> bannerList) {
        this.bannerList = bannerList;
    }
}
