package com.enduo.ndonline.productlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.my.security.ChangeLoginPasswordActivity;
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
 * 我要投资 investAjaxBorrow
 * Created by Administrator on 2017/1/2.
 */

public class Investmenting extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.user_money)
    TextView userMoney;
    @Bind(R.id.ye)
    TextView ye;
    @Bind(R.id.je)
    TextView je;
    @Bind(R.id.jypass)
    EditText jypass;
    @Bind(R.id.toubiao)
    Button toubiao;

    String team_amount;
    String buy_amount;
    String deadliness;
    String id;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investmenting);
        ButterKnife.bind(this);
        title.setText("我要投标");

        Intent intent = getIntent();
        team_amount = intent.getStringExtra("team_amount");
        buy_amount = intent.getStringExtra("buy_amount");
        deadliness = intent.getStringExtra("deadliness");
        id = intent.getStringExtra("id");

        ye.setText(team_amount);
        je.setText(buy_amount);
        userMoney.setText((String) SharedPreferencesUtils.getParam(this, "usableAmount", "0"));

    }

    @OnClick(R.id.toubiao)
    public void onClick() {
        if (!(Boolean) SharedPreferencesUtils.getParam(Investmenting.this, "payPwd", false)) {
            Intent intent = new Intent(Investmenting.this, ChangeLoginPasswordActivity.class);
            startActivity(intent);
        } else {
            if (LoginRegisterUtils.isNullOrEmpty(jypass)) {
                T.ShowToastForShort(Investmenting.this, "请输入交易密码");
                return;
            }

            //    net(jypass.getText().toString(),buy_amount,id);

        }


    }


//   private void  net(String p ,final String  p1 ,String p2){
//
//        NetWorks.investAjaxBorrow(p, p1, p2, new Subscriber<InfoBean>() {
//            @Override
//            public void onStart() {
//
//                if (dialog==null){
//                   dialog = DialogUtils.createProgressDialog(Investmenting.this,"投标中...");
//                }else{
//                    dialog.show();
//                }
//            }
//
//            @Override
//            public void onCompleted() {
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Logger.e(e.toString());
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onNext(InfoBean s) {
//               if (s.getState().getStatus()==0){
//
//                   // 1. 布局文件转换为View对象
//                   LayoutInflater inflater = LayoutInflater.from(Investmenting.this);
//                   View layout = inflater.inflate(R.layout.success_dialog, null);
//                   TextView txt = (TextView) layout.findViewById(R.id.txt);
//                   txt.setText("投资"+p1+"期限"+deadliness);
//                   // 2. 新建对话框对象
//                   final Dialog dialog = new AlertDialog.Builder(Investmenting.this).create();
//                   dialog.setCancelable(true);
//                   dialog.show();
//                   dialog.getWindow().setContentView(layout);
//
//               }else{
//                   T.ShowToastForShort(Investmenting.this,s.getState().getInfo());
//               }
//            }
//        });}

}
