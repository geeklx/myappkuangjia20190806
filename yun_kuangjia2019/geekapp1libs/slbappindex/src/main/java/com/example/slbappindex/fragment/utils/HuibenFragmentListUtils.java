package com.example.slbappindex.fragment.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
//import android.support.annotation.
// ;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.RequiresApi;

import com.example.biz3slbappshouye.bean.SListCommBean1;
import com.example.slbappindex.R;
import com.example.slbappindex.fragment.fragment1.huibendetaillistpart.BooksListBean;
import com.example.slbappindex.fragment.fragment1.mybookspart.Fragment2Bean;
import com.example.slbappindex.fragment.fragment1.remenhuibenpart.Fragment1Bean;
import com.haier.cellarette.baselibrary.bannerview.Biaoge_listBean;
import com.haier.cellarette.baselibrary.common.BaseApp;

import java.util.ArrayList;
import java.util.List;


public class HuibenFragmentListUtils {

    private static volatile HuibenFragmentListUtils instance;
    private static Context mContext;

    private HuibenFragmentListUtils(Context context) {
        mContext = context;
    }

    public static HuibenFragmentListUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (HuibenFragmentListUtils.class) {
                instance = new HuibenFragmentListUtils(context);
            }
        }
        return instance;
    }

    private String[] urls = new String[]{"https://wx2.sinaimg.cn/mw690/60d2c107ly1fx0rhagu00j20ei06btcu.jpg",
            "https://wx4.sinaimg.cn/mw690/60d2c107ly1fx0rhafbeuj20ej068jwn.jpg",
            "https://wx4.sinaimg.cn/mw690/60d2c107ly1fx0rhagbk2j20ej06cdkr.jpg",
            "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif",
            "https://wx2.sinaimg.cn/mw690/60d2c107ly1fx0rhaftl0j20ej069q86.jpg"};

    public List<Biaoge_listBean> Data1() {
        List<Biaoge_listBean> mList1 = new ArrayList<Biaoge_listBean>();
        mList1.add(new Biaoge_listBean(11, urls[0]));
        mList1.add(new Biaoge_listBean(22, urls[1]));
        mList1.add(new Biaoge_listBean(33, urls[2]));
        mList1.add(new Biaoge_listBean(44, urls[3]));
        mList1.add(new Biaoge_listBean(55, urls[4]));
        return mList1;
    }

    public List<Fragment2Bean> getSampleData(int lenth) {
        List<Fragment2Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Fragment2Bean status = new Fragment2Bean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar(i % 2 == 0 ? "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif" : "https://wx3.sinaimg.cn/mw690/60d2c107ly1fwzh7u2km6j216o1kwngf.jpg");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }


    public List<SListCommBean1> getSampleData1(int lenth) {
        List<SListCommBean1> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            SListCommBean1 status = new SListCommBean1();
            status.setBookName("Chad" + i);
            status.setReadmode("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setBannerImg(i % 2 == 0 ? "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif" : "https://wx3.sinaimg.cn/mw690/60d2c107ly1fwzh7u2km6j216o1kwngf.jpg");
            status.setSourceType("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    public List<BooksListBean> getSampleDataBooks(int lenth) {
        List<BooksListBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BooksListBean status = new BooksListBean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(false);
            status.setUserAvatar(i % 2 == 0 ? "http://wx2.sinaimg.cn/large/60d2c107ly1fvendex4pkg208c04pk14.gif" : "https://wx1.sinaimg.cn/mw1024/60d2c107gy1fw2ejwhp57j204q04g0sn.jpg");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    public List<Fragment1Bean> getSampleData0(int lenth) {
        List<Fragment1Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Fragment1Bean status = new Fragment1Bean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar(i % 2 == 0 ? "http://wx1.sinaimg.cn/large/60d2c107ly1ftwko7cqwrg20b4069u0x.gif" : "https://wx3.sinaimg.cn/mw690/60d2c107ly1fwzh7u2km6j216o1kwngf.jpg");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    // 弹出层
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showPopupMenu(Context context, View view, final OnPopMenu onPopMenu) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(context, view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.sample_menu, popupMenu.getMenu());
        popupMenu.setGravity(Gravity.CENTER);
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 控件每一个item的点击事件
                if (onPopMenu != null) {
                    onPopMenu.OnPopClick(item);
                }
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // 控件消失时的事件
                menu.dismiss();
            }
        });

    }

    public interface OnPopMenu {
        void OnPopClick(MenuItem item);
    }

    /**
     * 判断service是否已经运行
     * 必须判断uid,因为可能有重名的Service,所以要找自己程序的Service,不同进程只要是同一个程序就是同一个uid,个人理解android系统中一个程序就是一个用户
     * 用pid替换uid进行判断强烈不建议,因为如果是远程Service的话,主进程的pid和远程Service的pid不是一个值,在主进程调用该方法会导致Service即使已经运行也会认为没有运行
     * 如果Service和主进程是一个进程的话,用pid不会出错,但是这种方法强烈不建议,如果你后来把Service改成了远程Service,这时候判断就出错了
     *
     * @param className Service的全名,例如PushService.class.getName()
     * @return true:Service已运行 false:Service未运行
     */

    public boolean isServiceExisted(String className) {
        ActivityManager am = (ActivityManager) BaseApp.get().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = am.getRunningServices(Integer.MAX_VALUE);
        int myUid = android.os.Process.myUid();
        for (ActivityManager.RunningServiceInfo runningServiceInfo : serviceList) {
            if (runningServiceInfo.uid == myUid && runningServiceInfo.service.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    //判断是否在主进程,这个方法判断进程名或者pid都可以,如果进程名一样那pid肯定也一样
    //true:当前进程是主进程 false:当前进程不是主进程
    public boolean isUIProcess() {
        ActivityManager am = ((ActivityManager) BaseApp.get().getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = BaseApp.get().getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
