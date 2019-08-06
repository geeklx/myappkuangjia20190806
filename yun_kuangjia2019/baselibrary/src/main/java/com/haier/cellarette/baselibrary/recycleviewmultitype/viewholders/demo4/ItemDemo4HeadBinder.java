package com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo4;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo4.ItemHeadDemo4;

import me.drakeet.multitype.ItemViewBinder;


public class ItemDemo4HeadBinder extends ItemViewBinder<ItemHeadDemo4, ItemDemo4HeadBinder.ViewHolder> {

    @Override
    protected @NonNull
    ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.rec_demo4_item_head, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemHeadDemo4 category) {
        holder.title.setText(category.getContent1());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private @NonNull
        final TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv1);
        }
    }
}
