package com.enduo.ndonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.enduo.ndonline.APP.Contacts;
import com.enduo.ndonline.R;
import com.enduo.ndonline.bean.DiscountBean;
import com.enduo.ndonline.bean.DiscountResultBean;
import com.enduo.ndonline.bean.OneBean;
import com.enduo.ndonline.productlist.T1changerString;
import com.enduo.ndonline.utils.HashMapUtils;
import com.google.gson.Gson;
import com.pvj.xlibrary.utils.DateUtils;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/3/16.
 */

public class DiscountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private DiscountBean bean;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickLitener mOnItemClickListener = null;
    public enum ITEM_TYPE {
        ITEM,
        ITEMJX
    }
    private List<Integer> checkPositionlist;
    private List<Integer> checkJXPositionlist;
    HashMap map;
    HashMap moneymap;
    int maxmoney;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickListener = mOnItemClickLitener;
    }


    public DiscountAdapter(Context context, DiscountBean bean, int maxmoney) {
        this.mContext = context;
        this.bean = bean;
        inflater = LayoutInflater.from(mContext);
        checkPositionlist = new ArrayList<>();
        checkJXPositionlist = new ArrayList<>();
        map=new HashMap();
        moneymap=new HashMap();
        this.maxmoney=maxmoney;
    }

    @Override
    public int getItemCount() {
        int size=0;
        if (bean!=null){
            size=bean.getUserCoupon().size()+bean.getUserCouponJx().size();
        }

        return size;
    }



    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DiscountJXViewHolder) {
            DiscountBean.UserCouponJx userCouponJx=bean.getUserCouponJx().get(position);
            ((DiscountJXViewHolder) holder).money.setText(userCouponJx.getInterestticket()+"%");
            ((DiscountJXViewHolder) holder).gz1.setText(mContext.getResources().getString(R.string.discount_jx_item1));
            ((DiscountJXViewHolder) holder).gz2.setText(mContext.getResources().getString(R.string.discount_jx_item2));
            ((DiscountJXViewHolder) holder).gz3.setVisibility(View.GONE);
            ((DiscountJXViewHolder) holder).cv_countdownView.setText("有效期至"+ DateUtils.getStrTime3(userCouponJx.getExpirationDate()+""));


            ((DiscountJXViewHolder) holder).img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_discount_jx));



        } else if (holder instanceof DiscountViewHolder) {
            ((DiscountViewHolder) holder).gz3.setVisibility(View.VISIBLE);
            DiscountBean.UserCoupon userCoupon=bean.getUserCoupon().get(getItemCount()-position-1);
            ((DiscountViewHolder) holder).money.setText("￥"+userCoupon.getCouponAmount()+"");
            ((DiscountViewHolder) holder).gz1.setText(mContext.getResources().getString(R.string.discount_dj_item1));
            ((DiscountViewHolder) holder).gz2.setText(mContext.getResources().getString(R.string.discount_dj_item2));
            ((DiscountViewHolder) holder).gz3.setText(mContext.getResources().getString(R.string.discount_dj_item3));
            ((DiscountViewHolder) holder).cv_countdownView.setText("有效期至"+ DateUtils.getStrTime3(userCoupon.getExpirationDate()+""));
            if (userCoupon.getCouponType()==1){
                ((DiscountViewHolder) holder).img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_discount_tiyan));
                ((DiscountViewHolder) holder).gz1.setText(mContext.getResources().getString(R.string.discount_jx_item1));
                ((DiscountViewHolder) holder).gz2.setText(mContext.getResources().getString(R.string.discount_jx_item2));
                ((DiscountViewHolder) holder).gz3.setVisibility(View.GONE);

            }else if (userCoupon.getCouponType()==2){
                ((DiscountViewHolder) holder).img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_discount_hb));

            }else if (userCoupon.getCouponType()==4){
                ((DiscountViewHolder) holder).img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_discount_dj));

            }


        }
        holder.setIsRecyclable(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    if (getItemViewType(position)==ITEM_TYPE.ITEM.ordinal()){
                        //类型0：代金券

                        //清空加息
                        List jxlist=HashMapUtils.getKeys(map,"jx");
                        for (int i=0;i<jxlist.size();i++){
                            Integer pos= (Integer) jxlist.get(i);
                            map.remove(pos);
                        }
                        List otherlist=HashMapUtils.getKeys(map,"other");
                        for (int i=0;i<otherlist.size();i++){
                            Integer pos= (Integer) otherlist.get(i);
                            map.remove(pos);
                        }

                        notifyDataSetChanged();
                        DiscountBean.UserCoupon userCoupon=bean.getUserCoupon().get(getItemCount()-position-1);
                        int id=userCoupon.getId();
                        if (userCoupon.getCouponType()==4){
                            //代金券
                            map.put(position,"bt");


                            moneymap.put(id,"money");



                            if (((DiscountViewHolder) holder).checkBox.isChecked()){
                                //如果再次选择，移除
                                ((DiscountViewHolder) holder).checkBox.setVisibility(View.GONE);
                                ((DiscountViewHolder) holder).checkBox.setChecked(false);
                                moneymap.remove(id);
                                map.remove(position);
                                notifyDataSetChanged();
                            }
                            List moneylist=HashMapUtils.getKeys(moneymap,"money");
                            Double selemoney=0.0;
                            for (int j=0;j<moneylist.size();j++){
                                int str= (int) moneylist.get(j);
                                for (int k=0;k<bean.getUserCoupon().size();k++){
                                    int idstr=bean.getUserCoupon().get(k).getId();
                                    if (idstr==str){
                                        String a=bean.getUserCoupon().get(k).getCouponAmount();
                                        double dou = Double.valueOf(a).intValue();
                                        selemoney=selemoney+dou;
                                    }
                                }
                            }
                            if (selemoney>maxmoney){

                                Toast.makeText(mContext,"代金券金额不能超过投资金额的1%",Toast.LENGTH_SHORT).show();
                                selemoney=0.0;
                                List moneylists=HashMapUtils.getKeys(moneymap,"money");

                                for(int z=0;z<moneylists.size()-1;z++){
                                    int str= (int) moneylist.get(z);
                                    for (int k=0;k<bean.getUserCoupon().size();k++){
                                        int idstr=bean.getUserCoupon().get(k).getId();
                                        if (idstr==str){
                                            String a=bean.getUserCoupon().get(k).getCouponAmount();
                                            double dou = Double.valueOf(a).intValue();
                                            selemoney=selemoney+dou;
                                        }
                                    }
                                }

                                moneymap.remove(id);
                                map.remove(position);
                                notifyDataSetChanged();

                            }

                            //返回值
                            List list=HashMapUtils.getKeys(moneymap,"money");
                            if (list.size()>0){
                                ArrayList<DiscountResultBean> arrayList=new ArrayList<>();
                                double textmoney=0;
                                for (int i=0;i<list.size();i++){
                                    DiscountResultBean resultBean=new DiscountResultBean();
                                    int moneyid= (int) list.get(i);
                                    for (int j=0;j<bean.getUserCoupon().size();j++){
                                        DiscountBean.UserCoupon resUserConpon=bean.getUserCoupon().get(j);
                                        if (resUserConpon.getId()==moneyid){
                                            resultBean.setId(moneyid+"");
                                            String resamout=resUserConpon.getCouponAmount()+"";
                                            double dou = Double.valueOf(resamout).intValue();
                                            resultBean.setCouponAmount(resUserConpon.getCouponAmount()+"");
                                            resultBean.setCouponType(resUserConpon.getCouponType()+"");
                                            resultBean.setGid("-1");
                                            textmoney=textmoney+dou;
                                            arrayList.add(resultBean);
                                        }
                                    }
                                }
//
                                    Gson gson=new Gson();

                                    String result=""+gson.toJson(arrayList)+"";
                                    mOnItemClickListener.onItemClick(v,result,"代金券:"+textmoney+"元");

                            }else {
                                mOnItemClickListener.onItemClick(v,null,null);
                            }

                        }else if (userCoupon.getCouponType()==2){
                            //红包
                            map.clear();
                            moneymap.clear();
                            notifyDataSetChanged();
                            map.put(position,"hongbao");
                            moneymap.put(id,"money");
                            if (((DiscountViewHolder) holder).checkBox.isChecked()){
                                ((DiscountViewHolder) holder).checkBox.setVisibility(View.GONE);
                                ((DiscountViewHolder) holder).checkBox.setChecked(false);
                                map.remove(position);
                                moneymap.remove(id);
                                notifyDataSetChanged();
                            }

                            //返回值
                            List list=HashMapUtils.getKeys(moneymap,"money");
                            if (list.size()>0){
                                ArrayList<DiscountResultBean> arrayList=new ArrayList<>();
                                double textmoney=0;
                                DiscountResultBean resultBean=new DiscountResultBean();
                                    int moneyid= (int) list.get(0);
                                    for (int j=0;j<bean.getUserCoupon().size();j++){
                                        DiscountBean.UserCoupon resUserConpon=bean.getUserCoupon().get(j);
                                        if (resUserConpon.getId()==moneyid){
                                            resultBean.setId(moneyid+"");
                                            String resamout=resUserConpon.getCouponAmount()+"";
                                            float dou = Float.valueOf(resamout).intValue();
                                            textmoney=dou;
                                            resultBean.setCouponAmount(resUserConpon.getCouponAmount()+"");
                                            resultBean.setCouponType(resUserConpon.getCouponType()+"");
                                            int gid=-1;
                                            for (int i=0;i<bean.getUserCouponGz().size();i++){
                                                float f=bean.getUserCouponGz().get(i).getInterestQuota();
                                                if (bean.getUserCouponGz().get(i).getPropType()==2){
                                                if (f==dou){
                                                    gid=bean.getUserCouponGz().get(i).getId();
                                                }
                                                }
                                            }
                                            resultBean.setGid(""+gid);
                                            arrayList.add(resultBean);

                                    }
                                }
//
                                Gson gson=new Gson();

                                String result=""+gson.toJson(arrayList)+"";
                                mOnItemClickListener.onItemClick(v,result,"红包:"+textmoney+"元");

                            }else {
                                mOnItemClickListener.onItemClick(v,null,null);
                            }
                        }else {
                            //除代金券，加息券,红包外
                            map.clear();
                            moneymap.clear();
                            notifyDataSetChanged();
                            moneymap.put(id,"money");
                            map.put(position,"other");
                            if (((DiscountViewHolder) holder).checkBox.isChecked()){
                                ((DiscountViewHolder) holder).checkBox.setVisibility(View.GONE);
                                ((DiscountViewHolder) holder).checkBox.setChecked(false);
                                map.remove(position);
                                moneymap.remove(id);
                                notifyDataSetChanged();
                            }

                            //返回值
                            List list=HashMapUtils.getKeys(moneymap,"money");
                            if (list.size()>0){
                                ArrayList<DiscountResultBean> arrayList=new ArrayList<>();

                                DiscountResultBean resultBean=new DiscountResultBean();
                                int moneyid= (int) list.get(0);
                                for (int j=0;j<bean.getUserCoupon().size();j++){
                                    DiscountBean.UserCoupon resUserConpon=bean.getUserCoupon().get(j);
                                    if (resUserConpon.getId()==moneyid){
                                        resultBean.setId(moneyid+"");
                                        resultBean.setCouponAmount(resUserConpon.getCouponAmount()+"");
                                        resultBean.setCouponType(resUserConpon.getCouponType()+"");
                                        resultBean.setGid("-1");
                                        arrayList.add(resultBean);

                                    }
                                }
//
                                Gson gson=new Gson();

                                String result=""+gson.toJson(arrayList)+"";
                                mOnItemClickListener.onItemClick(v,result,"体验券:"+resultBean.getCouponAmount()+"元");

                            }else {
                                mOnItemClickListener.onItemClick(v,null,null);
                            }
                        }



                    } else if (getItemViewType(position)==ITEM_TYPE.ITEMJX.ordinal()){
                        //类型1：加息券
                        map.clear();
                        notifyDataSetChanged();
                        map.put(position,"jx");
                        if (((DiscountJXViewHolder) holder).checkBox.isChecked()){
                            ((DiscountJXViewHolder) holder).checkBox.setVisibility(View.GONE);
                            ((DiscountJXViewHolder) holder).checkBox.setChecked(false);
                            map.remove(position);
                            notifyDataSetChanged();
                        }
//返回值
                        List jxlist=HashMapUtils.getKeys(map,"jx");
                        if (jxlist.size()>0){
                            if (jxlist.size()==1){
                                int pos= (int) jxlist.get(0);
                                int textmoney=0;
                                Map<String,String> stringMap=new HashMap<String, String>();
                                stringMap.put("id",bean.getUserCouponJx().get(pos).getId()+"");
                                stringMap.put("couponType","0");
                                stringMap.put("couponAmount",bean.getUserCouponJx().get(pos).getInterestticket()+"");
                                int gid=-1;
                                for (int i=0;i<bean.getUserCouponGz().size();i++){
                                    if (bean.getUserCouponGz().get(i).getInterestQuota()==bean.getUserCouponJx().get(pos).getInterestticket()){
                                      if (bean.getUserCouponGz().get(i).getPropType()==0)
                                        gid=bean.getUserCouponGz().get(i).getId();
                                    }
                                }
                                stringMap.put("gid",""+gid);
                                Gson gson=new Gson();
//                            gson.toJson(map);
                                String result="["+gson.toJson(stringMap)+"]";
                                mOnItemClickListener.onItemClick(v,result,"加息券:"+bean.getUserCouponJx().get(pos).getInterestticket()+"");
                            }else
                                mOnItemClickListener.onItemClick(v,null,null);
                        }else {
                            mOnItemClickListener.onItemClick(v,null,null);
                        }
                    }




                }
            }
        });
