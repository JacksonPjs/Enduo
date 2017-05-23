package com.enduo.ndonline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import com.enduo.ndonline.appupdate.AppUpdataBean;
import com.enduo.ndonline.appupdate.AppUpdataUtils;
import com.enduo.ndonline.appupdate.AppUtils;
import com.enduo.ndonline.fragment.Fragment_two;
import com.enduo.ndonline.login2register.LoginActivity;
import com.enduo.ndonline.my.set.SetActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.ui.fragment.Fragment_About;
import com.enduo.ndonline.ui.fragment.Fragment_Home;
import com.enduo.ndonline.ui.fragment.Fragment_Manage;
import com.enduo.ndonline.ui.fragment.Fragment_Mine;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.DensityUtils;
import com.pvj.xlibrary.utils.EToast;
import com.pvj.xlibrary.utils.T;

import rx.Subscriber;

/**
 * 【高仿微信】01、微信主界面
 *
 * @author allenjuns@yahoo.com
 */
public class MainActivity extends BaseActivity implements Fragment_Home.OnFragmentInteractionListener {


    private TextView unreaMsgdLabel;// 未读消息textview
    private TextView unreadAddressLable;// 未读通讯录textview
    private TextView unreadFindLable;// 发现
    private Fragment[] fragments;
    private Fragment_Home homefragment;
    private Fragment_two managefragment;
    private Fragment_Mine minefragment;
    private Fragment_About aboutfragment;
    private ImageView[] imagebuttons;
    private TextView[] textviews;
    private int index;
    private int currentTabIndex;// 当前fragment的index


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        netUpdate();
        initTabView();
    }

    private void netUpdate() {

        NetWorks.appCurrentVersion(new Subscriber<AppUpdataBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                T.ShowToastForShort(MainActivity.this, "网络异常...");
            }

            @Override
            public void onNext(AppUpdataBean s) {
                Logger.d("升级程序");
                if (s.getAppVersion() > AppUtils.getVersionCode(MainActivity.this)) {
                    if (s.getIsMustNewVersion() == 1) {
                        AppUpdataUtils.showUpdateDialog2(MainActivity.this, s);
                    } else {
                        AppUpdataUtils.showUpdateDialog(MainActivity.this, s);
                    }

                } else {
                    //  T.ShowToastForShort(MainActivity.this,"已经是最新版本了...");
                }
            }
        });
    }

    private void initTabView() {

        unreaMsgdLabel = (TextView) findViewById(R.id.unread_msg_number);
        unreadAddressLable = (TextView) findViewById(R.id.unread_address_number);
        unreadFindLable = (TextView) findViewById(R.id.unread_find_number);

        homefragment = new Fragment_Home();

        fragments = new Fragment[]{homefragment, managefragment,
                minefragment, aboutfragment};

        imagebuttons = new ImageView[4];
        imagebuttons[0] = (ImageView) findViewById(R.id.ib_weixin);
        imagebuttons[1] = (ImageView) findViewById(R.id.ib_contact_list);
        imagebuttons[2] = (ImageView) findViewById(R.id.ib_find);
        imagebuttons[3] = (ImageView) findViewById(R.id.ib_profile);

        imagebuttons[0].setSelected(true);
        textviews = new TextView[4];
        textviews[0] = (TextView) findViewById(R.id.tv_weixin);
        textviews[1] = (TextView) findViewById(R.id.tv_contact_list);
        textviews[2] = (TextView) findViewById(R.id.tv_find);
        textviews[3] = (TextView) findViewById(R.id.tv_profile);
        textviews[0].setTextColor(Utils.getColor(this, R.color.colorPrimary));
        // 添加显示第一个fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homefragment)
                .show(homefragment).commit();

        //.add(R.id.fragment_container, contactlistfragment)
        //  .add(R.id.fragment_container, profilefragment)
        //    .add(R.id.fragment_container, findfragment)
        //    .hide(contactlistfragment).hide(profilefragment)
        //   .hide(findfragment).
    }

    public void onTabClicked(View view) {

        if (!(Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {
            if (view.getId() == R.id.re_find) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return;
            }
        }

        switch (view.getId()) {
            case R.id.re_weixin:
                index = 0;
                break;
            case R.id.re_contact_list:
                index = 1;
                if (fragments[index] == null) {
                    fragments[index] = new Fragment_two();
                }
                break;
            case R.id.re_find:
                index = 2;
                if (fragments[index] == null) {
                    fragments[index] = new Fragment_Mine();
                }
                break;
            case R.id.re_profile:
                index = 3;
                if (fragments[index] == null) {
                    fragments[index] = new Fragment_About();
                }
                break;
        }
        changeTabFragemt();


    }

    public void changeTabFragemt() {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);

            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();

        }

        imagebuttons[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        imagebuttons[index].setSelected(true);
        textviews[currentTabIndex].setTextColor(0xFF999999);
        textviews[index].setTextColor(Utils.getColor(this, R.color.colorPrimary));
        currentTabIndex = index;
    }

    /**
     * 当系统要回收Fragment时，我们告诉系统：不要再保存Fragment。相当于用户回到app的时候，我们就当用户是第一次打开app（因为很长时间没有操作了）。
     *
     * @param outState
     * @param outPersistentState
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        // super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (currentTabIndex == 2) {
            if (!(Boolean) SharedPreferencesUtils.getParam(this, "islogin", false)) {
                index = 0;
                if (fragments[index] == null) {
                    fragments[index] = new Fragment_Home();
                }
            }

            if (currentTabIndex != index) {
                FragmentTransaction trx = getSupportFragmentManager()
                        .beginTransaction();
                trx.hide(fragments[currentTabIndex]);

                if (!fragments[index].isAdded()) {
                    trx.add(R.id.fragment_container, fragments[index]);
                }
                trx.show(fragments[index]).commit();

            }

            imagebuttons[currentTabIndex].setSelected(false);
            // 把当前tab设为选中状态
            imagebuttons[index].setSelected(true);
            textviews[currentTabIndex].setTextColor(0xFF999999);
            textviews[index].setTextColor(Utils.getColor(this, R.color.colorPrimary));
            currentTabIndex = index;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EToast.reset();
    }

    @Override
    public void onFragmentInteraction(String uri) {
        index=1;
        if (fragments[1] == null) {
            fragments[1] = new Fragment_Manage();
        }
        changeTabFragemt();

    }
}
