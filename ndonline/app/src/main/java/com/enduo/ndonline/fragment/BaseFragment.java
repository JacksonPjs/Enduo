package com.enduo.ndonline.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pvj.xlibrary.log.Logger;


/**
 * Created by only on 16/6/14.
 */
public abstract class BaseFragment extends Fragment {
    Toast mToast;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Logger.d(this.getClass().getName()+"执行了 onCreateView");

        View ret = initView(inflater, container, savedInstanceState);

        initData();

        fillDate();

        requestData();


        return ret;

    }



    @Override
    public void onStart() {
        super.onStart();

        Logger.d(this.getClass().getName()+"执行了 onStart");
    }

    /**
     * init  get query from other page
     */
    public void initData() {
    }

    /**
     * find view from layout and set listener
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * init data
     */
    public abstract void fillDate();

    /**
     * network request
     */
    public abstract void requestData();



    // 特性：第一次创建的时候是不会被调用的
    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
        Logger.d(this.getClass().getName()+"执行了 onHiddenChanged 当前状态："+ hidden);

    }

    @Override
    public void onPause() {
        super.onPause();

        Logger.d(this.getClass().getName()+"执行了 onPause");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Logger.d(this.getClass().getName()+"执行了 onDestroy");


    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(this.getClass().getName()+"执行了 onStop");


    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(this.getClass().getName()+"执行了 onResume");

    }
}
