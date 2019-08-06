package com.haier.cellarette.baselibrary.zothers;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


public class StartHiddenManager {
    private View left, right;
    private int clickForTestNum = 5;
    private long startTestClickTime;

    public StartHiddenManager(View leftView, View rightView,String intent1) {
        left = leftView;
        right = rightView;
        onCreate(intent1);
    }

    private void onCreate(final String intent1) {
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (clickForTestNum == 5) {
                        startTestClickTime = System.currentTimeMillis();
                        clickForTestNum--;
                    } else if (clickForTestNum < 5 && (clickForTestNum % 2 == 1)) {
                        clickForTestNum--;
                    }
                    long endTestClickTime = System.currentTimeMillis();
                    Log.e("glin", "" + (endTestClickTime - startTestClickTime) + "====" + clickForTestNum);
                    if (clickForTestNum == 0 && (endTestClickTime - startTestClickTime <= 1000 * 5)) {
                        clickForTestNum = 5;
//                        left.getContext().startActivity(new Intent(left.getContext(), HiddenActivity.class));
                        left.getContext().startActivity(new Intent(intent1));
                    } else if (endTestClickTime - startTestClickTime > 1000 * 5) {
                        clickForTestNum = 5;
                    }
                    return true;
                }
                return false;
            }
        });
        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (clickForTestNum < 5 && (clickForTestNum % 2 == 0)) {
                        clickForTestNum--;
                    }
                }
                return false;
            }
        });
    }

    @SuppressWarnings("all")
    public void onDestory() {
        left = null;
        right = null;
    }
}
