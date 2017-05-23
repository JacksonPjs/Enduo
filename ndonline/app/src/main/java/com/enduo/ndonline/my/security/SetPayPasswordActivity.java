package com.enduo.ndonline.my.security;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.login2register.LoginActivity;
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

/**
 * 设置支付密码
 * Created by pvj on 2016/12/27.
 */

public class SetPayPasswordActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.fword)
    EditText fword;
    @Bind(R.id.firt_word)
    LinearLayout firtWord;
    @Bind(R.id.tword)
    EditText tword;
    @Bind(R.id.sword)
    EditText sword;
    @Bind(R.id.chagerloginpwd_go)
    Button chagerloginpwdGo;

    Dialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeoginpassword);
        ButterKnife.bind(this);

        if((Boolean) SharedPreferencesUtils.getParam(this,"payPwd",false)){
            title.setText("修改交易密码");
        }else{
            title.setText("设置交易密码");
            firtWord.setVisibility(View.GONE);
        }
        title.setText("设置交易密码");
    }

    @OnClick(R.id.chagerloginpwd_go)
    public void onClick() {
        if(LoginRegisterUtils.isNullOrEmpty(tword)){
            T.ShowToastForShort(this,"密码不能为空");
            return;
        }

        if(!LoginRegisterUtils.equals(tword,sword)){
            T.ShowToastForShort(this,"二次密码不一致");
            return;
        }

        net(tword.getText().toString(),sword.getText().toString());
    }


    //设置交易密码
    public  void net(String  p , String p2 ){
        NetWorks.setUserPayPwd(p, p2, new Subscriber<InfoBean>() {
            @Override
            public void onStart() {
                if (dialog==null){  dialog = DialogUtils.createProgressDialog(SetPayPasswordActivity.this,"设置中...");}
              else {
                    dialog.show();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                dialog.dismiss();
                T.ShowToastForShort(SetPayPasswordActivity.this,"网络异常");

            }

            @Override
            public void onNext(InfoBean s) {
               if (s.getState().getStatus()==0){
                   SharedPreferencesUtils.setParam(SetPayPasswordActivity.this,"payPwd",true);
                   finish();
                   dialog.dismiss();
                   T.ShowToastForShort(SetPayPasswordActivity.this,"设置成功");
               }else if(s.getState().getStatus()==99){

                   netLogin();
               }else{
                   dialog.dismiss();
                   T.ShowToastForShort(SetPayPasswordActivity.this,s.getState().getInfo());
               }


            }
        });
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
                        T.ShowToastForShort(SetPayPasswordActivity.this,"网络异常");
                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            net(tword.getText().toString(),sword.getText().toString());
                        } else {
                            if (dialog!=null&dialog.isShowing()){
                                dialog.dismiss();
                            }
                            Intent intent = new Intent(SetPayPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}
