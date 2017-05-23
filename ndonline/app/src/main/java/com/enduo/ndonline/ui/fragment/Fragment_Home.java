package com.enduo.ndonline.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.enduo.ndonline.R;
import com.enduo.ndonline.WebActivity;
import com.enduo.ndonline.adapter.AtRecycleAdapter;
import com.enduo.ndonline.adapter.MyRecycleItemAdapter;
import com.enduo.ndonline.bean.AnnouncementBean;
import com.enduo.ndonline.bean.InterestBean;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.fragment.BaseFragment;
import com.enduo.ndonline.more.announcement.AnndatilsActivity;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.productlist.T1changerString;
import com.enduo.ndonline.ui.widget.DividerItemDecoration;
import com.enduo.ndonline.ui.widget.FullyLinearLayoutManager;
import com.pvj.xlibrary.banner.Banner;
import com.pvj.xlibrary.banner.BannerIndicator;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.loadingrecyclerview.LoadMoreRecyclerLoadingLayout;
import com.pvj.xlibrary.log.Logger;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/21.
 * 首页
 */

public class Fragment_Home  extends BaseFragment implements  LoadingLayout.OnReloadListener, LoadMoreRecyclerLoadingLayout.OnRefreshAndLoadMoreListener {

    @Bind(R.id.layout_contiant_noe)
    LoadingLayout layoutContiant;
    @Bind(R.id.textview_auto_roll)
    TextSwitcher textSwitcher;

    @Bind(R.id.main_banner)
    Banner banner;
    @Bind(R.id.indicator)
    BannerIndicator bannerIndicator;

    @Bind(R.id.newtitle)
    TextView newtitle;
    @Bind(R.id.maxAmount)
    TextView maxAmount;
    @Bind(R.id.percent)
    TextView percent;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.title)
    TextView title;



    @Bind(R.id.gonggao)
    View gonggao;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.atrecyclerView)
    RecyclerView atrecyclerView;

//    @Bind(R.id.public_listview)
//    LoadMoreRecyclerLoadingLayout publicLv;

    private BitHandler bitHandler;

    List<OneBean.BannersBean> drawables;
    // 公告列表
    List<OneBean.Data4Bean> data4Beens;

    private int index = 0;

    MyRecycleItemAdapter adapter;
    AtRecycleAdapter atRecycleAdapter;
    Activity activity;
    int page = 1;
    int pagesize = 10;
    int stype=0;
    List<InterestBean.DataBean> biaoBeenList;


//公告handler
    class BitHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (textSwitcher != null) {
                textSwitcher.setText(data4Beens.get(index).getNoticeTitle());
                index++;
                if (index == data4Beens.size()) {
                    index = 0;
                }
            }

        }
    }
    OnFragmentInteractionListener mListener;
    //定义内部回调接口，将数据传递给MainActivity
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String uri);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * find view from layout and set listener
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one,
                null);
        ButterKnife.bind(this, rootView);
        title.setText("理财精选");

        return rootView;
    }

    @Override
    public void initData() {

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getContext());
                textView.setSingleLine();
                //  textView.setTextSize(Utils.sp2px(getContext(),12));
                textView.setTextSize(12);
                textView.setTextColor(Color.parseColor("#333231"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
                lp.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(lp);
                return textView;
            }
        });

        bitHandler = new BitHandler();


        //----------------------banner start------------------------------
        drawables = new ArrayList<>();
        data4Beens = new ArrayList<>();
//        drawables.add("1");
//        drawables.add("2");
//        drawables.add("3");
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams params = banner.getLayoutParams();
        params.width=width;
        params.height=width*2/5;
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
                Glide.with(Fragment_Home.this)
                        .load(NetService.API_SERVER_Url + ((OneBean.BannersBean) imgPath).getImgPath())
                        .error(R.mipmap.bg_defult)
                        .into(imageView);
            }
        });


        banner.setOnBannerItemClickListener(new Banner.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", drawables.get(position).getUrl());
                intent.putExtra("title", drawables.get(position).getBannerName());
                startActivity(intent);

                //    T.ShowToastForShort(getActivity(), "第" + position + "图片被点击了");
            }
        });

        //----------------------indicator start------------------------------
        bannerIndicator.setIndicatorSource(
                ContextCompat.getDrawable(getActivity(), R.drawable.point_selected),//select
                ContextCompat.getDrawable(getActivity(), R.drawable.point_normal),//unselect
                Utils.dp2px(getActivity(), 8)//widthAndHeight
        );
        banner.attachIndicator(bannerIndicator);

        biaoBeenList = new ArrayList<>();
        atRecycleAdapter = new AtRecycleAdapter(biaoBeenList, getActivity());
        //设置布局管理器
        atrecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        //添加分割线
        atrecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //设置Adapter
        atrecyclerView.setAdapter(atRecycleAdapter);

