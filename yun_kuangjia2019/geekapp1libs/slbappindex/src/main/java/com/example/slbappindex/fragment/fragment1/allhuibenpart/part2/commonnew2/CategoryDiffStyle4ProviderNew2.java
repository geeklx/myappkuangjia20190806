package com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.commonnew2;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.allhuibenpart.part2.linstenbooknew1.MoreLBImgBeanNew1;

public class CategoryDiffStyle4ProviderNew2 extends BaseItemProvider<CategoryDiffBeanNew2, BaseViewHolder> {

    @Override
    public int viewType() {
        return CategoryDiffAdapterNew2.STYLE_FOUR;
    }

    @Override
    public int layout() {
        return R.layout.activity_morehuiben_imgstyle2_item;
    }

    @Override
    public void convert(BaseViewHolder helper, CategoryDiffBeanNew2 data, int position) {
        ImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_def_loading)
                .error(R.drawable.ic_def_loading)
                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
        Glide.with(mContext).load(data.getmBean().getBgImg())
                .apply(options)
                .into(iv1);
        helper.addOnClickListener(R.id.iv1);
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
