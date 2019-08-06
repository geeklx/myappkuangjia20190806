package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SISSCBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean collect;
    private SISSCBean1 shareInfo;

    public SISSCBean() {
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public SISSCBean1 getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(SISSCBean1 shareInfo) {
        this.shareInfo = shareInfo;
    }
}
