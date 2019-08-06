package com.example.slbapplistenbook.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.slbapplistenbook.R;
import com.example.slbapplistenbook.bean.ListenMusicBean;
import com.geek.libglide47.base.GlideImageView;

public class ListenBookAdapter extends BaseQuickAdapter<ListenMusicBean, BaseViewHolder> {

    public ListenBookAdapter() {
        super(R.layout.activity_listenmusic_item1);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListenMusicBean item) {
//        ImageView iv1 = helper.itemView.findViewById(R.id.iv1);
//        UploadImgCircleImageView iv1 = helper.itemView.findViewById(R.id.iv1);
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.ic_def_loading)
//                .error(R.drawable.ic_def_loading)
//                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
//        Glide.with(mContext).load(item.getmAlbum()).apply(options).into(iv1);
        GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        iv1.loadImage(item.getmAlbum(), R.drawable.ic_def_loading);

        helper.addOnClickListener(R.id.iv1);
    }


}
