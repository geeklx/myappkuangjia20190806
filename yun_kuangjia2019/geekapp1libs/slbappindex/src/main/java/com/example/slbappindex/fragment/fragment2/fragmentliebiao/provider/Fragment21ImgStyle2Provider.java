package com.example.slbappindex.fragment.fragment2.fragmentliebiao.provider;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.adapter.Fragment21ImgAdapter;
import com.example.slbappindex.fragment.fragment2.fragmentliebiao.bean.Fragment21ImgBean;
import com.haier.cellarette.libutils.utilslib.etc.DateUtil;

import java.util.Date;

public class Fragment21ImgStyle2Provider extends BaseItemProvider<Fragment21ImgBean, BaseViewHolder> {

    @Override
    public int viewType() {
        return Fragment21ImgAdapter.STYLE_TWO;
    }

    @Override
    public int layout() {
        return R.layout.activity_fragment21_imgstyle2_item;
    }

    @Override
    public void convert(BaseViewHolder helper, Fragment21ImgBean data, int position) {
        String aaa = DateUtil.format(new Date(Long.valueOf(data.getmBean().getCreatedAt()) * 1000),
                DateUtil.FORMATER_YMD);
        helper.setText(R.id.tv_provider2, aaa);
//        helper.setText(R.id.tv_provider2, data.getmBean().getCreatedAt());
        helper.setText(R.id.tv1_provider2, data.getmBean().getText());
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
    public boolean onLongClick(BaseViewHolder helper, Fragment21ImgBean data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
