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
public class SCategoryBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SCategoryBean1> categoryViewList;

    public SCategoryBean() {
    }

    public SCategoryBean(List<SCategoryBean1> categoryViewList) {
        this.categoryViewList = categoryViewList;
    }

    public List<SCategoryBean1> getCategoryViewList() {
        return categoryViewList;
    }

    public void setCategoryViewList(List<SCategoryBean1> categoryViewList) {
        this.categoryViewList = categoryViewList;
    }
}
