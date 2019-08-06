package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SCategoryBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String pic;
    private String hios;

    public SCategoryBean1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }
}
