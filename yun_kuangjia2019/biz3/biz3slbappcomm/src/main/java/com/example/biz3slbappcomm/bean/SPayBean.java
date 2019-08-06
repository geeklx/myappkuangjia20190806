package com.example.biz3slbappcomm.bean;

import java.io.Serializable;


public class SPayBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SPayBean1 wxRetRes;// 微信
    private SPayBean2 zfbRetRes;// 支付宝

    public SPayBean() {
    }

    public SPayBean1 getWxRetRes() {
        return wxRetRes;
    }

    public void setWxRetRes(SPayBean1 wxRetRes) {
        this.wxRetRes = wxRetRes;
    }

    public SPayBean2 getZfbRetRes() {
        return zfbRetRes;
    }

    public void setZfbRetRes(SPayBean2 zfbRetRes) {
        this.zfbRetRes = zfbRetRes;
    }
}
