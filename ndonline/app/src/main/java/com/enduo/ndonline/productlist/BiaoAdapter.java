package com.enduo.ndonline.productlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.BiaoBean;
import com.enduo.ndonline.bean.DiscountListBean;
import com.enduo.ndonline.ui.activity.DetailsActivity;
import com.pvj.xlibrary.imageview.CircleImageView;
import com.pvj.xlibrary.imageview.CircleProgressView;
import com.pvj.xlibrary.loadinglayout.Utils;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BiaoAdapter extends RecyclerView.Adapter<BiaoAdapter.ViewHolder> {
    private List<BiaoBean.DataBean> datas;
    private Context context ;
    String[] str2={"申请中","即将开标", "招标中", "满标中", "还款中", "已还款", "借款失败", "复审失败", "流标中", "复审中", "已流标"};


    public BiaoAdapter(List<BiaoBean.DataBean> datas, Context context) {
        this.datas = datas;
        this.context = context ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biao_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
     final    BiaoBean.DataBean b = datas.get(position);


        holder.circleProgressbar.setProgress(T1changerString.progress(b.getBorrowStatus(),b.getHasBorrowAmount(),b.getBorrowAmount()));
        holder.name.setText(b.getBorrowTitle()+"");

        holder.mouth.setText(T1changerString.t2chager(b.getDeadline(), b.getDeadlineType()));

//        if(b.getDeadlineType()==1){
//            holder.danwei.setText("天");
//        }else if(b.getDeadlineType()==2){
//            holder.danwei.setText("月");
//        }

//        if (b.getBorrowStatus()==3){
//            holder.biao_statas.setBackgroundColor(Utils.getColor(context,R.color.colorPrimary));
//        }else if(b.getBorrowStatus()==5){
//            holder.biao_statas.setBackgroundColor(Utils.getColor(context,R.color.season));
//        }else {
//            holder.biao_statas.setBackgroundColor(Utils.getColor(context,android.R.color.darker_gray));
//        }
        switch (b.getBorrowStatus()){
            case 2:
                holder.biao_statas.setBackgroundResource(R.drawable.shape_zhuangtai_status2);
                holder.biao_statas.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));


                break;
            case 3:
                holder.biao_statas.setBackgroundResource(R.drawable.shape_zhaungtai_status3);
                holder.biao_statas.setTextColor(context.getResources().getColor(R.color.text_org));

                break;
            case 4:
                holder.biao_statas.setBackgroundResource(R.drawable.shape_zhuangtai_status4);
                holder.biao_statas.setTextColor(context.getResources().getColor(R.color.status4));


                break;
            case 5:
                holder.biao_statas.setBackgroundResource(R.drawable.shape_zhuangtai_status5);
                holder.biao_statas.setTextColor(context.getResources().getColor(R.color.season));


                break;
            case 6:
                holder.biao_statas.setBackgroundResource(R.drawable.shape_zhuangtai_status6);
                holder.biao_statas.setTextColor(context.getResources().getColor(R.color.status6));

                break;
            default:
                holder.biao_statas.setBackgroundResource(R.drawable.shape_zhuangtai_statuselse);
                holder.biao_statas.setTextColor(context.getResources().getColor(R.color.statuselse));
                break;
        }

        holder.biao_statas.setText(str2[b.getBorrowStatus()-1]);

        DecimalFormat df   = new DecimalFormat("######0.00");
        holder.lilv.setText(df.format(b.getAnnualRate())+"%");


      //  holder.circleProgressbar.setProgressNotInUiThread(80);

        holder.biaoHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b.getBorrowType() == ProductType.MouthMouth||b.getBorrowType() == ProductType.DAYDAY||b.getBorrowType() == ProductType.SeasonSeaon){
                    Intent intent = new Intent(context,DetailsActivity.class);
                    intent.putExtra("id",b.getId()+"");
                    intent.putExtra("borrowType",b.getBorrowType());
                    intent.putExtra("borrowStatus",b.getBorrowStatus());
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context,DetailsActivity.class);
                    intent.putExtra("id",b.getId()+"");
                    intent.putExtra("borrowType",b.getBorrowType());
                    intent.putExtra("borrowStatus",b.getBorrowStatus());
                    context.startActivity(intent);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.biao_statas)
        TextView biao_statas;

        @Bind(R.id.biao_head)
        View biaoHead;
        @Bind(R.id.lilv)
        TextView lilv;


        @Bind(R.id.mouth)
        TextView mouth;
        @Bind(R.id.circleProgressbar)
        CircleProgressView circleProgressbar;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
