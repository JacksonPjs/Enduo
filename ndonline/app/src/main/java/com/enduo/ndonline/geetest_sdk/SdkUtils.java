package com.enduo.ndonline.geetest_sdk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;


import com.enduo.ndonline.bean.InfoBean;
import com.enduo.ndonline.login2register.RegisterActivity;
import com.enduo.ndonline.net.NetService;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.LoginRegisterUtils;
import com.enduo.ndonline.utils.UUIDs;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;
import com.pvj.xlibrary.utils.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

public class SdkUtils {

    SdkLister l ;
    private Activity activity;
    private Context context;
    //考虑用户当时可能所处在弱网络环境，所以异步请求可能在后台用时很久才获取到验证的数据。xxdemo仅作演示。
    private ProgressDialog progressDialog;
    private GtAppDlgTask mGtAppDlgTask;
    private GtAppValidateTask mGtAppValidateTask;

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
            NetService.API_SERVER_Main+ "app/getPhoneCode.html?noncestr="+noncestr
    );


    /**
     *   验证手机号码能否注册
     *
     * @param
     */
    public void verificationNewUserPhone(final  Context context,final Activity activity,final EditText phoneEdit) {
        this.activity = activity ;
        this.context= context ;
        this.noncestr = UUIDs.uuid();
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
                    progressDialog = ProgressDialog.show(context, null, "Loading", true, true);


                    progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            toastMsg("user cancel progress dialog");

                            if(mGtAppDlgTask!=null){
                            if (mGtAppDlgTask.getStatus() == AsyncTask.Status.RUNNING) {
                                Log.i("async task", "status running");
                                captcha.cancelReadConnection();
                                mGtAppDlgTask.cancel(true);
                            } else {
                                Log.i("async task", "No thing happen");
                            }
                            }
                        }
                    });
                }

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e)

            {
                progressDialog.dismiss();
                toastMsg("网络异常");
             //   T.ShowToastForShort(activity,"网络异常");
            }

            @Override
            public void onNext(InfoBean s) {
                //  {"info":"可用的手机号码","status":"0"}

                if (s.getState().getStatus()==0){
                    getHuakuai();
                }else {
                    progressDialog.dismiss();
                    toastMsg("手机号码已注册，请更换手机号码");
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
        this.phone= phone;

        SdkUtils.GtAppDlgTask gtAppDlgTask = new SdkUtils.GtAppDlgTask();
        mGtAppDlgTask = gtAppDlgTask;
        mGtAppDlgTask.execute();


        captcha.setTimeout(5000);

        captcha.setGeetestListener(new Geetest.GeetestListener() {
            @Override
            public void readContentTimeout() {
                mGtAppDlgTask.cancel(true);
                //TODO 获取验证参数超时
                progressDialog.dismiss();
                //Looper.prepare() & Looper.loop(): 在当前线程并没有绑定Looper时返回为null, 可以与toastMsg()一同在正式版本移除
                Looper.prepare();
                toastMsg("read content time out");
                Looper.loop();
            }

            @Override
            public void submitPostDataTimeout() {
                mGtAppValidateTask.cancel(true);
                //TODO 提交二次验证超时
                toastMsg("submit error");
            }

            @Override
            public void receiveInvalidParameters() {
                //TODO 从API接收到无效的JSON参数
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                toastMsg("Did recieve invalid parameters.");
            }
        });
    }


    class GtAppDlgTask extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... params) {

            return captcha.checkServer();
        }

        @Override
        protected void onPostExecute(JSONObject parmas) {

            Logger.json("GtAppDlgTask=="+parmas.toString());
            if (parmas != null) {

                // 根据captcha.getSuccess()的返回值 自动推送正常或者离线验证
                if (captcha.getSuccess()) {
                    openGtTest(context, parmas);
                } else {
                    // TODO 从API_1获得极验服务宕机或不可用通知, 使用备用验证或静态验证
                    // 静态验证依旧调用上面的openGtTest(_, _, _), 服务器会根据getSuccess()的返回值, 自动切换
                    // openGtTest(context, params);
                    toastLongTimeMsg("Geetest Server is Down.");
                    // 执行此处网站主的备用验证码方案
                }

            } else {
                toastLongTimeMsg("Can't Get Data from API_1");
            }
        }

        public void openGtTest(Context ctx, JSONObject params) {


            Logger.json(params.toString());
            GtDialog dialog = new GtDialog(ctx, params);

            // 启用debug可以在webview上看到验证过程的一些数据
//        dialog.setDebug(true);

            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //TODO 取消验证
                    toastMsg("user close the geetest.");
                }
            });

            dialog.setGtListener(new GtDialog.GtListener() {

                @Override
                public void gtResult(boolean success, String result) {

                    if (success) {

                        Logger.json("gtResult="+result);
                   //    toastMsg("client captcha succeed:" + result);

                        SdkUtils.GtAppValidateTask gtAppValidateTask = new SdkUtils.GtAppValidateTask();
                        mGtAppValidateTask = gtAppValidateTask;
                        mGtAppValidateTask.execute(result);

                    } else {
                        //TODO 验证失败
                        toastMsg("client captcha failed:" + result);
                    }
                }

                @Override
                public void gtCallClose() {

                    toastMsg("close geetest windows");
                }

                @Override
                public void gtCallReady(Boolean status) {

                    progressDialog.dismiss();

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
                    progressDialog.dismiss();
                    toastMsg("Fatal Error Did Occur.");
                }

            });

        }
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
            //    validateParams.put("noncestr", noncestr);

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


    public  interface SdkLister  {
          void sdkSuccess(String r);
       //   void sdkFali(String r);
      }

    public void setSdkLister(SdkLister l){
        this.l =  l ;
    }
}
