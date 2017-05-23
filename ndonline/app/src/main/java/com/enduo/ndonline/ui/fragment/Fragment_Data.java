package com.enduo.ndonline.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.enduo.ndonline.APP.Contacts;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.IntroduceBean;
import com.enduo.ndonline.bean.ProductDetialBean;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.productlist.borrow_data.GirdViewAdapter;
import com.enduo.ndonline.ui.widget.MyScrollView;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/14.
 * 产品资源
 */

public class Fragment_Data extends Fragment implements MyScrollView.ScrollListener, LoadingLayout.OnReloadListener {

    String id;

    @Bind(R.id.girdview)
    GridView girdview;
    @Bind(R.id.layout_contiant_girdview)
    LoadingLayout layoutContiant;
    @Bind(R.id.myscrollview)
    MyScrollView scrollView;
    GirdViewAdapter adapter ;
    List<ProductDetialBean.DataBean> datas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        id = bundle.getString("id");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragemt_data, null);
        ButterKnife.bind(this, rootView);
        initView();
        net();
        return rootView;
    }



    private void net() {
        NetWorks.queryBorrowData(id, new Subscriber<ProductDetialBean>() {
            @Override
            public void onStart() {
                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.json(e.toString());
                layoutContiant.setStatus(LoadingLayout.Error);
            }

            @Override
            public void onNext(ProductDetialBean b) {
                if (b.getState().getStatus()==0){
                    datas.addAll(b.getData());
                    adapter.notifyDataSetChanged();
                    layoutContiant.setStatus(LoadingLayout.Success);
                }else{
                    layoutContiant.setStatus(LoadingLayout.Empty);
                }

            }
        });

    }

    private void initView() {
        scrollView.setScrollListener(this);
        datas =  new ArrayList<>();
        adapter = new GirdViewAdapter(datas,getActivity());
        girdview.setAdapter(adapter);
        layoutContiant.setOnReloadListener(this);
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

    @Override
    public void onReload(View v) {
        net();
        datas.clear();
    }
}
