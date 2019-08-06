package com.example.slbappusercenter.bean;

import java.io.Serializable;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyMedalCoolViewBeanDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String tag;
    private String text;
    private List<MyMedalCoolViewBean1Demo> list1;
    private List<MyMedalCoolViewBean1Demo> list2;

    public MyMedalCoolViewBeanDemo() {
    }

    public MyMedalCoolViewBeanDemo(String id, String tag, String text, List<MyMedalCoolViewBean1Demo> list1, List<MyMedalCoolViewBean1Demo> list2) {
        this.id = id;
        this.tag = tag;
        this.text = text;
        this.list1 = list1;
        this.list2 = list2;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MyMedalCoolViewBean1Demo> getList1() {
        return list1;
    }

    public void setList1(List<MyMedalCoolViewBean1Demo> list1) {
        this.list1 = list1;
    }

    public List<MyMedalCoolViewBean1Demo> getList2() {
        return list2;
    }

    public void setList2(List<MyMedalCoolViewBean1Demo> list2) {
        this.list2 = list2;
    }
}
