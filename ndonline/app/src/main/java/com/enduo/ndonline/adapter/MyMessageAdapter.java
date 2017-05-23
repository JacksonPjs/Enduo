package com.enduo.ndonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.MyMessgeBean;
import com.enduo.ndonline.bean.TouziBean;
import com.enduo.ndonline.my.message.MessageDetatilActivity;
import com.enduo.ndonline.productlist.T1changerString;
import com.pvj.xlibrary.utils.DateUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/3.
 */


public class MyMessageAdapter extends RecyclerView.Adapter<MyMessageAdapter.ViewHolder> {

    private List<MyMessgeBean.DataBean> datas;
    private Context context;

    public MyMessageAdapter(List<MyMessgeBean.DataBean> datas, Context context) {

        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mymessage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      final   MyMessgeBean.DataBean dataBean= datas.get(position);

        holder.title.setText(dataBean.getMessageTitle()+"");
        holder.time.setText(DateUtils.getStrTime2(dataBean.getCreateTime()+""));


        holder.daa.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MessageDetatilActivity.class);
                        intent.putExtra("data", (Serializable) dataBean);
                        context.startActivity(intent);
                    }
                }

        );
//
//
//MessageDetatilActivity
//        holder.time.setText(DateUtils.getStrTime2(dataBean.getInvestTime()+""));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.time)
        TextView time;
 @Bind(R.id.daa)
        View daa;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
