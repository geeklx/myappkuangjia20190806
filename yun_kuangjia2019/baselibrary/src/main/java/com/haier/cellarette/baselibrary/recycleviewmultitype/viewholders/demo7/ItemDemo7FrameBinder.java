package com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo7;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7ContentTextImgCommon;
import com.haier.cellarette.baselibrary.toasts2.Toasty;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by shining on 2018/3/26.
 */

public abstract class ItemDemo7FrameBinder<Content extends ItemDemo7ContentTextImgCommon, SubViewHolder extends ItemDemo7ContentBinder>
        extends ItemViewBinder<ItemDemo7, ItemDemo7FrameBinder.FrameHolder> {

    protected abstract ItemDemo7ContentBinder onCreateNewViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent);

    protected abstract void onBindNewViewHolder(@NonNull SubViewHolder holder, @NonNull Content content);

    @Override
    protected FrameHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_demo7_item_binder_frame, parent, false);
        ItemDemo7ContentBinder subViewHolder = onCreateNewViewHolder(inflater, parent);
        return new FrameHolder(root, subViewHolder, this);
    }

    @Override
    protected void onBindViewHolder(@NonNull FrameHolder holder, @NonNull ItemDemo7 item) {
        holder.avatar.setImageResource(item.getUser().avatar);
        holder.username.setText(item.getUser().getName());
        holder.createTime.setText(item.getCreateTime());
        final ItemDemo7ContentTextImgCommon weiboContent = item.getContent();
        onBindNewViewHolder((SubViewHolder) holder.subViewHolder, (Content) weiboContent);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toasty.normal(v.getContext(), "Position: " + getPosition(holder)).show();
//            }
//        });
//        holder.close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int pos = getPosition(holder);
//                if (pos != RecyclerView.NO_POSITION) {
//                    getAdapter().getItems().remove(pos);
//                    getAdapter().notifyDataSetChanged();
//                }
//            }
//        });
    }

    public static class FrameHolder extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView username;
        private FrameLayout container;
        private TextView createTime;
        private TextView close;
        private ItemDemo7ContentBinder subViewHolder;

        private View findViewById(int resId) {
            return itemView.findViewById(resId);
        }

        FrameHolder(View itemView, final ItemDemo7ContentBinder subViewHolder, final ItemDemo7FrameBinder binder) {
            super(itemView);
            avatar = (ImageView) findViewById(R.id.iv_c1);
            username = (TextView) findViewById(R.id.tv_c1);
            container = (FrameLayout) findViewById(R.id.container_c1);
            createTime = (TextView) findViewById(R.id.create_time_c1);
            close = (TextView) findViewById(R.id.close_c1);
            container.addView(subViewHolder.itemView);
            this.subViewHolder = subViewHolder;
            this.subViewHolder.frameHolder = this;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toasty.normal(v.getContext(), "Position: " + getAdapterPosition()).show();
                }
            });

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        binder.getAdapter().getItems().remove(pos);
                        binder.getAdapter().notifyItemRemoved(pos);
//                        binder.getAdapter().notifyDataSetChanged();
                    }

                }
            });

        }
    }
}
