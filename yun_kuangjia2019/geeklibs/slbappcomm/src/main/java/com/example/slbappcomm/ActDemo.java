package com.example.slbappcomm;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ActDemo extends AppCompatActivity {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;  //定时器任务
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(timerRunnable, 0, 2000, TimeUnit.SECONDS);
        mHandlerThread = new HandlerThread("myHandlerThread");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //这个方法是运行在 handler-thread 线程中的 ，可以执行耗时操作 更新UI
                Log.d("handler ", "消息： " + msg.what + "  线程： " + Thread.currentThread().getName());

            }
        };
    }

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            // 业务逻辑 拿到结果发送消息队列
            mHandler.sendEmptyMessage(111);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (scheduledThreadPoolExecutor != null) {
            scheduledThreadPoolExecutor.shutdown();
        }
        mHandlerThread.quit();
    }

}
