package com.example.slbappindex.fragment.fragment1.tingshulistpart;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.biz3slbappshouye.bean.SLB2CategoryListBean1;
import com.example.slbappindex.R;
import com.geek.libglide47.base.GlideImageView;

public class ListenBooksListAdapter extends BaseQuickAdapter<SLB2CategoryListBean1, BaseViewHolder> {

    private boolean isScrolling;

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    public ListenBooksListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SLB2CategoryListBean1 item) {
        final GlideImageView iv1 = helper.itemView.findViewById(R.id.iv1);
        TextView tv1 = helper.itemView.findViewById(R.id.tv1);
        TextView tv2 = helper.itemView.findViewById(R.id.tv2);
        TextView tv3 = helper.itemView.findViewById(R.id.tv3);
        TextView tv4 = helper.itemView.findViewById(R.id.tv4);
        TextView tv51 = helper.itemView.findViewById(R.id.tv51);
        TextView tv52 = helper.itemView.findViewById(R.id.tv52);
        iv1.loadImage(item.getCoverImg(), R.drawable.ic_def_loading);
        //
//        if (isScrolling() && !item.isRetweet()) {
//            Glide.with(mContext).pauseRequests();
//        } else {
//            Glide.with(mContext).resumeRequests();
//        }
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.ic_def_loading)
//                .error(R.drawable.ic_def_loading)
////                .skipMemoryCache(false)
//                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
//        Glide.with(mContext)
//                .load(item.getCoverImg())
//                .apply(options)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        item.setRetweet(true);
//                        return false;
//                    }
//                })
//                .into(iv1);
        helper.setText(R.id.tv1, item.getTitle());
        helper.setText(R.id.tv2, item.getDescr());
        helper.setText(R.id.tv3, item.getItemCountStr());
        helper.setText(R.id.tv4, item.getViewCountStr());
        if (item.isFree()) {
            tv52.setVisibility(View.VISIBLE);
            tv51.setVisibility(View.GONE);
            tv52.setText(item.getPriceStr());
            tv51.setText("");
        } else {
            tv51.setVisibility(View.VISIBLE);
            tv52.setVisibility(View.GONE);
            tv51.setText(item.getPriceStr());
            tv52.setText("");
        }
        //
        GlideImageView iv_bktt1 = helper.itemView.findViewById(R.id.iv_bktt1);
        GlideImageView iv_bktt2 = helper.itemView.findViewById(R.id.iv_bktt2);
        GlideImageView iv_bktt3 = helper.itemView.findViewById(R.id.iv_bktt3);
        GlideImageView iv_bktt4 = helper.itemView.findViewById(R.id.iv_bktt4);
//        (1:左下角,2:右下角,3:右上角,4:左上角)
        if (item.getCornerMap() != null) {
            if (item.getCornerMap().getP1() != null) {
                iv_bktt3.setVisibility(View.VISIBLE);
                iv_bktt3.loadImage(item.getCornerMap().getP1().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP1().getImgUrl()).into(iv_bktt3);
            } else {
                iv_bktt3.setVisibility(View.GONE);
                iv_bktt3.loadImage("", R.color.placeholder_color_transparent);
            }
            if (item.getCornerMap().getP2() != null) {
                iv_bktt4.setVisibility(View.VISIBLE);
                iv_bktt4.loadImage(item.getCornerMap().getP2().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP2().getImgUrl()).into(iv_bktt4);
            } else {
                iv_bktt4.setVisibility(View.GONE);
                iv_bktt4.loadImage("", R.color.placeholder_color_transparent);
            }
            if (item.getCornerMap().getP3() != null) {
                iv_bktt2.setVisibility(View.VISIBLE);
                iv_bktt2.loadImage(item.getCornerMap().getP3().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP3().getImgUrl()).into(iv_bktt2);
            } else {
                iv_bktt2.setVisibility(View.GONE);
                iv_bktt2.loadImage("", R.color.placeholder_color_transparent);
            }
            if (item.getCornerMap().getP4() != null) {
                iv_bktt1.setVisibility(View.VISIBLE);
                iv_bktt1.loadImage(item.getCornerMap().getP4().getImgUrl(), R.color.placeholder_color_transparent);
//                Glide.with(mContext).load(item.getCornerMap().getP4().getImgUrl()).into(iv_bktt1);
            } else {
                iv_bktt1.setVisibility(View.GONE);
                iv_bktt1.loadImage("", R.color.placeholder_color_transparent);
            }
        }
        helper.addOnClickListener(R.id.iv1);
    }


}
