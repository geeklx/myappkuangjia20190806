package com.haier.cellarette.baselibrary;

import android.os.Looper;
import android.util.Log;

public class TestCode {
    public static void main(String[] args) {
        new MyThread().start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    System.out.println("aaaaa");
                    sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
