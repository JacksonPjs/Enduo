package com.enduo.ndonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.enduo.ndonline.APP.Contacts;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.productlist.T1changerString;
import com.enduo.ndonline.ui.activity.DetailsActivity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */

public class MyRecycleItemAdapter extends RecyclerView.Adapter<MyRecycleItemAdapter.MyViewHolder> {

    private OneBean mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    DecimalFormat df = new DecimalFormat("######0.00");


    public MyRecycleItemAdapter(Context context, OneBean datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        int i = 0;
        if (mDatas.getData1() != null) {
            i = i + 1;
        }
        if (mDatas.getData2() != null) {
            i = i + 1;
        }
        if (mDatas.getData3() != null) {
            i = i + 1;
        }

        return i;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (position == 0) {
            if (mDatas.getData1() != null) {

                final OneBean.Data1Bean data1Bean = mDatas.getData1();
                holder.name.setText(data1Bean.getBorrowTitle());
                holder.percent.setText(df.format(data1Bean.getAnnualRate()) + "%");
                holder.min.setText(Contacts.APP.BorrowStatus[data1Bean.getBorrowStatus()-1]);

//                holder.miaoshu.setText(data1Bean.getBorrowTitle());
                holder.date.setText(T1changerString.t2chager(data1Bean.getDeadline(), data1Bean.getDeadlineType()));

                holder.rl_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,DetailsActivity.class);
                        intent.putExtra("id",data1Bean.getId()+"");
                        mContext.startActivity(intent);
                    }
                });
            }
        }
        if (position == 1) {
            if (mDatas.getData2() != null) {
                final OneBean.Data2Bean data2Bean = mDatas.getData2();
                holder.name.setText(data2Bean.getBorrowTitle());
                holder.percent.setText(df.format(data2Bean.getAnnualRate()) + "%");
                holder.min.setText(Contacts.APP.BorrowStatus[data2Bean.getBorrowStatus()-1]);

//                holder.miaoshu.setText(data2Bean.getBorrowTitle());
                holder.date.setText(T1changerString.t2chager(data2Bean.getDeadline(), data2Bean.getDeadlineType()));
                holder.rl_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,DetailsActivity.class);
                        intent.putExtra("id",data2Bean.getId()+"");
                        mContext.startActivity(intent);
                    }
                });
            }
        }
        if (position == 2) {
            if (mDatas.getData3() != null) {
                final OneBean.Data3Bean data3Bean = mDatas.getData3();
                holder.name.setText(data3Bean.getBorrowTitle());
                holder.percent.setText(df.format(data3Bean.getAnnualRate()) + "%");

//                holder.miaoshu.setText(data3Bean.getBorrowTitle());
                holder.min.setText(Contacts.APP.BorrowStatus[data3Bean.getBorrowStatus()-1]);
                holder.date.setText(T1changerString.t2chager(data3Bean.getDeadline(), data3Bean.getDeadlineType()));
                holder.rl_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,DetailsActivity.class);
                        intent.putExtra("id",data3Bean.getId()+"");
                        mContext.startActivity(intent);
                    }
                });

            }
        }


    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_home_licai_recycle, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, name, percent, miaoshu,min;
        RelativeLayout rl_item;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            name = (TextView) view.findViewById(R.id.name);
            percent = (TextView) view.findViewById(R.id.percent);
            miaoshu = (TextView) view.findViewById(R.id.miaoshu);
            min = (TextView) view.findViewById(R.id.min);
            rl_item = (RelativeLayout) view.findViewById(R.id.rl_item);
        }

    }
}