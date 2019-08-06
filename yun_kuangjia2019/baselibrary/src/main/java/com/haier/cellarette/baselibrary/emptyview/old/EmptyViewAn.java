package com.haier.cellarette.baselibrary.emptyview.old;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.emptyview.EmptyViewHelper;


/**
 * 数据加载中/失败默认布局
 * private EmptyView mEmptyView;
 * <p>
 * mEmptyView = (EmptyView) findViewById(R.id.empty_view);
 * <p>
 * mEmptyView.bind(containlayout).setRetryListener(new RetryListener);
 * <p>
 * mEmptyView.loading();
 * <p>
 * mEmptyView.errorNet();
 * <p>
 * mEmptyView.nodata();
 * <p>
 * mEmptyView.success();
 * <p>
 * <com.haiersmart.sfnation.widget.EmptyViewAn
 * android:id="@+id/empty_view_huodong"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:orientation="vertical"
 * android:visibility="gone"
 * app:errornet_layout="@layout/activity_network_errnet"
 * app:loading_layout="@layout/activity_network_loading"
 * app:nodata_layout="@layout/activity_network_nodata"
 * app:unreachable_layout="@layout/activity_network_errnet" />
 */

public class EmptyViewAn extends FrameLayout implements View.OnClickListener {

    private AnimationDrawable ad;
    private View mLoadingView; // 加载中
    private View mNodataLayout; // 无数据

    public View getmNodataLayout() {
        return mNodataLayout;
    }

    public void setmNodataLayout(View mNodataLayout) {
        this.mNodataLayout = mNodataLayout;
    }

    private View mErrorNetLayout; // 网络错误
    private View mUnReachableLayout; // 无网络连接或网络不可达

    private View mBindView;

    private RetryListener mListener;
    private UnReachableListener mUnReachableListener;

    private RetryListener2 mListener2;
    private UnReachableListener2 mUnReachableListener2;

    // 数据为空，网络失败， 加载中 无网络连接
    private String[] mNoticeString = new String[]{"暂无数据", "获取数据失败\n请检查网络是否通畅", "正在加载...", ""};

    public EmptyViewAn(Context context) {
        this(context, null, 0);
    }

    public EmptyViewAn(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyViewAn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.EmptyView, defStyleAttr, 0);
        int loadingLayoutId = ta.getResourceId(R.styleable.EmptyView_loading_layout, 0);
        int nodataLayoutId = ta.getResourceId(R.styleable.EmptyView_nodata_layout, 0);
        int erroNetLayoutId = ta.getResourceId(R.styleable.EmptyView_errornet_layout, 0);
        int unreachableLayoutId = ta.getResourceId(R.styleable.EmptyView_unreachable_layout, 0);
        ta.recycle();

        checkLayout(loadingLayoutId, nodataLayoutId);
        erroNetLayoutId = erroNetLayoutId == 0 ? nodataLayoutId : erroNetLayoutId;
        unreachableLayoutId = unreachableLayoutId == 0 ? erroNetLayoutId : unreachableLayoutId;

