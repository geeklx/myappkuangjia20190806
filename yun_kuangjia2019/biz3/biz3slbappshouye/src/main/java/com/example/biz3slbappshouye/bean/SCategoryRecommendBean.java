package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SCategoryRecommendBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SCategoryRecommendBean1> recommendList;

    public SCategoryRecommendBean() {
    }

    public SCategoryRecommendBean(List<SCategoryRecommendBean1> recommendList) {
        this.recommendList = recommendList;
    }

    public List<SCategoryRecommendBean1> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<SCategoryRecommendBean1> recommendList) {
        this.recommendList = recommendList;
    }
}
