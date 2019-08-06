package com.example.biz3slbapporder.bean;

import java.io.Serializable;


public class SOrderInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cover;
    private String name;
    private String finalPkgPrice;// 全套
    private String finalPrice;// 本册
    private String pkgPrice;// 全套
    private String pkgNameStr;// 全套(全20册)
    private String pkgPriceStr;// 原价20元
    private String pkgFinalPriceStr;// 现价1元
    private String price;// 本册
    private String sigNameStr;// 本册
    private String sigPriceStr;// 原价20元
    private String sigFinalPriceStr;// 现价1元
    private String showVip;
    private String payRight;// all 全和单 serial单

    public SOrderInfoBean() {
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinalPkgPrice() {
        return finalPkgPrice;
    }

    public void setFinalPkgPrice(String finalPkgPrice) {
        this.finalPkgPrice = finalPkgPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getPkgPrice() {
        return pkgPrice;
    }

    public void setPkgPrice(String pkgPrice) {
        this.pkgPrice = pkgPrice;
    }

    public String getPkgNameStr() {
        return pkgNameStr;
    }

    public void setPkgNameStr(String pkgNameStr) {
        this.pkgNameStr = pkgNameStr;
    }

    public String getPkgPriceStr() {
        return pkgPriceStr;
    }

    public void setPkgPriceStr(String pkgPriceStr) {
        this.pkgPriceStr = pkgPriceStr;
    }

    public String getPkgFinalPriceStr() {
        return pkgFinalPriceStr;
    }

    public void setPkgFinalPriceStr(String pkgFinalPriceStr) {
        this.pkgFinalPriceStr = pkgFinalPriceStr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSigNameStr() {
        return sigNameStr;
    }

    public void setSigNameStr(String sigNameStr) {
        this.sigNameStr = sigNameStr;
    }

    public String getSigPriceStr() {
        return sigPriceStr;
    }

    public void setSigPriceStr(String sigPriceStr) {
        this.sigPriceStr = sigPriceStr;
    }

    public String getSigFinalPriceStr() {
        return sigFinalPriceStr;
    }

    public void setSigFinalPriceStr(String sigFinalPriceStr) {
        this.sigFinalPriceStr = sigFinalPriceStr;
    }

    public String getShowVip() {
        return showVip;
    }

    public void setShowVip(String showVip) {
        this.showVip = showVip;
    }

    public String getPayRight() {
        return payRight;
    }

    public void setPayRight(String payRight) {
        this.payRight = payRight;
    }
}
