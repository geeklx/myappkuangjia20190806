package com.example.slbappreadbook.down;

import android.os.Bundle;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappreadbook.R;
import com.example.slbappreadbook.domain.HuibenFileCacheBeanItem;
import com.example.slbappreadbook.huancun.DownManager;
import com.haier.cellarette.baselibrary.common.ConstantsUtils;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.baselibrary.widget.AlertView;
import com.haier.cellarette.baselibrary.widget.BookPaddingDecoration;

import java.util.ArrayList;
import java.util.List;

public class DownActivity extends SlbBaseActivity implements View.OnClickListener {

    private TextView tvLeft;
    private TextView tv_right;
    private TextView tvCenter;
    // 我的书架列表 bufen
    private RecyclerView mRecyclerView;
    private DownAdapter mAdapter;
    private List<DownBean> mList;
    private ArrayList<HuibenFileCacheBeanItem> mList_huancun;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_down;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        tvCenter.setText("本地绘本");
        tvLeft.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);
        // 我的书架bufen
        getDataLocal();
        mAdapter.setNewData(mList);
        mAdapter.notifyDataSetChanged();
    }

    private void getDataLocal() {
        mList = new ArrayList<>();
        mList_huancun = new ArrayList<>();
        mList_huancun = DownManager.getInstance(getApplicationContext()).getListBean(CommonUtils.TXTID_BEAN);
        if (mList_huancun == null || mList_huancun.size() == 0) {
            return;
        }
        for (int i = 0; i < mList_huancun.size(); i++) {
            DownBean downBean = new DownBean();
            downBean.setId(mList_huancun.get(i).getId());
            downBean.setUserName(mList_huancun.get(i).getName());
            downBean.setUserAvatar(mList_huancun.get(i).getUrlImg());
            downBean.setText(mList_huancun.get(i).getUrlMp3());
            downBean.setRetweet(false);
            downBean.setCreatedAt("");
            mList.add(downBean);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_left) {
            onBackPressed();
        } else if (i == R.id.tv_right) {
            // 删除绘本空间bufen
            // 清除缓存bufen
            final AlertView dialog = new AlertView(this, "温馨提示", "清除缩略图及其他缓存文件",
                    "取消", "确定");
            dialog.show();
            dialog.setClicklistener(new AlertView.ClickListenerInterface() {
                                        @Override
                                        public void doLeft() {
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void doRight() {
                                            //TODO 清除应用缓存
                                            for (int i = 0; i < mAdapter.getData().size(); i++) {
                                                DownBean downBean = mAdapter.getItem(i);
                                                if (!TextUtils.isEmpty(SPUtils.getInstance().getString(downBean.getId()))) {
                                                    SPUtils.getInstance().put(downBean.getId(), "");
                                                    // SPUtils.getInstance().clear();
                                                }
                                            }
                                            // 判断目录是否存在，不存在则判断是否创建成功
                                            if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_mp3_huiben)) {
                                                // 删除绘本mp3
                                                FileUtils.deleteAllInDir(ConstantsUtils.file_url_mp3_huiben);
                                            }
                                            if (FileUtils.createOrExistsDir(ConstantsUtils.file_url_wenjian)) {
                                                // 删除绘本txt
                                                FileUtils.deleteAllInDir(ConstantsUtils.file_url_wenjian);
                                            }
                                            // 删除列表txt
                                            DownManager.getInstance(getApplicationContext()).deletetxt(CommonUtils.TXTID_BEAN);
                                            getDataLocal();
                                            mAdapter.setNewData(mList);
                                            mAdapter.notifyDataSetChanged();
                                            dialog.dismiss();

                                        }
                                    }
            );
        } else {

        }
    }

    private void onclick() {
        tvLeft.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                Toasty.normal(DownActivity.this, position + "item click").show();
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DownBean addressBean = (DownBean) adapter.getItem(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    Toasty.normal(DownActivity.this, addressBean.getUserAvatar() + "    " + position).show();
                } else if (i == R.id.brademo1_tweetName) {
                    Toasty.normal(DownActivity.this, addressBean.getUserName() + position).show();
                } else if (i == R.id.brademo1_tweetText) {
                    Toasty.normal(DownActivity.this, addressBean.getText() + position).show();
                } else if (i == R.id.iv_delss) {
                    Toasty.normal(DownActivity.this, "删除" + position).show();
                    adapter.notifyItemRemoved(position);
                    adapter.getData().remove(addressBean);
                    //请求接口bufen
                    if (adapter.getData().size() == 0) {
//                        niubiEmptyView.errornet("暂无数据");

                    }
                } else {
                }
            }
        });
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                DownBean addressBean = (DownBean) adapter.getItem(position);
                int i = view.getId();
                if (addressBean.isRetweet()) {
                    set_del(false);
                } else {
                    set_del(true);
                }
                return true;
            }
        });
    }

    private void set_del(boolean isRe) {
        for (int i = 0; i < mAdapter.getItemCount() - 1; i++) {
            DownBean bean1 = mAdapter.getItem(i);
            bean1.setRetweet(isRe);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void findview() {
        tvLeft = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);
        tvCenter = findViewById(R.id.tv_center);
        mRecyclerView = findViewById(R.id.recycler_view1);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);
        BookPaddingDecoration recycleViewDivider = new BookPaddingDecoration(this, OrientationHelper.VERTICAL, R.drawable.bg_book_shelf_charge9);
        mRecyclerView.addItemDecoration(recycleViewDivider);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3, OrientationHelper.VERTICAL, false));
        mAdapter = new DownAdapter(R.layout.activity_down_rec1_item);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
        mRecyclerView.setAdapter(mAdapter);


    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }


    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


}
