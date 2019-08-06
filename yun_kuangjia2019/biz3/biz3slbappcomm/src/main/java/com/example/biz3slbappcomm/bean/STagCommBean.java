package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

//{
//        "id":"102",
//        "createTime":null,
//        "updateTime":null,
//        "delFlag":null,
//        "title":"数字照片",
//        "subtitle":"数字照片",
//        "categoryId":null,
//        "linkUrl":"http://hexiang-app-test.sairobo.cn:8087/HXYX020/",
//        "coverImg":"http://edu-ae-test.oss-cn-hangzhou.aliyuncs.com/20190603/3e7623bd77484326b050bd4b77b1ba63.jpg",
//        "filePdf":null,
//        "fileVideo":null,
//        "filePdfAnswer":null,
//        "hasAttach":null,
//        "remark":null,
//        "detail":null,
//        "playCount":null,
//        "difficultLevel":null,
//        "resType":null,
//        "resTypeName":null,
//        "stretchMode":3
//        },
public class STagCommBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String createTime;
    private String updateTime;
    private String delFlag;
    private String title;
    private String subtitle;
    private String categoryId;
    private String linkUrl;
    private String coverImg;
    private String filePdf;
    private String fileVideo;
    private String filePdfAnswer;
    private String hasAttach;
    private String remark;
    private String detail;
    private String playCount;
    private String difficultLevel;
    private String resType;
    private String resTypeName;
    private String stretchMode;

    public STagCommBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getFilePdf() {
        return filePdf;
    }

    public void setFilePdf(String filePdf) {
        this.filePdf = filePdf;
    }

    public String getFileVideo() {
        return fileVideo;
    }

    public void setFileVideo(String fileVideo) {
        this.fileVideo = fileVideo;
    }

    public String getFilePdfAnswer() {
        return filePdfAnswer;
    }

    public void setFilePdfAnswer(String filePdfAnswer) {
        this.filePdfAnswer = filePdfAnswer;
    }

    public String getHasAttach() {
        return hasAttach;
    }

    public void setHasAttach(String hasAttach) {
        this.hasAttach = hasAttach;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    public String getDifficultLevel() {
        return difficultLevel;
    }

    public void setDifficultLevel(String difficultLevel) {
        this.difficultLevel = difficultLevel;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResTypeName() {
        return resTypeName;
    }

    public void setResTypeName(String resTypeName) {
        this.resTypeName = resTypeName;
    }

    public String getStretchMode() {
        return stretchMode;
    }

    public void setStretchMode(String stretchMode) {
        this.stretchMode = stretchMode;
    }
}
