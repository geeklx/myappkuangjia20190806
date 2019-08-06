package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SMyMedalCoolViewBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String medalBgAudio;
    private List<SMyMedalCoolViewBean1> list;

    public SMyMedalCoolViewBean() {
    }

    public String getMedalBgAudio() {
        return medalBgAudio;
    }

    public void setMedalBgAudio(String medalBgAudio) {
        this.medalBgAudio = medalBgAudio;
    }

    public List<SMyMedalCoolViewBean1> getList() {
        return list;
    }

    public void setList(List<SMyMedalCoolViewBean1> list) {
        this.list = list;
    }
}
