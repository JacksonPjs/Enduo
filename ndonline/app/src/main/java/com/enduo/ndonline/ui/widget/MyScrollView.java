package com.enduo.ndonline.ui.widget;

import android.content.Context;
import android.support.compat.BuildConfig;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.enduo.ndonline.APP.Contacts;

/**
 * Created by baoyunlong on 16/6/8.
 */
public class MyScrollView extends ScrollView {
    private static String TAG = MyScrollView.class.getName();
    private GestureDetector mGestureDetector;


    public void setScrollListener(ScrollListener scrollListener) {
        this.mScrollListener = scrollListener;
    }

    private ScrollListener mScrollListener;

    public MyScrollView(Context context) {
        super(context);
        init();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    private void init() {
        mGestureDetector = new GestureDetector(getContext(),
                new YScrollDetector());
        setFadingEdgeLength(0);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev)
                && mGestureDetector.onTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float xDistance = 0, yDistance = 0, xLast = 0, yLast = 0;
        boolean result = super.onTouchEvent(ev);
        requestDisallowInterceptTouchEvent(false);
        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                if (mScrollListener != null) {
                    int contentHeight = getChildAt(0).getHeight();
                    int scrollHeight = getHeight();
                    Log.d(TAG, "scrollY:" + getScrollY() + "contentHeight:" + contentHeight + " scrollHeight" + scrollHeight + "object:" + this);

                    int scrollY = getScrollY();
                    mScrollListener.onScroll(scrollY);

                    if (scrollY + scrollHeight >= contentHeight || contentHeight <= scrollHeight) {
                        mScrollListener.onScrollToBottom();
                    } else {
                        mScrollListener.notBottom();
                    }

                    if (scrollY == 0) {
                        mScrollListener.onScrollToTop();
                    }

                }
/*
* 左右滑动冲突
* */
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    result=false;
                    return false;
                }
                break;

        }


        return result;
    }

    private class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {

            if (Math.abs(distanceY) >= Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
    public interface ScrollListener {
        void onScrollToBottom();

        void onScrollToTop();

        void onScroll(int scrollY);

        void notBottom();
    }
}
