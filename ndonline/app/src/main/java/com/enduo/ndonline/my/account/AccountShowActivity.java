package com.enduo.ndonline.my.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.AconntBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.ui.widget.ProgressView;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;


/**
 * 账户总览
 * Created by Administrator on 2016/12/28.
 */

public class AccountShowActivity extends BaseActivity {

    @Bind(R.id.day1)
    TextView day1;
    @Bind(R.id.total2)
    TextView total2;
    @Bind(R.id.total1)
    TextView total1;
    @Bind(R.id.usableAmount)
    TextView usableAmount;
    @Bind(R.id.frozenAmount)
    TextView frozenAmount;
    @Bind(R.id.dsbj)
    TextView dsbj;
    @Bind(R.id.day_shouyi)
    TextView dayShouyi;
    @Bind(R.id.tatol_shouyi)
    TextView tatolShouyi;
    @Bind(R.id.ydsy)
    TextView ydsy;
    @Bind(R.id.jujianfei)
    TextView jujianfei;
    @Bind(R.id.feeAmount)
    TextView feeAmount;
    @Bind(R.id.layout_contiant_zl)
    LoadingLayout layoutContiantZl;
    @Bind(R.id.ProgressView)
    ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountshow);
        ButterKnife.bind(this);


    }
    @OnClick({R.id.btnBack})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBack:
                finish();
                break;
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        net();
    }

    private void net() {
        NetWorks.selectUserNumber(new Subscriber<AconntBean>() {
            @Override
            public void onStart() {

                layoutContiantZl.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                layoutContiantZl.setStatus(LoadingLayout.Error);
                Logger.json(e.toString());
            }

            @Override
            public void onNext(AconntBean s) {

                if(s.getState().getStatus()==0){
                    java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                    nf.setGroupingUsed(false);

                    day1.setText(s.getDay1()+"");

                    total1.setText("￥"+nf.format(s.getTotal1())+"");
                    total2.setText(s.getTotal2()+"");
                    usableAmount.setText(nf.format(s.getTAccount().getUsableAmount())+"");
                    frozenAmount.setText(s.getTAccount().getFrozenAmount()+"");
                    dsbj.setText(s.getDsbj()+"");
                    dayShouyi.setText(s.getDay1()+"");
                    tatolShouyi.setText(s.getTotal2()+"");
                    ydsy.setText(s.getYdsy()+"");
                    jujianfei.setText(s.getJujianfei()+"");
                    feeAmount.setText(s.getFeeAmount()+"");

                    layoutContiantZl.setStatus(LoadingLayout.Success);
                    double usa=s.getTAccount().getUsableAmount();
                    double allamount=s.getTAccount().getUsableAmount()+s.getTAccount().getFrozenAmount();
                    if (allamount==0){
                        progressView.setProgress(101);
                    }else{
                        double max=usa/allamount;
                        progressView.setProgress(max);

                    }
                }else if(s.getState().getStatus()==99){
                    netLogin();
                }




            }
        });
    }


    private void netLogin(){

        NetWorks.login( SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        layoutContiantZl.setStatus(LoadingLayout.Error);
                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                         if (loginBean.getState().getStatus()==0){
                             net();
                         }else{
                             Intent intent = new Intent(AccountShowActivity.this, LoginActivity.class);
                             startActivity(intent);
                         }
                    }
                }
        );
    }
}
