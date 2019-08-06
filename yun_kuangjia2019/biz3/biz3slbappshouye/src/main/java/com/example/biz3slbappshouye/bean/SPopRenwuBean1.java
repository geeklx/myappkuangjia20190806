package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

//"compInfos": [
//        {
//        "jumpUrl": "",
//        "orders": 0,
//        "rewards": [
//        {
//        "count": 0,
//        "img": "",
//        "rewardType": ""
//        }
//        ],
//        "title": ""
//        }
//        ],
//        "compNewTask": false
public class SPopRenwuBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String jumpUrl;
    private String orders;
    private List<SPopRenwuBean2> rewards;
    private String title;

    public SPopRenwuBean1() {
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public List<SPopRenwuBean2> getRewards() {
        return rewards;
    }

    public void setRewards(List<SPopRenwuBean2> rewards) {
        this.rewards = rewards;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
