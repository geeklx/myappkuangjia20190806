package com.haier.cellarette.baselibrary.mmkv;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.haier.cellarette.baselibrary.bannerview.Biaoge_listBean;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.tencent.mmkv.MMKV;

import java.util.Set;

public class MmkvUtils {

    private volatile static MmkvUtils instance;
    private static Context context;
    public static String myid = "MmkvUtils";

    private MmkvUtils(Context context) {
        this.context = context;
    }

    public static MmkvUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (MmkvUtils.class) {
                instance = new MmkvUtils(context);
            }
        }
        return instance;
    }

    public void get_demo() {
        Biaoge_listBean biaoge_listBean = new Biaoge_listBean(1, "yun1");
        Biaoge_listBean biaoge_listBean2 = new Biaoge_listBean(2, "yun2");
        MmkvUtils.getInstance(context).set_common_json("ceshi", JSON.toJSONString(biaoge_listBean), Biaoge_listBean.class);
        MmkvUtils.getInstance(context).set_common_json2("ceshi2", biaoge_listBean2);
        Biaoge_listBean bean = MmkvUtils.getInstance(context).get_common_json("ceshi", Biaoge_listBean.class);
        Biaoge_listBean bean2 = MmkvUtils.getInstance(context).get_common_json("ceshi2", Biaoge_listBean.class);
        MyLogUtil.e("--MmkvUtils-ceshi-", bean.getText_content());
        MyLogUtil.e("--MmkvUtils-ceshi2-", bean2.getText_content());
        //
        MmkvUtils.getInstance(context).set_common("ceshi3", "yun3");
        MmkvUtils.getInstance(context).set_xiancheng("ceshi4", "yun4");
        MyLogUtil.e("--MmkvUtils-ceshi3-", MmkvUtils.getInstance(context).get_common("ceshi3"));
        MyLogUtil.e("--MmkvUtils-ceshi4-", MmkvUtils.getInstance(context).get_xiancheng_string("ceshi4"));
        //
        MmkvUtils.getInstance(context).remove_common("ceshi");
        MmkvUtils.getInstance(context).remove_common("ceshi2");
        MmkvUtils.getInstance(context).remove_common("ceshi3");
        MyLogUtil.e("--MmkvUtils-ceshi3-", MmkvUtils.getInstance(context).get_common("ceshi3") + "--");
        MmkvUtils.getInstance(context).remove_xiancheng_common("ceshi4");
        MyLogUtil.e("--MmkvUtils-ceshi4-", "----" + MmkvUtils.getInstance(context).get_xiancheng_string("ceshi4"));
    }

    public void get(String path) {
        String rootDir = "";
        if (TextUtils.isEmpty(path)) {
            rootDir = MMKV.initialize(context);
        } else {
            rootDir = MMKV.initialize(path);
        }
        System.out.println("mmkv root: " + rootDir);
//        set_xiancheng("ceshi", "1");
//        get_xiancheng_string("ceshi");
    }

    public void set_common(String key, String value) {
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode(key, value);
    }

    public String get_common(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeString(key, "");
    }

    public void remove_common(String key) {
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.removeValueForKey(key);
    }

    public void remove_xiancheng_common(String key) {
        remove_xiancheng_common(1, myid, key);
    }

    public void remove_xiancheng_common(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.removeValueForKey(key);
    }

    public <T> T set_common_json(String key, String content, Class<T> tClass) {
        T bean_fastjson = JSON.parseObject(content, tClass);
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode(key, JSON.toJSONString(bean_fastjson));
        return (T) bean_fastjson;
    }

    public void set_common_json2(String key, Object object) {
        MMKV mmkv = MMKV.defaultMMKV();
        mmkv.encode(key, JSON.toJSONString(object));
    }

    public <T> T get_common_json(String key, Class<T> tClass) {
        MMKV mmkv = MMKV.defaultMMKV();
        return JSON.parseObject(mmkv.decodeString(key, ""), tClass);
    }

    /**
     * 1 or 多 线程通信保存
     *
     * @param xiancheng 传1就是单线程 大于1就是多线程
     * @param myid
     * @param key
     * @param value
     */
    public void set_xiancheng(int xiancheng, String myid, String key, String value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(String key, String value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, boolean value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, int value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, long value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, float value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, double value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, Set<String> value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, byte[] value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(String key, Parcelable value) {
        set_xiancheng(1, MmkvUtils.myid, key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, boolean value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, int value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, long value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, float value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, double value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, Set<String> value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, byte[] value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public void set_xiancheng(int xiancheng, String myid, String key, Parcelable value) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        mmkv.encode(key, value);
    }

    public String get_xiancheng_string(String key) {
        return get_xiancheng_string(1, myid, key);
    }

    public int get_xiancheng_int(String key) {
        return get_xiancheng_int(1, myid, key);
    }

    public boolean get_xiancheng_bool(String key) {
        return get_xiancheng_bool(1, myid, key);
    }

    public float get_xiancheng_float(String key) {
        return get_xiancheng_float(1, myid, key);
    }

    public long get_xiancheng_long(String key) {
        return get_xiancheng_long(1, myid, key);
    }

    public byte[] get_xiancheng_byte(String key) {
        return get_xiancheng_byte(1, myid, key);
    }

    public Set<String> get_xiancheng_set_string(String key) {
        return get_xiancheng_set_string(1, myid, key);
    }

    public <T extends Parcelable> T get_xiancheng_parcelable(String key, Class<T> tClass) {
        return get_xiancheng_parcelable(1, myid, key, tClass);
    }

    public String get_xiancheng_string(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv.decodeString(key, "");
    }

    public int get_xiancheng_int(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv.decodeInt(key, -1);
    }

    public boolean get_xiancheng_bool(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv.decodeBool(key, false);
    }

    public float get_xiancheng_float(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv.decodeFloat(key, 0.0f);
    }

    public long get_xiancheng_long(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv.decodeLong(key, -1);
    }

    public byte[] get_xiancheng_byte(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        byte[] bytes = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        bytes = mmkv.decodeBytes(key);
        return bytes == null ? new byte[]{} : mmkv.decodeBytes(key);
    }

    public Set<String> get_xiancheng_set_string(int xiancheng, String myid, String key) {
        MMKV mmkv = null;
        Set<String> bytes = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        bytes = mmkv.decodeStringSet(key);
        return bytes == null ? null : mmkv.decodeStringSet(key);
    }

    public <T extends Parcelable> T get_xiancheng_parcelable(int xiancheng, String myid, String key, Class<T> tClass) {
        MMKV mmkv = null;
        if (xiancheng == 1) {
            mmkv = MMKV.mmkvWithID(myid, MMKV.SINGLE_PROCESS_MODE);
        } else {
            mmkv = MMKV.mmkvWithID(myid, MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv.decodeParcelable(key, tClass);
    }

}
