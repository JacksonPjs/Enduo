package com.enduo.ndonline.my.share;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.adapter.ShareAdapter;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.bean.ShareBean;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 分享有礼
 * Created by Administrator on 2017/1/7.
 */

public class ShareActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.copy_button)
    TextView copyButton;
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.loadinglayout)
    LoadingLayout loadinglayout;
    @Bind(R.id.tjrwww)
    TextView tjrwww;
    @Bind(R.id.phoneid)
    TextView phoneid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        title.setText("分享有礼");
        tjrwww.setText(NetService.API_SERVER_Main+"regIndex.html?tjr="+SharedPreferencesUtils.getUserName(this));
      //  phoneid.setText(+"");
        net();
    }


    //
    @OnClick(R.id.copy_button)
    public void onClick() {

        copy(tjrwww.getText().toString(), this);
        T.ShowToastForShort(this, "链接已复制");
    }


    public void net() {
        NetWorks.selectReferee("1", "100", new Subscriber<ShareBean>() {
            @Override
            public void onStart() {
                loadinglayout.setStatus(LoadingLayout.Loading);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                // loadinglayout.setStatus(LoadingLayout.Loading);
                Logger.json(e.toString());
            }

            @Override
            public void onNext(ShareBean s) {

                if (s.getState().getStatus() == 0) {

                    ShareAdapter adapter = new ShareAdapter(ShareActivity.this, s.getData());
                    lv.setAdapter(adapter);
                    //     adapter.notifyDataSetChanged();
                    loadinglayout.setStatus(LoadingLayout.Success);
                } else if (s.getState().getStatus() == 99) {
                    netLogin();
                } else {
                    loadinglayout.setStatus(LoadingLayout.Empty);
                }
                // Logger.json(s);
            }
        });
    }

    private void netLogin() {

        NetWorks.login(SharedPreferencesUtils.getUserName(this),
                SharedPreferencesUtils.getPassword(this), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            net();
                        } else {
                            SharedPreferencesUtils.setIsLogin(ShareActivity.this, false);
                        }
                    }
                }
        );
    }


    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param content
     */
    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
}
