package com.enduo.ndonline.net;

import android.content.Context;
import android.content.SharedPreferences;

import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.log.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/1/2.
 */

public class AddCookiesInterceptor implements Interceptor {
    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();


      builder.addHeader("content-type","application/json;charset=UTF-8");

     //   Logger.e("------------------------------------111");
        //最近在学习RxJava,这里用了RxJava的相关API大家可以忽略,用自己逻辑实现即可
        if ((Boolean) SharedPreferencesUtils.getParam(MyApplication.context,"islogin",false)){

       //     Logger.e("------------------------------------");
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
            builder.addHeader("Cookie", sb.toString());

            Logger.d("Cookie---"+sb.toString());

        }




        return chain.proceed(builder.build());
    }
}