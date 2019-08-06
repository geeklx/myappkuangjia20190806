package com.haier.cellarette.baselibrary.recycleviewmultitype.viewholders.demo7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewmultitype.models.demo7.ItemDemo7ContentText;


public  class ItemDemo7SimpleTextViewBinder extends ItemDemo7FrameBinder<ItemDemo7ContentText, ItemDemo7SimpleTextViewBinder.ViewHolder> {

    @Override
    protected ItemDemo7ContentBinder onCreateNewViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.rec_demo7_item_simple_text, parent, false));
    }

    @Override
    protected void onBindNewViewHolder(@NonNull ViewHolder viewHolder, @NonNull ItemDemo7ContentText itemDemo7ContentText) {
        viewHolder.simpleText.setText(itemDemo7ContentText.getText());
    }

    static class ViewHolder extends ItemDemo7ContentBinder {

        private TextView simpleText;

        ViewHolder(View itemView) {
            super(itemView);
            simpleText = itemView.findViewById(R.id.simple_text);
        }
    }
}
