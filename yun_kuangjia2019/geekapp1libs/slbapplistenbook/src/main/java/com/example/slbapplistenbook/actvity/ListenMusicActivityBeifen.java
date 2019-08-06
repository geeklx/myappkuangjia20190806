//package com.example.slbapplistenbook.actvity;
//
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import androidx.recyclerview.widget.RecyclerView;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//
//import com.blankj.utilcode.util.DeviceUtils;
//import com.blankj.utilcode.util.SPUtils;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.example.biz3slbappusercenter.presenter.SSaveFavoritesPresenter;
//import com.example.biz3slbappusercenter.view.SSaveFavoritesView;
//import com.example.slbappcomm.CommonUtils;
//import com.example.slbappcomm.base.SlbBaseActivity;
//import com.haier.cellarette.baselibrary.screenutils.LightOnOffUtils;
//import com.example.slbapplistenbook.R;
//import com.example.slbapplistenbook.adapter.ListenBookAdapter;
//import com.example.slbapplistenbook.bean.ListenMusicBean;
//import com.example.slbapplistenbook.service.ListenMusicPlayerService;
//import com.example.slbapplistenbook.view.ListenMusicImageView;
//import com.example.slbapplistenbook.widget.GalleryLayoutManager;
//import com.example.slbapplistenbook.widget.ScaleTransformer;
//import com.haier.cellarette.baselibrary.common.BaseApp;
//import com.haier.cellarette.baselibrary.emptyview.NiubiEmptyView;
//import com.haier.cellarette.baselibrary.toasts2.Toasty;
//import com.haier.cellarette.baselibrary.zothers.NavigationBarUtil;
//import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class ListenMusicActivityBeifen extends SlbBaseActivity implements View.OnClickListener, SSaveFavoritesView {
//
//    private ListenMusicImageView Image;
//    private TextView MusicStatus;
//    private TextView MusicTime;
//    private SeekBar MusicSeekBar;
//    private TextView MusicTotal;
//    private TextView tv_playmode;
//    private TextView tv_pre;
//    private TextView tv_startpause;
//    private TextView tv_next;
//    private TextView tv_home;
//    private TextView tv_sc1;
//    //
//    private ListenMusicPlayerService.MyBinder mBinder;
//    private boolean flag;
//    private boolean is_fis;
//    private boolean is_bind_services;
//    private int current_url = 0;
//    private boolean isfinish1;
//    private boolean playmode;// false 全局 true 单曲
//    private boolean fromUser_seekbar;// false 正常 true 滚动
//    private boolean is_sc;// false 未收藏 true 收藏
//    //
//    private Timer timer;
//    private TimerTask timerTask;
//    //
//    private RelativeLayout rl11;
//    private NiubiEmptyView niubiEmptyView;
//    private GalleryLayoutManager galleryLayoutManager;
//    private RecyclerView recyclerview1;
//    private ListenBookAdapter mAdapter;
//    private List<ListenMusicBean> mList;
//    private SSaveFavoritesPresenter presenter1;
//    private String EXTRA_ID = "";// 以商品的id为txt的名字
//    private String EXTRA_NAME = "";// 以商品的id为txt的名字
//
//
//    private ArrayList<ListenMusicBean> getList() {
//        ArrayList<ListenMusicBean> list1 = new ArrayList<>();
//        list1.add(new ListenMusicBean(11, 11, "11", "https://wx4.sinaimg.cn/mw1024/60d2c107ly1fvend50aqaj20bl0fsjvm.jpg", "https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/19/32/1.mp3"));
//        list1.add(new ListenMusicBean(22, 22, "22", "https://wx4.sinaimg.cn/mw1024/60d2c107ly1fvend5iqztj20g90fnjxn.jpg", "https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/19/32/10.mp3"));
//        list1.add(new ListenMusicBean(33, 33, "33", "https://wx4.sinaimg.cn/mw1024/60d2c107ly1foruhklhsoj20j60oon0h.jpg", "https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/19/32/11.mp3"));
//        list1.add(new ListenMusicBean(44, 44, "44", "https://wx2.sinaimg.cn/mw1024/60d2c107ly1frciaxw80qj20u00mfnbs.jpg", "https://sairobo-edu-elephant.oss-cn-hangzhou.aliyuncs.com/picbook/19/32/1.mp3"));
//        list1.add(new ListenMusicBean(55, 55, "55", "https://wx3.sinaimg.cn/mw1024/60d2c107ly1foruhklhsoj20j60oon0h.jpg", "http://huiben-oss-cdn.sairobo.cn/audio/gift.mp3"));
//        return list1;
//    }
//
//
//    @Override
//    protected void onDestroy() {
//        if (mHandler != null) {
//            mHandler.removeCallbacksAndMessages(null);
//            mHandler = null;
//        }
//        if (scheduledExecutorService != null) {
//            scheduledExecutorService.shutdown();
//            scheduledExecutorService = null;
//        }
//        if (flag) {
//            if (mBinder != null && mBinder.isService()) {
////            mBinder.musicDestroy();
////            mBinder = null;
//
//                unbindService(conn);
////            stopService(new Intent(this, DemoMusicPlayerService.class));
////            this.finish();
//            }
//            flag = false;
//        }
//        presenter1.onDestory();
//        // 息屏
//        LightOnOffUtils.getInstance(this).set_off_light(this);
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //
//        Intent intent = new Intent(this, ListenMusicPlayerService.class);
//        intent.putExtra("flag", flag);
//        bindService(intent, conn, BIND_AUTO_CREATE);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Image.stopMusic();
//
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_listenmusic;
//    }
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//        //虚拟键
//        if (NavigationBarUtil.hasNavigationBar(this)) {
////            NavigationBarUtil.initActivity(getWindow().getDecorView());
//            NavigationBarUtil.hideBottomUIMenu(this);
//        }
//        // 亮屏
//        LightOnOffUtils.getInstance(this).set_on_light(this);
//        super.setup(savedInstanceState);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        findview();
//        onclick();
//        donetwork();
//    }
//
//    private void donetwork() {
//        //
//        set_update_progress_ui();
//        //
//        EXTRA_ID = getIntent().getStringExtra(CommonUtils.HUIBEN_IDS);
//        EXTRA_NAME = getIntent().getStringExtra(CommonUtils.HUIBEN_TITLES);
//        presenter1 = new SSaveFavoritesPresenter();
//        presenter1.onCreate(this);
//        //
//        setDatas();
//    }
//
//    private void setDatas() {
//        niubiEmptyView.loading("");
////        niubiEmptyView.errornet(CommonUtils.TIPS_WUSHUJU);
////        niubiEmptyView.errornet(CommonUtils.TIPS_WUWANG);
//        mList = new ArrayList<>();
//        mList = getList();
//        // 给service播放列表bufen
//        if (mBinder != null && mBinder.getService() != null) {
//            ArrayList<String> list12 = new ArrayList<>();
//            for (ListenMusicBean bean : mList) {
//                list12.add(bean.getmArtist());
//            }
//            mBinder.getService().setMusicList(list12);
//        }
//        // list
//        mAdapter.setNewData(mList);
//        mAdapter.notifyDataSetChanged();
//    }
//
//    private void onclick() {
//        niubiEmptyView.setRetry(new NiubiEmptyView.RetryListener() {
//            @Override
//            public void retry() {
//                setDatas();
//            }
//        });
//        tv_home.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                onBackPressed();
//            }
//        });
//        tv_playmode.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                // 播放模式
//                if (!playmode) {
//                    playmode = true;
//                    tv_playmode.setBackgroundResource(R.drawable.slb_lb_refresh2);
//                    mBinder.getService().setPlay_mode(1);
//                } else {
//                    playmode = false;
//                    tv_playmode.setBackgroundResource(R.drawable.slb_lb_refresh1);
//                    mBinder.getService().setPlay_mode(0);
//                }
//            }
//        });
//        tv_pre.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                // 上一首
//                current_url--;
//                if (current_url < 0) {
//                    current_url = 0;
//                    Toasty.normal(BaseApp.get(), "已经是第一").show();
//                    return;
//                }
//                //
//                if (galleryLayoutManager.getCurSelectedPosition() == 0) {
//                    Toasty.normal(BaseApp.get(), "已经是第一").show();
//                    return;
//                }
////                recyclerView1.smoothScrollToPosition(galleryLayoutManager.getCurSelectedPosition() - 1);
//                galleryLayoutManager.smoothScrollToPosition(recyclerview1, null, galleryLayoutManager.getCurSelectedPosition() - 1);
//                //
////                mBinder.getService().setCurrent(current_url);
////                play_next();
//            }
//        });
//        tv_startpause.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                // play pause stop
//                // 1.图片动画
//                Image.playMusic();
//                // 2.音乐播放
//                if (mBinder != null && mBinder.getService() != null && mBinder.getService().is_playing()) {
//                    // 暂停
//                    tv_startpause.setBackgroundResource(R.drawable.slb_lb_start);
//                    galleryLayoutManager.setState_ani(galleryLayoutManager.STATE_PLAYING);
//                    galleryLayoutManager.playMusic();
//                    mBinder.musicPause();
//
//                } else {
//                    // 开始
//                    tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
//                    galleryLayoutManager.setState_ani(galleryLayoutManager.STATE_PAUSE);
//                    galleryLayoutManager.playMusic();
//                    if ((current_url == mList.size() - 1) && isfinish1) {
//                        // 当在最后一个的时候 如果还拖动进度条 就继续播放当前 如果不拖动就返回第一个播放
//                        if (mBinder.getService().getPlay_mode() == 0) {
//                            current_url = 0;
//                        } else if (mBinder.getService().getPlay_mode() == 1) {
//                            current_url = current_url;
//                        }
//                        play_next();
//                    } else {
//                        mBinder.musicContinue();
//                    }
//                }
//            }
//        });
//        tv_next.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                // 下一首
//                current_url++;
//                if (current_url >= mList.size()) {
//                    current_url = mList.size() - 1;
//                    Toasty.normal(BaseApp.get(), "已经是最后").show();
//                    return;
//                }
//                //
//                if (galleryLayoutManager.getCurSelectedPosition() == mAdapter.getItemCount() - 1) {
//                    Toasty.normal(BaseApp.get(), "已经是最后").show();
//                    return;
//                }
////                recyclerView1.smoothScrollToPosition(galleryLayoutManager.getCurSelectedPosition() + 1);
//                galleryLayoutManager.smoothScrollToPosition(recyclerview1, null, galleryLayoutManager.getCurSelectedPosition() + 1);
//                //
////                mBinder.getService().setCurrent(current_url);
////                play_next();
//
//            }
//        });
//        MusicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                MyLogUtil.e("-seekbar-onProgressChanged--", "");
////                seekBar.setTag(progress);
//                fromUser_seekbar = fromUser;
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                MyLogUtil.e("-seekbar-onStartTrackingTouch--", "");
////                if (is_dianji) {
//////                    isontouching = true;
////                    if (handler22 != null) {
////                        handler22.removeCallbacks(runnable);
////                    }
////                }
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                MyLogUtil.e("-seekbar-onStopTrackingTouch--", "");
//                if (fromUser_seekbar) {
//                    fromUser_seekbar = false;
//                    play_next_ui();
//                    mBinder.musicSeekTo(seekBar.getProgress());
//
//                }
////                if (is_dianji) {
//////                    isontouching = false;
////                    set_isvis_seekbar(true);
////                }
//            }
//        });
//        galleryLayoutManager.setOnItemSelectedListener(new GalleryLayoutManager.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(RecyclerView recyclerView, View item, int position) {
//                current_url = position;
////                if (current_url == 0 && !is_fis) {
////                    is_fis = true;
////                    galleryLayoutManager.initMusic(item);
////                    galleryLayoutManager.playMusic();
////                }
//                ListenMusicBean cureentBean = mList.get(current_url);
//                ImageView iv1 = item.findViewById(R.id.iv1);
//                // 播放下一首bufen
//                play_next();
//
////                refreshUi(cureentBean);
////                ToastUtil.showToastShort(cureentBean.getText_content());
//            }
//        });
////        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
////            @Override
////            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//////                recyclerView1.smoothScrollToPosition(position);
////                galleryLayoutManager.smoothScrollToPosition(recyclerview1, null, position);
////
////            }
////        });
////        recyclerview1.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////
////            }
////
////            @Override
////            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////                GridLayoutManager l = (GridLayoutManager) recyclerView.getLayoutManager();
////                LinearLayoutManager l1 = (LinearLayoutManager) recyclerView.getLayoutManager();
////                int adapterNowPos = l.findFirstVisibleItemPosition();
////                int allItems = l.getItemCount();
////                // 滚动到哪一个就播放
////
////            }
////        });
//        tv_sc1.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                if (!is_sc) {
//                    is_sc = true;
//                    tv_sc1.setBackgroundResource(R.drawable.play_collect33);
//                } else {
//                    is_sc = false;
//                    tv_sc1.setBackgroundResource(R.drawable.play_collect3);
//                }
//                presenter1.getSaveFavoritesData(DeviceUtils.getAndroidID(), SPUtils.getInstance().getString(CommonUtils.USER_TOKEN), EXTRA_ID, !is_sc);
//
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View view) {
//        int i = view.getId();
////        if (i == R.id.tv_pre) {
////
////
////        } else if (i == R.id.tv_startpause) {
////
////        } else if (i == R.id.tv_next) {
////
////        } else {
////
////        }
//    }
//
//    private void findview() {
//        Image = findViewById(R.id.Image);
//        MusicStatus = findViewById(R.id.MusicStatus);
//        MusicTime = findViewById(R.id.MusicTime);
//        MusicSeekBar = findViewById(R.id.MusicSeekBar);
//        MusicTotal = findViewById(R.id.MusicTotal);
//        tv_playmode = findViewById(R.id.tv_playmode);
//        tv_pre = findViewById(R.id.tv_pre);
//        tv_startpause = findViewById(R.id.tv_startpause);
//        tv_next = findViewById(R.id.tv_next);
//        tv_home = findViewById(R.id.tv_home);
//        tv_sc1 = findViewById(R.id.tv_sc1);
//        recyclerview1 = findViewById(R.id.recyclerview1);
//
//        simpleDateFormat = new SimpleDateFormat("mm:ss");
//        //
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
////        recyclerview1.setLayoutManager(linearLayoutManager);
////        recyclerview1.setLayoutManager(new GridLayoutManager(this, 1, OrientationHelper.HORIZONTAL, false));
////        new LinearSnapHelper().attachToRecyclerView(recyclerview1);
//        galleryLayoutManager = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
//        galleryLayoutManager.attach(recyclerview1, 0);
//        galleryLayoutManager.setItemTransformer(new ScaleTransformer());//这个参数控制图片显示大小
//        mAdapter = new ListenBookAdapter();
//        recyclerview1.setAdapter(mAdapter);
//        niubiEmptyView = new NiubiEmptyView();
//        niubiEmptyView.bind(this, recyclerview1, mAdapter);
//
//    }
//
//    private ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mBinder = (ListenMusicPlayerService.MyBinder) service;
//            flag = true;
//            if (!is_bind_services) {
//                is_bind_services = true;
//                setDatas();
//            }
//            // 监听音乐播放完成bufen
////            if (is_bind_services) {
////                is_bind_services = false;
////                // 给service播放列表bufen
////                ArrayList<String> list12 = new ArrayList<>();
////                for (ListenMusicBean bean : mList) {
////                    list12.add(bean.getmArtist());
////                }
////                mBinder.getService().setMusicList(list12);
////                current_url = 0;
////                // 动画bufen
////                play_next_ui();
////                // 音乐bufen
////                if (flag) {
////                    mBinder.musicStart(ListenMusicActivity.this, mList.get(current_url).getmArtist());
//////                    mBinder.musicNext(ListenMusicActivity.this, mList.get(current_url).getmArtist());
//////                    set_time_change();
////
////                }
////            }
//            mBinder.getService().setOnPrepared(new ListenMusicPlayerService.DemoPreparedCallBack() {
//                @Override
//                public void isPrepared(boolean prepared) {
//                    if (!prepared) {
//                        // next
////                        current_url++;
////                        if (current_url >= getList().size()) {
////                            current_url = 0;
////                        }
////                        play_next();
//                    } else {
//                        MusicTotal.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                MusicTotal.setText(mBinder.getService().get_maxtime());
//                            }
//                        });
//                        MusicSeekBar.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                MusicSeekBar.setMax(mBinder.getService().getmPlayer().getDuration());
//                            }
//                        });
//                    }
//                }
//            });
//            mBinder.getService().setOnPlayFinishCallBack(new ListenMusicPlayerService.PlayFinishCallBack() {
//                @Override
//                public void isFinish(boolean isfinish, int path) {
//                    isfinish1 = isfinish;
//                    if (isfinish) {
//                        // 列表播放完成bufen
//                        tv_startpause.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Image.stopMusic();
//                                tv_startpause.setBackgroundResource(R.drawable.slb_lb_start);
//                            }
//                        });
//                        // 列表动画完成
//                        galleryLayoutManager.stopMusic();
//                    } else {
//                        current_url = path;
//                        play_next_ui();
//                    }
//                }
//            });
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            flag = false;
//        }
//    };
//
//    private void play_next() {
//        play_next_ui();
//        // 2.音乐播放
//        if (mBinder != null && mBinder.getService() != null) {
//            mBinder.getService().setCurrent(current_url);
//            mBinder.musicNext(ListenMusicActivityBeifen.this, mList.get(current_url).getmArtist());
//        }
//    }
//
//    private void play_next_ui() {
//        // 1.图片动画
//        Image.playNextMusic();
//        tv_startpause.setBackgroundResource(R.drawable.slb_lb_pause);
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.ic_def_loading)
//                .error(R.drawable.ic_def_loading)
//                .fallback(R.drawable.ic_def_loading); //url为空的时候,显示的图片;
//        Glide.with(this).load(mList.get(current_url).getmAlbum()).apply(options).into(Image);
//        // 2.列表动画
//        if (!is_fis) {
//            is_fis = true;
//            return;
//        }
//        MyLogUtil.e("geek111111111111", "ccccccccccc");
//        galleryLayoutManager.smoothScrollToPosition(recyclerview1, null, current_url);
//
//    }
//
//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(final Message message) {
//            if (message.what == 10 && message.obj != null) {
//                final int time = (Integer) message.obj;
//                MyLogUtil.e("---geektime---", time + "");
//                MusicTime.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MusicTime.setText(simpleDateFormat.format(time));
//                    }
//                });
//                MusicSeekBar.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        MusicSeekBar.setProgress(time);
//                    }
//                });
//            }
//            return false;
//        }
//    });
//
//    private SimpleDateFormat simpleDateFormat;
//    private ScheduledExecutorService scheduledExecutorService;
//
//    private void set_update_progress_ui() {
//        if (scheduledExecutorService != null) {
//            return;
//        }
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                if (mBinder != null && mBinder.getService() != null && mBinder.getService().is_playing() && !fromUser_seekbar) {
//                    // 将SeekBar位置设置到当前播放位置
//                    Message msg1 = Message.obtain();
//                    msg1.what = 10;
//                    msg1.obj = mBinder.getService().getmPlayer().getCurrentPosition();
//                    mHandler.sendMessage(msg1);
//                }
//            }
//        }, 0, 80, TimeUnit.MILLISECONDS);
//
//    }
//
//    // 收藏bufen
//    @Override
//    public void OnSaveFavoritesSuccess(String s) {
//        Toasty.normal(this, s).show();
//    }
//
//    @Override
//    public void OnSaveFavoritesNodata(String s) {
//        Toasty.normal(this, s).show();
//    }
//
//    @Override
//    public void OnSaveFavoritesFail(String s) {
//        Toasty.normal(this, s).show();
//    }
//}
