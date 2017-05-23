package com.enduo.ndonline.productlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.WebActivity;
import com.enduo.ndonline.bean.BorrowDetailBean;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.my.bank.MyBankActivity;
import com.enduo.ndonline.my.security.CertificationActivity;
import com.enduo.ndonline.my.security.ChangeLoginPasswordActivity;
import com.enduo.ndonline.my.security.SetPayPasswordActivity;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.productlist.borrow_data.BorrowDataActivity;
import com.enduo.ndonline.productlist.investmentrecord.InvestmentRecordListActivity;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.P2pUtils;
import com.enduo.ndonline.utils.PayWordsUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 月月盈
 * Created by Administrator on 2016/12/27.
 */

public class MouthMouthAcitivity extends BaseActivity implements LoadingLayout.OnReloadListener {

    String id;
    String word = null;

    @Bind(R.id.title_biao)
    TextView titleBiao;
    @Bind(R.id.biaoti)
    RelativeLayout biaoti;
    @Bind(R.id.mouth_LoadingLayout)
    LoadingLayout mouthLoadingLayout;
    @Bind(R.id.title_biao2)
    TextView titleBiao2;
    @Bind(R.id.annualRate)
    TextView annualRate;
    @Bind(R.id.minInvestAmount)
    TextView minInvestAmount;
    @Bind(R.id.interestBearingTime)
    TextView interestBearingTime;
    @Bind(R.id.deadline)
    TextView deadline;
    @Bind(R.id.shengyumoney)
    TextView shengyumoney;
    @Bind(R.id.introduce)
    TextView introduce;
    @Bind(R.id.detail)
    TextView detail;
    @Bind(R.id.risk)
    TextView risk;
    @Bind(R.id.fuwutiaolie)
    TextView fuwutiaolie;
    @Bind(R.id.investlist)
    TextView investlist;
    @Bind(R.id.textview_money)
    TextView textviewMoney;
    @Bind(R.id.hiti_islogin)
    EditText hitiIslogin;
    @Bind(R.id.touzi_button)
    Button touziButton;
    @Bind(R.id.cbox)
    CheckBox cbox;
    @Bind(R.id.bgaaa)
    View bgaaa;
    @Bind(R.id.llll)
    LinearLayout llll;
    @Bind(R.id.bglll)
    LinearLayout bglll;

    BorrowDetailBean bean;
    String deadliness;
    int borrowType;

    Dialog dialogPay;