        LayoutInflater inflater = LayoutInflater.from(context);
        mLoadingView = inflater.inflate(loadingLayoutId, this, false);
        mNodataLayout = inflater.inflate(nodataLayoutId, this, false);
        mErrorNetLayout = inflater.inflate(erroNetLayoutId, this, false);
        mUnReachableLayout = inflater.inflate(unreachableLayoutId, this, false);
        initViews();
    }

    private void initViews() {
        addView(mLoadingView);
        addView(mNodataLayout);
        addView(mErrorNetLayout);
        addView(mUnReachableLayout);

        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.GONE);
        mUnReachableLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setText();
        setClick(mErrorNetLayout, mUnReachableLayout);
    }

    private void setText() {
        //动画
        ImageView loadingImg = EmptyViewHelper.f(mLoadingView, R.id.loading_iv);
        if (loadingImg != null) {
            ad = (AnimationDrawable) loadingImg.getBackground();
//            ad.start();
//            new Handler().postDelayed(new Runnable() {
//                public void run() {
//                    ad.stop();
//                }
//            }, 5 * 1000);
        }
        TextView noDataNotice = EmptyViewHelper.f(mNodataLayout, R.id.empty_nodata_notice);
        if (noDataNotice != null) {
            noDataNotice.setText(mNoticeString[0]);
        }

        TextView errNotice = EmptyViewHelper.f(mErrorNetLayout, R.id.empty_errnet_notice);
        if (errNotice != null) {
            errNotice.setText(mNoticeString[1]);
        }

        TextView loadingNotice = EmptyViewHelper.f(mLoadingView, R.id.loading_notice);
        if (loadingNotice != null) {
            loadingNotice.setText(mNoticeString[2]);
        }

        TextView unreachableNotice = EmptyViewHelper.f(mUnReachableLayout, R.id.empty_unreachable_notice);
        if (unreachableNotice != null) {
            unreachableNotice.setText(mNoticeString[3]);
        }
    }

    private void setClick(View... contentViews) {
        for (View item : contentViews) {
            if (item == null) {
                continue;
            }
            if (EmptyViewHelper.f(item, R.id.empty_errnet_btn) != null) {
                EmptyViewHelper.click(this, EmptyViewHelper.f(item, R.id.empty_errnet_btn));
            } else {
                EmptyViewHelper.click(this, item);
            }
        }
    }

    private void checkLayout(int loading, int nodata) {
        if (loading == 0 || nodata == 0) {
            throw new UnsupportedOperationException("you must apply loading_layout and nodata_layout");
        }
    }

    /**
     * 加载中
     */
    public void loading() {
        //
        if (ad!=null){
            ad.start();
        }
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.VISIBLE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.GONE);
        mUnReachableLayout.setVisibility(View.GONE);

    }

    /**
     * 加载成功
     */
    public void success() {
        //
        if (ad!=null){
            ad.stop();
        }
        bindViewVisible(View.VISIBLE);
        setVisibility(View.GONE);

    }

    /**
     * 无数据,json返回成功， 但是数据集为空
     */
    public void nodata() {
//        if (!MobileUtils.isNetworkConnected(getContext())) {
//            errorNet();
//            return;
//        }
        //
        if (ad!=null){
            ad.stop();
        }
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.VISIBLE);
        mErrorNetLayout.setVisibility(View.GONE);
        mUnReachableLayout.setVisibility(View.GONE);

    }

    /**
     * 网络访问失败，或者json ok为false
     */
    public void errorNet() {
        //
        if (ad!=null){
            ad.stop();
        }
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.VISIBLE);
        mUnReachableLayout.setVisibility(View.GONE);

    }

    public void unReachable() {
        //
        if (ad!=null){
            ad.stop();
        }
        bindViewVisible(View.GONE);
        setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mNodataLayout.setVisibility(View.GONE);
        mErrorNetLayout.setVisibility(View.GONE);
        mUnReachableLayout.setVisibility(View.VISIBLE);

    }

    private void bindViewVisible(int visibility) {
        if (mBindView != null) mBindView.setVisibility(visibility);
    }

    /**
     * 设置绑定的view
     *
     * @param view
     * @return
     */
    public EmptyViewAn bind(View view) {
        mBindView = view;
        return this;
    }

    /**
     * 设置提醒语句
     *
     * @param notices 大小为3， 0：数据为空提醒，默认数据为空;
     *                1: 网络失败：默认获取数据失败请检查网络是否通畅;
     *                2: 加载中，预留字段
     *                3: 无网络连接
     * @return
     */
    public EmptyViewAn notices(String... notices) {
        int length = notices.length > mNoticeString.length ? mNoticeString.length : notices.length;
        for (int i = 0; i < length; i++) {
            mNoticeString[i] = notices[i];
        }

        setText();
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == mUnReachableLayout) {
            if (mUnReachableListener2 != null) {
                mUnReachableListener2.unReachableClicked(this);
            } else if (mUnReachableListener != null) {
                mUnReachableListener.unReachableClicked();
            }
            return;
        }

        if (mListener2 != null) {
            mListener2.retry(this);
        } else if (mListener != null) {
            mListener.retry();
        }


    }

    /**
     * 设置重试回调
     *
     * @param li
     */
    public void setRetryListener(RetryListener li) {
        mListener = li;
    }

    public void setRetryListener2(RetryListener2 li) {
        mListener2 = li;
    }

    public void setUnReachableListener(UnReachableListener li) {
        mUnReachableListener = li;
    }

    public void setUnReachableListener2(UnReachableListener2 li) {
        mUnReachableListener2 = li;
    }


    public interface RetryListener {
        void retry();
    }

    public interface UnReachableListener {
        void unReachableClicked();
    }

    public interface RetryListener2 {
        void retry(View emptyView);
    }

    public interface UnReachableListener2 {
        void unReachableClicked(View emptyView);
    }
}
