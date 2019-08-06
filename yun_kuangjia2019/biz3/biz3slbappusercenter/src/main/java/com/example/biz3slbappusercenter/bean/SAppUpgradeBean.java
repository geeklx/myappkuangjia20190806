package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

//"appUpgrade": {
//        "apkUrl": "http://localhost:8001/#/picbook-appupgrade",
//        "upgradeInfo": "hehe",
//        "createTime": "1543051939000",
//        "name": "tst",
//        "force": 1,
//        "updateTime": "1543405689000",
//        "appCode": "code",
//        "state": 1,
//        "id": "1",
//        "title": "更新",
//        "delFlag": 0,
//        "md5": "12345t2134ty"
//        }
public class SAppUpgradeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SAppUpgradeBean1 appUpgrade;

    public SAppUpgradeBean() {
    }

    public SAppUpgradeBean(SAppUpgradeBean1 appUpgrade) {
        this.appUpgrade = appUpgrade;
    }

    public SAppUpgradeBean1 getAppUpgrade() {
        return appUpgrade;
    }

    public void setAppUpgrade(SAppUpgradeBean1 appUpgrade) {
        this.appUpgrade = appUpgrade;
    }


}
