package com.enduo.ndonline.login2register;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.login_phone)
    EditText loginPhone;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login_go)
    Button loginGo;
    @Bind(R.id.login_register)
    TextView loginRegister;
    @Bind(R.id.login_forget)
    TextView loginForget;
    @Bind(R.id.title)
    TextView title;

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        MyApplication.instance.addActivity(this);
        title.setText("登录");
    }

    @OnClick({R.id.login_go, R.id.login_register, R.id.login_forget})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
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


                login(loginPhone.getText().toString(),loginPassword.getText().toString());

                break;
            case R.id.login_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_forget:
                intent = new Intent(this, ForgetPassWordActivity1.class);
                startActivity(intent);
                break;
        }
    }


    public void login(String name ,final String  passoword) {
        NetWorks.login(name, passoword, new Subscriber<LoginBean>() {
            @Override
            public void onStart() {
                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(LoginActivity.this, "登陆中...");
                } else {
                        dialog.show();
                }
            }

            @Override
            public void onCompleted() {
                dialog.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                T.ShowToastForLong(LoginActivity.this,"网络异常");
                dialog.dismiss();
                Logger.e(e.toString());
            }

            @Override
            public void onNext(LoginBean s) {
                     if (s.getState().getStatus()==0){

                         SharedPreferencesUtils.savaUser(LoginActivity.this,s,passoword);
                         finish();
                         T.ShowToastForLong(LoginActivity.this,"登陆成功");
                     }else{
                         T.ShowToastForLong(LoginActivity.this,s.getState().getInfo());
                     }


            }
        });
    }
}

