package com.enduo.ndonline.more.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.net.HttpLoggingInterceptor;
import com.enduo.ndonline.utils.P2pUtils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.StringUtil;
import com.pvj.xlibrary.utils.T;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收益计算器
 * Created by Administrator on 2016/12/28.
 */

public class CalculatorActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.t_moeny)
    EditText tMoeny;
    @Bind(R.id.t_shouyi)
    EditText tShouyi;
    @Bind(R.id.t_time)
    EditText tTime;
    @Bind(R.id.t_total)
    TextView tTotal;
    @Bind(R.id.calculator_go)
    TextView calculatorGo;
    @Bind(R.id.spinner)
    Spinner spinner;

    int  postion  =  0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);

        title.setText("收益计算器");
        spinner.setOnItemSelectedListener(this);

    }

    @OnClick(R.id.calculator_go)
    public void onClick() {

        if (StringUtil.isNullOrEmpty(tMoeny.getText().toString())) {
            T.ShowToastForShort(this, "投资金额未输入");
            return;
        }
        Double investAmount = Double.parseDouble(tMoeny.getText().toString());

        try {
            if (StringUtil.isNullOrEmpty(tShouyi.getText().toString())) {
                T.ShowToastForShort(this, "年化收益未输入");
                return;
            }
        } catch (Exception e) {
            T.ShowToastForShort(this, "年化收益输入不合法");
            return;
        }

        Double apr = Double.parseDouble(tShouyi.getText().toString());

        if (StringUtil.isNullOrEmpty(tTime.getText().toString())) {
            T.ShowToastForShort(this, "投资期限未输入");
            return;
        }
        int period = Integer.parseInt(tTime.getText().toString());
        Double total = 0.0 ;
        if(postion==0){
             total = P2pUtils.calculator2(investAmount, apr, period);
        }else {
            total = P2pUtils.calculator(investAmount, apr, period);
        }


        BigDecimal bigDecimalA = new BigDecimal(total);
        BigDecimal bigDecimalB = new BigDecimal(investAmount);
        // 加 +
        bigDecimalA = bigDecimalA.add(bigDecimalB);

        DecimalFormat df = new DecimalFormat("######0.00");

        String ss = df.format(total);

        tTotal.setText(ss);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Logger.d("spinner-position:"+position);
        postion = position ;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
