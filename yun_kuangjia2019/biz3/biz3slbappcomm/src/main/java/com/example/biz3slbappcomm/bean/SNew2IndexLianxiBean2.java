package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;

public class SNew2IndexLianxiBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String tagImg;
    private String name;
    private String type;
//    private String children;
    private String pid;

    public SNew2IndexLianxiBean2() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagImg() {
        return tagImg;
    }

    public void setTagImg(String tagImg) {
        this.tagImg = tagImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getChildren() {
//        return children;
//    }
//
//    public void setChildren(String children) {
//        this.children = children;
//    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
