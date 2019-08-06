package com.example.slbappindex.fragment.fragment1.allhuibenpart.part2;

import com.example.biz3slbappshouye.bean.SListCommBean1;

import java.io.Serializable;

public class MoreHuibenImgBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int style1 = 1;
    public static final int style2 = 2;
    public static final int style3 = 3;

    public int type;
    private SListCommBean1 mBean;

    public MoreHuibenImgBean(int type) {
        this.type = type;
    }

    public MoreHuibenImgBean(int type, SListCommBean1 mBean) {
        this.type = type;
        this.mBean = mBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SListCommBean1 getmBean() {
        return mBean;
    }

    public void setmBean(SListCommBean1 mBean) {
        this.mBean = mBean;
    }


}
