package com.enduo.ndonline.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.fragment.one.ViewPagerFramentAdapter;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.productlist.Fragment_commonly;
import com.enduo.ndonline.productlist.Fragment_new;
import com.enduo.ndonline.ui.fragment.Fragment_Home;
import com.pvj.xlibrary.banner.Banner;
import com.pvj.xlibrary.banner.BannerIndicator;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by maxy on 2016/11/25.
 */
public class Fragment_two extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Bind(R.id.putong_team)
    TextView putongTeam;
    @Bind(R.id.xinshou_team)
    TextView xinshouTeam;
    @Bind(R.id.biao_viewpager)
    ViewPager biaoViewpager;
    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.main_banner)
    Banner banner;
    @Bind(R.id.indicator)
    BannerIndicator bannerIndicator;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two,
                null);
        ButterKnife.bind(this,rootView);
        title.setText("理财列表");
        return rootView;
    }


    @Override
    public void initData() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.width=width;
        params.height=width*3/10;
        banner.setLayoutParams(params);

        banner.setInterval(5000);
        banner.setPageChangeDuration(500);
        banner.setBannerDataInit(new Banner.BannerDataInit() {
            @Override
            public ImageView initImageView() {
                return (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.imageview, null);
            }

            @Override
            public void initImgData(ImageView imageView, Object imgPath) {

                Logger.d("initImgData" + NetService.API_SERVER_Url + ((OneBean.BannersBean) imgPath).getImgPath());
                Glide.with(Fragment_two.this)
                        .load(NetService.API_SERVER_Url + ((OneBean.BannersBean) imgPath).getImgPath())
                        .error(R.mipmap.bg_defult)
                        .into(imageView);
            }
        });

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Fragment_commonly());
        fragmentList.add(new Fragment_new());
        ViewPagerFramentAdapter adapter = new ViewPagerFramentAdapter(getChildFragmentManager(),fragmentList);
        biaoViewpager.setAdapter(adapter);
        biaoViewpager.setOnPageChangeListener(this);

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

    @OnClick({R.id.putong_team, R.id.xinshou_team})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.putong_team:
                biaoViewpager.setCurrentItem(0);
                break;
            case R.id.xinshou_team:
                biaoViewpager.setCurrentItem(1);
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
            putongTeam.setTextColor(Utils.getColor(getActivity(),R.color.colorPrimary));
            xinshouTeam.setTextColor(Utils.getColor(getActivity(),R.color.text_newbie));
        }else{
            putongTeam.setTextColor(Utils.getColor(getActivity(),R.color.text_newbie));
            xinshouTeam.setTextColor(Utils.getColor(getActivity(),R.color.colorPrimary));
        }
    }
}
