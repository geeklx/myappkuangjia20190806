package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SMyMedalCoolViewBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String tag;
    private String descr;
    private String title;
    private List<SMyMedalCoolViewBean2> list;
    private SMyMedalCoolViewBean1Inside sMyMedalCoolViewBean1Inside;
    private int totalPage;
    private int page;

    public SMyMedalCoolViewBean1Inside getsMyMedalCoolViewBean1Inside() {
        return sMyMedalCoolViewBean1Inside;
    }

    public void setsMyMedalCoolViewBean1Inside(SMyMedalCoolViewBean1Inside sMyMedalCoolViewBean1Inside) {
        this.sMyMedalCoolViewBean1Inside = sMyMedalCoolViewBean1Inside;
    }

    public SMyMedalCoolViewBean1() {
    }

    public SMyMedalCoolViewBean1(String id, String tag, String descr, String title, List<SMyMedalCoolViewBean2> list) {
        this.id = id;
        this.tag = tag;
        this.descr = descr;
        this.title = title;
        this.list = list;
        this.page = page;
        this.totalPage = totalPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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
