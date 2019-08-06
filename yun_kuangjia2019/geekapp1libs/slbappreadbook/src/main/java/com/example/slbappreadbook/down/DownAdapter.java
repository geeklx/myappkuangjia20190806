package com.example.slbappreadbook.down;

import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.slbappreadbook.R;
import com.geek.libglide47.base.GlideImageView;
import com.haier.cellarette.baselibrary.zothers.ClickableMovementMethod;
import com.haier.cellarette.baselibrary.zothers.SpannableStringUtils;

public class DownAdapter extends BaseQuickAdapter<DownBean, BaseViewHolder> {

    public DownAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DownBean item) {
//        helper.itemView.bringToFront();
        GlideImageView iv = helper.itemView.findViewById(R.id.brademo1_img);
//        Glide.with(mContext).load(file).into(iv);
        iv.loadImage(item.getUserName(), R.drawable.ic_def_loading);
        helper.setText(R.id.brademo1_tweetName, item.getId());
//        helper.setText(R.id.brademo1_tweetText,item.getText());
        helper.setText(R.id.brademo1_tweetDate, item.getCreatedAt());
        ((TextView) helper.getView(R.id.brademo1_tweetText)).setText(SpannableStringUtils.getBuilder(item.getText()).
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
        //
        if (item.isRetweet()){
            helper.getView(R.id.iv_delss).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.iv_delss).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.brademo1_img);
        helper.addOnLongClickListener(R.id.brademo1_img);
        helper.addOnClickListener(R.id.brademo1_tweetName);
        helper.addOnClickListener(R.id.brademo1_tweetText);
    }


}
