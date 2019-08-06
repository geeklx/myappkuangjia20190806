package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

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
public class SPopRenwuBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String count;
    private String img;
    private String rewardType;

    public SPopRenwuBean2() {
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }
}
