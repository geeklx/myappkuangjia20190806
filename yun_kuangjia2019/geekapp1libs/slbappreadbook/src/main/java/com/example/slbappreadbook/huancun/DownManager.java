package com.example.slbappreadbook.huancun;

import android.content.Context;

import com.example.slbappreadbook.domain.HuibenFileCacheBeanItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车缓存的工具类
 */

public class DownManager {

    private static volatile DownManager sShoppingCartHistoryManager;
    private Context mContext;

    private DownManager(Context mContext) {
        this.mContext = mContext;
    }

    public static DownManager getInstance(Context context) {
        if (sShoppingCartHistoryManager == null) {
            synchronized (DownManager.class) {
                if (sShoppingCartHistoryManager == null) {
                    sShoppingCartHistoryManager = new DownManager(context);
                }
            }
        }
        return sShoppingCartHistoryManager;
    }

    // 写入缓存HashMap
    public void AddHashMap(String txt_id, ArrayList<HuibenFileCacheBeanItem> mGoodsList) {
        //写入缓存
        LinkedHashMap<String, HuibenFileCacheBeanItem> hashMap = new LinkedHashMap<>();
        for (HuibenFileCacheBeanItem bean : mGoodsList) {
            String goodsId = bean.getId();
            hashMap.put(goodsId + "", bean);
        }
        DownBaseManager.getInstance().addHashMap(txt_id, hashMap);
//        SPUtils.getInstance().put(CommonUtils.TXTID, txt_id);// 为了后面判断是否下载过绘本

    }

    /**
     * 读取缓存HashMap
     *
     * @param txt_id
     */
    public ArrayList<HuibenFileCacheBeanItem> getListBean(String txt_id) {
        ArrayList<HuibenFileCacheBeanItem> listBean = new ArrayList<>();
        LinkedHashMap<String, HuibenFileCacheBeanItem> hashMap = (LinkedHashMap<String, HuibenFileCacheBeanItem>) DownBaseManager.getInstance().getHashMap(txt_id);
        if (hashMap != null) {
            for (Map.Entry<String, HuibenFileCacheBeanItem> entry : hashMap.entrySet()) {
                HuibenFileCacheBeanItem bean = entry.getValue();
                if (bean != null) {
                    listBean.add(bean);
                }
            }
        }
        return listBean;
    }

    //删除缓存中的TXT 文件bufen
    public void deletetxt(String txt_id) {
        DownBaseManager.getInstance().delete(txt_id);
    }

}
