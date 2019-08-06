package com.example.slbappindex.fragment.fragment1.allhuibenpart.part2;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.slbappindex.R;

public class MoreHuibenImgStyle1Provider extends BaseItemProvider<MoreHuibenImgBean, BaseViewHolder> {

    @Override
    public int viewType() {
        return MoreHuibenImgAdapter.STYLE_ONE;
    }

    @Override
    public int layout() {
        return R.layout.activity_morehuiben_imgstyle1_item;
    }

    @Override
    public void convert(BaseViewHolder helper, MoreHuibenImgBean data, int position) {
        helper.setText(R.id.tv_provider2, data.getmBean().getBookName());
        helper.setText(R.id.tv1_provider2, data.getmBean().getBookName());
        helper.addOnClickListener(R.id.tv_provider2);
        helper.addOnClickListener(R.id.tv1_provider2);
    }

//    @Override
//    public void onClick(BaseViewHolder helper, MoreHuibenImgBean data, int position) {
//        if (helper.getItemId() == helper.getView(R.id.tv).getId()) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserName()).show();
//        } else if (helper.getItemId() == helper.getView(R.id.tv1).getId()) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getText()).show();
//        } else {
//        }
//
//    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MoreHuibenImgBean data, int position) {
//        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
