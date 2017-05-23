package com.enduo.ndonline.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/27.
 * 邮箱认证
 */

public class MailBoxActivity extends BaseActivity{
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.btn_code)
    Button button;

    @Bind(R.id.layout_contiant_bank)
    LoadingLayout layoutContiant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmail);
        ButterKnife.bind(this);

        init();
    }
    private void init(){
        title.setText("邮箱绑定");
        layoutContiant.setStatus(LoadingLayout.Success);
    }

    @OnClick({R.id.btn_code, R.id.login_go})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_code:
                //new倒计时对象,总共的时间,每隔多少秒更新一次时间
                final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(10000,1000);
                myCountDownTimer.start();
                break;
        }
    }

    //复写倒计时
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            button.setClickable(false);
            button.setBackground(Utils.getDrawble(MailBoxActivity.this, R.drawable.button_border_hui));
            button.setText(l/1000+"s");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            button.setText("重新获取");
            button.setBackground(Utils.getDrawble(MailBoxActivity.this, R.drawable.button_border_selector));

            //设置可点击
            button.setClickable(true);
        }
    }
}
