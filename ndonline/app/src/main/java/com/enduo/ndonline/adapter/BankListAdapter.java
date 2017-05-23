package com.enduo.ndonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.Bank;
import com.pvj.xlibrary.loadinglayout.Utils;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BankListAdapter extends BaseAdapter {
    List<Bank> bankLists;
    Context context ;

    public BankListAdapter(List<Bank> bankLists, Context context) {
        this.bankLists = bankLists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bankLists.size();
    }

    @Override
    public Bank getItem(int position) {
        return bankLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final  int position, View convertView, ViewGroup parent) {
        Bank bank = bankLists.get(position);
        ViewHoler  holer = null;
        if (convertView==null){
            holer = new ViewHoler();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bank_list,null);
            holer.name= (TextView) convertView.findViewById(R.id.bankname);
            holer.map= (ImageView) convertView.findViewById(R.id.bankmap);
            holer.containr= (View) convertView.findViewById(R.id.rlcont);
            convertView.setTag(holer);
        }
        holer = (ViewHoler) convertView.getTag();

        holer.name.setText(bank.getValue());

        if (bank.isSelect()){
            holer.map.setImageDrawable(Utils.getDrawble(context,R.mipmap.icon_selected));
        }else{
            holer.map.setImageDrawable(Utils.getDrawble(context,R.mipmap.icon_unselected));
        }

        return convertView;
    }


    class  ViewHoler {
        public TextView name ;
        public ImageView map ;
        public View containr ;


    }
}
