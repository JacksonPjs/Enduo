package com.pvj.xlibrary.TVextiew;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Bundle;

import com.pvj.xlibrary.TVextiew.View.AutoHorizontalScrollTextView;
import com.pvj.xlibrary.TVextiew.View.AutoVerticalScrollTextView;


/**
 * <?xml version="1.0" encoding="UTF-8"?>
 * -<LinearLayout android:orientation="vertical" android:layout_height="match_parent" android:layout_width="match_parent" xmlns:android="http://schemas.android.com/apk/res/android">
 * <!--左右滚动的textview-->
 * <www.autotextview.com.autotextview.View.AutoHorizontalScrollTextView android:layout_height="wrap_content" android:layout_width="match_parent" android:padding="5dp" android:singleLine="true" android:id="@+id/textview" android:textColor="#ffffff" android:background="#000000" android:textSize="20sp" android:layout_marginTop="20dp"/>
 * <!--上下滚动的TextView-->
 * <www.autotextview.com.autotextview.View.AutoVerticalScrollTextView android:layout_height="wrap_content" android:layout_width="match_parent" android:padding="5dp" android:id="@+id/textview_auto_roll" android:background="#000000" android:layout_marginTop="20dp" android:layout_marginBottom="20dp"/> </LinearLayout>
 */
public class MainActivity extends Activity {

    private int number = 0;
    private boolean isRunning = true;

    private String[] strings = {"我的剑，就是你的剑!", "俺也是从石头里蹦出来得!", "我用双手成就你的梦想!", "人在塔在!", "犯我德邦者，虽远必诛!", "我会让你看看什么叫残忍!", "我的大刀早已饥渴难耐了!"};
    private String string = "我的剑，就是你的剑!   俺也是从石头里蹦出来得!    我用双手成就你的梦想!    人在塔在!    犯我德邦者，虽远必诛!    我会让你看看什么叫残忍!    我的大刀早已饥渴难耐了!";

    private AutoVerticalScrollTextView verticalScrollTV;
    private AutoHorizontalScrollTextView horizontalScrollTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

     //   horizontalScrollTV = (AutoHorizontalScrollTextView) findViewById(R.id.textview);
        horizontalScrollTV.setText(string);

    //    verticalScrollTV = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll);
        verticalScrollTV.setText(strings[0]);

        new Thread() {
            @Override
            public void run() {
                while (isRunning) {
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                verticalScrollTV.next();
                number++;
                verticalScrollTV.setText(strings[number % strings.length]);
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
