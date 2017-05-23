package com.enduo.ndonline.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.enduo.ndonline.R;


/**
 * Created by Administrator on 2017/3/15.
 */

public class DiscountView extends View {

    Paint mPaint; //画笔,包含了画几何图形、文本等的样式和颜色信息
    int phase;//phase参数指定了绘制的虚线相对了起始地址（Path起点）的取余偏移（对路径总长度）。

    // 可是通过不断地递增/递减来改变phase的值，达到一个路径自身不断循环移动的动画效果。
    public DiscountView(Context context) {
        super(context);
        init(context, null);

    }

    public DiscountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        //TypedArray是一个用来存放由context.obtainStyledAttributes获得的属性的数组
        //在使用完成后，一定要调用recycle方法
        //属性的名称是styleable中的名称+“_”+属性名称
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
//        int textColor = array.getColor(R.styleable.MyView_textColor, 0XFF00FF00); //提供默认值，放置未指定
//        float textSize = array.getDimension(R.styleable.MyView_textSize, 36);
//        mPaint.setColor(textColor);
//        mPaint.setTextSize(textSize);
//
//        array.recycle(); //一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(false);                       //设置画笔为无锯齿
        mPaint.setColor(getResources().getColor(R.color.bg_huise));                    //设置画笔颜色
        canvas.drawColor(Color.WHITE);                  //白色背景
        mPaint.setStrokeWidth((float) 3.0);              //线宽
        mPaint.setStyle(Paint.Style.STROKE);


//        float x = (getWidth() - getHeight() / 2) / 2;//1/4 x
//        float y = getHeight() / 4;
//
        float x=getWidth()/2;
        float y = getHeight();


//虚线
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.LTGRAY);
        Path path = new Path();
        path.moveTo(x, y/8);
        path.lineTo(x, getHeight()-y/8);
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, phase);
        paint.setPathEffect(effects);
        canvas.drawPath(path, paint);

//        phase++;
//        // 重绘，产生动画效果
//        invalidate();
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, 0, y/8, mPaint);
        canvas.drawCircle(x, getHeight(), y/8, mPaint);

//        RectF oval = new RectF(x, y,
//                getWidth() - x, getHeight() - y);
//        canvas.drawArc(oval, 0, 200, false, mPaint);           //绘制弧形


//        RectF oval5 = new RectF(10, 30, 180, 100);//矩形
//        canvas.drawRect(oval5, mPaint);



//
//        //圆角矩形（左右弧形）
//        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果
//        RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形(left,top,right,bottom)
//        canvas.drawRoundRect(oval3, 20, 20, mPaint);//第二个参数是x半径，第三个参数是y半径



//画圆角矩形
//        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果
////        canvas.drawText("画圆角矩形:", 10, 260, mPaint);
//        RectF oval4 = new RectF(0, 100, 100, 300);// 设置个新的长方形
//        canvas.drawRoundRect(oval4, 5, 20, mPaint);//第二个参数是x半径，第三个参数是y半径



//        canvas.drawCircle(100, 100, 20, mPaint);         //绘制圆形
//        canvas.drawCircle(150, 150, 30, mPaint);         //绘制圆形
//        canvas.drawCircle(200, 200, 40, mPaint);         //绘制圆形
//        canvas.drawCircle(250, 250, 50, mPaint);         //绘制圆形
//        canvas.drawCircle(300, 300, 60, mPaint);         //绘制圆形
//        canvas.drawCircle(350, 350, 70, mPaint);         //绘制圆形
//        //Canvas中含有很多画图的接口，利用这些接口，我们可以画出我们想要的图形
//        //mPaint = new Paint();
//        //mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.FILL); //设置填充
////        canvas.drawCircle(50,50,50,mPaint);
//        canvas.drawRect(10, 10, 100, 100, mPaint); //绘制矩形
//
//        mPaint.setColor(Color.BLUE);
//        canvas.drawText("我是被画出来的", 10, 120, mPaint);
    }
}
