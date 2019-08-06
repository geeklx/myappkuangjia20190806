package com.example.biz3slbapporder.bean;

import java.io.Serializable;
import java.util.List;


public class SVIPCardBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String avatar;
    private String endDateString;
    private String nickName;
    private String vipRemark;
    private String vipRuleUrl;
    private List<SVIPCardBean1> priceList;

    public SVIPCardBean() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVipRemark() {
        return vipRemark;
    }

    public void setVipRemark(String vipRemark) {
        this.vipRemark = vipRemark;
    }

    public String getVipRuleUrl() {
        return vipRuleUrl;
    }

    public void setVipRuleUrl(String vipRuleUrl) {
        this.vipRuleUrl = vipRuleUrl;
    }

    public List<SVIPCardBean1> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<SVIPCardBean1> priceList) {
        this.priceList = priceList;
    }
}
