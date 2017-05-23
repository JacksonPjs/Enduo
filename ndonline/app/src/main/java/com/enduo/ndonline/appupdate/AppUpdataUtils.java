package com.enduo.ndonline.appupdate;

import java.io.File;



import com.pvj.xlibrary.utils.T;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.request.RequestCall;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class AppUpdataUtils {

	/**
	 * 显示升级对话框
	 */
	public static void showUpdateDialog(final Context context,
			final AppUpdataBean bean) {
	//	final AppUpdataBean.UpdateInfoBean u = bean.getUpdateInfo();

		MyDialog.Builder builder = new MyDialog.Builder(context);
		builder.setTitle("版本描述");
		builder.setMessage(bean.getNewVersionIntroduce().replaceAll(";", "\r\n"));// 设置版本描述
		builder.setNegativeButton("忽略",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
				// 06-03 18:44:21.528: I/String(1977):
				// app下载地址：http://www.myzhongjin.com/UploadFile/app/android/zhongjinonline.apk
			String	url = bean.getDownUrl();
			//	String url = "http://www.myzhongjin.com/UploadFile/app/android/zhongjinonline.apk";

				final RequestCall call = OkHttpUtils.get().url(url).build();
				final UpDataProgressDialog.Builder builder1 = new UpDataProgressDialog.Builder(
						context);

				builder1.create().show();
				builder1.setCancleProessListiser(new UpDataProgressDialog.CancleProess() {

					public void cancle(Dialog dialog) {
						call.cancel();
						dialog.dismiss();

					}
				});

				call.execute(new FileCallBack(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/enduo", "enduo.apk")//
				{

					@Override
					public void inProgress(float progress) {
						builder1.setProgressBar((int) (100 * progress));
						builder1.setProgressText((int) (100 * progress));
					}

					@Override
					public void onError(Request request, Exception e) {
						T.ShowToastForLong(context, "下载失败");
					}

					@Override
					public void onResponse(File file) {

						Intent intent = new Intent();
						intent.setAction("android.intent.action.VIEW");
						intent.addCategory("android.intent.category.DEFAULT");
						intent.setDataAndType(Uri.fromFile(file),
								"application/vnd.android.package-archive");
						context.startActivity(intent);
					}




				});
			}
		});
		builder.create().show();

	}

	
	/**
	 * 显示升级对话框 // 强制升级
	 * @param
	 */
	public static void showUpdateDialog2(final Context context,
			final AppUpdataBean bean) {
	//、、	final AppUpdataBean u = bean;

		MyDialog2.Builder builder = new MyDialog2.Builder(context);
		
		builder.setTitle( "版本描述");
		builder.setMessage(bean.getNewVersionIntroduce().replaceAll(";", "\r\n"));// 设置版本描述
		builder.setNegativeButton("忽略",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.setPositiveButton("升级", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
				// 06-03 18:44:21.528: I/String(1977):
				// app下载地址：http://www.myzhongjin.com/UploadFile/app/android/zhongjinonline.apk
				String	url = bean.getDownUrl();
				//String url = "http://www.myzhongjin.com/UploadFile/app/android/zhongjinonline.apk";

				final RequestCall call = OkHttpUtils.get().url(url).build();
				final UpDataProgressDialog.Builder builder1 = new UpDataProgressDialog.Builder(
						context);

				builder1.create().show();
				builder1.setCancleProessListiser(new UpDataProgressDialog.CancleProess() {

					public void cancle(Dialog dialog) {
						call.cancel();
						dialog.dismiss();

					}
				});

				call.execute(new FileCallBack(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/enduo", "enduo.apk")//
				{

					@Override
					public void inProgress(float progress) {
						builder1.setProgressBar((int) (100 * progress));
						builder1.setProgressText((int) (100 * progress));
					}

					@Override
					public void onError(Request request, Exception e) {
						T.ShowToastForLong(context, "下载失败");
					}

					@Override
					public void onResponse(File file) {

						Intent intent = new Intent();
						intent.setAction("android.intent.action.VIEW");
						intent.addCategory("android.intent.category.DEFAULT");
						intent.setDataAndType(Uri.fromFile(file),
								"application/vnd.android.package-archive");
						context.startActivity(intent);
					}

				});
			}
		});
		builder.create().show();

	}
}
