package com.enduo.ndonline.login2register;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.RegisterBean;
import com.enduo.ndonline.geetest_sdk.SdkUtils;
import com.enduo.ndonline.geetest_sdk.SdkUtils2;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.pvj.xlibrary.utils.CountDownButtonHelper;
import com.pvj.xlibrary.utils.T;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class ForgetPassWordActivity1 extends BaseActivity implements SdkUtils2.SdkLister {

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
        title.setText("找回密码");
    }


    @OnClick({R.id.getcode, R.id.login_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getcode:
                sdkUtils.verificationNewUserPhone(ForgetPassWordActivity1.this, ForgetPassWordActivity1.this, loginPhone);
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
        NetWorks.updateforGetPass(phone, telcode, pass, new Subscriber<InfoBean>() {
            @Override
            public void onStart() {
                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(ForgetPassWordActivity1.this, "修改中...");
                } else {
                    if (dialog != null && !dialog.isShowing()) {
                        dialog.show();
                    }

                }
            }

            @Override
            public void onCompleted() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
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
                    T.ShowToastForShort(ForgetPassWordActivity1.this, b.getState().getInfo());
                    finish();
                } else {
                    T.ShowToastForShort(ForgetPassWordActivity1.this, b.getState().getInfo());
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
                T.ShowToastForShort(ForgetPassWordActivity1.this, json.getString("info"));
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
                T.ShowToastForShort(ForgetPassWordActivity1.this, json.getString("info"));
            }

        } catch (JSONException e) {
            T.ShowToastForShort(ForgetPassWordActivity1.this, "网络异常，请稍后试");
            e.printStackTrace();
        }
    }

}
