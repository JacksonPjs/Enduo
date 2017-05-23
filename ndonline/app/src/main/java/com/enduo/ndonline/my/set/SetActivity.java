package com.enduo.ndonline.my.set;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.enduo.ndonline.BaseActivity;
import com.enduo.ndonline.R;
import com.enduo.ndonline.appupdate.AppUpdataBean;
import com.enduo.ndonline.appupdate.AppUpdataUtils;
import com.enduo.ndonline.appupdate.AppUtils;
import com.enduo.ndonline.my.security.SecurityActivity;
import com.enduo.ndonline.net.NetWorks;
import com.enduo.ndonline.utils.DialogUtils;
import com.enduo.ndonline.utils.FileSizeUtil;
import com.enduo.ndonline.utils.FileUtil;
import com.enduo.ndonline.utils.SharedPreferencesUtils;
import com.pvj.xlibrary.log.Logger;
import com.pvj.xlibrary.utils.T;

import java.io.File;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/12/31.
 * 设置
 */

public class SetActivity extends BaseActivity {

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.exit)
    Button exit;
    @Bind(R.id.clearmmmm)
    View clearmmmm;
    @Bind(R.id.updata)
    TextView updata;
   @Bind(R.id.number_size)
    TextView number_size;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        title.setText("设置");

        long cd =getFolderSize(getExternalCacheDir()) ;
       double fileSizeLong =   FileSizeUtil.FormetFileSize(cd,3);
        number_size.setText(fileSizeLong+"M");

    }



    @OnClick({R.id.clearmmmm, R.id.updata,R.id.exit,R.id.safe})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clearmmmm:
                cleanInternalCache(SetActivity.this);

                long cd =getFolderSize(getExternalCacheDir()) ;
                double fileSizeLong =   FileSizeUtil.FormetFileSize(cd,3);
                number_size.setText(fileSizeLong+"M");
                break;
            case R.id.updata:
                NetWorks.appCurrentVersion(new Subscriber<AppUpdataBean>() {
                    @Override
                    public void onStart() {
                        if(dialog==null){
                            dialog = DialogUtils.createProgressDialog(SetActivity.this,"请稍后...");
                        }else{
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                        Logger.e(e.toString());
                        T.ShowToastForShort(SetActivity.this,"网络异常...");
                    }

                    @Override
                    public void onNext(AppUpdataBean s) {
                      if (s.getAppVersion()> AppUtils.getVersionCode(SetActivity.this)){
                        if(s.getIsMustNewVersion()==1){
                             AppUpdataUtils.showUpdateDialog2(SetActivity.this,s);
                         }else {
                            AppUpdataUtils.showUpdateDialog(SetActivity.this,s);
                          }

                     }else{
                          T.ShowToastForShort(SetActivity.this,"已经是最新版本了...");
                      }
                    }
                });

                break;
            case R.id.exit:
                SharedPreferencesUtils.clearAll(this);
                finish();
                break;
            case R.id.safe:
                //安全中心
               Intent intent = new Intent(this, SecurityActivity.class);
                startActivity(intent);
                break;
        }
    }


    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public  long getFolderSize(File file)  {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            size = 0 ;
        }
        return size;
    }

    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * *
     *
     * @param directory
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache) * *
     *
     * @param context
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }
}
