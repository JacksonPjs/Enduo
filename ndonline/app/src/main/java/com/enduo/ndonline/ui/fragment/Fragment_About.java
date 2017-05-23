package com.enduo.ndonline.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.WebActivity;
import com.enduo.ndonline.WebActivityJS;
import com.enduo.ndonline.fragment.BaseFragment;
import com.enduo.ndonline.more.announcement.AnnouncementListActivity;
import com.enduo.ndonline.more.calculator.CalculatorActivity;
import com.enduo.ndonline.net.NetService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/21.
 * 关于页
 */

public class Fragment_About extends BaseFragment {
    @Bind(R.id.calculator)
    TextView calculator;
    @Bind(R.id.help)
    TextView help;
    @Bind(R.id.guide)
    TextView guide;
    @Bind(R.id.summary)
    TextView summary;
    @Bind(R.id.brand)
    TextView brand;
    @Bind(R.id.contact)
    TextView contact;
    @Bind(R.id.notice)
    TextView notice;
    @Bind(R.id.title)
    TextView title;
    /**
     * find view from layout and set listener
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_four,
                null);
        ButterKnife.bind(this, rootView);
        title.setText("关于");
        return rootView;
    }

    /**
     * init data
     */
    @Override
    public void fillDate() {

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

    @OnClick({R.id.calculator, R.id.help, R.id.guide, R.id.summary, R.id.brand, R.id.contact, R.id.notice})
    public void onClick(View view) {
        Intent intent = null ;
        switch (view.getId()) {
            case R.id.calculator:
                intent = new Intent(getActivity(), CalculatorActivity.class);
                startActivity(intent);
                break;
            case R.id.help:
                intent = new Intent(getActivity(), WebActivityJS.class);
                intent.putExtra("url", "http://192.168.1.238:8080/jp/app/texthxApp.html");
                intent.putExtra("title", "充值测试");
                startActivity(intent);

                break;
            case R.id.guide:
                break;
            case R.id.summary:
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", NetService.API_SERVER_Url+"wechat/aboutUsAPP.html");
                intent.putExtra("title", "公司简介");
                startActivity(intent);
                break;
            case R.id.brand:
                break;
            case R.id.contact:
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", NetService.API_SERVER_Url+"wechat/contactUsAPP.html");
                intent.putExtra("title", "联系我们");
                startActivity(intent);
                break;
            case R.id.notice:
                intent = new Intent(getActivity(), AnnouncementListActivity.class);
                startActivity(intent);
                break;
        }
    }
}

