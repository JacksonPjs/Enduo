package com.enduo.ndonline.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.enduo.ndonline.adapter.BankListAdapter;
import com.enduo.ndonline.bean.Bank;
import com.pvj.xlibrary.utils.T;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BankDialog {

    Banklistenr l ;

    /**
     * 普通等待提示框
     */
    public Dialog createProgressDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("选择银行");
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View mTitleView = layoutInflater.inflate(R.layout.dalog_title, null);
        builder.setCustomTitle(mTitleView);


       final List<Bank> banks = BankList.getBankList();
        final BankListAdapter catalogsAdapter = new BankListAdapter(banks, activity);

        builder.setAdapter(catalogsAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

                Bank bank = banks.get(arg1);
                for (int i = 0; i <banks.size() ; i++) {
                    if (i==arg1){
                        banks.get(i).setSelect(true);
                    }else{
                        banks.get(i).setSelect(false);
                    }
                }
                catalogsAdapter.notifyDataSetChanged();

                if (l!=null){
                    l.onBank(bank);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        Display display = activity.getWindowManager().getDefaultDisplay();
        //    params.width =(int) (display.getWidth()*0.8);
        params.height = (int) (display.getHeight() * 0.6);    //使用这种方式更改了dialog的框宽
        window.setAttributes(params);
        return dialog;
    }


   public interface Banklistenr{
         void onBank(Bank bank);
   }

    public void setBanklistenr(Banklistenr l){
        this.l = l ;
    }
}