//        publicLv.verticalLayoutManager(getContext())
//                .setAdapter(atRecycleAdapter)
//                .setOnReloadListener(this)
//                .setRecycleViewBackgroundColor(Utils.getColor(getActivity(), R.color.bg_huise))
//                .setOnRefreshAndLoadMoreListener(this);




    }

    /**
     * init data
     */
    @Override
    public void fillDate() {
        layoutContiant.setOnReloadListener(this);
    }

    /**
     * network request
     */
    @Override
    public void requestData() {
        NetWorks.index(new Subscriber<OneBean>() {
            @Override
            public void onStart() {
                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {


                Logger.e(e.toString());
                layoutContiant.setStatus(LoadingLayout.Error);
            }

            @Override
            public void onNext(OneBean oneBean) {

                if (oneBean.getState().getStatus() == 0) {
                    //广告
                    layoutContiant.setStatus(LoadingLayout.Success);
                    drawables.clear();
                    drawables.addAll(oneBean.getBanners());
                    banner.setDataSource(drawables);

                    // 设置 广播消息
                    data4Beens.clear();
                    data4Beens.addAll(oneBean.getData4());

                    new myThread().start();
                    adapter = new MyRecycleItemAdapter(getActivity(), oneBean);

                    //设置布局管理器
                    recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                    //添加分割线
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                    //设置Adapter
                    recyclerView.setAdapter(adapter);

                    OneBean.Data0Bean data0Bean=oneBean.getData0();
                    if (data0Bean!=null){
                        DecimalFormat df = new DecimalFormat("######0.00");
                        maxAmount.setText("新用户最高可享"+data0Bean.getMinInvestAmount()+"元");
                        newtitle.setText(data0Bean.getBorrowTitle());
                        percent.setText(df.format(data0Bean.getAnnualRate()) + "%");
                        date.setText(T1changerString.t2chager(data0Bean.getDeadline(), data0Bean.getDeadlineType()));
                    }



                } else {
                    layoutContiant.setStatus(LoadingLayout.Error);
                }


            }
        });

        net(0,0);


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
                        Toast.makeText(getActivity(),"没有更多的内容",Toast.LENGTH_SHORT).show();
                    }

                }

                atRecycleAdapter.notifyDataSetChanged();
            }

        });
    }


    private class myThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (index < data4Beens.size()) {
                try {
                    synchronized (this) {
                        bitHandler.sendEmptyMessage(0);
                        this.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }





    @Override
    public void onPause() {
        banner.pauseScroll();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.resumeScroll();
    }


    @OnClick({R.id.gonggao,R.id.moremoney,R.id.interest})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.gonggao:

                if (data4Beens == null) {
                    return;
                }


                int i = index - 1;
                if (i < 0) {
                    i = data4Beens.size() - 1;
                }


                Logger.d("i=" + i + "lengh=" + data4Beens.size());
                OneBean.Data4Bean data4Bean = data4Beens.get(i);

                AnnouncementBean.DataBean b = new AnnouncementBean.DataBean();
                b.setCreateTime(data4Bean.getCreateTime());
                b.setNoticeContent(data4Bean.getNoticeContent());
                b.setNoticeTitle(data4Bean.getNoticeTitle());


                Intent intent = new Intent(getContext(), AnndatilsActivity.class);
                intent.putExtra("data", (Serializable) b);
                getActivity().startActivity(intent);

                break;
            case  R.id.moremoney:
                if (mListener!=null){
                    mListener.onFragmentInteraction("1");
                }
                break;
            case R.id.interest:
                page++;
                net(1,3);
                break;

        }
    }

    @Override
    public void onReload(View v) {
        requestData();
        page = 1;
//        publicLv.setStatus(LoadingLayout.Loading);
//        net(0,0);
    }
    @Override
    public void onRefresh() {
//        page = 1;
//        net(0, 0);
//        publicLv.setTextStart();
    }

    @Override
    public void onLoadMore(int inrefresh) {
//        page++;
//        net(1, inrefresh);
    }
}