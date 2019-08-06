package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;
import java.util.List;

public class SlbBannerBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SlbBannerBean1> bannerList;

    public SlbBannerBean() {
    }

    public List<SlbBannerBean1> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<SlbBannerBean1> bannerList) {
        this.bannerList = bannerList;
    }
}
