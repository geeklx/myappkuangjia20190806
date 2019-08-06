package com.example.slbappindex.fragment.fragment1.huibendetaillistpart.fenleilistpart;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SListCommBean1;
import com.example.slbappcomm.widgets.CustomRoundAngleImageView;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;

import java.io.File;

public class CategoryBooksListAdapter extends BaseQuickAdapter<SListCommBean1, BaseViewHolder> {

    public CategoryBooksListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SListCommBean1 item) {
//        AutoSize.autoConvertDensity((Activity) mContext, 375, true);
        //加载图片
        File file = new File(item.getCoverImg());
        CustomRoundAngleImageView iv = helper.itemView.findViewById(R.id.iv_provider3);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
        Glide.with(mContext).load(item.getCoverImg())
                .apply(options)
                .into(iv);
        //
        GlideImageView iv_bktt1 = helper.itemView.findViewById(com.example.slbappindex.R.id.iv_bktt1);
        GlideImageView iv_bktt2 = helper.itemView.findViewById(com.example.slbappindex.R.id.iv_bktt2);
        GlideImageView iv_bktt3 = helper.itemView.findViewById(com.example.slbappindex.R.id.iv_bktt3);
        GlideImageView iv_bktt4 = helper.itemView.findViewById(com.example.slbappindex.R.id.iv_bktt4);
//        (1:左下角,2:右下角,3:右上角,4:左上角)
        if (item.getCornerMap() != null) {
            if (item.getCornerMap().getP1() != null) {
                iv_bktt3.setVisibility(View.VISIBLE);
                iv_bktt3.loadImage(item.getCornerMap().getP1().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP1().getImgUrl()).into(iv_bktt3);
            } else {
                iv_bktt3.setVisibility(View.GONE);
                iv_bktt3.loadImage(null, R.color.placeholder_color_transparent);
            }
            if (item.getCornerMap().getP2() != null) {
                iv_bktt4.setVisibility(View.VISIBLE);
                iv_bktt4.loadImage(item.getCornerMap().getP2().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP2().getImgUrl()).into(iv_bktt4);
            } else {
                iv_bktt4.setVisibility(View.GONE);
                iv_bktt4.loadImage(null, R.color.placeholder_color_transparent);
            }
            if (item.getCornerMap().getP3() != null) {
                iv_bktt2.setVisibility(View.VISIBLE);
                iv_bktt2.loadImage(item.getCornerMap().getP3().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP3().getImgUrl()).into(iv_bktt2);
            } else {
                iv_bktt2.setVisibility(View.GONE);
                iv_bktt2.loadImage(null, R.color.placeholder_color_transparent);
            }
            if (item.getCornerMap().getP4() != null) {
                iv_bktt1.setVisibility(View.VISIBLE);
                iv_bktt1.loadImage(item.getCornerMap().getP4().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP4().getImgUrl()).into(iv_bktt1);
            } else {
                iv_bktt1.setVisibility(View.GONE);
                iv_bktt1.loadImage(null, R.color.placeholder_color_transparent);
            }
        }
        TextView tv_ji11 = helper.itemView.findViewById(R.id.tv_ji11);
        if (!TextUtils.isEmpty(item.getItemCountStr())) {
            tv_ji11.setVisibility(View.VISIBLE);
            tv_ji11.setText(item.getItemCountStr());
        } else {
            tv_ji11.setVisibility(View.GONE);
            tv_ji11.setText("");
        }
        helper.setText(R.id.tv_provider3, item.getBookName());
        helper.setText(R.id.tv_provider31, item.getDescr());
        helper.addOnClickListener(R.id.iv_provider3);
        helper.addOnClickListener(R.id.tv_provider3);
        helper.addOnLongClickListener(R.id.iv_provider3);
        helper.addOnLongClickListener(R.id.tv_provider3);
    }


}
