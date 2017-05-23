package com.enduo.ndonline.ui.activity;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.WebActivity;
import com.enduo.ndonline.bean.BorrowDetailBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.fragment.one.Fragment_day;
import com.enduo.ndonline.fragment.one.ViewPagerFramentAdapter;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.my.bank.AddBankActivity;
import com.enduo.ndonline.my.bank.MyBankActivity;
import com.enduo.ndonline.my.chager.ChagerActivity;
import com.enduo.ndonline.my.security.CertificationActivity;
import com.enduo.ndonline.my.security.SetPayPasswordActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.productlist.Fragment_commonly;
import com.enduo.ndonline.productlist.MouthMouthAcitivity;
import com.enduo.ndonline.productlist.T1changerString;
import com.enduo.ndonline.ui.fragment.Fragemt_Explain;
import com.enduo.ndonline.ui.fragment.Fragemt_Notes;
import com.enduo.ndonline.ui.fragment.Fragment_Data;
import com.enduo.ndonline.ui.widget.CountdownTextView;
import com.enduo.ndonline.ui.widget.CustomPopupWindow;
import com.enduo.ndonline.ui.widget.ExpandableTextView;
import com.enduo.ndonline.ui.widget.GradationScrollView;
import com.enduo.ndonline.ui.widget.MyScrollView;
import com.enduo.ndonline.ui.widget.countdownview.CountdownView;
import com.enduo.ndonline.utils.DateUtils;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/10.
 */

public class DetailsActivity extends BaseActivity implements ViewPager.OnPageChangeListener, CountdownView.OnCountdownEndListener {
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.day_text)
    TextView dayText;
    @Bind(R.id.month_text)
    TextView monthText;
    @Bind(R.id.season_text)
    TextView seasonText;
    @Bind(R.id.tv_expand)
    TextView tv_expand;
    @Bind(R.id.icon_expan)
    ImageView icon;
    @Bind(R.id.scroll)
    MyScrollView scrollView;
    @Bind(R.id.gotopay)
    Button topay;
    @Bind(R.id.buy)
    TextView buy;
    @Bind(R.id.ll_popupLayout)
    LinearLayout ll_Popup;
    @Bind(R.id.select)
    Button select;
    @Bind(R.id.pay_recyclerView)
    RecyclerView payrecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.progress)
    TextView progress;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.maxAmount)
    TextView maxAmount;
    @Bind(R.id.percent)
    TextView percent;
    @Bind(R.id.data)
    TextView data;
    @Bind(R.id.interestBearingTime)
    TextView interestBearingTime;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.borrowAmount)
    TextView borrowAmount;
    @Bind(R.id.paytype)
    TextView paytype;

    @Bind(R.id.countdowntime)
    CountdownView countdowntime;

    CustomPopupWindow mPop;
    BorrowDetailBean bean;

//    ;


    String id;
    boolean flag;

    List<Fragment> fragmentList;
    ViewPagerFramentAdapter viewPagerFramentAdapter;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        fragmentList = new ArrayList<>();

        viewPagerFramentAdapter = new ViewPagerFramentAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewPagerFramentAdapter);
        viewPager.setOnPageChangeListener(this);

        Fragemt_Explain fragment_day1 = new Fragemt_Explain();
        Bundle bundle1 = new Bundle();
