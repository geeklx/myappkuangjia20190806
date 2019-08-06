package com.example.slbappreadbook.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SHuibenDetailTuijianBean1;
import com.example.slbappreadbook.R;
import com.geek.libglide47.base.GlideImageView;

public class ReadBookLastViewAdapter extends BaseQuickAdapter<SHuibenDetailTuijianBean1, BaseViewHolder> {

    public ReadBookLastViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        AutoSizeCompat.autoConvertDensityOfGlobal(mContext.getResources());//如果没有自定义需求用这个方法
//        AutoSizeCompat.autoConvertDensity(mContext.getResources(), 375, false);//如果有自定义需求就用这个方法
//        if (ScreenUtils.isLandscape()) {
//            AutoSize.autoConvertDensityOfGlobal((Activity) mContext); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity((Activity) mContext, 375, false); //如果有自定义需求就用这个方法
//        } else {
//            AutoSize.autoConvertDensityOfGlobal((Activity) mContext); //如果没有自定义需求用这个方法
//            AutoSize.autoConvertDensity((Activity) mContext, 375, true);// 667
//        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    protected void convert(BaseViewHolder helper, SHuibenDetailTuijianBean1 item) {
        GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        iv1.loadImage(item.getCoverImg(), R.drawable.ic_def_loading);
        helper.setText(R.id.tv1, item.getBookName());
        if (item.getCornerType().equals("0")) {
            helper.setVisible(R.id.tv_tips, true);
            helper.setText(R.id.tv_tips, "推荐");
        } else {
            helper.setVisible(R.id.tv_tips, false);
        }
        helper.addOnClickListener(R.id.iv1);
    }

}
