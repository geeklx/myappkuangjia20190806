package com.example.slbappusercenter.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.slbappusercenter.R;
import com.example.slbappusercenter.bean.MyMedalBean;

public class MyMedalAdapter extends BaseQuickAdapter<MyMedalBean, BaseViewHolder> {

    public MyMedalAdapter() {
        super(R.layout.activity_my_medal_title_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMedalBean item) {
        ImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        if (item.isRetweet()) {
            //选中
            iv1.setImageResource(item.getIcon2());
//            Glide.with(mContext).load(item.getImg()).into(iv1);
        } else {
            //未选中
            iv1.setImageResource(item.getIcon());
        }
//        helper.addOnClickListener(R.id.iv1);
    }
}
