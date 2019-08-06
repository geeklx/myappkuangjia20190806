package com.example.slbappindex.order.dingdan;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappcomm.bean.SNew2IndexZhukeBean1;
import com.example.slbappcomm.widgets.CustomRoundAngleImageView;
import com.example.slbappindex.R;

public class OrderAdapterNew2 extends BaseQuickAdapter<SNew2IndexZhukeBean1, BaseViewHolder> {

    public OrderAdapterNew2(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SNew2IndexZhukeBean1 item) {
        CustomRoundAngleImageView iv1_poppay = helper.itemView.findViewById(R.id.iv1);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
        Glide.with(mContext).load(item.getBgImg())
                .apply(options)
                .into(iv1_poppay);

        helper.setText(R.id.tv1, item.getTitle() + "");
        helper.setText(R.id.tv2, item.getSubTitle() + "");
        helper.setText(R.id.tv4, "已有" + item.getCounts() + "人学习");

        helper.addOnClickListener(R.id.rl1);
        helper.addOnClickListener(R.id.rl2);
    }


}
