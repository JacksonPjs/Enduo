package com.enduo.ndonline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.pvj.xlibrary.loadinglayout.LoadingLayout;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by wanglei on 2016/12/11.
 */
@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
public class WebActivityJS extends BaseActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.contentLayout)
    LoadingLayout contentLayout;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
     //   title.setText();

        MyApplication.instance.addActivity(this);

        url = getIntent().getStringExtra("url");
        String title1 = getIntent().getStringExtra("title");
        if (title!=null){
            title.setText(title1);
        }

        initWebView();
    }

    private void initWebView() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    if (contentLayout != null)
                        contentLayout.setStatus(LoadingLayout.Success);
                    if (webView != null)
                        url = webView.getUrl();
                } else {
                    if (contentLayout != null)
                        contentLayout.setStatus(LoadingLayout.Loading);

                }
            }
        });


        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.addJavascriptInterface(new JavaScriptObject(this), "enduoApp");

        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.removeAllViews();
            webView.destroy();
        }
    }

    public class JavaScriptObject {
        Context mContxt;
        //sdk17版本以上加上注解
        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }
        @JavascriptInterface
        public void goRecharge() {
         //   T.ShowToastForShort(mContxt,"goReCharge");
            finish();
            Logger.d("goReCharge");

        }
        @JavascriptInterface
        public void goUserIndex() {
       //   T.ShowToastForShort(mContxt,"goUserIndex");
MyApplication.instance.Allfinlish();
          Logger.d("goUserIndex");
        }
    }
}
