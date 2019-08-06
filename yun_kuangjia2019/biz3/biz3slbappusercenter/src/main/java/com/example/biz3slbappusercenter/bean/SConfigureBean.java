package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SConfigureBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String medalSound;
    private String forceLogin;
    private String forceChild;
    private String startSound;
    private String appLoginBg;

    public String getMedalSound() {
        return medalSound;
    }

    public void setMedalSound(String medalSound) {
        this.medalSound = medalSound;
    }

    public String getForceLogin() {
        return forceLogin;
    }

    public void setForceLogin(String forceLogin) {
        this.forceLogin = forceLogin;
    }

    public String getForceChild() {
        return forceChild;
    }

    public void setForceChild(String forceChild) {
        this.forceChild = forceChild;
    }

    public String getStartSound() {
        return startSound;
    }

    public void setStartSound(String startSound) {
        this.startSound = startSound;
    }

    public String getAppLoginBg() {
        return appLoginBg;
    }

    public void setAppLoginBg(String appLoginBg) {
        this.appLoginBg = appLoginBg;
    }
}