//CheckBox 点击事件
        if (map.containsKey(position)) {
            if (getItemViewType(position) == ITEM_TYPE.ITEM.ordinal()) {

                ((DiscountViewHolder) holder).checkBox.setVisibility(View.VISIBLE);
                ((DiscountViewHolder) holder).checkBox.setChecked(true);
                ((DiscountViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((DiscountViewHolder) holder).checkBox.setVisibility(View.GONE);
                        map.remove(position);
                        DiscountBean.UserCoupon userCoupon=bean.getUserCoupon().get(getItemCount()-position-1);
                        int id=userCoupon.getId();
                        moneymap.remove(id);
                        if (mOnItemClickListener != null) {
                            String str= ((DiscountViewHolder) holder).money.getText().toString();
                            mOnItemClickListener.onItemClick(v,null,null);
                        }

                    }
                });
            }
            if (getItemViewType(position)==ITEM_TYPE.ITEMJX.ordinal()){
                ((DiscountJXViewHolder) holder).checkBox.setVisibility(View.VISIBLE);
                ((DiscountJXViewHolder) holder).checkBox.setChecked(true);
                ((DiscountJXViewHolder) holder).checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((DiscountJXViewHolder) holder).checkBox.setVisibility(View.GONE);
                        map.remove(position);
                        if (mOnItemClickListener != null) {
                           String str= ((DiscountJXViewHolder) holder).money.getText().toString();

                            mOnItemClickListener.onItemClick(v,null,null);
                        }
                    }
                });
            }
        }


    }
    @Override
    public int getItemViewType(int position) {
        int jxsize=bean.getUserCouponJx().size();
        return position <=jxsize-1 ? ITEM_TYPE.ITEMJX.ordinal() : ITEM_TYPE.ITEM.ordinal();
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM.ordinal()) {
            return new DiscountViewHolder(inflater.inflate(R.layout.activity_discount_item, parent, false));
        } else {
            return new DiscountJXViewHolder(inflater.inflate(R.layout.activity_discount_item, parent, false));
        }
    }



    class DiscountViewHolder extends RecyclerView.ViewHolder {

        TextView money,cv_countdownView;
        ImageView img;
        TextView gz1,gz2,gz3;
        CheckBox checkBox;

        public DiscountViewHolder(View view) {
            super(view);
            money = (TextView) view.findViewById(R.id.money);
            cv_countdownView = (TextView) view.findViewById(R.id.cv_countdownView);
            gz1 = (TextView) view.findViewById(R.id.gz1);
            gz2 = (TextView) view.findViewById(R.id.gz2);
            gz3 = (TextView) view.findViewById(R.id.gz3);
            img = (ImageView) view.findViewById(R.id.img);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        }

    }
    class DiscountJXViewHolder extends RecyclerView.ViewHolder {

        TextView money,cv_countdownView;
        ImageView img;
        TextView gz1,gz2,gz3;
        CheckBox checkBox;

        public DiscountJXViewHolder(View view) {
            super(view);
            money = (TextView) view.findViewById(R.id.money);
            cv_countdownView = (TextView) view.findViewById(R.id.cv_countdownView);
            gz1 = (TextView) view.findViewById(R.id.gz1);
            gz2 = (TextView) view.findViewById(R.id.gz2);
            gz3 = (TextView) view.findViewById(R.id.gz3);
            img = (ImageView) view.findViewById(R.id.img);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);


        }

    }



    public  interface OnItemClickLitener {
        void onItemClick(View view , String result,String text);
    }
}