package com.example.slbappindex.fragment.fragment1.popcommpart;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.slbappindex.R;

public class PopCommAdapter extends BaseQuickAdapter<PopComm_ListBean, BaseViewHolder> {
    private String checkItemPosition = "-11";

    public void setCheckItem(String position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public PopCommAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PopComm_ListBean item) {
        TextView tv11 = helper.itemView.findViewById(R.id.tv11);
        tv11.setText(item.getText_content());
//        if (checkItemPosition.equals(item.getText_id())) {
        if (item.isEnselect()) {
            tv11.setPressed(true);
        } else {
            tv11.setPressed(false);
        }
        helper.addOnClickListener(R.id.tv11);
    }
}
