package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SListenBookCategoryRecommendBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SCommonCategoryRecommendBean1> recommendList;

    public SListenBookCategoryRecommendBean() {
    }

    public List<SCommonCategoryRecommendBean1> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<SCommonCategoryRecommendBean1> recommendList) {
        this.recommendList = recommendList;
    }
}
