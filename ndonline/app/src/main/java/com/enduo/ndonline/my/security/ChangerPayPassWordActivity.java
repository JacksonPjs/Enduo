package com.enduo.ndonline.my.security;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.geetest_sdk.SdkUtils2;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.CountDownButtonHelper;
import com.pvj.xlibrary.utils.T;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;


/**
 *  修改支付密码
 */
public class ChangerPayPassWordActivity extends BaseActivity implements SdkUtils2.SdkLister {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.login_phone)
    EditText loginPhone;
    @Bind(R.id.yzm)
    EditText yzm;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.password2)
    EditText password2;
    @Bind(R.id.login_go)
    Button loginGo;
    @Bind(R.id.getcode)
    TextView getcode;
    SdkUtils2 sdkUtils;

    Dialog dialog;

    private CountDownButtonHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word1);
        ButterKnife.bind(this);

        initView();

        if (sdkUtils == null) {
            sdkUtils = new SdkUtils2();
        }
        sdkUtils.setSdkLister(this);

    }

    private void initView() {
        title.setText("修改支付密码");
        loginGo.setText("修改");


        loginPhone.setText(SharedPreferencesUtils.getUserName(this));
        loginPhone.setEnabled(false);
    }


    @OnClick({R.id.getcode, R.id.login_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getcode:
                sdkUtils.verificationNewUserPhone(ChangerPayPassWordActivity.this, ChangerPayPassWordActivity.this, loginPhone);
                break;
            case R.id.login_go:

                if (LoginRegisterUtils.isNullOrEmpty(loginPhone)) {
                    T.ShowToastForShort(this, "手机号码未输入");
                    return;
                }

                if (!LoginRegisterUtils.isPhone(loginPhone)) {
                    T.ShowToastForShort(this, "手机号码不正确");
                    return;
                }

                if (LoginRegisterUtils.isNullOrEmpty(loginPassword)) {
                    T.ShowToastForShort(this, "用户密码未输入");
                    return;
                }

                if (!LoginRegisterUtils.isPassWord(loginPassword)) {
                    T.ShowToastForShort(this, "用户密码格式不合法");
                    return;
                }


                if (!LoginRegisterUtils.equals(loginPassword, password2)) {
                    T.ShowToastForShort(this, "二次手机密码不一致");
                    return;
                }

                if (LoginRegisterUtils.isNullOrEmpty(yzm)) {
                    T.ShowToastForShort(this, "手机验证未输入");
                    return;
                }


                net(loginPhone.getText().toString(), yzm.getText().toString(), loginPassword.getText().toString());

                break;
        }
    }

    private void net(String phone, String telcode, String pass) {
        NetWorks.updateUserPay(phone, telcode, pass, new Subscriber<InfoBean>() {
            @Override
            public void onStart() {
                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(ChangerPayPassWordActivity.this, "修改中...");
                } else {
                    if (dialog != null && !dialog.isShowing()) {
                        dialog.show();
                    }

                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onNext(InfoBean b) {
                if (b.getState().getStatus() == 0) {
                    T.ShowToastForShort(ChangerPayPassWordActivity.this, b.getState().getInfo());
                    finish();
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }  if(b.getState().getStatus() == 99){
                    netLogin();
                }else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    T.ShowToastForShort(ChangerPayPassWordActivity.this, b.getState().getInfo());
                }

            }
        });
    }

    @Override
    public void sdkSuccess(String r) {
        try {
            JSONObject json1 = new JSONObject(r);
            JSONObject json = json1.getJSONObject("state");

            int status = json.getInt("status");
            if (status == 0) {
                T.ShowToastForShort(ChangerPayPassWordActivity.this, json.getString("info"));
                if (helper == null) {
                    helper = new CountDownButtonHelper(getcode,
                            "发送验证码", 60, 1);
                    helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

                        @Override
                        public void finish() {
                            getcode.setEnabled(true);
                        }
                    });
                }

                helper.start();
            } else {
                T.ShowToastForShort(ChangerPayPassWordActivity.this, json.getString("info"));
            }

        } catch (JSONException e) {
            T.ShowToastForShort(ChangerPayPassWordActivity.this, "网络异常，请稍后试");
            e.printStackTrace();
        }
    }


    private void netLogin() {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (dialog!=null&dialog.isShowing()){
                            dialog.dismiss();
                        }
                        T.ShowToastForShort(ChangerPayPassWordActivity.this,"网络异常");
                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            net(loginPhone.getText().toString(), yzm.getText().toString(), loginPassword.getText().toString());
                        } else {
                            if (dialog!=null&dialog.isShowing()){
                                dialog.dismiss();
                            }
                            Intent intent = new Intent(ChangerPayPassWordActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}
