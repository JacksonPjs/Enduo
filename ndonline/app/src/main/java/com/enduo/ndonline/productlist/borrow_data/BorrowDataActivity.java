package com.enduo.ndonline.productlist.borrow_data;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.ProductDetialBean;
import com.enduo.ndonline.net.NetWorks;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * 产品资质资料
 */

public class BorrowDataActivity extends BaseActivity implements LoadingLayout.OnReloadListener {
    GirdViewAdapter adapter ;
    List<ProductDetialBean.DataBean> datas;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.girdview)
    GridView girdview;
    @Bind(R.id.layout_contiant_girdview)
    LoadingLayout layoutContiant;

    String id  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_data);
        ButterKnife.bind(this);
        title.setText("项目资料");

        init();
        initView();
        net();
    }

    private void init() {
        id = getIntent().getStringExtra("id");
        datas =  new ArrayList<>();
        adapter = new GirdViewAdapter(datas,this);

    }

    private void initView() {
        girdview.setAdapter(adapter);
        layoutContiant.setOnReloadListener(this);
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

    @Override
    public void onReload(View v) {
        net();
        datas.clear();
    }
}
