package com.enduo.ndonline.my.bank;

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
import com.enduo.ndonline.bean.Bank;
import com.enduo.ndonline.bean.CertificationBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.my.security.CertificationActivity;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.BankDialog;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/27.
 */

public class AddBankActivity extends BaseActivity implements BankDialog.Banklistenr {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.bankbtn)
    TextView bankbtn;
    @Bind(R.id.login_go)
    Button loginGo;

    Dialog dialogBank;

    Dialog dialog;

    Bank bank;
    @Bind(R.id.card)
    EditText card;
    @Bind(R.id.layout_contiant_bank)
    LoadingLayout layoutContiant;
    @Bind(R.id.zhihang)
    EditText zhihang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbank);
        ButterKnife.bind(this);
        title.setText("我的银行卡");

    }

    @Override
    protected void onResume() {
        super.onResume();
        net();
    }

    @Override
    public void onBank(Bank bank) {
        this.bank = bank;
        bankbtn.setText(bank.getValue());
    }

    @OnClick({R.id.bankbtn, R.id.login_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bankbtn:
                if (dialogBank == null) {
                    BankDialog bankDialog = new BankDialog();
                    bankDialog.setBanklistenr(this);
                    dialogBank = bankDialog.createProgressDialog(this);
                } else {
                    dialogBank.show();
                }
                break;
            case R.id.login_go:
                if (bank == null) {
                    T.ShowToastForShort(AddBankActivity.this, "请选择所属银行...");
                    return;
                }

                if (LoginRegisterUtils.isNullOrEmpty(card)) {
                    T.ShowToastForShort(AddBankActivity.this, "请输入银行卡号");
                    return;
                }


                if (card.getText().length()<16) {
                T.ShowToastForShort(AddBankActivity.this, "银行卡号输入不正确");
                return;
            }

                bankNet(card.getText().toString());


                break;
        }
    }

    // 请求绑定银行卡
    private void bankNet(String carno) {
            StringBuilder sb = new StringBuilder();
            sb.append(" _ed_token_");
            sb.append("=");
            sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context,"token",""));
            sb.append(";");

            sb.append(" _ed_username_");
            sb.append("=");
            sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context,"name",""));
            sb.append(";");

            sb.append(" _ed_cellphone_");
            sb.append("=");
            sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context,"phone",""));
            sb.append(";");

            if (dialog ==null){
                dialog = DialogUtils.createProgressDialog(AddBankActivity.this,"添加银行卡中...");
            }else{
              if(!dialog.isShowing()){
                  dialog.show();
              }
            }
            OkHttpUtils
                    .post()
                    .url(NetService.API_SERVER+"/insertBankCard.html")
                    .addHeader("Cookie", sb.toString())
                    .addParams("bankCode", bank.getKey())
                    .addParams("bankName", bank.getValue())
                    .addParams("bankCardNo", carno)
                    .build()
                    .execute(new Callback<String>() {

                        @Override
                        public String parseNetworkResponse(Response response) throws IOException {
                            return response.body().string();
                        }

                        @Override
                        public void onError(Request request, Exception e) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onResponse(String o) {
                            Logger.json(o);

                            dialog.dismiss();
                            try {
                                JSONObject json = new JSONObject((String)o.toString());

                                JSONObject sata = json.getJSONObject("state");

                                int s =sata.getInt("status");
                                if (s==0){
                                    SharedPreferencesUtils.setParam(AddBankActivity.this,"tBankCardlist",true);
                                    dialog.dismiss();
                                    finish();
                                }else if(s==99){
                                         netLogin(1);
                                }else {
                                    dialog.dismiss();
                                    T.ShowToastForShort(AddBankActivity.this,sata.getString("info"));
                                }
                            } catch (JSONException e) {
                                if(dialog!=null&&dialog.isShowing()){
                                    dialog.dismiss();
                                }
                                T.ShowToastForShort(AddBankActivity.this,"数据异常!");
                             //   e.printStackTrace();
                            }

                        }


                    });
    }

    // 获取身份信息
    private void net() {
        NetWorks.userPerson(new Subscriber<CertificationBean>() {
            @Override
            public void onStart() {

                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                layoutContiant.setStatus(LoadingLayout.Error);
                T.ShowToastForShort(AddBankActivity.this, "网络异常");
            }

            @Override
            public void onNext(CertificationBean s) {
                if (s.getState().getStatus() == 0) {
                    name.setText(s.getTPerson().getRealName());
                    layoutContiant.setStatus(LoadingLayout.Success);
                } else if (s.getState().getStatus() == 99) {
                    netLogin(0);
                } else {
                    T.ShowToastForShort(AddBankActivity.this, s.getState().getInfo());
                    layoutContiant.setStatus(LoadingLayout.Error);
                }
            }
        });
    }


    /**
     *
     * @param style   0  是 请求实名      1 ， 是请求插入银行卡
     */
    private void netLogin(final int style) {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(dialog!=null&&dialog.isShowing()){
                            dialog.dismiss();
                        }

                        if(style==0){
                            layoutContiant.setStatus(LoadingLayout.Error);
                        }
                        //  layoutContiantZl.setStatus(LoadingLayout.Error);
                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            if (style==0){
                                net();
                            }else if(style==1){
                                bankNet(card.getText().toString());
                            }

                        } else {
                            if(dialog!=null&&dialog.isShowing()){
                                dialog.dismiss();
                            }
                            Intent intent = new Intent(AddBankActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}
