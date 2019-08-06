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
public class SPopRenwuBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SPopRenwuBean1> compInfos;
    private boolean compNewTask;

    public SPopRenwuBean() {
    }

    public List<SPopRenwuBean1> getCompInfos() {
        return compInfos;
    }

    public void setCompInfos(List<SPopRenwuBean1> compInfos) {
        this.compInfos = compInfos;
    }

    public boolean isCompNewTask() {
        return compNewTask;
    }

    public void setCompNewTask(boolean compNewTask) {
        this.compNewTask = compNewTask;
    }
}
