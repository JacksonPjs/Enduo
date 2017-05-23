package com.enduo.ndonline.my.chager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.R;
import com.enduo.ndonline.WebActivity;
import com.enduo.ndonline.WebActivityJS;
import com.enduo.ndonline.bean.ChagerBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.my.security.CertificationActivity;
import com.enduo.ndonline.my.share.ShareActivity;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by pvj on 2016/12/27.
 * 充值
 */

public class ChagerActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.t_moeny)
    EditText tMoeny;
    @Bind(R.id.weixinpay)
    RelativeLayout weixinpay;
    @Bind(R.id.alipay)
    RelativeLayout alipay;
    @Bind(R.id.calculator_go)
    Button calculatorGo;
    @Bind(R.id.weixcheck)
    ImageView weixcheck;
    @Bind(R.id.alicheck)
    ImageView alicheck;

    String payway = "jdQpay";

    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wxd930ea5d5a258f4f";
    @Bind(R.id.bankcheck)
    ImageView bankcheck;
    @Bind(R.id.bankpay)
    RelativeLayout bankpay;
    private IWXAPI api;


    Dialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chager);
        ButterKnife.bind(this);
        title.setText("充值");

        MyApplication.instance.addActivity(this);

  //      api = WXAPIFactory.createWXAPI(this, APP_ID);

        // 将该app注册到微信
  //      api.registerApp(APP_ID);


    }

    @OnClick({R.id.weixinpay, R.id.alipay, R.id.calculator_go,R.id.bankpay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weixinpay:
                payway = "wxApp";
                weixcheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method));
                alicheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method_nor));
                bankcheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method_nor));
                break;
            case R.id.alipay:
                payway = "alipayApp";
                alicheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method));
                weixcheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method_nor));
                bankcheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method_nor));
                break;
            case R.id.bankpay:
                payway = "jdQpay";
                bankcheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method));
                alicheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method_nor));
                weixcheck.setImageDrawable(Utils.getDrawble(this, R.mipmap.icon_method_nor));
                break;
            case R.id.calculator_go:

                if (LoginRegisterUtils.isNullOrEmpty(tMoeny)) {
                    T.ShowToastForShort(ChagerActivity.this, "请输入充值金额");
                    return;
                }

                net();
                break;
        }
    }

    private void net() {

        NetWorks.wxpay(payway,tMoeny.getText().toString(), new Subscriber<ChagerBean>() {
            @Override
            public void onStart() {

                if (dialog ==null){
                    dialog = DialogUtils.createProgressDialog(ChagerActivity.this,"充值请求中");
                }else {
                    dialog.show();
                }

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
                T.ShowToastForShort(ChagerActivity.this,"网络异常");
                Logger.e(e.toString());
            }

            @Override
            public void onNext(ChagerBean s) {
               if (s.getState().getStatus()==66){
                   dialog.dismiss();
                  Intent   intent = new Intent(ChagerActivity.this, WebActivityJS.class);
                   intent.putExtra("url", s.getData());
                   intent.putExtra("title", "快捷支付");
                   startActivity(intent);
               }else if(s.getState().getStatus()==99){
                   netLogin();
               }else if(s.getState().getStatus()==0){
                   T.ShowToastForShort(ChagerActivity.this,"后台还未通");
               }else {
                   T.ShowToastForShort(ChagerActivity.this,s.getState().getInfo());
               }

            }
        });
    }



    //  0 是 获取   1是设置
    private void netLogin() {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(dialog!=null){
                            dialog.dismiss();
                        }


                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                           net();
                        }else {
                            if(dialog!=null){
                                dialog.dismiss();
                            }

                            SharedPreferencesUtils.setIsLogin(ChagerActivity.this,false);
                        }
                    }
                }
        );
    }


    private void weipay(String content) {

        try {
            JSONObject json = new JSONObject(content);

            if (null != json && !json.has("retcode")) {
                PayReq req = new PayReq();
                //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                req.appId = json.getString("appid");
                req.partnerId = json.getString("partnerid");
                req.prepayId = json.getString("prepayid");
                req.nonceStr = json.getString("noncestr");
                req.timeStamp = json.getString("timestamp");
                req.packageValue = json.getString("package");
                req.sign = json.getString("sign");
                req.extData = "app data"; // optional
                Toast.makeText(ChagerActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
            } else {
                Logger.e("充值异常");
            }
        } catch (JSONException e) {
            //  e.printStackTrace();

            Logger.e("PAY_GET" + e.getMessage());
            Toast.makeText(ChagerActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}
