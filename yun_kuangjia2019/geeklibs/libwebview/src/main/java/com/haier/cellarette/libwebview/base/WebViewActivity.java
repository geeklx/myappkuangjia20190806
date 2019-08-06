package com.haier.cellarette.libwebview.base;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.haier.cellarette.libutils.utilslib.app.MyLogUtil;
import com.haier.cellarette.libwebview.R;
import com.haier.cellarette.libwebview.hois2.HiosHelper;
import com.haier.cellarette.libwebview.hois2.SlbLoginUtil2;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String UA = "Mozilla/5.0 (Linux; Android 5.1; sudy6580_we_l Build/C320) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Safari/537.36";

    private View mBackImageView;
    private View mBackView;
    private View mCloseView;
    private TextView mTitleTextView;
    private ProgressBar mProgressBar;

    protected WebView mWebView;

    private String mUrl;
    private LinearLayout mTop;
    protected ViewGroup parent;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_webview_layout);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setUp();
        mUrl = getIntent().getStringExtra("url");
        loadUrl(mUrl);
    }

    protected void setUp() {
        findview();
        onclickListener();
        setupWebView();
    }

    /**
     * ture  显示
     * falas  不显示
     *
     * @param b
     */
    protected void setTopVisible(boolean b) {
        if (b) {
            mTop.setVisibility(View.VISIBLE);
        } else {
            mTop.setVisibility(View.GONE);
        }
    }


    /**
     * ture  显示
     * falas  不显示
     *
     * @param b
     */
    protected void setProgress(boolean b) {
        if (b) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }

    protected void findview() {
        mTop = findViewById(R.id.rlTop);
        progress = findViewById(R.id.progress);
        mBackImageView = findViewById(R.id.ic_back);
        mBackView = findViewById(R.id.back);
        mCloseView = findViewById(R.id.close);
        mTitleTextView = findViewById(R.id.title);
        mProgressBar = findViewById(R.id.progress);
    }

    protected void onclickListener() {
        mBackImageView.setOnClickListener(this);
        mBackView.setOnClickListener(this);
        mCloseView.setOnClickListener(this);
    }

    protected void setupWebView() {
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parent = findViewById(R.id.container);
        parent.addView(mWebView);

        WebSettings settings = mWebView.getSettings();//拿到webbiew的settings
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false); // 隐藏webview缩放按钮
        settings.setUseWideViewPort(true);//让图片更适合窗口
        settings.setLoadWithOverviewMode(true);//打开页面时， 自适应屏幕
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setUserAgentString(UA);
        //使WebView支持js
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);

        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        mWebView.addJavascriptInterface(this, "jiugui");//js对应tag

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                MyLogUtil.e("---webviewurl--", url);
                if (url.contains("hios://NaviBack")) {
//                    onBackPressed();
                    finish();
                    return true;
                }
                return HiosHelper.shouldOverrideUrl(WebViewActivity.this, getUrl(url));
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // 如果接受到ssl错误， 接受证书， 继续执行
                Log.d("geek", error.toString());
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                String title = view.getTitle();
                if (title != null) {
                    mTitleTextView.setText(title);
                }
                super.onPageFinished(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitleTextView.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() == View.GONE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
    }

    private String getUrl(String url) {
        String aaaa = "";
        try {
            aaaa = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return aaaa;
    }

    protected void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mWebView.loadUrl("javascript:onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.loadUrl("javascript:onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.loadUrl("javascript:onPause()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        mWebView.loadUrl("javascript:onStop()");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_back || id == R.id.back) {
            onBackPressed();
            return;
        }

        if (id == R.id.close) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
//        FixInputMethodBug.fixFocusedViewLeak(getApplication());
        mWebView.loadUrl("javascript:onDestroy()");

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        super.onDestroy();
    }

    protected void set_destory() {
        mWebView.loadUrl("javascript:onDestroy()");

        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.clearCache(true);
            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();

            try {
                mWebView.destroy();
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    // *****js调用获取信息****

    private String user_id = "";

    /**
     * 通知获取userId,通过调用js函数onGetUserId来回调user_id
     */
    /**
     * type 0: 关闭硬件加速， 1 开启
     */
    @JavascriptInterface
    public void layerType(int type) {
        if (type == 0) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            return;
        }

        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    @JavascriptInterface
    public void userId() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
//                loginToDo(new Runnable() {
//                    @Override
//                    public void run() {
//                        mWebView.loadUrl("javascript:onGetUserId(\"" + user_id + "\")");
//                    }
//                });
                mWebView.loadUrl("javascript:onGetUserId(\"" + user_id + "\")");
            }
        });
    }

    private String mode = "";

    /**
     * 获取fridgeModel
     *
     * @return
     */
    @JavascriptInterface
    public String fridgeModel() {
        return mode;
    }


    @JavascriptInterface
    public void finishPage() {
        finish();
    }

    /**
     * hios://com.example.shining.p022_hios20.activity.NoHiosMainActivity?act={i}1&sku_id={s}2a&category_id={s}3a
     *
     * @param hios_url
     */
    @JavascriptInterface
    public void JumpToShop(String hios_url) {
        HiosHelper.resolveAd(WebViewActivity.this, WebViewActivity.this, hios_url);
    }

//    private void loginToDo(Runnable r) {
//        UserUtils.loginToDo(this, r);
//    }

    /**
     * js调用此方法
     */
    @JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewActivity.this, "我可以跳转了~", Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * js调用此方法. 并且将参数传递过来
     *
     * @param str js  传递过来的参数
     */
    @JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewActivity.this, "我可以拿到你给我的方法跳转了~" + str, Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (UserUtils.get().onActivityResult(requestCode, resultCode, data)) {
//            if (UserUtils.get().isUserLogin()) {
//                onUserLogined(UserUtils.get().userId());
//            }
//            return;
//        }
//        if (LoginUtil.get().login_activity_result(requestCode, resultCode, data)) {
////            if (LoginUtil.get().isUserLogin()) {
////                onUserLogined(LoginUtil.get().userId());
////            } else {
////                onUserLoginCanceled();
////            }
//            return;
//        }
        //登录和未登录成功状态
        if (SlbLoginUtil2.get().login_activity_result(requestCode, resultCode, data)) {
//            if (LoginUtil.get().isUserLogin()) {
//                onUserLogined(LoginUtil.get().userId());
//            } else {
//                onUserLoginCanceled();
//            }
            return;
        }
        //正常状态
        onActResult(requestCode, resultCode, data);
    }

    protected void onUserLogined(String userId) {
    }

    protected void onActResult(int requestCode, int resultCode, Intent data) {
    }

}
