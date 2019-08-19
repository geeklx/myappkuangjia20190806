package com.example.slbappindex.fragment.fragment1.tingshulistpart.detailpart2;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean1;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;

public class ListenBooksListAdapter2 extends BaseQuickAdapter<SLB4CategoryListDetailBean1, BaseViewHolder> {

    private String EX_ID = "";

    public String getEX_ID() {
        return EX_ID;
    }

    public void setEX_ID(String EX_ID) {
        this.EX_ID = EX_ID;
    }

    public ListenBooksListAdapter2(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SLB4CategoryListDetailBean1 item) {
//        AutoSize.autoConvertDensity((Activity) mContext, 375, true);
        GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        helper.setText(R.id.tv1, (helper.getAdapterPosition() + 1) + "    " + item.getTitle());
        if (TextUtils.equals(item.getReadRight(), "2") || TextUtils.equals(item.getReadRight(), "3") || TextUtils.equals(item.getReadRight(), "4")) {
//            helper.getView(R.id.iv1).setBackgroundResource(R.drawable.slb_vipsup);
            iv1.loadImage("", R.drawable.slb_vipsup);
        } else {
            if (TextUtils.equals(item.getItemId(), getEX_ID())) {
                iv1.loadLocalImage(R.drawable.slbts_play1, R.drawable.slbts_play1);
            } else {
                iv1.loadImage("", R.drawable.slb_vipplay);
            }

        }
        if (TextUtils.equals(item.getReadStatus(), "1")) {
            // 已听
            helper.setVisible(R.id.tv_yt1, true);
            helper.setText(R.id.tv_yt1, "已听");
            if (TextUtils.equals(SPUtils.getInstance().getString(CommonUtils.ID_SCTD, ""), item.getItemId())) {
                // 上次听到
                helper.setVisible(R.id.tv_yt1, true);
                helper.setText(R.id.tv_yt1, "上次听到");
            }
//        } else if (TextUtils.equals(item.getReadStatus(), "2")) {
        } else {
            helper.setVisible(R.id.tv_yt1, false);
            helper.setText(R.id.tv_yt1, "");

        }
        helper.addOnClickListener(R.id.rl1);
    }


}
