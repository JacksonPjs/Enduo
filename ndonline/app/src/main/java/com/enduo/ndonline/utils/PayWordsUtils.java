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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enduo.ndonline.R;
import com.pvj.xlibrary.utils.T;

/**
 * Created by Administrator on 2016/12/31.
 */

public class PayWordsUtils {
    static Paylistenr l ;
    /** 普通等待提示框 */
    public  Dialog createProgressDialog(final  Activity activity) {

        LayoutInflater inflaterDl = LayoutInflater.from(activity);
       View layout = inflaterDl.inflate(R.layout.pay_dialog, null );

        //对话框
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
        dialog.getWindow().setContentView(layout);

        Display display =activity.getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes() ;
        params.width =(int) (display.getWidth()*0.8);
    //    params.height =(int) (display.getWidth()*0.5);    //使用这种方式更改了dialog的框宽
        dialog.getWindow().setAttributes(params);



      final   EditText editText = (EditText) layout.findViewById(R.id.textPay);

        //取消按钮
        Button btnCancel = (Button) layout.findViewById(R.id.dialog_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                editText.setText("");
            }
        });


        //确定按钮
        Button btnOK = (Button) layout.findViewById(R.id.dialog_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              if(LoginRegisterUtils.isNullOrEmpty(editText)){
                  T.ShowToastForShort(activity,"交易密码未输入");
               return;
              }

                if(!LoginRegisterUtils.isPassWord(editText)){
                  T.ShowToastForShort(activity,"交易密码输入不正确");
               return;
              }


                if (l!=null){
                    dialog.dismiss();
                    l.onOk(editText.getText().toString());

                    editText.setText("");
                }

            }
        });
        return dialog ;
    }

    public interface Paylistenr{
        void onOk(String bank);
    }

    public void setPaylistenr(Paylistenr l){
        this.l = l ;
    }
}
