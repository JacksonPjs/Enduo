package com.enduo.ndonline.login2register;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassWordActivity3 extends BaseActivity {


    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.forget_password3)
    EditText forgetPassword3;
    @Bind(R.id.forget_password3_s)
    EditText forgetPassword3S;
    @Bind(R.id.forget_go3)
    Button forgetGo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass_word3);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        title.setText("找回密码");
    }


    @OnClick(R.id.forget_go3)
    public void onClick() {


    }
}
