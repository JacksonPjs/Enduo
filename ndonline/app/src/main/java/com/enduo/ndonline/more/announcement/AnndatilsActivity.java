package com.enduo.ndonline.more.announcement;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.AnnouncementBean;
import com.enduo.ndonline.bean.MyMessgeBean;
import com.pvj.xlibrary.utils.DateUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 平台公告详情
 */

public class AnndatilsActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.biaoti)
    TextView biaoti;
    @Bind(R.id.laiyuan)
    TextView laiyuan;
    @Bind(R.id.neirong)
    TextView neirong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anndatils);
        ButterKnife.bind(this);

        title.setText("平台公告");

        AnnouncementBean.DataBean b = (AnnouncementBean.DataBean) getIntent().getSerializableExtra("data");

        biaoti.setText(b.getNoticeTitle());
        laiyuan.setText("来源:官方公告"+ DateUtils.getStrTime2(b.getCreateTime()+""));


        Spanned text = Html.fromHtml(b.getNoticeContent());
        //  tv.setText(text);
        neirong.setText("        "+text);

    }
}
