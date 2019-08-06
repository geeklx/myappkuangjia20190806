package com.example.slbappindex.fragment.fragment1.remenhuibenpart;

import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SListCommBean1;
import com.example.slbappcomm.widgets.CustomRoundAngleImageView;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.zothers.ClickableMovementMethod;
import com.haier.cellarette.baselibrary.zothers.SpannableStringUtils;

public class Fragment1Adapter extends BaseQuickAdapter<SListCommBean1, BaseViewHolder> {

    public Fragment1Adapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SListCommBean1 item) {
//        AutoSize.autoConvertDensityOfGlobal((Activity) mContext); //如果没有自定义需求用这个方法
//        AutoSize.autoConvertDensity((Activity) mContext,375,true);
//        helper.itemView.bringToFront();
//        GlideImageView iv = helper.itemView.findViewById(R.id.brademo1_img);
//        Glide.with(mContext).load(file).into(iv);
//        iv.loadImage(item.getCoverImg(), R.drawable.ic_def_loading);
        //
        GlideImageView iv_bktt1 = helper.itemView.findViewById(R.id.iv_bktt1);
        GlideImageView iv_bktt2 = helper.itemView.findViewById(R.id.iv_bktt2);
        GlideImageView iv_bktt3 = helper.itemView.findViewById(R.id.iv_bktt3);
        GlideImageView iv_bktt4 = helper.itemView.findViewById(R.id.iv_bktt4);
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
        CustomRoundAngleImageView iv = helper.itemView.findViewById(R.id.brademo1_img);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片
        Glide.with(mContext).load(item.getCoverImg())
                .apply(options)
                .into(iv);
        helper.setText(R.id.brademo1_tweetName, item.getBookId());
//        helper.setText(R.id.brademo1_tweetText,item.getText());
        helper.setText(R.id.brademo1_tweetDate, item.getBookId() + "");
        ((TextView) helper.getView(R.id.brademo1_tweetText)).setText(SpannableStringUtils.getBuilder(item.getBookId() + "").
                append("点击查看博客链接").setClickSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri url = Uri.parse("http://blog.51cto.com/liangxiao");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(url);
                mContext.startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Utils.getApp().getResources().getColor(R.color.red));
                ds.setUnderlineText(true);
            }
        }).create());
        ((TextView) helper.getView(R.id.brademo1_tweetText)).setMovementMethod(ClickableMovementMethod.getInstance());
        helper.getView(R.id.brademo1_tweetText).setFocusable(false);
        helper.getView(R.id.brademo1_tweetText).setClickable(true);
        helper.getView(R.id.brademo1_tweetText).setLongClickable(false);

        helper.addOnClickListener(R.id.brademo1_img);
        helper.addOnLongClickListener(R.id.brademo1_img);
        helper.addOnClickListener(R.id.brademo1_tweetName);
        helper.addOnClickListener(R.id.brademo1_tweetText);
    }


}
