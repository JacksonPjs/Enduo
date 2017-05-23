package com.enduo.ndonline.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.R;
import com.enduo.ndonline.adapter.DiscountAdapter;
import com.enduo.ndonline.bean.DiscountBean;
import com.enduo.ndonline.bean.InterestBean;
import com.enduo.ndonline.bean.LoginBean;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/3/16.
 */

public class DiscountDialog extends Dialog implements View.OnClickListener {

    private TextView cancel;
    LinearLayout ll_nodata;
    Context context;
    View localView;
    btnBackListener listener;
    RecyclerView recyclerView;
    onItemClickListener itemClickListener;
    DiscountBean bean=null;
    DiscountAdapter adapter;
    String result=null;
    String test=null;
    int money;
    int borrtype;




    public DiscountDialog(Context context, int money,int borrtype, btnBackListener listener) {
        super(context);
        this.listener=listener;
        this.money=money;
        this.context = context;
        this.borrtype = borrtype;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 这句代码换掉dialog默认背景，否则dialog的边缘发虚透明而且很宽
        // 总之达不到想要的效果
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        LayoutInflater inflater = getLayoutInflater();
        localView = inflater.inflate(R.layout.pop_discount, null);
        localView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.score_business_query_enter));
        setContentView(localView);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        WindowManager manager=window.getWindowManager();
        Display d = manager.getDefaultDisplay();
        getWindow().setLayout(ViewGroup.LayoutParams.FILL_PARENT, (int) (d.getHeight()*0.5));
//        this.setCanceledOnTouchOutside(false);
//        this.setCancelable(false);
        initView();
        net();
        initListener();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        this.dismiss();
        return super.onTouchEvent(event);
    }

    private void initListener() {
        cancel.setOnClickListener(this);
    }

    private void initView() {
        cancel = (TextView) findViewById(R.id.btnBack);
        ll_nodata = (LinearLayout) findViewById(R.id.ll_nodata);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void net(){
        StringBuilder sb = new StringBuilder();
        sb.append(" _ed_token_");
        sb.append("=");
        sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context,"token",""));
        sb.append(";");

        sb.append(" _ed_username_");
        sb.append("=");
        sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context,"name",""));
        sb.append(";");

        sb.append(" _ed_cellphone_");
        sb.append("=");
        sb.append((String) SharedPreferencesUtils.getParam(MyApplication.context,"phone",""));
        sb.append(";");
        NetWorks.interestDisountList(sb.toString(), new Subscriber<DiscountBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(DiscountBean discountBean) {
                if (discountBean.getState().getStatus() == 0){
                    DiscountBean bean=discountBean;
                    DiscountBean lastBean=new DiscountBean();
                    if (borrtype!=5){
                        for (int i=0;i<bean.getUserCoupon().size();i++){
                            if (bean.getUserCoupon().get(i).getCouponType()==1){
                                bean.getUserCoupon().remove(i);
                                i=0;
                            }
                        }
                    }
                    for (int i=0;i<bean.getUserCoupon().size();i++){
                        String couponamount=bean.getUserCoupon().get(i).getCouponAmount();
                        double amout = Double.valueOf(couponamount).intValue();
                        if (money/100<amout){
                            if (bean.getUserCoupon().get(i).getCouponType()==4){
                                bean.getUserCoupon().remove(i);
                                i=0;
                            }

                        }
                    }
                   ArrayList<DiscountBean.UserCouponGz> gzArrayList= discountBean.getUserCouponGz();
                    List<Float> quotallist=new ArrayList<>();
                    List<Double> quotadjllist=new ArrayList<>();
                    ArrayList<DiscountBean.UserCoupon> userCouponllist=new ArrayList<>();
                    ArrayList<DiscountBean.UserCouponJx> userCouponjxllist=new ArrayList<>();
                    for (int i=0;i<gzArrayList.size();i++) {
                        if (gzArrayList.get(i).getUseMinMoney() <= money) {
                            if (gzArrayList.get(i).getPropType()==0){
                                float quota = gzArrayList.get(i).getInterestQuota();
                                quotallist.add(quota);
                            }else {
                                double quo= (double) gzArrayList.get(i).getInterestQuota();
                                quotadjllist.add(quo);
                            }

                        }

                    }
                    for (int j = 0; j < discountBean.getUserCoupon().size(); j++) {
                        String disAmount = discountBean.getUserCoupon().get(j).getCouponAmount();
                        double dis = Double.valueOf(disAmount).intValue();
                        if (discountBean.getUserCoupon().get(j).getCouponType()==1){
                            userCouponllist.add(discountBean.getUserCoupon().get(j));
                        }
                        if (quotadjllist.contains(dis)) {
                            DiscountBean.UserCoupon userCoupon=discountBean.getUserCoupon().get(j);
                            userCouponllist.add(userCoupon);
                        }




                    }
                    for (int z = 0; z < discountBean.getUserCouponJx().size(); z++) {
                        float jxamount = discountBean.getUserCouponJx().get(z).getInterestticket();
                        if (quotallist.contains(jxamount)) {
                            DiscountBean.UserCouponJx userCouponJx=discountBean.getUserCouponJx().get(z);
                            userCouponjxllist.add(userCouponJx);
                        }

                    }
                    lastBean.setUserCoupon(userCouponllist);
                    lastBean.setUserCouponJx(userCouponjxllist);
                    lastBean.setUserCouponGz(discountBean.getUserCouponGz());

                    if (discountBean.getUserCouponJx().size()==0&&discountBean.getUserCoupon().size()==0
                            ||(lastBean.getUserCoupon().size()<=0&&lastBean.getUserCouponJx().size()<=0)){
                        ll_nodata.setVisibility(View.VISIBLE);
                    }else {
                        ll_nodata.setVisibility(View.GONE);

                        adapter = new DiscountAdapter(context, lastBean,money/100);
                        //设置布局管理器
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        //添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
                        //设置Adapter
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickLitener(new DiscountAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, String position,String text) {
                                result = position + "";
                                test=text;
//                            Toast.makeText(context, "请在该区域之外点击"+position, 0).show();
//                            if (itemClickListener!=null){
//                                itemClickListener.onItemclicck("点击"+position);
////                                dismiss();
//                            }
                            }
                        });
                    }
                } else if (discountBean.getState().getStatus() == 99) {
                    netLogin();
                }

            }
        });
    }
    private void netLogin() {

        NetWorks.login(SharedPreferencesUtils.getUserName(getContext()),
                SharedPreferencesUtils.getPassword(getContext()), new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.json(e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (loginBean.getState().getStatus() == 0) {
                            SharedPreferencesUtils.savaUser(context, loginBean, SharedPreferencesUtils.getPassword(context));
                            net();

                        } else {
                            SharedPreferencesUtils.setParam(getContext(), "islogin", false);
                        }
                    }
                }
        );
    }



    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                if (listener!=null){
                    listener.btnBackonclick(result,test);
                }
                this.dismiss();
                break;
//            case R.id.clearallpan:
//                Toast.makeText(context, "请在该区域之外点击", 0).show();
//                break;
        }
    }

    public   interface btnBackListener{
        void btnBackonclick(String result, String text);
    }
    public   interface onItemClickListener{
        String onItemclicck(String string);
    }
    public void setonItemClickListener(onItemClickListener listener){
        itemClickListener=listener;
    }
}
