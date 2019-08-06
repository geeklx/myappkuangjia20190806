package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SHuibenDetailTuijianBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SHuibenDetailTuijianBean1> result;

    public SHuibenDetailTuijianBean() {
    }

    public List<SHuibenDetailTuijianBean1> getResult() {
        return result;
    }

    public void setResult(List<SHuibenDetailTuijianBean1> result) {
        this.result = result;
    }
}
