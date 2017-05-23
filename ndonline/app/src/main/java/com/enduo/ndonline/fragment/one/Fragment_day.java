package com.enduo.ndonline.fragment.one;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.bean.ProductDetialBean;
import com.enduo.ndonline.productlist.DayDayAcitivity;
import com.enduo.ndonline.productlist.MouthMouthAcitivity;
import com.enduo.ndonline.productlist.ProductType;
import com.enduo.ndonline.productlist.T1changerString;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.log.Logger;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/27.
 */

public class Fragment_day extends Fragment {

    @Bind(R.id.dayday_go)
    View daydayGo;
    @Bind(R.id.bg)
    ImageView bg;

    OneBean data;

    int p ;

    @Bind(R.id.lv)
    TextView lv;
    @Bind(R.id.qt)
    TextView qt;
    @Bind(R.id.jx)
    TextView jx;
    @Bind(R.id.tm)
    TextView tm;

    DecimalFormat df   = new DecimalFormat("######0.00");



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        data = (OneBean) bundle.get("data");
        p = bundle.getInt("p");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_day, null);
        Logger.d(this.getClass().getName() + "执行了 onCreateView");
        ButterKnife.bind(this, rootView);


        if (p==1){

            daydayGo.setBackgroundResource(R.drawable.button_border_selector);
            OneBean.Data1Bean data1 = data.getData1();
            if(data1==null){
                daydayGo.setBackgroundResource(R.drawable.button_border_hui);
                return rootView;
            }

            lv.setText(df.format(data1.getAnnualRate())+"");
            qt.setText(((int)data1.getMinInvestAmount())+"起投");
            jx.setText(T1changerString.t1chager(data1.getInterestBearingTime()));


            tm.setText("剩余"+df.format((data1.getBorrowAmount()-data1.getHasBorrowAmount())/10000)+"万");
         //   tm.setText(T1changerString.t2chager(data1.getDeadline(), data1.getDeadlineType()));
        }else if(p==2){
            bg.setImageDrawable(Utils.getDrawble(getActivity(),R.mipmap.bg02));
            daydayGo.setBackgroundResource(R.drawable.button_border_press_mouth);
            OneBean.Data2Bean data2= data.getData2();

            if(data2==null){
                daydayGo.setBackgroundResource(R.drawable.button_border_hui);
                return rootView;
            }
            lv.setText(df.format(data2.getAnnualRate())+"");
            qt.setText(((int)data2.getMinInvestAmount())+"起投");
            jx.setText(T1changerString.t1chager(data2.getInterestBearingTime()));

            tm.setText("剩余"+df.format((data2.getBorrowAmount()-data2.getHasBorrowAmount())/10000)+"万");
         //   tm.setText("剩余"+data2.getBorrowAmount()/10000+"万");
          //  tm.setText(T1changerString.t2chager(data2.getDeadline(), data2.getDeadlineType()));
        }else if(p==3){
            bg.setImageDrawable(Utils.getDrawble(getActivity(),R.mipmap.bg03));
            daydayGo.setBackgroundResource(R.drawable.button_border_press_season);

            OneBean.Data3Bean data3= data.getData3();

            if(data3==null){
                daydayGo.setBackgroundResource(R.drawable.button_border_hui);
                return rootView;
            }


            lv.setText( df.format(data3.getAnnualRate())+"");
            qt.setText(((int)data3.getMinInvestAmount())+"起投");
            jx.setText(T1changerString.t1chager(data3.getInterestBearingTime()));

         //   tm.setText("剩余"+data3.getBorrowAmount()/10000+"万");
            tm.setText("剩余"+df.format((data3.getBorrowAmount()-data3.getHasBorrowAmount())/10000)+"万");
          //  tm.setText(T1changerString.t2chager(data3.getDeadline(), data3.getDeadlineType()));
        }



        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.dayday_go)
    public void onClick() {


        if (p==1){

            OneBean.Data1Bean data1 = data.getData1();
            if(data1==null){
                return ;
            }

            Intent intent = new Intent(getActivity(), MouthMouthAcitivity.class);
            intent.putExtra("id", data.getData1().getId() + "");
            intent.putExtra("borrowType",  data.getData1().getBorrowType());
            startActivity(intent);
        }else if(p==2){
            OneBean.Data2Bean data2 = data.getData2();
            if(data2==null){
                return ;
            }

            Intent intent = new Intent(getActivity(), MouthMouthAcitivity.class);
            intent.putExtra("id",  data.getData2().getId() + "");
            intent.putExtra("borrowType",  data.getData2().getBorrowType());
            startActivity(intent);
        }else if(p==3){
            OneBean.Data3Bean data3 = data.getData3();
            if(data3==null){
                return ;
            }

            Intent intent = new Intent(getActivity(), MouthMouthAcitivity.class);
            intent.putExtra("id",  data.getData3().getId() + "");
            intent.putExtra("borrowType",  data.getData3().getBorrowType());
            startActivity(intent);
        }

    }
}
