package com.enduo.ndonline.fragment.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enduo.ndonline.R;
import com.enduo.ndonline.productlist.DayDayAcitivity;
import com.pvj.xlibrary.log.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/27.
 */

public class Fragment_season extends Fragment {

    @Bind(R.id.dayday_go)
    View daydayGo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_season, null);
        Logger.d(this.getClass().getName() + "执行了 onCreateView");
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.dayday_go)
    public void onClick() {
        Intent intent = new Intent(getActivity(), DayDayAcitivity.class);
        startActivity(intent);
    }
}
