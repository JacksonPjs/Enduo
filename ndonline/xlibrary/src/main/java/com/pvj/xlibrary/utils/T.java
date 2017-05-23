package com.pvj.xlibrary.utils;

import android.content.Context;

/**
 * toast 统一管理类
 *
 * @author ouyangbin
 */
public class T {
    private static Object t;

    private T() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 短时间显示toast
     *
     * @param c
     * @param s
     */
    public static void ShowToastForShort(Context c, String s) {
//        if (t == null) {
//            t = Toast.makeText(c, s, Toast.LENGTH_SHORT);
//        } else {
//
//         //
//         //   t.setDuration(Toast.LENGTH_SHORT);
//            if(t instanceof EToast){
//                Toast.makeText(c,s,Toast.LENGTH_SHORT);
//            }else if(t instanceof android.widget.Toast){
//                ((Toast) t).setText(s);
//            }
//
//        }
//
//        ((Toast) t).show();

        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示toast
     *
     * @param c
     * @param s
     */
    public static void ShowToastForLong(Context c, String s) {
//        if (t == null) {
//            t = Toast.makeText(c, s, Toast.LENGTH_LONG);
//        } else {
//            if(t instanceof EToast){
//                Toast.makeText(c,s,Toast.LENGTH_LONG);
//            }else if(t instanceof android.widget.Toast){
//                ((Toast) t).setText(s);
//            }
//
//          //  t.setDuration(Toast.LENGTH_LONG);
//        }
//        ((Toast) t).show();

        ShowToastForShort(c, s);
    }

    /**
     * 自定义时间显示toast
     *
     * @param c
     * @param s
     * @param time
     */
    public static void ShowToast(Context c, String s, int time) {
        if (t == null) {
            t = Toast.makeText(c, s, time);
        } else {
            if(t instanceof EToast){
                Toast.makeText(c,s,Toast.LENGTH_LONG);
            }else if(t instanceof android.widget.Toast){
                ((Toast) t).setText(s);
            }
          //  t.setDuration(time);
        }
        ((Toast) t).show();
    }
}
