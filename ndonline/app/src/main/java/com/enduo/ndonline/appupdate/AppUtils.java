package com.enduo.ndonline.appupdate;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2017/1/9.
 */

public class AppUtils {

    /**
     * 获取app版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        try {
            if (context==null) {
                return 0;
            }

            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
