package com.enduo.ndonline.my.security;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AlphabetIndexer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.CertificationBean;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.URLDecoder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import rx.Subscriber;

/**
 * 实名认证
 * Created by pvj on 2016/12/27.
 */

public class CertificationActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.sfz)
    EditText sfz;
    @Bind(R.id.rez_go)
    Button rezGo;
    @Bind(R.id.loadinglayout)
    LoadingLayout loadinglayout;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
        title.setText("实名认证");

        if ((Boolean) SharedPreferencesUtils.getParam(this, "tPerson", false)) {
            net();
            rezGo.setBackground(Utils.getDrawble(this, R.drawable.button_border_hui));
            rezGo.setEnabled(false);
            rezGo.setText("已认证");
        } else {
            loadinglayout.setStatus(LoadingLayout.Success);
        }


    }

    private void net() {
        NetWorks.userPerson(new Subscriber<CertificationBean>() {
            @Override
            public void onStart() {
                loadinglayout.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                loadinglayout.setStatus(LoadingLayout.Error);
                T.ShowToastForShort(CertificationActivity.this, "网络异常");
            }

            @Override
            public void onNext(CertificationBean s) {
                if (s.getState().getStatus() == 0) {
                    userName.setText(s.getTPerson().getRealName());
                    userName.setKeyListener(null);
                    sfz.setText(s.getTPerson().getCardNo());
                    sfz.setKeyListener(null);
                    loadinglayout.setStatus(LoadingLayout.Success);

                } else if (s.getState().getStatus() == 99) {
                    netLogin(0);
                } else {
                    T.ShowToastForShort(CertificationActivity.this, s.getState().getInfo());
                }
            }
        });
    }


    @OnClick(R.id.rez_go)
    public void onClick() {
        if (LoginRegisterUtils.isNullOrEmpty(userName)) {
            T.ShowToastForShort(this, "请填写姓名");
            return;
        }

        if (LoginRegisterUtils.isNullOrEmpty(sfz)) {
            T.ShowToastForShort(this, "请填写身份证");
            return;
        }


        goShiMin(userName.getText().toString(), sfz.getText().toString());

    }


    public void goShiMin(String name, String no) {

        StringBuilder sb = new StringBuilder();
        sb.append(" _ed_token_");
        sb.append("=");
        sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context, "token", ""));
        sb.append(";");

        sb.append(" _ed_username_");
        sb.append("=");
        sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context, "name", ""));
        sb.append(";");

        sb.append(" _ed_cellphone_");
        sb.append("=");
        sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context, "phone", ""));
        sb.append(";");

        if (dialog == null) {
            dialog = DialogUtils.createProgressDialog(CertificationActivity.this, "实名中...");
        } else {
            dialog.show();
        }
        OkHttpUtils
                .post()
                .url(NetService.API_SERVER + "/regPerson.html")
                .addHeader("Cookie", sb.toString())
                .addParams("realName", name)
                .addParams("cardNo", no)
                .build()
                .execute(new Callback<String>() {

                    @Override
                    public String parseNetworkResponse(Response response) throws IOException {
                        return response.body().string();
                    }

                    @Override
                    public void onError(Request request, Exception e) {
                        Logger.e(e.toString());
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String o) {

                      Logger.json(o);
                        try {
                            JSONObject json = new JSONObject((String) o.toString());

                            JSONObject sata = json.getJSONObject("state");

                            int s = sata.getInt("status");
                            if (s == 0) {
                                SharedPreferencesUtils.setParam(CertificationActivity.this, "tPerson", true);
                                dialog.dismiss();
                                finish();
                            } else if (s == 99) {
                                netLogin(1);
                            } else {
                                dialog.dismiss();
                                T.ShowToastForShort(CertificationActivity.this, sata.getString("info"));
                            }
                        } catch (JSONException e) {
                            //  e.printStackTrace();
                            dialog.dismiss();
                            T.ShowToastForShort(CertificationActivity.this, "数据异常，请联系客服！");
                        }

                    }
                });

    }


    //  0 是 获取   1是设置
    private void netLogin(final int style) {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        T.ShowToastForShort(CertificationActivity.this, "网络异常");
                        Logger.e(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            if (style == 0) {
                                net();
                            } else {
                                goShiMin(userName.getText().toString(), sfz.getText().toString());
                            }
                        } else {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            SharedPreferencesUtils.setIsLogin(CertificationActivity.this, false);
                        }
                    }
                }
        );
    }

}
