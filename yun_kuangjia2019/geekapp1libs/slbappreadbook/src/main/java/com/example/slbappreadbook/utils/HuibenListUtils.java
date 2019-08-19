package com.example.slbappreadbook.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

import com.example.slbappreadbook.R;
import com.example.slbappreadbook.domain.BaseBean2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuibenListUtils {

    private static volatile HuibenListUtils instance;
    private static Context mContext;

    private HuibenListUtils(Context context) {
        mContext = context;
    }

    public static HuibenListUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (HuibenListUtils.class) {
                instance = new HuibenListUtils(context);
            }
        }
        return instance;
    }

    /**
     * 亮屏bufen
     * @param activity
     */
    @SuppressLint("InvalidWakeLockTag")
    public void set_on_light(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
//
//        if (mWakeLock == null) {
//            mWakeLock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "--mWakeLock--");
//        }
//
//        if (!mWakeLock.isHeld()) {
//            mWakeLock.acquire();
//            Log.e("--mWakeLock--", "Wakelock aquired!!");
//        }
    }

    /**
     * 息屏bufen
     * @param activity
     */
    public void set_off_light(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        if (mWakeLock != null && mWakeLock.isHeld()) {
//            mWakeLock.release();
//        }
    }


    public boolean IsEqual(List<BaseBean2> defaultList1, List<BaseBean2> bannerBeanList1) {
        if (defaultList1.size() != bannerBeanList1.size()) {
            return false;
        }
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        for (int i = 0; i <= defaultList1.size() - 1; i++) {
            a.add(defaultList1.get(i).getId() + "");
        }
        for (int j = 0; j <= bannerBeanList1.size() - 1; j++) {
            b.add(bannerBeanList1.get(j).getId() + "");
        }
        return compare(a, b);
    }

    public static <T extends Comparable<T>> boolean compare(List<T> a, List<T> b) {
        if (a.size() != b.size())
            return false;
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i)))
                return false;
        }
        return true;
    }

    public int[] ids = new int[]{111, 222, 333, 444, 555, 666, 777, 888, 999, 101010, 111111, 121212, 131313, 141414, 151515, 161616, 171717, 181818};
    public int[] drawableList1 = new int[]{R.drawable.slb_share, R.drawable.slbdown, R.drawable.slb_share, R.drawable.slb_share, R.drawable.slb_share, R.drawable.slb_share,
            R.drawable.slb_share, R.drawable.slbdown, R.drawable.slbdown, R.drawable.slb_share, R.drawable.slb_share, R.drawable.slb_share, R.drawable.slb_share, R.drawable.slb_share,
            R.drawable.slb_share, R.drawable.slbdown, R.drawable.slbdown, R.drawable.slb_share
    };
    // img
    public String[] drawableList2 = new String[]{
            "http://10.0.0.155:3399/love_html/mp3/part1/a1.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a2.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a3.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a4.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a5.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a6.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a7.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a8.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a9.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a10.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a11.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a12.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a13.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a14.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a15.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a16.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a17.jpg",
            "http://10.0.0.155:3399/love_html/mp3/part1/a18.gif"
    };
    // mp3
    public String[] drawableList3 = new String[]{
            "http://10.0.0.155:3399/love_html/mp3/part1/a1.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a2.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a3.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a4.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a5.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a6.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a7.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a8.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a9.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a10.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a11.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a12.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a13.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a14.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a15.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a16.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a17.mp3",
            "http://10.0.0.155:3399/love_html/mp3/part1/a18.mp3"
    };

    public List<BaseBean2> getList() {
        List<BaseBean2> items = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            BaseBean2 baseBean = new BaseBean2();
            baseBean.setId(ids[i]);// 图片的ids
            baseBean.setUrl(drawableList2[i]);// 不需要保存GLide本身有缓存
            baseBean.setUrl_mp3(drawableList3[i]);// 保存到规定的目录
            items.add(baseBean);
        }
        return items;
    }

}
