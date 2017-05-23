package com.enduo.ndonline.productlist;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.IntroduceBean;
import com.enduo.ndonline.net.NetWorks;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * 产品介绍
 * Created by pvj on 2016/12/30.
 */

public class ProductIntroduceActivity extends BaseActivity implements LoadingLayout.OnReloadListener {
    String id;
    String type;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.introduce_tv)
    TextView introduceTv;
    @Bind(R.id.layout_contiant)
    LoadingLayout layoutContiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inroduce);
        ButterKnife.bind(this);

        layoutContiant.setOnReloadListener(this);

        init();
        net(type);
    }

    private void init() {

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        if(type.equals("introduce")){
            title.setText("项目简介");
        }else if (type.equals("risk")){
            title.setText("风控师意见");
        }
    }

    private void net(String type) {
        if (type.equals("introduce")){
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
                        introduceTv.setText("        "+text);
                    } else {
                        layoutContiant.setStatus(LoadingLayout.Empty);
                    }

                }
            });
        }else if(type.equals("risk")){
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
                        introduceTv.setText(text);
                    } else {
                        layoutContiant.setStatus(LoadingLayout.Empty);
                    }

                }
            });
        }


    }

    @Override
    public void onReload(View v) {
        net(type);
    }
}
