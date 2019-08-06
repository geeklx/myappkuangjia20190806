package com.example.slbappreadbook.huancun;

import com.blankj.utilcode.util.SPUtils;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappreadbook.domain.HuibenFileCacheBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车缓存的工具类
 */

public class HuibenFlieCacheBaseBean2Manager {

    private static HuibenFlieCacheBaseBean2Manager sShoppingCartHistoryManager;


    private HuibenFlieCacheBaseBean2Manager() {

    }

    public static HuibenFlieCacheBaseBean2Manager getInstance() {
        if (sShoppingCartHistoryManager == null) {
            synchronized (HuibenFlieCacheBaseBean2Manager.class) {
                if (sShoppingCartHistoryManager == null) {
                    sShoppingCartHistoryManager = new HuibenFlieCacheBaseBean2Manager();
                }
            }
        }
        return sShoppingCartHistoryManager;
    }

    // 写入缓存HashMap
    public void AddHashMap(String txt_id, ArrayList<HuibenFileCacheBean> mGoodsList) {
        //写入缓存
        LinkedHashMap<String, HuibenFileCacheBean> hashMap = new LinkedHashMap<>();
        for (HuibenFileCacheBean bean : mGoodsList) {
            String goodsId = bean.getId();
            hashMap.put(goodsId + "", bean);
        }
        HuibenFlieCacheBaseManager.getInstance().addHashMap(txt_id, hashMap);


    }

    /**
     * 读取缓存HashMap
     *
     * @param txt_id
     */
    public ArrayList<HuibenFileCacheBean> getListBean(String txt_id) {
        ArrayList<HuibenFileCacheBean> listBean = new ArrayList<>();
        LinkedHashMap<String, HuibenFileCacheBean> hashMap = (LinkedHashMap<String, HuibenFileCacheBean>) HuibenFlieCacheBaseManager.getInstance().getHashMap(txt_id);
        if (hashMap != null) {
            for (Map.Entry<String, HuibenFileCacheBean> entry : hashMap.entrySet()) {
                HuibenFileCacheBean bean = entry.getValue();
                if (bean != null) {
                    listBean.add(bean);
                }
            }
        }
        return listBean;
    }

    //删除缓存中的TXT 文件bufen
    public void deletetxt(String txt_id) {
        HuibenFlieCacheBaseManager.getInstance().delete(txt_id);
    }

}
