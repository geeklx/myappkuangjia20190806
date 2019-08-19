package com.example.slbappindex.fragment.fragment1.allhuibenpart.part3;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.slbappcomm.widgets.CustomRoundAngleImageView;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;

import java.io.File;

public class CategoryDiffStyle2ProviderNew2Index extends BaseItemProvider<CategoryDiffBeanNew2Index, BaseViewHolder> {

    @Override
    public int viewType() {
        return CategoryDiffAdapterNew2Index.STYLE_TWO;
    }

    @Override
    public int layout() {
        return R.layout.activity_fragment21_imgstyle3_item_new11;
    }

    @Override
    public void convert(BaseViewHolder helper, CategoryDiffBeanNew2Index data, int position) {
        //加载图片
        File file = new File(data.getmBean().getCoverImg());
        CustomRoundAngleImageView iv = helper.itemView.findViewById(R.id.iv_provider3);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
        Glide.with(mContext).load(data.getmBean().getCoverImg())
                .apply(options)
                .into(iv);
//        GlideImageView iv = helper.itemView.findViewById(R.id.iv_provider3);
//        Glide.with(mContext).load(file).into(iv);
//        iv.loadLocalImage(file.getAbsolutePath(), R.drawable.ic_def_loading);
//        iv.loadImage(data.getmBean().getBannerImg(), R.drawable.ic_def_loading);
//        helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img1);
//        if (position % 2 == 0) {
//            helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img1);
//        } else {
//            helper.setImageResource(R.id.iv_provider3, R.drawable.animation_img2);
//        }
        //
        GlideImageView iv_bktt1 = helper.itemView.findViewById(R.id.iv_bktt1);
        GlideImageView iv_bktt2 = helper.itemView.findViewById(R.id.iv_bktt2);
        GlideImageView iv_bktt3 = helper.itemView.findViewById(R.id.iv_bktt3);
        GlideImageView iv_bktt4 = helper.itemView.findViewById(R.id.iv_bktt4);
//        (1:左下角,2:右下角,3:右上角,4:左上角)
//        if (data.getmBean().getCornerMap() != null) {
//            if (data.getmBean().getCornerMap().getP1() != null) {
//                iv_bktt3.setVisibility(View.VISIBLE);
//                iv_bktt3.loadImage(data.getmBean().getCornerMap().getP1().getImgUrl(), R.color.placeholder_color_transparent);
////                Glide.with(mContext).load(item.getCornerMap().getP1().getImgUrl()).into(iv_bktt3);
//            } else {
//                iv_bktt3.setVisibility(View.GONE);
//                iv_bktt3.loadImage("", R.color.placeholder_color_transparent);
//            }
//            if (data.getmBean().getCornerMap().getP2() != null) {
//                iv_bktt4.setVisibility(View.VISIBLE);
//                iv_bktt4.loadImage(data.getmBean().getCornerMap().getP2().getImgUrl(), R.color.placeholder_color_transparent);
////                Glide.with(mContext).load(item.getCornerMap().getP2().getImgUrl()).into(iv_bktt4);
//            } else {
//                iv_bktt4.setVisibility(View.GONE);
//                iv_bktt4.loadImage("", R.color.placeholder_color_transparent);
//            }
//            if (data.getmBean().getCornerMap().getP3() != null) {
//                iv_bktt2.setVisibility(View.VISIBLE);
//                iv_bktt2.loadImage(data.getmBean().getCornerMap().getP3().getImgUrl(), R.color.placeholder_color_transparent);
////                Glide.with(mContext).load(item.getCornerMap().getP3().getImgUrl()).into(iv_bktt2);
//            } else {
//                iv_bktt2.setVisibility(View.GONE);
//                iv_bktt2.loadImage("", R.color.placeholder_color_transparent);
//            }
//            if (data.getmBean().getCornerMap().getP4() != null) {
//                iv_bktt1.setVisibility(View.VISIBLE);
//                iv_bktt1.loadImage(data.getmBean().getCornerMap().getP4().getImgUrl(), R.color.placeholder_color_transparent);
////                Glide.with(mContext).load(item.getCornerMap().getP4().getImgUrl()).into(iv_bktt1);
//            } else {
//                iv_bktt1.setVisibility(View.GONE);
//                iv_bktt1.loadImage("", R.color.placeholder_color_transparent);
//            }
//        }
//        TextView tv_ji11 = helper.itemView.findViewById(R.id.tv_ji11);
//        if (!TextUtils.isEmpty(data.getmBean().getItemCountStr())) {
//            tv_ji11.setVisibility(View.VISIBLE);
//            tv_ji11.setText(data.getmBean().getItemCountStr());
//        } else {
//            tv_ji11.setVisibility(View.GONE);
//            tv_ji11.setText("");
//        }
        helper.setText(R.id.tv_provider3, data.getmBean().getTitle());
        helper.setText(R.id.tv_provider31, data.getmBean().getSubtitle());

        helper.addOnClickListener(R.id.iv_provider3);
        helper.addOnClickListener(R.id.tv_provider3);
        helper.addOnClickListener(R.id.tv_provider31);
    }

//    @Override
//    public void onClick(BaseViewHolder helper, MoreLBImgBeanNew1 data, int position) {
//        if (helper.getChildClickViewIds().contains(R.id.iv)) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserAvatar()).show();
//        } else if (helper.getChildClickViewIds().contains(R.id.tv)) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserName()).show();
//        } else {
//        }
//
//    }

//    @Override
//    public boolean onLongClick(BaseViewHolder helper, MoreLBImgBeanNew1 data, int position) {
//        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
//        return true;
//    }
}
