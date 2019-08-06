package com.example.slbapplistenbook.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SLB4CategoryListDetailBean1;
import com.example.slbapplistenbook.R;
import com.geek.libglide47.base.GlideImageView;

public class ListenBooksListAdapterList1 extends BaseQuickAdapter<SLB4CategoryListDetailBean1, BaseViewHolder> {

    private String EX_ID = "";
    private boolean isOnPause;
    private int pos;

    public String getEX_ID() {
        return EX_ID;
    }

    public void setEX_ID(String EX_ID) {
        this.EX_ID = EX_ID;
    }

    public boolean isOnPause() {
        return isOnPause;
    }

    public void setOnPause(boolean onPause) {
        isOnPause = onPause;
    }

    public SLB4CategoryListDetailBean1 get_next_item(int postion) {
        SLB4CategoryListDetailBean1 item1 = new SLB4CategoryListDetailBean1();
        if (getData().size() >= postion + 1) {
            item1 = getData().get(postion + 1);
        }
        return item1;
    }

    public ListenBooksListAdapterList1(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SLB4CategoryListDetailBean1 item) {
        pos = helper.getLayoutPosition();
        GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        iv1.loadImage(item.getCoverImg(), R.drawable.ic_def_loading);
        GlideImageView iv2 = helper.itemView.findViewById(R.id.iv2);
        helper.setText(R.id.tv1, item.getTitle());
//        helper.setText(R.id.tv1, (helper.getAdapterPosition() + 1) + "    " + item.getTitle());
        if (TextUtils.equals(item.getReadRight(), "2") || TextUtils.equals(item.getReadRight(), "3") || TextUtils.equals(item.getReadRight(), "4")) {
//            helper.getView(R.id.iv1).setBackgroundResource(R.drawable.slb_vipsup);
            iv2.loadImage("", R.drawable.slb_vipsup);
            helper.setVisible(R.id.ll1, true);
        } else {
            if (TextUtils.equals(item.getItemId(), getEX_ID())) {
                helper.setVisible(R.id.ll1, true);
                if (isOnPause) {
                    iv2.loadLocalImage(R.drawable.slb_vipplay22, R.drawable.slb_vipplay22);
                } else {
                    iv2.loadLocalImage(R.drawable.slbts_play1, R.drawable.slbts_play1);
                }
            } else {
                helper.setVisible(R.id.ll1, false);
                iv2.loadImage("", R.drawable.slb_vipplay);
            }

        }
        helper.addOnClickListener(R.id.iv1);
    }


}