//                    bundle1.putSerializable("data", (Serializable) oneBean);
        bundle1.putString("id", id);
        fragment_day1.setArguments(bundle1);
        fragmentList.add(fragment_day1);

        Fragment_Data fragment_day2 = new Fragment_Data();
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", id);
        fragment_day2.setArguments(bundle2);
        fragmentList.add(fragment_day2);

        Fragemt_Notes fragment_day3 = new Fragemt_Notes();
        Bundle bundle3 = new Bundle();
        bundle3.putString("id", id);
        fragment_day3.setArguments(bundle3);
        fragmentList.add(fragment_day3);

        viewPagerFramentAdapter.notifyDataSetChanged();


        mPop = new CustomPopupWindow(this);
        if (!(Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {
            topay.setText(getResources().getString(R.string.logintopay));
            topay.setBackground(null);
            topay.setTextColor(getResources().getColor(R.color.mouth_btn_bg));
        } else {
            topay.setText(getResources().getString(R.string.topay));
            topay.setTextColor(getResources().getColor(R.color.ll_nor));

            topay.setBackground(getResources().getDrawable(R.drawable.button_border));
        }

        net();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        net();
    }


    @OnClick({R.id.buy, R.id.gotopay, R.id.icon_expan, R.id.select, R.id.rl_pop, R.id.day_text, R.id.season_text,
            R.id.month_text, R.id.back})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.buy:


                if (!(Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    if (bean.getData().getBorrowAmount() - bean.getData().getHasBorrowAmount() <= 0) {

                        T.ShowToastForShort(DetailsActivity.this, "该标已满，没有剩余额度可投！");
                    } else if (!SharedPreferencesUtils.getIsRealName(this)) {
                        intent = new Intent(DetailsActivity.this, CertificationActivity.class);
                        startActivity(intent);
                        T.ShowToastForShort(this, "请先实名认证才能投资");
                    } else if (!(Boolean) SharedPreferencesUtils.getParam(DetailsActivity.this, "payPwd", false)) {
                        intent = new Intent(DetailsActivity.this, SetPayPasswordActivity.class);
                        startActivity(intent);
                        return;
                    } else if (!SharedPreferencesUtils.getIsBank(this)) {
                        T.ShowToastForShort(this, "请先添加银行卡才能投资");
                        intent = new Intent(DetailsActivity.this, AddBankActivity.class);
                        startActivity(intent);
                    } else if (!(Boolean) SharedPreferencesUtils.getParam(DetailsActivity.this, "payPwd", false)) {
                        T.ShowToastForShort(this, "请先设置交易密码才能投资");
                        intent = new Intent(DetailsActivity.this, SetPayPasswordActivity.class);
                        startActivity(intent);
                    } else {
                        intent = new Intent(this, PayActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", (Serializable) bean);
                        bundle.putString("id", id + "");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                }
                break;

            case R.id.gotopay:
                if (!(Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {
                    intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(this, ChagerActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.select:
                Animation animation2;
                animation2 = AnimationUtils.loadAnimation(this,
                        R.anim.score_business_query_exit);
                ll_Popup.setVisibility(View.GONE);   // 取出布局
                ll_Popup.startAnimation(animation2); // 开始退出动画
                break;
            case R.id.icon_expan:
                if (!flag) {
                    flag = true;
                    tv_expand.setEllipsize(null);  //展开
                    tv_expand.setSingleLine(false);
                } else {
                    flag = false;
                    tv_expand.setEllipsize(TextUtils.TruncateAt.END);  //收缩
                    tv_expand.setLines(3);
                }
                handler.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                                 }
                             }

                );
                break;
            case R.id.day_text:
                viewPager.setCurrentItem(0);
                break;
            case R.id.month_text:
                viewPager.setCurrentItem(1);
                break;
            case R.id.season_text:
                viewPager.setCurrentItem(2);
                break;

        }
    }

    private void net() {
        if (!(Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {
            topay.setText(getResources().getString(R.string.logintopay));
            topay.setBackground(null);
            topay.setTextColor(getResources().getColor(R.color.mouth_btn_bg));
        } else {
            topay.setText(getResources().getString(R.string.topay));
            topay.setTextColor(getResources().getColor(R.color.ll_nor));
            topay.setBackground(getResources().getDrawable(R.drawable.button_border));
        }
        NetWorks.queryBorrowDetail(id, new Subscriber<BorrowDetailBean>() {

            @Override
            public void onStart() {
            }

            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
            }

            @Override
            public void onNext(BorrowDetailBean s) {
                bean = s;
                if (s.getState().getStatus() == 0) {

                    setData(s);
                } else {
                }
            }
        });
    }

    private void setData(BorrowDetailBean b) {


        DecimalFormat df = new DecimalFormat("######0.00");
        BorrowDetailBean.DataBean d = b.getData();
        title.setText(d.getBorrowTitle());
//
        percent.setText(df.format(d.getAnnualRate()) + "%");
        Spanned text = Html.fromHtml(d.getIntroductionInfos());
        tv_expand.setText(text);
//        minInvestAmount.setText((int) d.getMinInvestAmount() + "起投");
        interestBearingTime.setText(T1changerString.t1chager(d.getInterestBearingTime()));
//
        String deadliness = T1changerString.t2chager(d.getDeadline(), d.getDeadlineType());
        data.setText(deadliness);
        double hasborrwamount = d.getHasBorrowAmount();
        double BorrowAmount = d.getBorrowAmount();
        double progressi = hasborrwamount / BorrowAmount * 10000;
        int progressint = (int) progressi;
        progressBar.setProgress(progressint);
        float protext = (float) (progressi / 100);
        progress.setText(protext + "%");
        if (d.getBorrowAmount() >= 10000) {
            borrowAmount.setText("项目金额：" + d.getBorrowAmount() / 10000 + "万");

        } else {
            borrowAmount.setText("项目金额：" + d.getBorrowAmount() + "");

        }


        maxAmount.setText("剩余额度：" + T1changerString.t3chager(d.getBorrowAmount() - d.getHasBorrowAmount()));
        paytype.setText(T1changerString.t4chager(d.getRepayType()));
        long millionSeconds = com.pvj.xlibrary.utils.DateUtils.getTimeSecond(d.getRemainTime() + "");

        countdowntime.start(millionSeconds);

        countdowntime.setOnCountdownEndListener(this);

        if (d.getBorrowStatus() == 3) {
            buy.setBackgroundColor(this.getResources().getColor(R.color.text_org));
            buy.setText("立即投资");

        } else {
            buy.setBackgroundColor(this.getResources().getColor(R.color.bar_clor));
            buy.setClickable(false);
            switch (d.getBorrowStatus()){
                case 2:
                    buy.setText("即将开标");

                    break;
                case 4:
                    buy.setText("满标审核中");

                    break;
                case 5:
                    buy.setText("正在还款");

                    break;
                case 6:
                    buy.setText("还款结束");

                    break;
                case 9:
                    buy.setText("已流标");

                    break;
            }

//            buy.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }

        if (d.getBorrowAmount() - d.getHasBorrowAmount() <= 0) {
//            hitiIslogin.setKeyListener(null);
//            textviewMoney.setVisibility(View.VISIBLE);
//            textviewMoney.setText("该标已满，没有剩余额度可投！");
        }


        if ((Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {  //登陆
            tv_money.setText("" + (String) SharedPreferencesUtils.getParam(this, "usableAmount", "0"));
//            hitiIslogin.setHint("可用总额:" + (String) SharedPreferencesUtils.getParam(this, "usableAmount", "0"));

        } else {
//            hitiIslogin.setHint("登陆后才能投资");
        }

        //  holder.circleProgressbar.setProgress(T1changerString.progress(d.getBorrowStatus(),d.getHasBorrowAmount(),d.getBorrowAmount()));
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            dayText.setTextColor(Utils.getColor(this, R.color.colorPrimary));
            monthText.setTextColor(Utils.getColor(this, R.color.font_color1));
            seasonText.setTextColor(Utils.getColor(this, R.color.font_color1));


            ObjectAnimator animator = ObjectAnimator.ofFloat(dayText, "rotation", 0, 360, 360);
            animator.setDuration(1000);
            animator.start();
        } else if (position == 1) {
            monthText.setTextColor(Utils.getColor(this, R.color.mouth));
            dayText.setTextColor(Utils.getColor(this, R.color.font_color1));
            seasonText.setTextColor(Utils.getColor(this, R.color.font_color1));

            ObjectAnimator animator = ObjectAnimator.ofFloat(monthText, "rotation", 0, 360, 360);
            animator.setDuration(1000);
            animator.start();
        } else if (position == 2) {
            seasonText.setTextColor(Utils.getColor(this, R.color.season));
            monthText.setTextColor(Utils.getColor(this, R.color.font_color1));
            dayText.setTextColor(Utils.getColor(this, R.color.font_color1));

            ObjectAnimator animator = ObjectAnimator.ofFloat(seasonText, "rotation", 0, 360, 360);
            animator.setDuration(1000);
            animator.start();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Details Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    @Override
    public void onEnd(CountdownView cv) {
        Object tag = cv.getTag();
        if (bean.getData().getBorrowStatus() == 2) {
            buy.setBackgroundColor(this.getResources().getColor(R.color.text_org));
            buy.setClickable(true);
            buy.setText("立即投资");
        }


    }
}
