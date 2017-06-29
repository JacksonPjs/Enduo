package com.enduo.ndonline.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.CenterIndexBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.fragment.BaseFragment;
import com.enduo.ndonline.my.account.AccountShowActivity;
import com.enduo.ndonline.my.bank.MyBankActivity;
import com.enduo.ndonline.my.cashflow.CashFlowActivity;
import com.enduo.ndonline.my.chager.ChagerActivity;
import com.enduo.ndonline.my.investment.InvestmentActivity;
import com.enduo.ndonline.my.message.MyMessageActivity;
import com.enduo.ndonline.my.security.SecurityActivity;
import com.enduo.ndonline.my.set.SetActivity;
import com.enduo.ndonline.my.share.ShareActivity;
import com.enduo.ndonline.my.withdraw.WithdrawActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.ui.activity.Activity_discount;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/21.
 * 我的
 */

public class Fragment_Mine extends BaseFragment {


    //    @Bind(R.id.user_head)
//    CircleImageView userHead;
    @Bind(R.id.chager)
    TextView chager;
    @Bind(R.id.count_to)
    LinearLayout countTo;
    @Bind(R.id.touzi_to)
    LinearLayout touziTo;
    @Bind(R.id.bank_to)
    LinearLayout bankTo;
    @Bind(R.id.money_to)
    LinearLayout moneyTo;
    @Bind(R.id.anquan_to)
    LinearLayout anquanTo;
    @Bind(R.id.measgg_to)
    LinearLayout measggTo;
    @Bind(R.id.share_to)
    LinearLayout shareTo;
    @Bind(R.id.set)
    ImageView set;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.toatal)
    TextView toatal;
    @Bind(R.id.eableuse)
    TextView eableuse;
    @Bind(R.id.toatal2)
    TextView toatal2;
    @Bind(R.id.eye_set)
    ImageView eyeSet;

    @Bind(R.id.withdraw)
    View withdraw;
    boolean IsGone=false;
    /**
     * find view from layout and set listener
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mine,
                null);
        ButterKnife.bind(this, rootView);

        //标题内容  ChagerActivity


        return rootView;
    }

    /**
     * init data
     */
    @Override
    public void fillDate() {

    }


    @Override
    public void onResume() {
        super.onResume();
        name.setText((String) SharedPreferencesUtils.getParam(getActivity(), "name", ""));
        toatal.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total1", "0"));
        eableuse.setText((String) SharedPreferencesUtils.getParam(getActivity(), "usableAmount", "0"));
        toatal2.setText((String) SharedPreferencesUtils.getParam(getActivity(), "toatal2", "0"));

        // userHead

//        Glide.with(getActivity()).load(NetService.API_SERVER_Main + (String) SharedPreferencesUtils.getParam(getActivity(), "images", "")).
//                placeholder(R.mipmap.user_img)
//                .error(R.mipmap.user_img)
//                .into(userHead);

        selectUserIndex();

    }

    /**
     * network request
     */
    @Override
    public void requestData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.count_to, R.id.withdraw, R.id.chager, R.id.touzi_to, R.id.bank_to, R.id.money_to,
            R.id.anquan_to, R.id.measgg_to, R.id.share_to, R.id.set,R.id.eye_set})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.eye_set:
                if (!IsGone){
                    eyeSet.setImageDrawable(getResources().getDrawable(R.mipmap.icon_eye_gone));
                    toatal.setText("--");
                    eableuse.setText("--");
                    toatal2.setText("--");
                    IsGone=true;
                }else {
                    eyeSet.setImageDrawable(getResources().getDrawable(R.mipmap.icon_eye));
                    toatal.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total1", "0"));
                    eableuse.setText((String) SharedPreferencesUtils.getParam(getActivity(), "usableAmount", "0"));
                    toatal2.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total2", "0"));
                    IsGone=false;
                }

                break;

            case R.id.count_to:
                //账户总览（资金总览）
                intent = new Intent(getActivity(), AccountShowActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.set:
                //安全设置
                intent = new Intent(getActivity(), SecurityActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.touzi_to:
                //我的投资
                intent = new Intent(getActivity(), InvestmentActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.bank_to:
                //我的银行卡
                intent = new Intent(getActivity(), MyBankActivity.class);
                getActivity().startActivity(intent);

                break;
            case R.id.chager:
                //充值
                intent = new Intent(getActivity(), ChagerActivity.class);
                getActivity().startActivity(intent);

                break;
            case R.id.withdraw:
                //提现
                if (SharedPreferencesUtils.getIsBank(getContext())) {
                    intent = new Intent(getActivity(), WithdrawActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    T.ShowToastForShort(getContext(), "提现前，需要添加银行卡.");
                }

                break;
            case R.id.money_to:
                //资金流水（我的账单）
                intent = new Intent(getActivity(), CashFlowActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.anquan_to:
//
                //优惠券
                intent = new Intent(getActivity(), Activity_discount.class);
                getActivity().startActivity(intent);
                break;
            case R.id.measgg_to:
                //我的消息
                intent = new Intent(getActivity(), MyMessageActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.share_to:
                //分享有礼
                intent = new Intent(getActivity(), ShareActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            name.setText((String) SharedPreferencesUtils.getParam(getActivity(), "name", ""));
            toatal.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total1", "0"));
            eableuse.setText((String) SharedPreferencesUtils.getParam(getActivity(), "usableAmount", "0"));

            // userHead

//            Glide.with(getActivity()).load(NetService.API_SERVER_Main + (String) SharedPreferencesUtils.getParam(getActivity(), "images", "")).
//                    placeholder(R.mipmap.user_img)
//                    .error(R.mipmap.user_img)
//                    .into(userHead);

            selectUserIndex();
        }
    }

    private void selectUserIndex() {

        NetWorks.selectUserIndex(new Subscriber<CenterIndexBean>() {
                                     @Override
                                     public void onCompleted() {

                                     }

                                     @Override
                                     public void onError(Throwable e) {

                                         Logger.json(e.toString());
                                     }

                                     @Override
                                     public void onNext(CenterIndexBean loginBean) {
                                         if (loginBean.getState().getStatus() == 0) {
                                             SharedPreferencesUtils.savaUser2(getActivity(), loginBean);
                                             name.setText((String) SharedPreferencesUtils.getParam(getActivity(), "name", ""));
                                             toatal.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total1", "0"));
                                             eableuse.setText((String) SharedPreferencesUtils.getParam(getActivity(), "usableAmount", "0"));
                                             toatal2.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total2", "0"));
                                         } else if (loginBean.getState().getStatus() == 99) {
                                             netLogin();
                                         }
                                     }
                                 }
        );
    }

    private void netLogin() {

        NetWorks.login(SharedPreferencesUtils.getUserName(getContext()),
                SharedPreferencesUtils.getPassword(getContext()), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            SharedPreferencesUtils.savaUser(getActivity(), loginBean, SharedPreferencesUtils.getPassword(getActivity()));
                            name.setText((String) SharedPreferencesUtils.getParam(getActivity(), "name", ""));
                            toatal.setText((String) SharedPreferencesUtils.getParam(getActivity(), "total1", "0"));
                            toatal2.setText((String) SharedPreferencesUtils.getParam(getActivity(), "toatal2", "0"));
                        } else {
                            SharedPreferencesUtils.setParam(getContext(), "islogin", false);
                        }
                    }
                }
        );
    }
}