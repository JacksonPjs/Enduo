package com.enduo.ndonline.productlist;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.BorrowDetailBean;
import com.enduo.ndonline.net.NetWorks;
import com.pvj.xlibrary.imageview.CircleProgressView;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;


/**
 * 项目详情
 * Created by Administrator on 2016/12/28.
 *
 *     还款方式（1，一次性还款，2，为按月付息，到期还本,3等额本息）
 */

public class ItemDetailsActivity extends BaseActivity {


    String id;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.min)
    TextView min;
    @Bind(R.id.lilv)
    TextView lilv;

    @Bind(R.id.mouth)
    TextView mouth;
    @Bind(R.id.circleProgressbar)
    CircleProgressView circleProgressbar;
    @Bind(R.id.biao_head)
    LinearLayout biaoHead;
    @Bind(R.id.layout_contiant)
    LoadingLayout layoutContiant;
     @Bind(R.id.btn_toubiao)
     View btnToubiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        title.setText("项目详情");
        init();
        net();
    }

    private void init() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
    }

    private void net() {
        NetWorks.queryBorrowDetail(id, new Subscriber<BorrowDetailBean>() {

            @Override
            public void onStart() {
                layoutContiant.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {
                btnToubiao.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {
                layoutContiant.setStatus(LoadingLayout.Error);
                Logger.e(e.toString());
            }

            @Override
            public void onNext(BorrowDetailBean s) {
               if (s.getState().getStatus()==0){
             layoutContiant.setStatus(LoadingLayout.Success);

               }else{
                   layoutContiant.setStatus(LoadingLayout.Empty);
               }
            }
        });
    }
}
