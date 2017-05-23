package com.enduo.ndonline.wxapi;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.pvj.xlibrary.utils.T;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


		api.handleIntent(getIntent(), this);


	}

	@Override
	public void onReq(BaseReq baseReq) {
		T.ShowToastForShort(this,"微信回掉onReq");
	}

	@Override
	public void onResp(BaseResp baseResp) {

		T.ShowToastForShort(this,"onResp"+String.valueOf(baseResp.errCode));
	}
}
