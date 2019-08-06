package com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.commonnew2;

import com.example.biz3slbappshouye.bean.SCommonCategoryRecommendBean2;

import java.io.Serializable;

public class CategoryDiffBeanNew2 implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int style1 = 1;
    public static final int style2 = 2;
    public static final int style3 = 3;
    public static final int style4 = 4;

    public int type;
    private SCommonCategoryRecommendBean2 mBean;

    public CategoryDiffBeanNew2(int type) {
        this.type = type;
    }

    public CategoryDiffBeanNew2(int type, SCommonCategoryRecommendBean2 mBean) {
        this.type = type;
        this.mBean = mBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SCommonCategoryRecommendBean2 getmBean() {
        return mBean;
    }

    public void setmBean(SCommonCategoryRecommendBean2 mBean) {
        this.mBean = mBean;
    }


}
