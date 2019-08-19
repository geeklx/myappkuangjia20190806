package com.example.slbappusercenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
//import android.support.annotation.Nullable;
//import androidx.appcompat.widget.GridLayoutManager;
//import androidx.appcompat.widget.OrientationHelper;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ServiceUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean1;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean1Inside;
import com.example.biz3slbappcomm.bean.SMyMedalCoolViewBean2;
import com.example.biz3slbappcomm.presenter.SMyMedalCoolViewPresenter;
import com.example.biz3slbappcomm.view.SMyMedalView;
import com.example.slbappcomm.CommonUtils;
import com.example.slbappcomm.base.SlbBaseActivity;
import com.example.slbappcomm.pagertransformer.VpAccordionTransformer;
import com.example.slbappcomm.playermusic.ListenMusicPlayerService;
import com.example.slbappcomm.viewpager.LxCoolViewPager;
import com.example.slbappusercenter.adapter.MedalCoolViewAdapter;
import com.example.slbappusercenter.adapter.MyMedalAdapter;
import com.example.slbappusercenter.bean.MyMedalBean;
import com.example.slbappusercenter.widgets.CustomIndicator;
import com.example.slbappusercenter.widgets.MedalCoolViewPager;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileassets.GetAssetsFileMP3TXTJSONAPKUtil;
import com.haier.cellarette.baselibrary.common.BaseAppManager;
import com.haier.cellarette.baselibrary.emptyview.EmptyView;
import com.haier.cellarette.baselibrary.screenutils.ScreenListenerUtils;
import com.haier.cellarette.baselibrary.yanzheng.LocalBroadcastManagers;
import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyMedalActivity extends SlbBaseActivity implements View.OnClickListener, SMyMedalView, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private TextView tv1;
    private RecyclerView recyclerview1;
    private MyMedalAdapter myMedalAdapter;
    private List<MyMedalBean> mList;
    public static final String LIST_TAG1 = "recent";
    public static final String LIST_TAG11 = "future";
    public static final String LIST_TAG2 = "pic";
    public static final String LIST_TAG3 = "audio";
    public static final String id1 = "11";
    public static final String id2 = "22";
    public static final String id3 = "33";
    private String which_id = "";
    //
    private MedalCoolViewPager viewpager;
    private MedalCoolViewAdapter adapter;
    private List<SMyMedalCoolViewBean1> mList1;
    private List<SMyMedalCoolViewBean1> mListTemp;
    private int current = -1;
    private SMyMedalCoolViewPresenter presenter;
    private EmptyView mEmptyView;
    private RelativeLayout rl2;
    private CustomIndicator indicator;
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private ScreenListenerUtils screenListenerUtils;

    private void addList() {
        mList.add(new MyMedalBean(LIST_TAG1, "https://www.douyu.com/directory/myFollow", R.drawable.medal_recent1, R.drawable.medal_recent11, true));
        mList.add(new MyMedalBean(LIST_TAG2, "https://www.douyu.com/directory/myFollow", R.drawable.medal_huiben1, R.drawable.medal_huiben11, false));
        mList.add(new MyMedalBean(LIST_TAG3, "https://www.douyu.com/directory/myFollow", R.drawable.medal_tingshu1, R.drawable.medal_tingshu11, false));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ServiceUtils.isServiceRunning("com.example.slbappcomm.playermusic.ListenMusicPlayerService")) {
            SPUtils.getInstance().put(CommonUtils.LISTENBOOK_TAG1, false);
            stopService(new Intent(this, ListenMusicPlayerService.class));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_medal;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        if (NavigationBarUtil.hasNavigationBar(this)) {
//            NavigationBarUtil.initActivity(getWindow().getDecorView());
            NavigationBarUtil.hideBottomUIMenu(this);
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        findview();
        onclick();
        donetwork();
        mMessageReceiver = new PlayMusicReceiverIndex();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(playmusic_action);
        LocalBroadcastManagers.getInstance(getApplicationContext()).registerReceiver(mMessageReceiver, filter);
        // 息屏bufen
        screenListenerUtils = new ScreenListenerUtils(this);
        screenListenerUtils.begin(new ScreenListenerUtils.ScreenStateListener() {

            @Override
            public void onUserPresent() {
                Log.e("onUserPresent", "onUserPresent");
                if (TextUtils.equals(ActivityUtils.getTopActivity().getClass().getName(), "com.example.slbappusercenter.MedalPopActivity")) {
                    return;
                }
                // 循环播放
                Intent msgIntent = new Intent();
                msgIntent.setAction(MyMedalActivity.playmusic_action);
                msgIntent.putExtra(MyMedalActivity.playmusic_action, 0);// 0 循环 1 结束
                LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
            }

            @Override
            public void onScreenOn() {
                Log.e("onScreenOn", "onScreenOn");

            }

            @Override
            public void onScreenOff() {
                Log.e("onScreenOff", "onScreenOff");
                GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).MusicDestory();
            }
        });
        // 友盟统计
        MobclickAgent.onEvent(this, "MyMedalActivity");

    }

    private PlayMusicReceiverIndex mMessageReceiver;
    public static final String playmusic_action = "play_music";

    /**
     * 监听循环播放变化bufen
     */
    public class PlayMusicReceiverIndex extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (playmusic_action.equals(intent.getAction())) {
                    // 循环播放bufen
                    if (TextUtils.equals(ActivityUtils.getTopActivity().getClass().getName(), "com.example.slbappindex.IndexMainActivity")) {
                        return;
                    }
                    GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic2(MyMedalActivity.this, true, "android.resource://" + getPackageName() + "/" + R.raw.mymedal_bg);
                }

            } catch (Exception e) {
            }
        }
    }

    private void donetwork() {
        //
        presenter = new SMyMedalCoolViewPresenter();
        presenter.onCreate(this);
        presenter.getMyMedalCoolViewData();

    }

    private void initList() {
        for (int i = 0; i < mList.size(); i++) {
            MyMedalBean item = mList.get(i);
            if (item.isRetweet()) {
                item.setRetweet(false);
            }
        }
    }

    private void initList1(String tag1) {
        for (int i = 0; i < mList.size(); i++) {
            MyMedalBean item = mList.get(i);
            if (TextUtils.equals(tag1, item.getId())) {
                item.setRetweet(true);
            } else {
                item.setRetweet(false);
            }
        }
    }

    private void get_which_id(String tag1) {
//        initList();
//        bean.setRetweet(true);
        initList1(tag1);
        myMedalAdapter.setNewData(mList);
        myMedalAdapter.notifyDataSetChanged();
        if (TextUtils.equals(tag1, LIST_TAG1)) {
            which_id = LIST_TAG1;
        }
        if (TextUtils.equals(tag1, LIST_TAG11)) {
            which_id = LIST_TAG11;
        }
        if (TextUtils.equals(tag1, LIST_TAG2)) {
            which_id = LIST_TAG2;
        }
        if (TextUtils.equals(tag1, LIST_TAG3)) {
            which_id = LIST_TAG3;
        }
    }

    private void set_viewpager(String tag1) {
        int current1 = 0;
        for (int i = 0; i < mListTemp.size(); i++) {
            if (TextUtils.equals(tag1, mListTemp.get(i).getTag())) {
                current1 = i;
                break;
            }
        }
        viewpager.setCurrentItem(current1);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv1) {
            onBackPressed();
//            onHomePressed();
        } else {

        }
    }

    private String aaa = "";

    private void set_viewpager_which_music(String pos) {
        if (TextUtils.equals(aaa, pos)) {
            return;
        }
        aaa = pos;
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).MusicDestory();
        if (TextUtils.equals(pos, LIST_TAG1) || TextUtils.equals(pos, LIST_TAG11)) {
//            Toasty.normal(this, "历史").show();
            playMusic(this, true, "android.resource://" + getPackageName() + "/" + R.raw.medal_recent);
        }
        if (TextUtils.equals(pos, LIST_TAG2)) {
//            Toasty.normal(this, "绘本").show();
            playMusic(this, true, "android.resource://" + getPackageName() + "/" + R.raw.medal_huiben);
        }
        if (TextUtils.equals(pos, LIST_TAG3)) {
//            Toasty.normal(this, "听书").show();
            playMusic(this, true, "android.resource://" + getPackageName() + "/" + R.raw.medal_tingshu);
        }
    }

    private void onclick() {
        tv1.setOnClickListener(this);
        mEmptyView.bind(rl2).setRetryListener(new EmptyView.RetryListener() {
            @Override
            public void retry() {
                // 重试bufen
                donetwork();
            }
        });
        myMedalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            private final int MIN_CLICK_DELAY_TIME = 1500;
            private long lastClickTime;

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                long curClickTime = System.currentTimeMillis();
                if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                    // 超过点击间隔后再将lastClickTime重置为当前点击时间
                    lastClickTime = curClickTime;
                    final MyMedalBean bean = (MyMedalBean) adapter.getItem(position);
                    get_which_id(bean.getId());
                    set_viewpager(bean.getId());
                }
            }
        });
        viewpager.addOnPageChangeListener(new MedalCoolViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                MyLogUtil.e("----第几个pos--onPageScrolled--", i + "");
                current = i;
                SMyMedalCoolViewBean1 model = adapter.getContacts(i);
            }

            @Override
            public void onPageSelected(int i) {
                MyLogUtil.e("----第几个pos--onPageSelected--", i + "");
                final SMyMedalCoolViewBean1 model = adapter.getContacts(i);
                get_which_id(model.getTag());
                Message msg = Message.obtain();
                msg.obj = model.getTag();
                msg.what = 0;
                mHandler.sendMessage(msg);
                if (TextUtils.equals(model.getTag(), LIST_TAG1)) {
                    indicator.setIndicatorVisible(false);
                } else {
                    if (model.getTotalPage() == 1) {
                        indicator.setIndicatorVisible(false);
                    } else {
                        if (model.getPage() == 0 || model.getPage() == model.getTotalPage()) {//reset指示点
                            indicator.initIndicator(model.getTotalPage(), model.getPage());
                        } else {//tag相同，切换选中指示点
                            indicator.changeIndicatorStatus(model.getPage());
                        }
                        indicator.setIndicatorVisible(true);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                MyLogUtil.e("----第几个pos--onPageScrollStateChanged--", i + "");
                switch (i) {
                    case LxCoolViewPager.SCROLL_STATE_IDLE:
                        //无动作、初始状态
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged无动作");
                        break;
                    case LxCoolViewPager.SCROLL_STATE_DRAGGING:
                        //点击、滑屏
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged点击、滑屏");
                        break;
                    case LxCoolViewPager.SCROLL_STATE_SETTLING:
                        //释放
                        Log.i("--onPageChanged--", "---->onPageScrollStateChanged释放");
                        // 音乐bufen
//                        if (mBinder != null) {
//                            mBinder.musicNext(ReadBookActivity.this, adapter.getContacts(i).getAudio());
//                        }
                        break;
                }
            }
        });
    }

    private void findview() {
        tv1 = findViewById(R.id.tv1);
        rl2 = findViewById(R.id.rl2);
        mEmptyView = findViewById(R.id.empty_view);
        recyclerview1 = findViewById(R.id.recyclerview1);
        recyclerview1.setHasFixedSize(true);
        recyclerview1.setNestedScrollingEnabled(false);
        recyclerview1.setFocusable(false);
        recyclerview1.setLayoutManager(new GridLayoutManager(this, 1, OrientationHelper.HORIZONTAL, false));
        myMedalAdapter = new MyMedalAdapter();
        recyclerview1.setAdapter(myMedalAdapter);
        //
        viewpager = findViewById(R.id.medalcoolviewpager1);
        viewpager.setScrollMode(LxCoolViewPager.ScrollMode.HORIZONTAL);
        viewpager.setAutoScroll(false);
        viewpager.setAutoScrollDirection(LxCoolViewPager.AutoScrollDirection.BACKWARD);
        viewpager.setInfiniteLoop(false);
        viewpager.setScrollDuration(true, 600);
        viewpager.setDrawEdgeEffect(false);
        viewpager.setEdgeEffectColor(getResources().getColor(R.color.transparent_white));
        viewpager.setPageTransformer(false, new VpAccordionTransformer());
        mList1 = new ArrayList<>();
        adapter = new MedalCoolViewAdapter(MyMedalActivity.this, mList1);

        indicator = findViewById(R.id.indicator);
//        indicator.initIndicator(6);
        indicator.setIndicatorVisible(true);
//            indicator.setIndicatorAlign(CustomIndicator.IndicatorAlign.CENTER);
//        indicator.getmIndicatorContainer().setPadding(40, 10, 40, 10);
        //
        mHandlerThread = new HandlerThread("myHandlerThread");
        mHandlerThread.start();
        //在这个线程中创建一个handler对象
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int msg1 = msg.what;
                if (msg1 == 0) {
                    set_viewpager_which_music((String) msg.obj);
                } else if (msg1 == 1) {
                    if (null != player0) {
                        player0.stop();
                        player0.release();
                        player0 = null;
                    }
                    player0 = GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic0(MyMedalActivity.this, true, "android.resource://" + getPackageName() + "/" + R.raw.medal_recent);
                    player0.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // 循环播放
                            Intent msgIntent = new Intent();
                            msgIntent.setAction(MyMedalActivity.playmusic_action);
                            msgIntent.putExtra(MyMedalActivity.playmusic_action, 0);// 0 循环 1 结束
                            LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
                            if (null != player0) {
                                player0.stop();
                                player0.release();
                                player0 = null;
                            }
                        }
                    });
                }
            }
        };
    }

    public MediaPlayer mediaPlayer;

    public void playMusic(Context context, boolean assets_or_raw, String uri_or_assetsurl) {
        Log.e("playMusic:", "playMusic");
        MusicDestory();
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();
//                initMediaPlayer();
            }
