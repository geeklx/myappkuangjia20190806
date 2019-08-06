package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;

public class SNew2IndexLianxiBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private SNew2IndexLianxiBean2 tagListView;
    private List<STagCommBean> resourceViews;

    public SNew2IndexLianxiBean1() {
    }

    public SNew2IndexLianxiBean2 getTagListView() {
        return tagListView;
    }

    public void setTagListView(SNew2IndexLianxiBean2 tagListView) {
        this.tagListView = tagListView;
    }

    public List<STagCommBean> getResourceViews() {
        return resourceViews;
    }

    public void setResourceViews(List<STagCommBean> resourceViews) {
        this.resourceViews = resourceViews;
    }
}
