package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

//"appUpgrade":{
//"upgradeInfo":"版本：1.01\" + \" \" + \"1.修改已知问题\\n2.加入动态绘本\\n3.加入小游戏等你来学习升级",
//"title":"新版本已准备好",
//"version":"101",
//"apkUrl":"http://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/apk/20181205/20181205102534_q9g9.apk",
//"appPkgName":"com.example.slbapp",
//"name":"合象APP",
//"force":0,
//"md5":"BF:D7:2A:0E:73:92:69:8F:3E:CC:09:84:A2:C9:0E:95"
//}
public class SAppUpgradeBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String upgradeInfo;
    private String title;
    private String version;
    private String apkUrl;
    private String appPkgName;
    private String name;
    private String force;
    private String md5;

    public SAppUpgradeBean1() {
    }

    public SAppUpgradeBean1(String upgradeInfo, String title, String version, String apkUrl, String appPkgName, String name, String force, String md5) {
        this.upgradeInfo = upgradeInfo;
        this.title = title;
        this.version = version;
        this.apkUrl = apkUrl;
        this.appPkgName = appPkgName;
        this.name = name;
        this.force = force;
        this.md5 = md5;
    }

    public String getUpgradeInfo() {
        return upgradeInfo;
    }

    public void setUpgradeInfo(String upgradeInfo) {
        this.upgradeInfo = upgradeInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getAppPkgName() {
        return appPkgName;
    }

    public void setAppPkgName(String appPkgName) {
        this.appPkgName = appPkgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "SAppUpgradeBean1{" +
                "upgradeInfo='" + upgradeInfo + '\'' +
                ", title='" + title + '\'' +
                ", version='" + version + '\'' +
                ", apkUrl='" + apkUrl + '\'' +
                ", appPkgName='" + appPkgName + '\'' +
                ", name='" + name + '\'' +
                ", force='" + force + '\'' +
                ", md5='" + md5 + '\'' +
                '}';
    }
}
