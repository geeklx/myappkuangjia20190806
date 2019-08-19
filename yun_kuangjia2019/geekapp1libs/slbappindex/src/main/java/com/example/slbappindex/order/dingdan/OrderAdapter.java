package com.example.slbappindex.order.dingdan;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbapporder.bean.SOrderListBean1;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;

public class OrderAdapter extends BaseQuickAdapter<SOrderListBean1, BaseViewHolder> {

    public OrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SOrderListBean1 item) {
        GlideImageView iv1_poppay = helper.itemView.findViewById(R.id.iv1);
        iv1_poppay.loadImage(item.getAvatar(), R.drawable.ic_def_loading);

        helper.setText(R.id.tv1, item.getOrderName() + "");
        helper.setText(R.id.tv2, item.getFinalprice() + "");
        helper.setText(R.id.tv3, "已优惠：" + item.getDiscount() + "元");
        if (item.getPayStatus().equals("0")) {
            helper.setText(R.id.tv_finish1, "未支付");
        } else if (item.getPayStatus().equals("1")) {
            helper.setText(R.id.tv_finish1, "已支付");
        } else if (item.getPayStatus().equals("2")) {
            helper.setText(R.id.tv_finish1, "取消");
        }
        if (item.getSourceType().equals("vip")) {
            helper.setVisible(R.id.tv5, false);
        } else {
            helper.setVisible(R.id.tv5, true);
            if (TextUtils.equals(item.getSourceType(),"book")||TextUtils.equals(item.getSourceType(),"bookitem")){
                helper.setText(R.id.tv5, "去阅读");
            }else if (TextUtils.equals(item.getSourceType(),"audio")||TextUtils.equals(item.getSourceType(),"audioitem")){
                helper.setText(R.id.tv5, "去听书");
            }
        }
        helper.setText(R.id.tv41, "订单编号：" + item.getOrderNo());
        helper.setText(R.id.tv42, "下单时间：" + item.getPayTime());
        helper.setText(R.id.tv43, "支付时间：" + item.getPayTime());
        helper.setText(R.id.tv44, "支付方式：" + item.getPayMethod());
        helper.addOnClickListener(R.id.tv5);
    }


}
