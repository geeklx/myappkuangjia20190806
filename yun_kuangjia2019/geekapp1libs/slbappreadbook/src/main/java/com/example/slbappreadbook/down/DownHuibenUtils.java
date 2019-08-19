package com.example.slbappreadbook.down;

import android.content.Context;
import android.util.Log;

import com.example.slbappreadbook.domain.HuibenFileCacheBean;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.ArrayList;
import java.util.List;

public class DownHuibenUtils {

    public static DownHuibenUtils instance = null;

    private DownHuibenUtils() {
    }

    public static DownHuibenUtils getInstance() {
        if (null == instance) {
            instance = new DownHuibenUtils();
        }
        return instance;
    }

    public void pause(FileDownLoaderCallBack callBack) {
        FileDownloader.getImpl().pause(downloadListener(callBack));
    }

    public void start(Context context,ArrayList<HuibenFileCacheBean> mGoodsList,List<String> mList_url, String paths,FileDownLoaderCallBack callBack) {

// 第一种方式 :
//for (String url : URLS) {
//    FileDownloader.getImpl().create(url)
//            .setCallbackProgressTimes(0) // 由于是队列任务, 这里是我们假设了现在不需要每个任务都回调`FileDownloadListener#progress`, 我们只关系每个任务是否完成, 所以这里这样设置可以很有效的减少ipc.
//            .setListener(queueTarget)
//            .asInQueueTask()
//            .enqueue();
//}

//if(serial){
        // 串行执行该队列
//    FileDownloader.getImpl().start(queueTarget, true);
// }

// if(parallel){
        // 并行执行该队列
//    FileDownloader.getImpl().start(queueTarget, false);
//}
//        FileDownloader.setup(context);
//        // 第二种方式:
//        final FileDownloadQueueSet queueSet = new FileDownloadQueueSet(downloadListener);
//        final List<BaseDownloadTask> tasks = new ArrayList<>();
//        for (int i = 0; i < mList_url.size(); i++) {
//            tasks.add(FileDownloader.getImpl().create(mList_url.get(i)).setTag(i + 1).setPath(paths));
//        }
//        queueSet.disableCallbackProgressTimes();
//        queueSet.setAutoRetryTimes(1);
//        queueSet.downloadTogether(tasks);
//        queueSet.start();
        // 业务逻辑bufen
        FileDownloader.setup(context);
        final FileDownloadQueueSet queueSet = new FileDownloadQueueSet(downloadListener(callBack));
        final List<BaseDownloadTask> tasks = new ArrayList<>();
        for (int i = 0; i < mList_url.size(); i++) {
            String paths_name = mList_url.get(i).substring(mList_url.get(i).lastIndexOf("/") + 1);// a16
            tasks.add(FileDownloader.getImpl().create(mList_url.get(i)).setTag(mGoodsList.get(i)).setPath(paths + paths_name));
        }
        queueSet.disableCallbackProgressTimes();
        queueSet.setAutoRetryTimes(1);
//        queueSet.downloadTogether(tasks);
        queueSet.downloadSequentially(tasks);
        queueSet.start();
    }

    private FileDownloadListener downloadListener(final FileDownLoaderCallBack callBack){
        return new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                //等待，已经进入下载队列

            }

            @Override
            protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                //下载进度回调
                int percent = (int) ((double) soFarBytes / (double) totalBytes * 100);
//            textView.setText("("+percent+"%"+")");
                if (callBack!=null){
                    callBack.downLoadProgress(task,soFarBytes,totalBytes);
                }
            }

            @Override
            protected void blockComplete(BaseDownloadTask task) {
            }

            @Override
            protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {

            }

            @Override
            protected void completed(BaseDownloadTask task) {
                //完成整个下载过程
                if (callBack!=null){
                    callBack.downLoadComplated(task);
                }
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                //暂停下载

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                //下载出现错误
                if (callBack!=null){
                    callBack.downLoadError(task,e);
                }
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                //在下载队列中(正在等待/正在下载)已经存在相同下载连接与相同存储路径的任务
                String aa = "";
                Log.e("------warn---", "warn");
                if (callBack!=null){
                    callBack.downLoadWarn(task);
                }
            }
        };
    }


    public interface FileDownLoaderCallBack {
        void downLoadComplated(BaseDownloadTask task);
        void downLoadWarn(BaseDownloadTask task);

        void downLoadError(BaseDownloadTask task, Throwable e);

        void downLoadProgress(BaseDownloadTask task, int soFarBytes, int totalBytes);
    }


}
