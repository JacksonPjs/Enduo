package com.enduo.ndonline.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.adapter.AtRecycleAdapter;
import com.enduo.ndonline.bean.AnnouncementBean;
import com.enduo.ndonline.bean.InterestBean;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.more.announcement.AnndatilsActivity;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.ui.fragment.Fragment_Home;
import com.enduo.ndonline.ui.widget.DividerItemDecoration;
import com.enduo.ndonline.ui.widget.FullyLinearLayoutManager;
import com.pvj.xlibrary.banner.Banner;
import com.pvj.xlibrary.banner.BannerIndicator;
import com.pvj.xlibrary.log.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/5/10.
 */

public class AtDetailsActivity extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.atrecyclerView)
    RecyclerView atrecyclerView;

    @Bind(R.id.main_banner)
    Banner banner;
    @Bind(R.id.indicator)
    BannerIndicator bannerIndicator;

    InterestBean.DataBean bean;
    List<InterestBean.DataBean> biaoBeenList;
    AtRecycleAdapter atRecycleAdapter;
    int page = 1;
    int pagesize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_details);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        bean= (InterestBean.DataBean) bundle.getSerializable("bean");
        init();
    }
    private void init(){
        if (bean!=null){
            title.setText(bean.getTitle());
            Spanned text = Html.fromHtml(bean.getContent()+"");
            content.setText(text);

            banner.setInterval(5000);
            banner.setPageChangeDuration(500);
            banner.setBannerDataInit(new Banner.BannerDataInit() {
                @Override
                public ImageView initImageView() {
                    return (ImageView) LayoutInflater.from(AtDetailsActivity.this).inflate(R.layout.imageview, null);
                }

                @Override
                public void initImgData(ImageView imageView, Object imgPath) {

                    Log.e("initImgData" , NetService.API_SERVER_Url + bean.getSrcImgPath());
                    Glide.with(AtDetailsActivity.this)
                            .load(NetService.API_SERVER_Url + bean.getSrcImgPath())
                            .error(R.mipmap.bg_defult)
                            .into(imageView);
                }
            });


            biaoBeenList = new ArrayList<>();
            atRecycleAdapter = new AtRecycleAdapter(biaoBeenList,this);
            //设置布局管理器
            atrecyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
            //添加分割线
            atrecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
            //设置Adapter
            atrecyclerView.setAdapter(atRecycleAdapter);
            net(0,0);
        }
    }

    @OnClick({R.id.interest})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.interest:
                page++;
                net(1,3);
                break;

        }
    }

    /**
     * @param stype     是刷新 还是加载  0是刷新  1是加载
     * @param inrefresh 第几次刷新下的加载
     */
    private void net(final int stype, final int inrefresh) {
        NetWorks.interestList(page + "", pagesize + "", new Subscriber<InterestBean>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {


                Logger.e(e.toString());
            }

            @Override
            public void onNext(InterestBean biaoBean) {

                if (stype == 0) {
                    if (biaoBean.getState().getStatus() == 0) {
                        biaoBeenList.clear();
                        biaoBeenList.addAll(biaoBean.getData());
                    } else {
                    }

                } else if (stype == 1) {

                    if (biaoBean.getState().getStatus() == 0) {
                        biaoBeenList.addAll(biaoBean.getData());
                    } else {
                    }

                    if (biaoBean.getData()==null){
                        Toast.makeText(AtDetailsActivity.this,"没有更多的内容",Toast.LENGTH_SHORT).show();
                    }

                }

                atRecycleAdapter.notifyDataSetChanged();
            }

        });
    }



}