//            mediaPlayer.reset();
            if (assets_or_raw) {
                if (uri_or_assetsurl.toLowerCase().contains("http")) {
                    // 播放网络benfen
                    mediaPlayer.setDataSource(uri_or_assetsurl);
                } else {
                    // 播放本地bufen
                    mediaPlayer.setDataSource(context, Uri.parse(uri_or_assetsurl));
                }
            } else {
                AssetManager assetManager = context.getAssets();
                AssetFileDescriptor fileDescriptor = assetManager.openFd(uri_or_assetsurl);
                mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());// 设置数据源
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("playMusic:error:", e.toString());
            e.printStackTrace();
        }

//        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.e("playMusic:", "start");
        mediaPlayer.start();
//        mediaPlayer.setLooping(true);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("playMusic:", "onCompletion");
        // 循环播放声音bufen
        if (BaseAppManager.getInstance().top() != null) {
            if (TextUtils.equals("com.example.slbappusercenter.MyMedalActivity", BaseAppManager.getInstance().top().getClass().getName())) {
                GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic2(this, true, "android.resource://" + getPackageName() + "/" + R.raw.mymedal_bg);
            }
        }

    }

    public void MusicDestory() {
        if (null != mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private boolean is_first_in;

    @Override
    protected void onResume() {
        super.onResume();
        if (!is_first_in) {
            is_first_in = true;
        } else {
            // 循环播放
            Intent msgIntent = new Intent();
            msgIntent.setAction(MyMedalActivity.playmusic_action);
            msgIntent.putExtra(MyMedalActivity.playmusic_action, 0);// 0 循环 1 结束
            LocalBroadcastManagers.getInstance(getApplicationContext()).sendBroadcast(msgIntent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MusicDestory();
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).MusicDestory();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestory();
        mHandlerThread.quit();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (null != player0) {
            try {
                player0.stop();
                player0.release();
                player0 = null;
            } catch (Exception e) {

            }
        }
        MusicDestory();
        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).MusicDestory();
        LocalBroadcastManagers.getInstance(getApplicationContext()).unregisterReceiver(mMessageReceiver);
        screenListenerUtils.unregisterListener();
        super.onDestroy();

    }

    /**
     * --------------------------------业务逻辑分割线----------------------------------
     */


    @Override
    public void OnMyMedalSuccess(SMyMedalCoolViewBean sMyMedalCoolViewBean) {
        mEmptyView.success();
        mList = new ArrayList<>();
        mListTemp = new ArrayList<>();
        addList();
        myMedalAdapter.setNewData(mList);
        //
        if (sMyMedalCoolViewBean.getList() != null && sMyMedalCoolViewBean.getList().size() > 0) {
            SMyMedalCoolViewBean1 tag11Object = null;
            for (SMyMedalCoolViewBean1 sMyMedalCoolViewBean1 : sMyMedalCoolViewBean.getList()) {
                if (sMyMedalCoolViewBean1.getTag().equals(LIST_TAG11)) {
                    tag11Object = sMyMedalCoolViewBean1;
                } else {
                    mList1.add(sMyMedalCoolViewBean1);
                }
            }
            for (SMyMedalCoolViewBean1 sMyMedalCoolViewBean1 : mList1) {
                if (sMyMedalCoolViewBean1.getTag().equals(LIST_TAG1)) {
                    SMyMedalCoolViewBean1Inside sMyMedalCoolViewBean1Inside = new SMyMedalCoolViewBean1Inside();
                    sMyMedalCoolViewBean1Inside.setDescr(tag11Object.getDescr());
                    sMyMedalCoolViewBean1Inside.setId(tag11Object.getId());
                    sMyMedalCoolViewBean1Inside.setList(tag11Object.getList());
                    sMyMedalCoolViewBean1Inside.setTag(tag11Object.getTag());
                    sMyMedalCoolViewBean1Inside.setTitle(tag11Object.getTitle());
                    sMyMedalCoolViewBean1.setsMyMedalCoolViewBean1Inside(sMyMedalCoolViewBean1Inside);
                }
            }
        }
        //
        for (int i = 0; i < mList1.size(); i++) {
            SMyMedalCoolViewBean1 bean1 = mList1.get(i);// 当前tag下总bean
            if (TextUtils.equals(bean1.getTag(), LIST_TAG1)) {
                SMyMedalCoolViewBean1 bean0 = new SMyMedalCoolViewBean1();
                bean0.setId(bean1.getId());
                bean0.setTag(bean1.getTag());
                bean0.setTitle(bean1.getTitle());
                bean0.setDescr(bean1.getDescr());
                bean0.setList(bean1.getList());
                bean0.setsMyMedalCoolViewBean1Inside(bean1.getsMyMedalCoolViewBean1Inside());
                mListTemp.add(bean0);
            }
            if (TextUtils.equals(bean1.getTag(), LIST_TAG2) || TextUtils.equals(bean1.getTag(), LIST_TAG3)) {
                int size1 = bean1.getList().size();// 当前tag下总list  26
//                如果是两个int相除，取整除部分，忽略余数；26/9= 2
//                如果是两个int取余，取余数部分，忽略整除。26%9= 8
                int count = size1 % 9 == 0 ? size1 / 9 : size1 / 9 + 1;//当前tag下总页数
                for (int j = 0; j < count; j++) {
                    SMyMedalCoolViewBean1 bean2 = new SMyMedalCoolViewBean1();// 当前tag下总bean
                    bean2.setId(bean1.getId());
                    bean2.setTag(bean1.getTag());
                    bean2.setTitle(bean1.getTitle());
                    bean2.setDescr(bean1.getDescr());
                    bean2.setPage(j);
                    bean2.setTotalPage(count);
                    List<SMyMedalCoolViewBean2> tempList = new ArrayList<>();
                    if (j == count - 1) {//最后一页
                        tempList = bean1.getList().subList(j * 9, size1);
                    } else {
                        tempList = bean1.getList().subList(j * 9, j * 9 + 9);
                    }
                    bean2.setList(tempList);
                    mListTemp.add(bean2);
                }
            }
        }

        adapter.setData(mListTemp);// 网络
        viewpager.setAdapter(adapter);
        get_which_id(mListTemp.get(0).getTag());
        indicator.initIndicator(mListTemp.get(0).getTotalPage(), 0);
        // 循环播放声音bufen
//        GetAssetsFileMP3TXTJSONAPKUtil.getInstance(getApplicationContext()).playMusic2(getApplicationContext(), true, "android.resource://" + getPackageName() + "/" + R.raw.mymedal_bg);
        // 播放一次最近历史声音
        Message msg = Message.obtain();
        msg.obj = "1";
        msg.what = 1;
        mHandler.sendMessage(msg);

    }

    private MediaPlayer player0 = null;

    @Override
    public void OnMyMedalNodata(String s) {
        mEmptyView.nodata();
    }

    @Override
    public void OnMyMedalFail(String s) {
        mEmptyView.errorNet();
        //
//        SMyMedalCoolViewBean bean_fastjson = null;
//        try {
//            String assets_content = GetAssetsFileMP3TXTJSONAPKUtil.getInstance(this).get_assets_content(this, "jsonbean/demobean3.json");
//            bean_fastjson = JSON.parseObject(assets_content, SMyMedalCoolViewBean.class);
//            OnMyMedalSuccess(bean_fastjson);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
