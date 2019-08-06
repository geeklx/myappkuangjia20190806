package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SLB4CategoryListDetailBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SLB4CategoryListDetailBean1 audioView;
    private SLB4CategoryListDetailBean2 shareInfo;
    private List<SLB4CategoryListDetailBean1> list;

    public SLB4CategoryListDetailBean() {
    }

    public SLB4CategoryListDetailBean1 getAudioView() {
        return audioView;
    }

    public void setAudioView(SLB4CategoryListDetailBean1 audioView) {
        this.audioView = audioView;
    }

    public SLB4CategoryListDetailBean2 getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(SLB4CategoryListDetailBean2 shareInfo) {
        this.shareInfo = shareInfo;
    }

    public List<SLB4CategoryListDetailBean1> getList() {
        return list;
    }

    public void setList(List<SLB4CategoryListDetailBean1> list) {
        this.list = list;
    }
}
