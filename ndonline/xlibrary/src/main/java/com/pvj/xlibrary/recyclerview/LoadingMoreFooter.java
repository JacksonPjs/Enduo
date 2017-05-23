package com.pvj.xlibrary.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pvj.xlibrary.R;


public class LoadingMoreFooter extends LinearLayout {


    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;
    private TextView mText;
    ProgressBar pb ;

	public LoadingMoreFooter(Context context) {
		super(context);
		initView();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public LoadingMoreFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

    public void initView(){
        setGravity(Gravity.CENTER);

        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setPadding(0,dip2px(getContext(),10),0,dip2px(getContext(),10));

         pb = new ProgressBar(getContext());
        //android:indeterminateDrawable
        pb.setLayoutParams(new LayoutParams(dip2px(getContext(),20),dip2px(getContext(),20)));
        Drawable d = this.getResources().getDrawable(R.drawable.loading_animation);
        pb.setIndeterminateDrawable(d);
       // android:indeterminateBehavior


        addView(pb);


        mText = new TextView(getContext());
        mText.setText("正在加载...");

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

         layoutParams.setMargins(dip2px(getContext(),10),0,0,0);
        mText.setLayoutParams(layoutParams);
        addView(mText);
    }


    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     *
     *            （DisplayMetrics类中属性density）
     * @return
     */
    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    public void setTextEnd(){
        mText.setText("---没有更多数据了---");
        pb.setVisibility(View.GONE);
    }

    public void setTextStart(){
        mText.setText("正在加载...");
        pb.setVisibility(View.VISIBLE);
    }
}
