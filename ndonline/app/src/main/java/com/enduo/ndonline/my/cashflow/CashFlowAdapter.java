package com.enduo.ndonline.my.cashflow;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.CaseFlowBean;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.utils.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26.
 */

public class CashFlowAdapter extends RecyclerView.Adapter<CashFlowAdapter.ViewHolder> {

    private List<CaseFlowBean.DataBean> datas;
    private Context context;
//    String[] str2={ "充值成功", "提现冻结", "提现成功解冻", "提现成功","提现失败解冻", "投资冻结", "投资成功解冻", "投资成功", "投资失败解冻", "借款成功", "还款成功", "回收本金","回收收益", "受让债权成功", "转让债权成功", "居间管理费", "转让债权手续费", "红包", "新手奖励", "奖励加成", "推荐收益奖励", "提现扣除手续费"};



    public CashFlowAdapter(List<CaseFlowBean.DataBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cash_flow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CaseFlowBean.DataBean b = datas.get(position);
        holder.time.setText(DateUtils.getStrTime2(b.getCreateTime() + ""));
        //1（收入）2（冻结）3.（解冻）4.（支出）


        if (b.getOperType() == 1) {
         //   holder.type.setText("收入");
            holder.type.setBackground(context.getResources().getDrawable(R.mipmap.type_sr));
            holder.money.setText("+" + b.getOperAmount());
            holder.money.setTextColor(context.getResources().getColor(R.color.text_org));
        } else if (b.getOperType() == 2) {
          //  holder.type.setText("冻结");
            holder.type.setBackground(context.getResources().getDrawable(R.mipmap.type_dj));

            holder.money.setText("-" + b.getOperAmount());
            holder.money.setTextColor(context.getResources().getColor(R.color.mouth_btn_bg));
        } else if (b.getOperType() == 3) {
          //  holder.type.setText("解冻");
            holder.type.setBackground(context.getResources().getDrawable(R.mipmap.type_jd));
            holder.money.setTextColor(context.getResources().getColor(R.color.bg_2));

            holder.money.setText("+" + b.getOperAmount());
        } else if (b.getOperType() == 4) {
          //  holder.type.setText("支出");
            holder.type.setBackground(context.getResources().getDrawable(R.mipmap.type_zc));
            holder.money.setTextColor(context.getResources().getColor(R.color.season));
            holder.money.setText("-" + b.getOperAmount());
        }


//        if(b.getFundType()==1){
//            holder.type.setTextColor(Color.parseColor("#1Ad46e"));
//        }else  if(b.getFundType()==4){
//            holder.type.setTextColor(Utils.getColor(context,R.color.colorPrimary));
//        }else{
//            holder.type.setTextColor(Color.parseColor("#555555"));
//        }
//
//        holder.type.setText(str2[b.getFundType()-1]);

//        java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
//        nf.setGroupingUsed(false);
//        holder.usebMoney.setText( nf.format(b.getUsableAmount() )+ "");
        holder.benzhu.setText(b.getRemarks() + "");

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.money)
        TextView money;

        @Bind(R.id.benzhu)
        TextView benzhu;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
