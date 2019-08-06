package com.example.slbappindex.fragment.fragment1.allhuibenpart.part1;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SCategoryBean1;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;

public class MoreTingshuCategoryAdapter extends BaseQuickAdapter<SCategoryBean1, BaseViewHolder> {

    public MoreTingshuCategoryAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SCategoryBean1 item) {
//        helper.itemView.bringToFront();
        GlideImageView iv = helper.itemView.findViewById(R.id.brademo1_img);
        iv.loadImage(item.getPic(), R.drawable.ic_def_loading);
        helper.setText(R.id.brademo1_tweetName, item.getName());

        helper.addOnClickListener(R.id.brademo1_img);
    }


}
