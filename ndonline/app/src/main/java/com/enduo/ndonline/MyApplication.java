package com.enduo.ndonline;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;

import com.enduo.ndonline.net.OkHttpUtils;
import com.pvj.xlibrary.loadinglayout.LoadingLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2016/12/8.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    public static  Context context ;

    public static MyApplication instance;
    List<Activity> activities;
    @Override
    public void onCreate() {
        super.onCreate();
        context= this ;
        instance = this;
        activities = new ArrayList<Activity>();
     //   JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //初始化单列的OkHttpClient 对象
        initOkHttpUtils();
        //初始化Glide对象
        initGlide();

        LoadingLayout.getConfig()
                .setLoadingPageLayout(R.layout.loading);
    }

    private void initGlide() {
      Glide.get(this).register(GlideUrl.class, InputStream.class,new OkHttpUrlLoader.Factory(OkHttpUtils.getOkHttpClient()));

    }

    private void initOkHttpUtils() {
       OkHttpClient okHttpClient = OkHttpUtils.getOkHttpClient();
       Log.i(TAG,"---->initOkHttpUtils: "+okHttpClient.toString());

    }


    public void Allfinlish() {
        for (int i = 0; i < activities.size(); i++) {
            Activity activity = activities.get(i);

            if (activity != null) {
                activity.finish();
            }
        }
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

}
