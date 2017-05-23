package com.enduo.ndonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InterestBean;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.ui.activity.AtDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */

public class AtRecycleAdapter extends RecyclerView.Adapter<AtRecycleAdapter.AtHolder>{
    private List<InterestBean.DataBean> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public  AtRecycleAdapter(List<InterestBean.DataBean> datas, Context context){
        this. mContext=context;
        this. mDatas=datas;
        inflater= LayoutInflater. from(mContext);
    }

    @Override
    public AtHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_at,null,false);
        AtHolder holder=new AtHolder(view);

        return holder;
    }



    @Override
    public void onBindViewHolder(AtHolder holder, int position) {
        final InterestBean.DataBean dataBean=mDatas.get(position);
        holder.title.setText(dataBean.getTitle());
        holder.source.setText(dataBean.getSrc());
        Glide.with(mContext).load(NetService.API_SERVER_Main+dataBean.getSrcImgPath())
                .error(R.mipmap.bg_defult)
                .placeholder(R.mipmap.bg_defult)
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, AtDetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("bean",dataBean);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
     class AtHolder extends RecyclerView.ViewHolder {
         TextView title;
         TextView source;
         ImageView img;

         public AtHolder(View itemView) {
             super(itemView);
             title=(TextView) itemView.findViewById(R.id. title);
             source=(TextView) itemView.findViewById(R.id. source);
             img=(ImageView) itemView.findViewById(R.id. img);

         }
     }
}
