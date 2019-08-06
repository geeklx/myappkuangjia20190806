package com.example.slbappindex.vip;

import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbapporder.bean.SVIPCardBean1;
import com.example.slbappindex.R;

public class VipAdapter extends BaseQuickAdapter<SVIPCardBean1, BaseViewHolder> {

    public VipAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SVIPCardBean1 item) {
        TextView tv2 = helper.itemView.findViewById(R.id.tv2);
        TextView iv1 = helper.itemView.findViewById(R.id.iv1);
//        iv1_poppay.loadImage(item.getContent1(), R.drawable.ic_def_loading);
        //中间划横线
        tv2.getPaint().setFlags(tv2.getPaint().getFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv2.setText("原价￥" + item.getPrice());
        helper.setText(R.id.tv1, item.getName());
        helper.setText(R.id.tv3, item.getFinalPrice());
        helper.setText(R.id.tv4, item.getBtnStatus());
        if (TextUtils.isEmpty(item.getTip())) {
            helper.setVisible(R.id.iv1, false);
            helper.setText(R.id.iv1, "");
        } else {
            helper.setVisible(R.id.iv1, true);
            helper.setText(R.id.iv1, item.getTip());
        }

        helper.addOnClickListener(R.id.tv4);
    }


}
