package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SCommonCategoryRecommendBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String classfyName;
    private List<SCommonCategoryRecommendBean2> simpleShowV2Views;

    public SCommonCategoryRecommendBean1() {
    }

    public String getClassfyName() {
        return classfyName;
    }

    public void setClassfyName(String classfyName) {
        this.classfyName = classfyName;
    }

    public List<SCommonCategoryRecommendBean2> getSimpleShowV2Views() {
        return simpleShowV2Views;
    }

    public void setSimpleShowV2Views(List<SCommonCategoryRecommendBean2> simpleShowV2Views) {
        this.simpleShowV2Views = simpleShowV2Views;
    }
}
