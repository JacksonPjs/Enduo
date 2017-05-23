package com.enduo.ndonline.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.fragment.one.ViewPagerFramentAdapter;
import com.enduo.ndonline.productlist.Fragment_commonly;
import com.enduo.ndonline.productlist.Fragment_new;
import com.enduo.ndonline.ui.fragment.Fragment_NoUseDiscount;
import com.enduo.ndonline.ui.fragment.Fragment_OutOfDateDisount;
import com.enduo.ndonline.ui.fragment.Fragment_UseDiscount;
import com.pvj.xlibrary.loadinglayout.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/15.
 */
/*
* 优惠券*/

public class Activity_discount extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.putong_team)
    TextView putongTeam;
    @Bind(R.id.xinshou_team)
    TextView xinshouTeam;
    @Bind(R.id.jilu_team)
    TextView jiluTeam;
    @Bind(R.id.biao_viewpager)
    ViewPager biaoViewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        ButterKnife.bind(this);
        title.setText("优惠券");
        init();

    }

    private  void init(){
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_NoUseDiscount());
        fragmentList.add(new Fragment_UseDiscount());
        fragmentList.add(new Fragment_OutOfDateDisount());
        ViewPagerFramentAdapter adapter = new ViewPagerFramentAdapter(getSupportFragmentManager(),fragmentList);
        biaoViewpager.setAdapter(adapter);
        biaoViewpager.setOnPageChangeListener(this);

    }
    @OnClick({R.id.putong_team, R.id.xinshou_team, R.id.jilu_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.putong_team:
                biaoViewpager.setCurrentItem(0);
                break;
            case R.id.xinshou_team:
                biaoViewpager.setCurrentItem(1);
                break;
            case R.id.jilu_team:
                biaoViewpager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switchBtn(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void switchBtn(int position){
        if (position==0){
            putongTeam.setTextColor(Utils.getColor(this,R.color.colorPrimary));
            jiluTeam.setTextColor(Utils.getColor(this,R.color.text_newbie));
            xinshouTeam.setTextColor(Utils.getColor(this,R.color.text_newbie));
        }else if (position==1){
            putongTeam.setTextColor(Utils.getColor(this,R.color.text_newbie));
            jiluTeam.setTextColor(Utils.getColor(this,R.color.text_newbie));
            xinshouTeam.setTextColor(Utils.getColor(this,R.color.colorPrimary));
        }else if (position==2){
            putongTeam.setTextColor(Utils.getColor(this,R.color.text_newbie));
            xinshouTeam.setTextColor(Utils.getColor(this,R.color.text_newbie));
            jiluTeam.setTextColor(Utils.getColor(this,R.color.colorPrimary));
        }
    }

}
