package com.enduo.ndonline.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/8.
 */

public class DateUtils {

    public static String getDay(int remainTime) {
        int day = (int) remainTime / (3600 * 24);
        int Hour = (int) remainTime % (3600 * 24) / 3600;
        int Minute = (int) remainTime % (3600 * 24) % 3600 / 60;
        int Second = (int) remainTime % (3600 * 24) % 3600 % 60;


        return day + "天" + Hour + "小时" + Minute + "分" + Second + "秒";
    }


}
