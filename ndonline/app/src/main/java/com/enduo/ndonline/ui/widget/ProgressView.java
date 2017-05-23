package com.enduo.ndonline.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.enduo.ndonline.R;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/4/28.
 */

public class ProgressView extends View {

    private static final String TAG = "ProgressView";

    private int mMaxProgress = 100;

    private double mProgress = 0;
    //影响圆环大小
    private final int mCircleLineStrokeWidth = 25;

    private final int mTxtStrokeWidth = 0;

    // 画圆所在的距形区域
    private final RectF mRectF;

    private final Paint mPaint;

    private final Context mContext;

    private String mTxtHint1;

    private String mTxtHint2;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mRectF = new RectF();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if (width != height) {
            int min = Math.min(width, height);
            width = min;
            height = min;
        }

        // 设置画笔相关属性
        mPaint.setAntiAlias(true);

        mPaint.setColor(getResources().getColor(R.color.bg_2));
        canvas.drawColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(mCircleLineStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        // 位置
        mRectF.left = mCircleLineStrokeWidth ; // 左上角x
        mRectF.top = mCircleLineStrokeWidth ; // 左上角y
        mRectF.right = width - mCircleLineStrokeWidth ; // 左下角x
        mRectF.bottom = height - mCircleLineStrokeWidth ; // 右下角y
        if (mProgress>mMaxProgress){
            mPaint.setColor(getResources().getColor(R.color.text_huise));

        }
        // 绘制圆圈，进度条背景
        canvas.drawArc(mRectF, -90, 360, false, mPaint);
//        mPaint.setColor(Color.rgb(0xf8, 0x60, 0x30));
        mPaint.setColor(getResources().getColor(R.color.text_org));
        if (mProgress>mMaxProgress){

        }else{
            double max=mProgress / mMaxProgress;
            canvas.drawArc(mRectF, -90, (float) (max * 360), false, mPaint);

        }

        // 绘制进度文案显示
        mPaint.setStrokeWidth(mTxtStrokeWidth);


    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setProgress(double progress) {
        this.mProgress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(int progress) {
        this.mProgress = progress;
        this.postInvalidate();
    }

    public String getmTxtHint1() {
        return mTxtHint1;
    }

    public void setmTxtHint1(String mTxtHint1) {
        this.mTxtHint1 = mTxtHint1;
    }

    public String getmTxtHint2() {
        return mTxtHint2;
    }

    public void setmTxtHint2(String mTxtHint2) {
        this.mTxtHint2 = mTxtHint2;
    }
}