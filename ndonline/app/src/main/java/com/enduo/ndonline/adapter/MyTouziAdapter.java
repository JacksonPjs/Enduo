package com.enduo.ndonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.TouziBean;
import com.enduo.ndonline.productlist.T1changerString;
import com.enduo.ndonline.utils.P2pUtils;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.utils.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/3.
 */


public class MyTouziAdapter extends RecyclerView.Adapter<MyTouziAdapter.ViewHolder> {

    private List<TouziBean.DataBean> datas;
    private Context context;

    String[] str2={"申请中","初审通过", "招标中", "复审中", "还款中", "已还款", "借款失败(初审)", "复审失败", "流标", "复审处理中", "流标货复审不通过处理中"};

    public MyTouziAdapter(List<TouziBean.DataBean> datas, Context context) {

        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_touzi_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TouziBean.DataBean dataBean= datas.get(position);
        holder.money.setText(dataBean.getInvestAmount()+"");

        if (dataBean.getDeadlineType()==1){
            holder.yuqi.setText("" + P2pUtils.calculator(dataBean.getInvestAmount(), dataBean.getAnnualRate(), dataBean.getDeadline()));

        }else if (dataBean.getDeadlineType()==2){
            holder.yuqi.setText("" + P2pUtils.calculator2(dataBean.getInvestAmount(), dataBean.getAnnualRate(), dataBean.getDeadline()));

        }
        holder.name.setText(dataBean.getBorrowTitle()+"");
        holder.shouyi.setText(dataBean.getAnnualRate()+"%");
        holder.qixian.setText(dataBean.getAnnualRate()+"%");
        holder.qixian.setText(T1changerString.t2chager(dataBean.getDeadline(), dataBean.getDeadlineType()));

       switch (dataBean.getBorrowStatus()){
           case 2:
               holder.zhuangtai.setBackgroundResource(R.drawable.shape_zhuangtai_status2);
               holder.zhuangtai.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));


               break;
           case 3:
               holder.zhuangtai.setBackgroundResource(R.drawable.shape_zhaungtai_status3);
               holder.zhuangtai.setTextColor(context.getResources().getColor(R.color.text_org));

               break;
           case 4:
               holder.zhuangtai.setBackgroundResource(R.drawable.shape_zhuangtai_status4);
               holder.zhuangtai.setTextColor(context.getResources().getColor(R.color.status4));


               break;
           case 5:
               holder.zhuangtai.setBackgroundResource(R.drawable.shape_zhuangtai_status5);
               holder.zhuangtai.setTextColor(context.getResources().getColor(R.color.season));


               break;
           case 6:
               holder.zhuangtai.setBackgroundResource(R.drawable.shape_zhuangtai_status6);
               holder.zhuangtai.setTextColor(context.getResources().getColor(R.color.status6));

               break;
           default:
               holder.zhuangtai.setBackgroundResource(R.drawable.shape_zhuangtai_statuselse);
               holder.zhuangtai.setTextColor(context.getResources().getColor(R.color.statuselse));
               break;
       }
//        if (dataBean.getBorrowStatus()==3){
//        }else if(dataBean.getBorrowStatus()==5){
//
//        }else {
//            holder.zhuangtai.setBackgroundColor(Utils.getColor(context,android.R.color.darker_gray));
//        }
     //   String[] ss = new String;
        holder.zhuangtai.setText(str2[dataBean.getBorrowStatus()-1]);
//        if(==3){
//
//        }else if(dataBean.getBorrowStatus()==5){
//            holder.zhuangtai.setText("还款中");
//        }else if(dataBean.getBorrowStatus()==6){
//            holder.zhuangtai.setText("已还款");
//        }
        holder.time.setText("投资时间:"+DateUtils.getStrTime2(dataBean.getInvestTime()+""));

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
        @Bind(R.id.shouyi)
        TextView shouyi;
        @Bind(R.id.qixian)
        TextView qixian;
        @Bind(R.id.zhuangtai)
        TextView zhuangtai;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.yuqi)
        TextView yuqi;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
