package com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo6;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo6.ItemDemo61;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo6ContentBinder extends ItemViewBinder<ItemDemo61, ItemDemo6ContentBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.rec_demo6_item_content, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemDemo61 item) {
        holder.iv1.setImageResource(item.getContent1());
        holder.tv1.setText(item.getContent2());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv1;
        private TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv1);
            tv1 = itemView.findViewById(R.id.tv1);
        }

    }
}
