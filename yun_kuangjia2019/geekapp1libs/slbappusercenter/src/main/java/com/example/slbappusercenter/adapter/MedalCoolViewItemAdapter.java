package com.example.slbappusercenter.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean2;
import com.example.slbappusercenter.R;
import com.geek.libglide47.base.GlideImageView;

public class MedalCoolViewItemAdapter extends BaseQuickAdapter<SMyMedalCoolViewBean2, BaseViewHolder> {

    public MedalCoolViewItemAdapter() {
        super(R.layout.activity_my_medal_content_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SMyMedalCoolViewBean2 item) {
        GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
//        Glide.with(mContext).load(item.getImg()).into(iv1);
//        iv1.setImageResource(item.getIcon());
//        iv1.loadLocalImage(item.getIcon(), R.drawable.ic_def_loading);
        iv1.loadImage(item.getImgUrl(), R.drawable.ic_def_loading);
        helper.addOnClickListener(R.id.iv1);
    }
}
