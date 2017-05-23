package com.enduo.ndonline.net;

import android.app.Application;
import android.util.Log;

import com.enduo.ndonline.MyApplication;
import com.enduo.ndonline.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.*;

/**
 * Created by Administrator on 2016/12/8.
 */

public class OkHttpUtils {
    private static OkHttpClient okHttpClient = null ;
    private static final String TAG = "OkHttpUtils";

//    public static OkHttpClient getOkHttpClient(){
//        if (okHttpClient==null){
//            synchronized (OkHttpClient.class){
//                if (okHttpClient == null){
//                   okHttpClient = new OkHttpClient();
//
//                }
//            }
//        }
//        return  okHttpClient ;
//    }

    public static OkHttpClient getOkHttpClient(){
        if (okHttpClient==null){
            synchronized (OkHttpClient.class){
                if (okHttpClient == null){
                 HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                  httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    //同样okhttp3后也使用build设计模式
                    okHttpClient = new OkHttpClient.Builder()
                            //设置一个自动管理cookies的管理器
                       //    .cookieJar(new CookiesManger(MyApplication.context))
                            //添加拦截器
                        //  .addInterceptor(httpLoggingInterceptor)
               //    .addInterceptor(new ReceivedCookiesInterceptor(MyApplication.context))
                   .addInterceptor(new AddCookiesInterceptor(MyApplication.context))
                            .addInterceptor(httpLoggingInterceptor)
                            //添加网络连接器
                         //   .addNetworkInterceptor(new NetworkBaseInterceptor())
                            //设置请求读写的超时时间
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();

                }
            }
        }
        return  okHttpClient ;
    }

    static class BaseInterceptor implements Interceptor {


        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();



            // 一些公共参数 在这里处理...



            return chain.proceed(request);
        }
    }
    static class NetworkBaseInterceptor implements Interceptor {
        private static String TAG = "HTTP-Interceptor";
        private Headers mHeaders;
        private Map<String,String> mMapHeaders;

        @Override
        public Response intercept(Chain chain) throws IOException {
            //封装headers
            Request request;

            Request.Builder builder = chain.request().newBuilder();
            if (mHeaders != null) {
                Set<String> set = mHeaders.names();
                for (String name : set) {
                    builder.addHeader(name, mHeaders.get(name));
                }
            } else if (mMapHeaders != null){
                for (String name: mMapHeaders.keySet()){
                    builder.addHeader(name,mMapHeaders.get(name));
                }
            }
            request = builder
        //            .addHeader("Content-Type", "application/json")
        //            .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .build();
//            L.i("==========================================================requestHeaders=========================================================");
//            L.d(TAG, "requestHeaders=====>" + request.headers());
//            L.i("==========================================================requestHeaders=========================================================");
//            L.i(" ");
//            L.d("===========================================================requestBodys==========================================================");
//            L.i(TAG, "request:" + request.toString());
//            L.d("===========================================================requestBodys==========================================================");
//            L.i(" ");
            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
    //        L.i("==========================================================responseHeaders========================================================");
      //      L.d(TAG, String.format(Locale.getDefault(), "Received response for %s in (%.1fms)%n%s",
      //              response.request().url(), (t2 - t1) / 1e6d, response.headers()));
     //       L.i("==========================================================responseHeaders========================================================");
     //       L.i(" ");
            Long content_length = response.body().contentLength();
            ResponseBody responseBody;
            if (content_length < 0) {
                responseBody = response.peekBody(1024 * 4);
            } else {
                responseBody = response.peekBody(content_length);
            }
       //     L.d("==========================================================responseBodys==========================================================");
     //       L.i(TAG, "response body:" + responseBody.string());
    //        L.d("==========================================================responseBodys==========================================================");
            return response;
        }

        public Headers getHeaders() {
            return mHeaders;
        }

        public void setHeaders(Headers headers) {
            this.mHeaders = headers;
        }

        public void setMapHeaders(Map<String, String> mapHeaders) {
            this.mMapHeaders = mapHeaders;
        }
    }

    /**
     * 打印 url日志
     *
     * @param request
     */
    private static String logUrl(Request request) {
        String requestUrl = request.url().toString(); // 获取请求url地址
        String methodStr = request.method(); // 获取请求方式

        //    String bodyStr = (body == null ? "" : body.toString());
        // 打印Request数据
        Log.i(TAG, "requestUrl---->" + requestUrl);
        Log.i(TAG, "requestMethod---->" + methodStr);

        return requestUrl ;
        // 在get请求时会报错
        //  Log.i("ysl", "requestBody=====>" + body.toString());
    }



}
