//package com.example.slbapppay.wx.wxapi;
//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.example.slbapppay.R;
//import com.example.slbapppay.wx.Constants;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
//
//    private IWXAPI api;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
//
//    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
//        api.handleIntent(getIntent(), this);
//    }
//
//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		setIntent(intent);
//        api.handleIntent(intent, this);
//	}
//
//	@Override
//	public void onReq(BaseReq req) {
//		BaseReq req1 = req;
//	}
//
//	@Override
//	public void onResp(BaseResp resp) {
//		BaseResp resp1 = resp;
//	}
//}