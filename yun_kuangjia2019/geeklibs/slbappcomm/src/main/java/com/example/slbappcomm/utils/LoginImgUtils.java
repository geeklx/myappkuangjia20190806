package com.example.slbappcomm.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.R;

// 获取缓存头像
public class LoginImgUtils {

    private static volatile LoginImgUtils instance;
    private Context context;

    private LoginImgUtils(Context context) {
        this.context = context;
    }

    public static LoginImgUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (LoginImgUtils.class) {
                instance = new LoginImgUtils(context);
            }
        }
        return instance;
    }

    /**
     * 获取头像bufen
     *
     * @param iv1
     */
    public void get_head_icon(ImageView iv1) {
        RequestOptions options = new RequestOptions()
//                .signature(new ObjectKey(UUID.randomUUID().toString()))  // 重点在这行
                .skipMemoryCache(false)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.drawable.head_moren2)
                .error(R.drawable.head_moren2)
                .fallback(R.drawable.head_moren2); //url为空的时候,显示的图片;
        if (TextUtils.isEmpty(SPUtils.getInstance().getString(CommonUtils.USER_IMG))) {
            Glide.with(context).load(R.drawable.head_moren2).into(iv1);
        } else {
            Glide.with(context).load(SPUtils.getInstance().getString(CommonUtils.USER_IMG))
                    .apply(options)
                    .into(iv1);
//            if (FileUtils.isFileExists(CommonUtils.img_file_url + CommonUtils.img_file_name)) {
//                File file = new File(CommonUtils.img_file_url + CommonUtils.img_file_name);
//                Glide.with(BaseApp.get()).load(file)
//                        .apply(options)
//                        .into(iv1);
//            } else {
//                Glide.with(BaseApp.get()).load(SPUtils.getInstance().getString(CommonUtils.USER_IMG))
//                        .apply(options)
//                        .into(iv1);
//            }
        }
    }

}
