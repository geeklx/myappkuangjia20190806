package com.example.slbappindex.fragment.fragment1.allhuibenpart.part3;

import com.example.biz3slbappcomm.bean.STagCommBean;

import java.io.Serializable;

public class CategoryDiffBeanNew2Index implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int style1 = 1;// 标题1          a
    public static final int style2 = 2;// 列表item 2    a a
    public static final int style3 = 3;// 列表item 1     b
    public static final int style4 = 4;// 列表item 1     c
    public static final int style5 = 5;// 列表item 1    ddd

    public int type;
    private STagCommBean mBean;

    public CategoryDiffBeanNew2Index(int type) {
        this.type = type;
    }

    public CategoryDiffBeanNew2Index(int type, STagCommBean mBean) {
        this.type = type;
        this.mBean = mBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public STagCommBean getmBean() {
        return mBean;
    }

    public void setmBean(STagCommBean mBean) {
        this.mBean = mBean;
    }


}
