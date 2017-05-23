package com.enduo.ndonline.appupdate;

/**
 * app 升级bean
 * Created by pvj on 2016/6/3.
 */
public class AppUpdataBean {
    /**
     * appVersion : 1
     * downUrl : http://resources.xinxinmoxing.com/apk/app-debug.apk
     * isMustNewVersion : 0
     * newVersionIntroduce : 让你更新就更新，这么多屁话！
     */

    private int appVersion;
    private String downUrl;
    private int isMustNewVersion;
    private String newVersionIntroduce;

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public int getIsMustNewVersion() {
        return isMustNewVersion;
    }

    public void setIsMustNewVersion(int isMustNewVersion) {
        this.isMustNewVersion = isMustNewVersion;
    }

    public String getNewVersionIntroduce() {
        return newVersionIntroduce;
    }

    public void setNewVersionIntroduce(String newVersionIntroduce) {
        this.newVersionIntroduce = newVersionIntroduce;
    }
}
