package com.enduo.ndonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.ShareBean;
import com.pvj.xlibrary.loadinglayout.Utils;
import com.pvj.xlibrary.utils.DateUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ShareAdapter extends BaseAdapter {
    List<ShareBean.DataBean> datas;
    Context context;

    public ShareAdapter(Context context, List<ShareBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public ShareBean.DataBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShareBean.DataBean dataBean = datas.get(position);
        ViewHolder holer = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_share, null);
            holer = new ViewHolder(convertView);

            convertView.setTag(holer);
        }
        holer = (ViewHolder) convertView.getTag();

        holer.xuhao.setText(position+1+"");

        if (dataBean.getCellPhone().length()>4){
            String l = dataBean.getCellPhone().substring(dataBean.getCellPhone().length()-4,dataBean.getCellPhone().length());
            holer.name.setText("***"+l);
       }else{
            holer.name.setText("***"+dataBean.getCellPhone());
        }


        holer.time.setText(DateUtils.getStrTime3(dataBean.getCreateTime()+""));
        if (dataBean.getIsRecharge()>0){
            holer.chager.setText("是");
        }else {
            holer.chager.setText("否");

        }
        if (dataBean.getIsInvest()>0){
            holer.touzi.setText("是");
        }else {
            holer.touzi.setText("否");

        }

        holer.jiangli.setText("");
        return convertView;
    }



     class ViewHolder {
        @Bind(R.id.xuhao)
        TextView xuhao;
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.chager)
        TextView chager;
        @Bind(R.id.touzi)
        TextView touzi;
        @Bind(R.id.jiangli)
        TextView jiangli;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
