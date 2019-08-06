package com.example.slbappreadbook;

import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;

import java.util.Observable;

public class ReadBookObservable extends Observable {

    public void setData(Object arg){
        setChanged();
        notifyObservers(arg);
    }

    public void getData(Object obj){
        MyLogUtil.d(""+obj);
    }


}