package com.enduo.ndonline.login2register;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.bean.RegisterBean;
import com.enduo.ndonline.geetest_sdk.SdkUtils;
import com.enduo.ndonline.geetest_sdk.SdkUtilsNEW;
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

public class RegisterActivity extends BaseActivity implements SdkUtilsNEW.SdkLister {
    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.get_regist)
    TextView getRegist;


    private CountDownButtonHelper helper;
    SdkUtilsNEW sdkUtils;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.yzm)
    EditText yzm;
    @Bind(R.id.tjr)
    EditText tjr;
    @Bind(R.id.regist_go)
    Button registGo;

    Dialog dialog;


    Dialog dialog2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        title.setText("新用户注册");

        if (sdkUtils == null) {
            sdkUtils = new SdkUtilsNEW();
        }

        sdkUtils.setSdkLister(this);

        MyApplication.instance.addActivity(this);

    }


    @OnClick({R.id.get_regist, R.id.regist_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_regist:

                sdkUtils.verificationNewUserPhone(RegisterActivity.this, RegisterActivity.this, phone);
                break;
            case R.id.regist_go:
                if (LoginRegisterUtils.isNullOrEmpty(phone)) {
                    T.ShowToastForShort(this, "手机号码未输入");
                    return;
                }

                if (!LoginRegisterUtils.isPhone(phone)) {
                    T.ShowToastForShort(this, "手机号码不正确");
                    return;
                }

                if (LoginRegisterUtils.isNullOrEmpty(password)) {
                    T.ShowToastForShort(this, "用户密码未输入");
                    return;
                }

                if (!LoginRegisterUtils.isPassWord(password)) {
                    T.ShowToastForShort(this, "用户密码格式不合法");
                    return;
                }

                if (LoginRegisterUtils.isNullOrEmpty(yzm)) {
                    T.ShowToastForShort(this, "手机验证未输入");
                    return;
                }


                if (LoginRegisterUtils.isNullOrEmpty(tjr)) { // 无推荐人情况
                    regist(phone.getText().toString(),password.getText().toString(),yzm.getText().toString(),"");
                } else {  // 有推荐人
                    chackRefereeUser(tjr.getText().toString());
                }

                break;
        }
    }

    private void regist(final String cellPhone,final String pwd, String regCode, String regReferee) {

        NetWorks.regist(cellPhone, pwd, regCode, regReferee, new Subscriber<InfoBean>() {
            @Override
            public void onStart() {
                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(RegisterActivity.this, "注册中...");
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
                if (b.getState().getStatus()== 0){
                    T.ShowToastForShort(RegisterActivity.this,"注册成功");
                    login(cellPhone,pwd);
               //     finish();
                }else{
                    T.ShowToastForShort(RegisterActivity.this,b.getState().getInfo());
                }

            }
        });

    }


    public void login(String name ,final String  passoword) {
        NetWorks.login(name, passoword, new Subscriber<LoginBean>() {
            @Override
            public void onStart() {
                if (dialog2 == null) {
                    dialog2 = DialogUtils.createProgressDialog(RegisterActivity.this, "登陆中...");
                } else {
                    dialog2.show();
                }
            }

            @Override
            public void onCompleted() {
                dialog2.dismiss();
            }

            @Override
            public void onError(Throwable e) {
                T.ShowToastForLong(RegisterActivity.this,"网络异常");
                dialog2.dismiss();
                Logger.e(e.toString());
            }

            @Override
            public void onNext(LoginBean s) {
                if (s.getState().getStatus()==0){

                    SharedPreferencesUtils.savaUser(RegisterActivity.this,s,passoword);
                 //   finish();
                    MyApplication.instance.Allfinlish();
                 //  T.ShowToastForLong(RegisterActivity.this,"登陆成功");
                }else{
                    T.ShowToastForLong(RegisterActivity.this,s.getState().getInfo());
                }


            }
        });
    }


    // 验证推荐人是否正确
    private void chackRefereeUser( final String regReferee) {
        NetWorks.chackRefereeUser(regReferee, new Subscriber<String>() {
            @Override
            public void onStart() {
                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(RegisterActivity.this, "注册中...");
                } else {
                    dialog.show();
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
            public void onNext(String s) {
                  // {"info":"推荐人不存在","status":"22"}

                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject json2 = json.getJSONObject("state");

                    int status = json2.getInt("status");
                    if (status==0){
                        regist(phone.getText().toString(),password.getText().toString(),yzm.getText().toString(),regReferee);
                    }else{
                        dialog.dismiss();
                         T.ShowToastForShort(RegisterActivity.this,"推荐人不存在,如无推荐人可不填");
                      //  toastMsg("手机号码已注册，请更换手机号码");
                    }

                } catch (JSONException e) {
                    dialog.dismiss();
                    T.ShowToastForShort(RegisterActivity.this,"数据异常，请稍后试");
                    //  T.ShowToastForShort(activity,"数据异常，请稍后试");
                    Logger.e(e.toString());
                    //    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void sdkSuccess(String r) {
        // {"info":"操作频繁，请稍后重试...","status":"21","value":"63"}
        // {"info":"手机验证码发送成功，请及时查收手机短信！","status":"0"}
        {
            //  {"info":"可用的手机号码","status":"0"}



            try {
                JSONObject json1 = new JSONObject(r);
                JSONObject json = json1.getJSONObject("state");

                int status = json.getInt("status");
                if (status == 0) {
                    T.ShowToastForShort(RegisterActivity.this, json.getString("info"));
                    if (helper == null) {
                        helper = new CountDownButtonHelper(getRegist,
                                "发送验证码", 60, 1);
                        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

                            @Override
                            public void finish() {
                                getRegist.setEnabled(true);
                            }
                        });
                    }

                    helper.start();
                } else {
                    T.ShowToastForShort(RegisterActivity.this, json.getString("info"));
                }

            } catch (JSONException e) {
                T.ShowToastForShort(RegisterActivity.this, "网络异常，请稍后试");
                e.printStackTrace();
            }
        }
    }
}
