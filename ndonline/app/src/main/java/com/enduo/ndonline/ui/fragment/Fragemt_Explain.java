package com.enduo.ndonline.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enduo.ndonline.APP.Contacts;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.IntroduceBean;
import com.enduo.ndonline.bean.InvestmentBean;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.productlist.investmentrecord.InvestmentAdapter;
import com.enduo.ndonline.ui.widget.MyScrollView;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/10.
 */
//项目说明
public class Fragemt_Explain extends Fragment implements MyScrollView.ScrollListener {

    String id;

    @Bind(R.id.explain)
    TextView tv_explain;
    @Bind(R.id.risk)
    TextView tv_risk;
    @Bind(R.id.layout_contiant)
    LoadingLayout layoutContiant;
    @Bind(R.id.myscrollview)
    MyScrollView scrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getString("id");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_explain, null);
        ButterKnife.bind(this, rootView);
        initView();
        net(0,0);
        return rootView;
    }


    /**
     * @param stype     是刷新 还是加载  0是刷新  1是加载
     * @param inrefresh 第几次刷新下的加载
     */
    private void net(final int stype, final int inrefresh) {
        NetWorks.queryBorrowIntroduce(id, new Subscriber<IntroduceBean>() {
            @Override
            public void onStart() {
                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                layoutContiant.setStatus(LoadingLayout.Error);
                Logger.json(e.toString());
            }

            @Override
            public void onNext(IntroduceBean b) {

                if (b.getState().getStatus() == 0) {
                    layoutContiant.setStatus(LoadingLayout.Success);

                    Spanned text = Html.fromHtml(b.getData());
                    //  tv.setText(text);
                    tv_explain.setText("        " + text);
                } else {
                    layoutContiant.setStatus(LoadingLayout.Empty);
                }

            }
        });
        NetWorks.queryBorrowRisk(id, new Subscriber<IntroduceBean>() {
            @Override
            public void onStart() {
                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                layoutContiant.setStatus(LoadingLayout.Error);
                Logger.json(e.toString());
            }

            @Override
            public void onNext(IntroduceBean b) {

                if (b.getState().getStatus() == 0) {
                    layoutContiant.setStatus(LoadingLayout.Success);

                    Spanned text = Html.fromHtml(b.getData());
                    //  tv.setText(text);
                    tv_risk.setText(text);
                } else {
                    layoutContiant.setStatus(LoadingLayout.Empty);
                }

            }
        });

    }

    private void initView() {
            scrollView.setScrollListener(this);
        Contacts.APP.isTop=true;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onScrollToBottom() {

    }

    @Override
    public void onScrollToTop() {

    }

    @Override
    public void onScroll(int scrollY) {
        //判断时候滑动到了顶部
        if (scrollY == 0) {
            Contacts.APP.isTop = true;
        } else {
            Contacts.APP.isTop = false;
        }

    }

    @Override
    public void notBottom() {

    }
}
