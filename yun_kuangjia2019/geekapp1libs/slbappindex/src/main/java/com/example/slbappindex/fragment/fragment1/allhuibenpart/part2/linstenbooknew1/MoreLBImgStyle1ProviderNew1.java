package com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.linstenbooknew1;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.slbappindex.R;

public class MoreLBImgStyle1ProviderNew1 extends BaseItemProvider<MoreLBImgBeanNew1, BaseViewHolder> {

    @Override
    public int viewType() {
        return MoreLBImgAdapterNew1.STYLE_ONE;
    }

    @Override
    public int layout() {
        return R.layout.activity_morehuiben_imgstyle1_item;
    }

    @Override
    public void convert(BaseViewHolder helper, MoreLBImgBeanNew1 data, int position) {
        helper.setText(R.id.tv_provider2, data.getmBean().getTitle());
        helper.setText(R.id.tv1_provider2, data.getmBean().getTitle());
//        helper.addOnClickListener(R.id.tv_provider2);
//        helper.addOnClickListener(R.id.tv1_provider2);
    }

//    @Override
//    public void onClick(BaseViewHolder helper, MoreLBImgBeanNew1 data, int position) {
//        if (helper.getItemId() == helper.getView(R.id.tv).getId()) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getUserName()).show();
//        } else if (helper.getItemId() == helper.getView(R.id.tv1).getId()) {
//            Toasty.normal(BaseApp.get(), position + "item click=" + data.getmBean().getText()).show();
//        } else {
//        }
//
//    }

//    @Override
//    public boolean onLongClick(BaseViewHolder helper, MoreLBImgBeanNew1 data, int position) {
////        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
//        return true;
//    }
}
