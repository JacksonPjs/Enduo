package com.enduo.ndonline.more.announcement;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.AnnouncementBean;
import com.enduo.ndonline.bean.InvestmentBean;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.productlist.investmentrecord.InvestmentAdapter;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.loadingrecyclerview.LoadMoreRecyclerLoadingLayout;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 *  平台公告列表
 * Created by Administrator on 2016/12/30.
 */

public class AnnouncementListActivity extends BaseActivity implements LoadingLayout.OnReloadListener, LoadMoreRecyclerLoadingLayout.OnRefreshAndLoadMoreListener {
    List<AnnouncementBean.DataBean> biaoBeenList;
    AnnouncementAdapter adapter;

    int page = 1;
    int pagesize = 10;
    String id ;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.public_listview)
    LoadMoreRecyclerLoadingLayout publicLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_list);
        ButterKnife.bind(this);

        title.setText("平台公告");
        id = getIntent().getStringExtra("id");


        initView();

        net(0, 0);
    }

    /**
     * @param stype     是刷新 还是加载  0是刷新  1是加载
     * @param inrefresh 第几次刷新下的加载
     */
    private void net(final int stype, final int inrefresh) {
        NetWorks.noticeList(page + "", pagesize + "", new Subscriber<AnnouncementBean>() {
            @Override
            public void onCompleted() {
                publicLv.setRefreshing(false);

                if (stype==0){
                    publicLv.setRefreshing(false);
                }else{
                    publicLv.loadMoreComplete();
                }

            }

            @Override
            public void onError(Throwable e) {
                if (stype==0){
                    publicLv.setRefreshing(false);
                }else{
                    publicLv.loadMoreComplete();
                }
                if (page==1){
                    publicLv.setStatus(LoadingLayout.Error);
                }else{
                    publicLv.setTextEnd();
                }

                Logger.e(e.toString());
            }

            @Override
            public void onNext(AnnouncementBean biaoBean) {

                if (stype == 0) {
                    if (biaoBean.getState().getStatus() == 0) {
                        biaoBeenList.clear();
                        biaoBeenList.addAll(biaoBean.getData());
                        publicLv.setStatus(LoadingLayout.Success);
                    } else {
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
            }

        });

    }

    private void initView() {
        biaoBeenList = new ArrayList<>();
        adapter = new AnnouncementAdapter(biaoBeenList, this);

        publicLv.verticalLayoutManager(this)
                .setAdapter(adapter)
                .setOnReloadListener(this)
                .addItemDecoration(new RecycleViewDivider(
                        this, LinearLayoutManager.VERTICAL, 10, Utils.getColor(this,R.color.bg_huise)))
                .setOnRefreshAndLoadMoreListener(this);

    }

    @Override
    public void onReload(View v) {
        page = 1;
        publicLv.setStatus(LoadingLayout.Loading);
        net(0,0);
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
}
