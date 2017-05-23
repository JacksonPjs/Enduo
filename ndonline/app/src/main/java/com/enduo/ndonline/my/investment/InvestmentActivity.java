package com.enduo.ndonline.my.investment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.adapter.MyTouziAdapter;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.bean.TouziBean;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.my.bank.MyBankActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.loadingrecyclerview.LoadMoreRecyclerLoadingLayout;
import com.pvj.xlibrary.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * 我的投资
 * Created by Administrator on 2016/12/28.
 */

public class InvestmentActivity extends BaseActivity implements LoadingLayout.OnReloadListener, LoadMoreRecyclerLoadingLayout.OnRefreshAndLoadMoreListener {
    List<TouziBean.DataBean> biaoBeenList;
    MyTouziAdapter adapter;


    int page = 1;
    int pagesize = 10;
    @Bind(R.id.public_listview)
    LoadMoreRecyclerLoadingLayout publicLv;
    @Bind(R.id.title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        title.setText("我的投资");
        initView();
        net(0, 0);

    }


    /**
     * @param stype     是刷新 还是加载  0是刷新  1是加载
     * @param inrefresh 第几次刷新下的加载
     */
    private void net(final int stype, final int inrefresh) {
        NetWorks.selectInvestListing(page + "", pagesize + "", new Subscriber<TouziBean>() {
            @Override
            public void onCompleted() {
                publicLv.setRefreshing(false);
                //    publicLv.setStatus(LoadingLayout.Success);
                if (stype == 0) {
                    publicLv.setRefreshing(false);
                } else {
                    publicLv.loadMoreComplete();
                }

            }

            @Override
            public void onError(Throwable e) {
                if (stype == 0) {
                    publicLv.setRefreshing(false);
                } else {
                    publicLv.loadMoreComplete();
                }
                if (page == 1) {
                    publicLv.setStatus(LoadingLayout.Error);
                } else {
                    publicLv.setTextEnd();
                }

                Logger.e(e.toString());
            }

            @Override
            public void onNext(TouziBean biaoBean) {

                if (stype == 0) {
                    if (biaoBean.getState().getStatus() == 0) {
                        biaoBeenList.clear();
                        biaoBeenList.addAll(biaoBean.getData());
                        publicLv.setStatus(LoadingLayout.Success);
                    } else if(biaoBean.getState().getStatus() == 99){
                        netLogin();
                    }else {
                        publicLv.setStatus(LoadingLayout.Empty);
                    }

                } else if (stype == 1) {
                    if (publicLv.getRefreshCount() == inrefresh) {

                        if (biaoBean.getState().getStatus() == 0) {
                            biaoBeenList.addAll(biaoBean.getData());
                        } else {
                            publicLv.setTextEnd();
                        }

                    }
                }
                adapter.notifyDataSetChanged();
                Logger.d("MyTouziAdapter=="+  biaoBeenList.size());

            }

        });

    }

    private void initView() {
        biaoBeenList = new ArrayList<>();
        adapter = new MyTouziAdapter(biaoBeenList, this);

        publicLv.verticalLayoutManager(this)
                .setAdapter(adapter)
                .setOnReloadListener(this)
                .setRecycleViewBackgroundColor(Utils.getColor(this, R.color.bg_huise))
                .setOnRefreshAndLoadMoreListener(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onReload(View v) {
        page = 1;
        publicLv.setStatus(LoadingLayout.Loading);
        net(0, 0);
    }

    @Override
    public void onRefresh() {
        page = 1;
        net(0, 0);
        publicLv.setTextStart();
    }

    @Override
    public void onLoadMore(int inrefresh) {
        page++;
        net(1, inrefresh);
    }

    private void netLogin() {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        publicLv.setStatus(LoadingLayout.Error);
                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            net(0,0);
                        } else {
                         SharedPreferencesUtils.setIsLogin(InvestmentActivity.this,false);
                        }
                    }
                }
        );
    }


}
