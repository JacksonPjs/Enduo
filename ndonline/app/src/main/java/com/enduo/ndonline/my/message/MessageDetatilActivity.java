package com.enduo.ndonline.my.message;

import android.os.Bundle;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.MyMessgeBean;
import com.enduo.ndonline.net.NetWorks;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.DateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MessageDetatilActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.ctitle)
    TextView ctitle;
    @Bind(R.id.contet)
    TextView contet;
    @Bind(R.id.time)
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagedetail);
        ButterKnife.bind(this);

        title.setText("我的消息");




        MyMessgeBean.DataBean dataBean = (MyMessgeBean.DataBean) getIntent().getSerializableExtra("data");



        ctitle.setText(dataBean.getMessageTitle());
        contet.setText(dataBean.getMessageContent());
        time.setText(DateUtils.getStrTime2(dataBean.getCreateTime()+""));






//        NetWorks.showUserMessage("568", new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Logger.json(e.toString());
//            }
//
//            @Override
//            public void onNext(String s) {
//                Logger.json(s);
//            }
//        });
//    }
    }
}
