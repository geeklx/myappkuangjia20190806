package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

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
public class SLB1CategoryBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SLB1CategoryBean1> categoryViewList;

    public SLB1CategoryBean() {
    }

    public List<SLB1CategoryBean1> getCategoryViewList() {
        return categoryViewList;
    }

    public void setCategoryViewList(List<SLB1CategoryBean1> categoryViewList) {
        this.categoryViewList = categoryViewList;
    }
}
