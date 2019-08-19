// IMyAidlInterface.aidl
package com.example.slbapplistenbook;

// Declare any non-default types here with import statements
import com.example.slbapplistenbook.bean.ListenMusicBean;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
      void play();
        void pause();
        void stop();
        void pre();
        void next();
        boolean isPlaying();
        int ids();
        String name();
        String img();
        int getRepeatMode();
}