    DecimalFormat df = new DecimalFormat("######0.00");

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouthmouth);
        ButterKnife.bind(this);
        titleBiao.setText("项目详情");
        mouthLoadingLayout.setOnReloadListener(this);

        init();

    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        borrowType = intent.getIntExtra("borrowType", 0);

        if (borrowType == ProductType.DAYDAY) {
            bgaaa.setBackground(Utils.getDrawble(this, R.mipmap.icon_tty));
            llll.setBackground(Utils.getDrawble(this, R.mipmap.icon_tty));
            bglll.setBackgroundColor(Utils.getColor(this, R.color.dayday_bg));
            touziButton.setBackgroundColor(Utils.getColor(this, R.color.dayday_btn_bg));

        } else if (borrowType == ProductType.SeasonSeaon) {
            bgaaa.setBackground(Utils.getDrawble(this, R.mipmap.icon_jjm));
            llll.setBackground(Utils.getDrawble(this, R.mipmap.icon_jjm));
            bglll.setBackgroundColor(Utils.getColor(this, R.color.season_bg));
            touziButton.setBackgroundColor(Utils.getColor(this, R.color.season_btn_bg));
        } else if (borrowType == ProductType.MouthMouth) {
            bgaaa.setBackground(Utils.getDrawble(this, R.mipmap.icon_yyz));
            llll.setBackground(Utils.getDrawble(this, R.mipmap.icon_yyz));
            bglll.setBackgroundColor(Utils.getColor(this, R.color.mouth_bg));
            touziButton.setBackgroundColor(Utils.getColor(this, R.color.mouth_btn_bg));
        }


        hitiIslogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Logger.d("beforeTextChanged---" + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Logger.d("onTextChanged---" + s.toString());
                double m = 0;
                if (s.toString() == null || s.toString().trim().length() == 0) {
                    m = 0;
                } else {
                    m = Double.parseDouble(s.toString());
                }


                textviewMoney.setVisibility(View.VISIBLE);
                if (bean.getData().getDeadlineType() == 1) {
                    Logger.d("bean.getData().getDeadlineType() == 1");
                    textviewMoney.setText("预计收益：" + P2pUtils.calculator(m, bean.getData().getAnnualRate(), bean.getData().getDeadline()));
                } else if (bean.getData().getDeadlineType() == 2) {
                    Logger.d("bean.getData().getDeadlineType() == 2");

                    textviewMoney.setText("预计收益：" + P2pUtils.calculator2(m, bean.getData().getAnnualRate(), bean.getData().getDeadline()));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.d("afterTextChanged---" + s.toString());
            }
        });


    }

    private void net() {
        NetWorks.queryBorrowDetail(id, new Subscriber<BorrowDetailBean>() {

            @Override
            public void onStart() {
                mouthLoadingLayout.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {
                biaoti.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                mouthLoadingLayout.setStatus(LoadingLayout.Error);
                Logger.e(e.toString());
            }

            @Override
            public void onNext(BorrowDetailBean s) {
                bean = s;
                if (s.getState().getStatus() == 0) {
                    mouthLoadingLayout.setStatus(LoadingLayout.Success);

                    setData(s);
                } else {
                    mouthLoadingLayout.setStatus(LoadingLayout.Empty);
                }
            }
        });
    }

    private void setData(BorrowDetailBean b) {
        BorrowDetailBean.DataBean d = b.getData();
        titleBiao2.setText(d.getBorrowTitle());

        annualRate.setText(df.format(d.getAnnualRate()) + "");
        minInvestAmount.setText((int) d.getMinInvestAmount() + "起投");
        interestBearingTime.setText(T1changerString.t1chager(d.getInterestBearingTime()));

        deadliness = T1changerString.t2chager(d.getDeadline(), d.getDeadlineType());
        deadline.setText(deadliness);


        shengyumoney.setText("剩余额度：" + T1changerString.t3chager(d.getBorrowAmount() - d.getHasBorrowAmount()));

        if (d.getBorrowAmount() - d.getHasBorrowAmount() <= 0) {
            hitiIslogin.setKeyListener(null);
            textviewMoney.setVisibility(View.VISIBLE);
            textviewMoney.setText("该标已满，没有剩余额度可投！");
        }


        if ((Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {  //登陆

            hitiIslogin.setHint("可用总额:" + (String) SharedPreferencesUtils.getParam(this, "usableAmount", "0"));

        } else {
            hitiIslogin.setHint("登陆后才能投资");
        }

        //  holder.circleProgressbar.setProgress(T1changerString.progress(d.getBorrowStatus(),d.getHasBorrowAmount(),d.getBorrowAmount()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        net();
        if ((Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {  //登陆

            hitiIslogin.setHint("可用余额:" + (String) SharedPreferencesUtils.getParam(this, "usableAmount", "0"));

        } else {
            hitiIslogin.setHint("登陆后才能投资");
        }
    }

    @Override
    public void onReload(View v) {
        biaoti.setVisibility(View.VISIBLE);
        net();
    }

    @OnClick({R.id.introduce, R.id.detail, R.id.fuwutiaolie, R.id.risk, R.id.investlist, R.id.touzi_button})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.introduce:
                intent = new Intent(this, ProductIntroduceActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", "introduce");
                startActivity(intent);
                break;
            case R.id.detail:
                intent = new Intent(this, BorrowDataActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.risk:
                intent = new Intent(this, ProductIntroduceActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", "risk");
                startActivity(intent);
                break;
            case R.id.investlist:
                intent = new Intent(this, InvestmentRecordListActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

                break;
            case R.id.fuwutiaolie:
                intent = new Intent(this, WebActivity.class);
                intent.putExtra("url", NetService.API_SERVER_Url + "wechat/showAgreementAPP.html");
                intent.putExtra("title", "认购协议");
                startActivity(intent);

                break;
            case R.id.touzi_button:  //立即投资

                if (bean.getData().getBorrowAmount() -bean.getData().getHasBorrowAmount() <= 0) {

                    T.ShowToastForShort(MouthMouthAcitivity.this, "该标已满，没有剩余额度可投！");
                    return;
                }




                if ((Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {  //登陆

                    if (!(Boolean) SharedPreferencesUtils.getIsRealName(MouthMouthAcitivity.this)) {
                        intent = new Intent(MouthMouthAcitivity.this, CertificationActivity.class);
                        startActivity(intent);
                        return;
                    }



                    double money = 0;
                    if (LoginRegisterUtils.isNullOrEmpty(hitiIslogin)) {

                    } else {
                        money = Double.parseDouble(hitiIslogin.getText().toString());
                    }

                    if (money < bean.getData().getMinInvestAmount()) {

                        textviewMoney.setVisibility(View.VISIBLE);
                        textviewMoney.setText("最低投资:" + bean.getData().getMinInvestAmount());
                        return;
                    }

                    double anbleM = Double.parseDouble((String) SharedPreferencesUtils.getParam(this, "usableAmount", "0"));
                    if (money > anbleM) {
                        textviewMoney.setVisibility(View.VISIBLE);
                        textviewMoney.setText("可用余额少于投资金额.请先充值");
                        return;
                    }

                    double able = (bean.getData().getBorrowAmount() - bean.getData().getHasBorrowAmount());

                    if (money > able) {
                        textviewMoney.setVisibility(View.VISIBLE);
                        textviewMoney.setText("投资金额不能大于剩余额度.");
                        return;
                    }


                    if (!cbox.isChecked()) {
                        textviewMoney.setVisibility(View.VISIBLE);
                        textviewMoney.setText("需要同意认购协议才能投资.");
                        return;
                    }




                    if (!(Boolean) SharedPreferencesUtils.getParam(MouthMouthAcitivity.this, "payPwd", false)) {
                        intent = new Intent(MouthMouthAcitivity.this, SetPayPasswordActivity.class);
                        startActivity(intent);
                        return;
                    }


                    if (dialogPay == null) {
                        PayWordsUtils payWordsUtils = new PayWordsUtils();
                        dialogPay = payWordsUtils.createProgressDialog(this);
                        payWordsUtils.setPaylistenr(new PayWordsUtils.Paylistenr() {
                            @Override
                            public void onOk(String bank) {
                                //   T.ShowToastForShort(MouthMouthAcitivity.this,bank);
                                word = bank;
                                netPay(bank, hitiIslogin.getText().toString(), id);
                            }
                        });
                    } else {
                        dialogPay.show();
                    }
                } else {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }


    /**
     * @param p  密码
     * @param p1 金额
     * @param p2 标号
     */
    private void netPay(String p, final String p1, String p2) {

        NetWorks.investAjaxBorrow(p, p1, p2, new Subscriber<String>() {
            @Override
            public void onStart() {

                if (dialog == null) {
                    dialog = DialogUtils.createProgressDialog(MouthMouthAcitivity.this, "投标中...");
                } else {
                    dialog.show();
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                T.ShowToastForShort(MouthMouthAcitivity.this, "网络异常");
                dialog.dismiss();
            }

            @Override
            public void onNext(String s) {
                try {
                    JSONObject json = new JSONObject(s);

                    if (json.has("status")) {
                        String status = json.getString("status");
                        if (status.equals("0")) {
                            // 1. 布局文件转换为View对象
                            LayoutInflater inflater = LayoutInflater.from(MouthMouthAcitivity.this);
                            View layout = inflater.inflate(R.layout.success_dialog, null);
                            TextView txt = (TextView) layout.findViewById(R.id.txt);
                            txt.setText("投资" + p1 + "期限" + deadliness);
                            // 2. 新建对话框对象
                            final Dialog dialog2 = new AlertDialog.Builder(MouthMouthAcitivity.this).create();
                            dialog2.setCancelable(true);
                            dialog2.show();
                            dialog2.getWindow().setContentView(layout);

                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            T.ShowToastForShort(MouthMouthAcitivity.this, json.getString("info"));
                        }
                    } else {
                        JSONObject jsonstatus = json.getJSONObject("state");
                        String sat = jsonstatus.getString("status");
                        if (sat.equals("99")) {
                            netLogin();
                        } else {
                            dialog.dismiss();
                            T.ShowToastForShort(MouthMouthAcitivity.this, jsonstatus.getString("info"));
                        }

                    }


                } catch (JSONException e) {
                    //    e.printStackTrace();
                    T.ShowToastForShort(MouthMouthAcitivity.this, "数据异常");
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
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }

                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            netPay(word, hitiIslogin.getText().toString(), id);
                        } else {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            Intent intent = new Intent(MouthMouthAcitivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}
