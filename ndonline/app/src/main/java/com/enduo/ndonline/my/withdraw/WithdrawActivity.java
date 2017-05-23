package com.enduo.ndonline.my.withdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.bean.WithdrawBean;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.my.bank.MyBankActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 提现
 * Created by Administrator on 2017/1/5.
 */

public class WithdrawActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.bankname)
    TextView bankname;
    @Bind(R.id.cardtype)
    TextView cardtype;


    @Bind(R.id.money)
    TextView money;
    @Bind(R.id.cardnum)
    TextView cardnum;
    @Bind(R.id.t_moeny)
    EditText tMoeny;
    @Bind(R.id.t_shouyi)
    EditText tShouyi;
    @Bind(R.id.calculator_go)
    Button calculatorGo;
    @Bind(R.id.loadinglayout)
    LoadingLayout loadinglayout;


    Dialog dialog;
    String UsableAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
        title.setText("提现");

        net();


    }


    private void net() {
        NetWorks.userWithdraw(new Subscriber<WithdrawBean>() {
            @Override
            public void onStart() {
                loadinglayout.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                loadinglayout.setStatus(LoadingLayout.Error);
                Logger.json(e.toString());
            }

            @Override
            public void onNext(WithdrawBean s) {
                if (s.getState().getStatus() == 0) {
//                    money.setText(s.getData1().getUsableAmount());
                    UsableAmount=s.getData1().getUsableAmount()+"";
                    bankname.setText(s.getData2().getBankName() +"");
                    cardtype.setText(s.getRealName()+"");
                    tMoeny.addTextChangedListener(new EditTextChangeListener());

                    String str1 = "可提现金额:";
                    String str2 = "" + UsableAmount;
                    String str3 = "元";

                    SpannableStringBuilder builder = new SpannableStringBuilder(str1 + str2 + str3  );
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffa200")),
                            str1.length(), (str1 + str2).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffa200")),
//                            (str1 + str2 + str3).length(), (str1 + str2 + str3 ).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                    money.setText(builder);

                    String aa = s.getData2().getBankCardNo();
                    int n = 4;

                    if (aa.length() > 4) {
                        String b = aa.substring(aa.length() - n, aa.length());

//                        bankname.setText(s.getData2().getBankName() + "(****" + b + ")");
                        cardnum.setText( "****  ****  ****  ****  " + b );
                    } else {
//                        bankname.setText(s.getData2().getBankName() + "(****" + aa + ")");
                        cardnum.setText( "****  ****  ****  ****" + aa );
                    }

                    loadinglayout.setStatus(LoadingLayout.Success);
                } else if (s.getState().getStatus() == 99) {
                    netLogin(0);
                } else {
                    T.ShowToastForShort(WithdrawActivity.this, s.getState().getInfo());
                    loadinglayout.setStatus(LoadingLayout.Error);
                }

                // Logger.json(s);
            }
        });
    }


    private void netLogin(final int style) {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (style == 0) {
                            loadinglayout.setStatus(LoadingLayout.Error);
                        } else {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            if (style == 0) {
                                net();
                            } else {
                                tongLianUserWithdraw();
                            }

                        } else {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            Intent intent = new Intent(WithdrawActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }

    public class EditTextChangeListener implements TextWatcher {
        /**
         * 编辑框的内容发生改变之前的回调方法
         */
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        /**
         * 编辑框的内容正在发生改变时的回调方法 >>用户正在输入
         * 我们可以在这里实时地 通过搜索匹配用户的输入
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        /**
         * 编辑框的内容改变以后,用户没有继续输入时 的回调方法
         */
        @Override
        public void afterTextChanged(Editable editable) {
            String k=UsableAmount;
            double amout = Double.valueOf(k).intValue();

            if (!editable.toString().isEmpty()){
                int ed=Double.valueOf(editable.toString()).intValue();
                if (amout< ed){
                    money.setText( "金额已超出可提现金额");
                    money.setTextColor(getResources().getColor(R.color.dayday_btn_bg));
                }else {
                    String str1 = "可提现金额:";
                    String str2 = "" + UsableAmount;
                    String str3 = "元";
                    money.setTextColor(getResources().getColor(R.color.font_color1));

                    SpannableStringBuilder builder = new SpannableStringBuilder(str1 + str2 + str3  );
                    builder.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffa200")),
                            str1.length(), (str1 + str2).length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    money.setText(builder);
                }
            }

        }
    }

    @OnClick({R.id.calculator_go,R.id.show_about})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calculator_go:
                if (LoginRegisterUtils.isNullOrEmpty(tMoeny)) {
                    T.ShowToastForShort(WithdrawActivity.this, "金额未输入");
                    return;
                }

                if (LoginRegisterUtils.isNullOrEmpty(tShouyi)) {
                    T.ShowToastForShort(WithdrawActivity.this, "交易密码未输入");
                    return;
                }


                tongLianUserWithdraw();
                break;
            case R.id.show_about:

                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("1.账户资金提现手续费2元/笔\n"+"2.未投资金额加收提现金额0.25%手续费");
                builder.setPositiveButton("确定", null);
                builder.show();

                break;
        }



    }


    private void tongLianUserWithdraw() {

        NetWorks.tongLianUserWithdraw(tMoeny.getText().toString(), tShouyi.getText().toString()
                , new Subscriber<InfoBean>() {
                    @Override
                    public void onStart() {
                        if (dialog == null) {
                            dialog = DialogUtils.createProgressDialog(WithdrawActivity.this, "提现中...");
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
                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(InfoBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            finish();
                            T.ShowToastForShort(WithdrawActivity.this, loginBean.getState().getInfo());
                            dialog.dismiss();
                        } else if (loginBean.getState().getStatus() == 99) {
                            netLogin(1);
                        } else {
                            T.ShowToastForShort(WithdrawActivity.this, loginBean.getState().getInfo());
                            dialog.dismiss();
                        }
                    }
                }
        );
    }
}
