package com.enduo.ndonline.productlist.investmentrecord;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.InvestmentBean;
import com.pvj.xlibrary.utils.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26.
 */

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.ViewHolder> {

    private List<InvestmentBean.DataBean> datas;
    private Context context;

    public InvestmentAdapter(List<InvestmentBean.DataBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inv_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InvestmentBean.DataBean b = datas.get(position);
        holder.money.setText(b.getInvestAmount() + "元");
        if(b.getUserName().length()==11){
            String str = b.getUserName();
            String ss = str.substring(0,str.length()-(str.substring(1)).length())+"*********"+str.substring(10);
            holder.name.setText(ss);
        }else{
            holder.name.setText(b.getUserName());
        }

        holder.time.setText(DateUtils.getStrTime2(b.getInvestTime() + ""));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.money)
        TextView money;
        @Bind(R.id.time)
        TextView time;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
