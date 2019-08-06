package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

//"categoryViewList": [
//        {
//        "id": "1",
//        "createTime": "2018-11-24 17:32",
//        "updateTime": "2018-11-27 20:45",
//        "appConfId": "3",
//        "categoryId": "2",
//        "categoryCode": "3",
//        "title": "4",
//        "pic": "5",
//        "orders": 6
//        }
//        ]
public class SLB1CategoryBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String appConfId;
    private String categoryId;
    private String id;
    private String name;
    private String orders;
    private String pic;
    private String title;
    private String type;
    private String hios;

    public SLB1CategoryBean1() {
    }

    public String getAppConfId() {
        return appConfId;
    }

    public void setAppConfId(String appConfId) {
        this.appConfId = appConfId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }
}
