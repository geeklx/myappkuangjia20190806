package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SMyMedalCoolViewBean1Inside implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String tag;
    private String descr;
    private String title;
    private List<SMyMedalCoolViewBean2> list;

    public SMyMedalCoolViewBean1Inside() {
    }

    public SMyMedalCoolViewBean1Inside(String id, String tag, String descr, String title, List<SMyMedalCoolViewBean2> list) {
        this.id = id;
        this.tag = tag;
        this.descr = descr;
        this.title = title;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SMyMedalCoolViewBean2> getList() {
        return list;
    }

    public void setList(List<SMyMedalCoolViewBean2> list) {
        this.list = list;
    }
}
