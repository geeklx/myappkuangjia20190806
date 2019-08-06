package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SLBBannerBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SLBBannerBean1> bannerList;

    public SLBBannerBean() {
    }

    public List<SLBBannerBean1> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<SLBBannerBean1> bannerList) {
        this.bannerList = bannerList;
    }
}
