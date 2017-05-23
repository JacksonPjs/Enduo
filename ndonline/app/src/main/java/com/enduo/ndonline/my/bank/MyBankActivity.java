package com.enduo.ndonline.my.bank;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.BankListBean;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.CustomDialog;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/27.
 * 我的银行卡
 */

public class MyBankActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.addbank)
    TextView addbank;
    @Bind(R.id.addbank_contaitnr)
    View addbankContair;
    @Bind(R.id.bank_contair)
    View bankContair;
    @Bind(R.id.bankname)
    TextView bankname;
    @Bind(R.id.banknum)
    TextView banknum;
    @Bind(R.id.jiebang)
    TextView jiebang;
    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.layout_contiant)
    LoadingLayout layoutContiant;

    BankListBean bak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybank);
        ButterKnife.bind(this);
        title.setText("我的银行卡");
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPreferencesUtils.getIsBank(this)) {
            addbankContair.setVisibility(View.GONE);
            bankContair.setVisibility(View.VISIBLE);

            netList();
        } else {
            layoutContiant.setStatus(LoadingLayout.Success);
            addbankContair.setVisibility(View.VISIBLE);
            bankContair.setVisibility(View.GONE);
        }

    }

    // 获取银行卡列表
    private void netList() {
        NetWorks.selectBankCard(new Subscriber<BankListBean>() {

            @Override
            public void onStart() {
                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.json(e.toString());
                T.ShowToastForShort(MyBankActivity.this, "网络异常");
                layoutContiant.setStatus(LoadingLayout.Error);
            }

            @Override
            public void onNext(BankListBean bean) {
                if (bean.getState().getStatus() == 0) {

                    bak = bean;
                    bankname.setText(bean.getData().get(0).getBankName());
                    name.setText("持卡人："+bean.getRealName()+"");


                    String aa = bean.getData().get(0).getBankCardNo();
                    int n = 4;

                    if (aa.length() > 4) {
                        String b = aa.substring(aa.length() - n, aa.length());

                        banknum.setText("**** **** **** " + b);
                    } else {
                        banknum.setText("**** **** **** " + aa);
                    }

                    layoutContiant.setStatus(LoadingLayout.Success);
                } else if (bean.getState().getStatus() == 99) {
                    netLogin(0);
                } else {
                    layoutContiant.setStatus(LoadingLayout.Error);
                    T.ShowToastForShort(MyBankActivity.this, bean.getState().getInfo());
                }

            }
        });
    }

    @OnClick({R.id.addbank, R.id.jiebang})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addbank:
                if (SharedPreferencesUtils.getIsRealName(this)) {
                    Intent intent = new Intent(MyBankActivity.this, AddBankActivity.class);
                    startActivity(intent);
                } else {
                    T.ShowToastForShort(this, "请先实名认证才能添加银行卡");
                }
                break;

            case R.id.jiebang:
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                //    builder.setMessage("这个就是自定义的提示框");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //设置你的操作事项
                    deleteBankCard(bak.getData().get(0).getId());
                }
            });

                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.create().show();

                break;
        }


    }

    private void netLogin(final int sytle) {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (sytle == 0) {
                            layoutContiant.setStatus(LoadingLayout.Error);
                        } else if (sytle == 1) {
                            dialog.dismiss();
                        }

                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            if (sytle == 0) {
                                netList();
                            } else if (sytle == 1) {
                                deleteBankCard(bak.getData().get(0).getId());
                            }

                        } else {
                            if (sytle == 1) {
                                dialog.dismiss();
                            }

                            SharedPreferencesUtils.setIsLogin(MyBankActivity.this, false);
                            T.ShowToastForShort(MyBankActivity.this, "账号存在异常，需重新登陆！！");
//                            Intent intent = new Intent(MyBankActivity.this, LoginActivity.class);
//                            startActivity(intent);
                        }
                    }
                }
        );
    }

    Dialog dialog;

    private void deleteBankCard(String id) {
        NetWorks.deleteBankCard(id, new Subscriber<InfoBean>() {
            @Override
            public void onStart() {
                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(MyBankActivity.this, "解绑中");
                } else {
                    dialog.show();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();

            }

            @Override
            public void onNext(InfoBean infoBean) {
                if (infoBean.getState().getStatus() == 0) {
                    dialog.dismiss();

                    SharedPreferencesUtils.setIsBank(MyBankActivity.this, false);
                    addbankContair.setVisibility(View.VISIBLE);
                    bankContair.setVisibility(View.GONE);
                } else if (infoBean.getState().getStatus() == 99) {
                    netLogin(1);
                } else {
                    dialog.dismiss();
                }
            }
        });
    }

}
