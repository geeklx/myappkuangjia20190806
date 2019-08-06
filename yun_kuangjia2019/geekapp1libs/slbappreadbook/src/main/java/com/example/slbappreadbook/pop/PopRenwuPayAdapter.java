package com.example.slbappreadbook.pop;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SPopRenwuBean2;
import com.example.slbappcomm.R;
import com.geek.libglide47.base.GlideImageView;

public class PopRenwuPayAdapter extends BaseQuickAdapter<SPopRenwuBean2, BaseViewHolder> {

    public PopRenwuPayAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SPopRenwuBean2 item) {
        GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        iv1.loadImage(item.getImg(), R.drawable.ic_def_loading);
        helper.setText(R.id.tv1, item.getCount() + "");
//        helper.addOnClickListener(R.id.iv1_poppay);
//        helper.addOnClickListener(R.id.tv1_poppay);
    }


}
