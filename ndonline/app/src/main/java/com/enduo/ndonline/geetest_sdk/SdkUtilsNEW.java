package com.enduo.ndonline.geetest_sdk;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;

import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.UUIDs;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

public class SdkUtilsNEW {

    SdkLister l ;
    private Activity activity;
    private Context context;

    Dialog dialog ;

    String phone ;

    String noncestr ;

    // 创建验证码网络管理器实例
    private Geetest captcha = new Geetest(

            // 设置获取id，challenge，success的URL，需替换成自己的服务器URL
         //   "http://api.apiapp.cc/gtcap/start-mobile-captcha/",
            NetService.API_SERVER_Main+"app/startCaptcha.html",



          //  http://192.168.1.196:8080/startCaptcha.html
            // 设置二次验证的URL，需替换成自己的服务器URL
       //     "http://api.apiapp.cc/gtcap/gt-server-validate/"
            NetService.API_SERVER_Main+ "app/getPhoneCode.html"
    );


    /**
     *   验证手机号码能否注册
     *
     * @param
     */
    public void verificationNewUserPhone(final  Context context,final Activity activity,final EditText phoneEdit) {
        this.activity = activity ;
        this.context= context ;
     //   this.noncestr = UUIDs.uuid();
        Logger.d("noncestr="+noncestr);

        if (LoginRegisterUtils.isNullOrEmpty(phoneEdit)) {
            toastMsg("手机号码未输入");
         //   T.ShowToastForShort(this, "手机号码未输入");
            return ;
        }

        if (!LoginRegisterUtils.isPhone(phoneEdit)) {
            toastMsg("手机号码不正确");
        //    T.ShowToastForShort(this, "手机号码不正确");
            return;
        }




        this.phone= phoneEdit.getText().toString();


        NetWorks.verificationNewUserPhone(phone, new Subscriber<InfoBean>() {
            @Override
            public void onStart() {
                if (!activity.isFinishing()) {
                    if(dialog==null){
                        dialog = DialogUtils.createProgressDialog(activity,"请求中");
                    }else {
                        dialog.show();
                    }
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e)

            {
                dialog.dismiss();
                toastMsg("网络异常");
            //  T.ShowToastForShort(activity,"网络异常");
            }

            @Override
            public void onNext(InfoBean s) {
                //  {"info":"可用的手机号码","status":"0"}

                if (s.getState().getStatus()==0){
                    getHuakuai();
                }else {
                    dialog.dismiss();
                    toastMsg(s.getState().getInfo());
                }

            }
        });
    }



    /**
     * 获取滑块验证
     *
     *
     */
    public void getHuakuai() {

        sdknet();
    }





    public void sdknet() {
        noncestr = UUIDs.uuid();
        NetWorks.startCaptcha(noncestr, new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.e(e.toString());
                dialog.dismiss();
                toastMsg("网络异常");
            }

            @Override
            public void onNext(String s) {
             Logger.json(s);
                try {


                    JSONObject json = new JSONObject(s);

                    String ppp = json.getString("data");
                    JSONObject  parmas  =    new JSONObject(ppp);
                    Logger.json("----"+parmas.toString());
                    dialog.dismiss();
                    openGtTest(context, parmas);
                } catch (Exception e) {
                    dialog.dismiss();
                    toastMsg("服务器异常");
                    Logger.e("----"+e.toString());

                }


            }
        });



    }





    private void toastMsg(final String msg) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void toastLongTimeMsg(final String msg) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private SdkUtilsNEW.GtAppValidateTask mGtAppValidateTask;
    public  interface SdkLister  {
          void sdkSuccess(String r);
       //   void sdkFali(String r);
      }

    public void setSdkLister(SdkLister l){
        this.l =  l ;
    }


    public void openGtTest(Context ctx, JSONObject params) {


        Logger.json("ddddd"+params.toString());
        GtDialog dialog = new GtDialog(ctx, params);

        // 启用debug可以在webview上看到验证过程的一些数据
//        dialog.setDebug(true);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //TODO 取消验证
                toastMsg("未滑动滑块");
            }
        });

        dialog.setGtListener(new GtDialog.GtListener() {

            @Override
            public void gtResult(boolean success, String result) {



                if (success) {

                    Logger.d("gtResult="+result);

              //      toastMsg("滑动正确");

                    SdkUtilsNEW.GtAppValidateTask gtAppValidateTask = new SdkUtilsNEW.GtAppValidateTask();
                    mGtAppValidateTask = gtAppValidateTask;
                    mGtAppValidateTask.execute(result);
                } else {
                    //TODO 验证失败
                    toastMsg("请正确滑动滑块");
                }
            }

            @Override
            public void gtCallClose() {

                toastMsg("close geetest windows");
            }

            @Override
            public void gtCallReady(Boolean status) {


                if (status) {
                    //TODO 验证加载完成
                    toastMsg("请正确滑动滑块");
                } else {
                    //TODO 验证加载超时,未准备完成
                    toastMsg("there's a network jam");
                }
            }

            @Override
            public void gtError() {
                toastMsg("Fatal Error Did Occur.");
            }

        });

    }




    class GtAppValidateTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                JSONObject res_json = new JSONObject(params[0]);

                Map<String, String> validateParams = new HashMap<String, String>();

                validateParams.put("geetest_challenge", res_json.getString("geetest_challenge"));

                validateParams.put("geetest_validate", res_json.getString("geetest_validate"));

                validateParams.put("geetest_seccode", res_json.getString("geetest_seccode"));

                validateParams.put("cellPhone", phone);
                  validateParams.put("noncestr", noncestr);

                String response = captcha.submitPostData(validateParams, "utf-8");

                //TODO 验证通过, 获取二次验证响应, 根据响应判断验证是否通过完整验证

                return response;

            } catch (Exception e) {

                e.printStackTrace();
            }

            return "invalid result";
        }

        @Override
        protected void onPostExecute(String params) {

            Logger.d("server captcha GtAppValidateTask:" + params);
            // {"info":"操作频繁，请稍后重试...","status":"21","value":"63"}
            // {"info":"手机验证码发送成功，请及时查收手机短信！","status":"0"}
            //  toastMsg("server captcha 我的 啊 :" + params);
            l.sdkSuccess(params);
        }
    }


}
