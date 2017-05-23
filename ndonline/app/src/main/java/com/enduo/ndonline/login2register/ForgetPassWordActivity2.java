package com.enduo.ndonline.login2register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassWordActivity2 extends BaseActivity {


    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.phone_tv2)
    TextView phoneTv2;
    @Bind(R.id.forget_yzm)
    EditText forgetYzm;
    @Bind(R.id.forget_get)
    Button forgetGet;
    @Bind(R.id.forget_go2)
    Button forgetGo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word2);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        title.setText("找回密码");
    }


    @OnClick({R.id.forget_get, R.id.forget_go2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forget_get:
                break;
            case R.id.forget_go2:
                break;
        }
    }
}
