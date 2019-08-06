package com.example.biz3slbappcomm.bean;

import java.io.Serializable;


public class SNew2IndexZhukeBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bgImg;// 购买图标
    private String counts;
    private String id;// 折扣金额
    private String status;// 最终价格
    private String statusName;// 订单名称
    private String stretchMode;// 订单号
    private String subTitle;// wechat，alipay
    private String title;// 0 未支付， 1 已支付， 2 取消
    private String url;// 付款时间
    private String urlType;// 原始价格

    public SNew2IndexZhukeBean1() {
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStretchMode() {
        return stretchMode;
    }

    public void setStretchMode(String stretchMode) {
        this.stretchMode = stretchMode;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }
}
