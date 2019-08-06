package com.example.slbappcomm.playermusic;

import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

import java.util.Observable;

public class ListenBookObservable extends Observable {

    public void setData(String arg){
        setChanged();
        notifyObservers(arg);
    }

    public void getData(Object obj){
        MyLogUtil.d(""+obj);
    }


}