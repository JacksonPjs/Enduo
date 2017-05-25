package com.enduo.ndonline.my.security;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.my.bank.MyBankActivity;
import com.enduo.ndonline.ui.activity.MailBoxActivity;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 安全中心
 * Created by pvj on 2016/12/27.
 */

public class SecurityActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.mail_to)
    TextView mailTo;

    @Bind(R.id.certification_tip)
    TextView certificationTip;
    @Bind(R.id.phone_tip)
    TextView phoneTip;@Bind(R.id.bank_tip)
    TextView bankTip;


    @Bind(R.id.changer_pwd_to)
    TextView changerPwdTo;
    @Bind(R.id.chager_payword_to)
    TextView chagerPaywordTo;
    @Bind(R.id.bank_go)
    ImageView bankGo;
    @Bind(R.id.certification_go)
    ImageView certificationGo;
    @Bind(R.id.phone_go)
    ImageView phoneGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        title.setText("安全设置");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Drawable drawable = Utils.getDrawble(this, R.mipmap.icon_certification);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth() / 2, drawable.getMinimumHeight() / 2);


//        phoneTo.setCompoundDrawables(null, null, drawable, null);

        certificationTip.setText((String) SharedPreferencesUtils.getParam(this, "realname", ""));

        if ((Boolean) SharedPreferencesUtils.getParam(this, "tPerson", false)) {
            phoneGo.setImageDrawable(drawable);
            phoneTip.setText((String) SharedPreferencesUtils.getParam(this, "phone", ""));

        }
        if ((Boolean) SharedPreferencesUtils.getParam(this, "email", false)) {
            mailTo.setCompoundDrawables(null, null, drawable, null);
        }

        if ((Boolean) SharedPreferencesUtils.getParam(this, "tBankCardlist", false)) {

            bankGo.setImageDrawable(drawable);
            String aa = (String) SharedPreferencesUtils.getParam(this, "bankcardno", "");
            int n = 4;

            if (aa.length() > 4) {
                String b = aa.substring(aa.length() - n, aa.length());

                bankTip.setText("**** **** **** " + b);
            } else {
                bankTip.setText("**** **** **** " + aa);
            }
//            bankTip.setText((String) SharedPreferencesUtils.getParam(this, "bankcardno", ""));
        }

        if ((Boolean) SharedPreferencesUtils.getParam(this, "tPerson", false)) {
            certificationGo.setImageDrawable(drawable);

        }

        if ((Boolean) SharedPreferencesUtils.getParam(this, "payPwd", false)) {
            chagerPaywordTo.setText("修改交易密码");

        } else {
            chagerPaywordTo.setText("设置交易密码");

        }


    }

    @OnClick({R.id.mail_to, R.id.certification_rl, R.id.phone_rl, R.id.bank_rl, R.id.changer_pwd_to,
            R.id.chager_payword_to, R.id.exit})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.mail_to:
                //邮箱认证
                intent = new Intent(this, MailBoxActivity.class);
                startActivity(intent);
                break;
            case R.id.certification_rl:
                //实名认证
                intent = new Intent(this, CertificationActivity.class);
                startActivity(intent);
                break;
            case R.id.phone_rl:
                break;
            case R.id.bank_rl:
                //银行卡认证
                intent = new Intent(this, MyBankActivity.class);
                startActivity(intent);
                break;
            case R.id.changer_pwd_to:
                intent = new Intent(this, ChangeLoginPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.chager_payword_to:
                if ((Boolean) SharedPreferencesUtils.getParam(this, "payPwd", false)) {
                    intent = new Intent(this, ChangerPayPassWordActivity.class);
                    startActivity(intent);

                } else {
                    intent = new Intent(this, SetPayPasswordActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.exit:
                SharedPreferencesUtils.clearAll(this);
                finish();
                break;
        }
    }


}
