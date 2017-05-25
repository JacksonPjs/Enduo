package com.enduo.ndonline.utils;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.enduo.ndonline.bean.CenterIndexBean;
import com.enduo.ndonline.bean.LoginBean;
import com.pvj.xlibrary.log.Logger;

/**
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float,
 * Long类型的参数 同样调用getParam就能获取到保存在手机里面的数据
 * 
 * 保存数据 SharedPreferencesUtils.setParam(this, "String", "xiaanming");
 * SharedPreferencesUtils.setParam(this, "int", 10);
 * SharedPreferencesUtils.setParam(this, "boolean", true);
 * SharedPreferencesUtils.setParam(this, "long", 100L);
 * SharedPreferencesUtils.setParam(this, "float", 1.1f);
 * 
 * 
 * 获取数据 SharedPreferencesUtils.getParam(TimerActivity.this, "String", "");
 * SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
 * SharedPreferencesUtils.getParam(TimerActivity.this, "boolean", false);
 * SharedPreferencesUtils.getParam(TimerActivity.this, "long", 0L);
 * SharedPreferencesUtils.getParam(TimerActivity.this, "float", 0.0f);
 * 
 * @author xiaanming
 * 
 */
public class SharedPreferencesUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "enduo";

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void setParam(Context context, String key, Object object) {

		String type = object.getClass().getSimpleName();
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();

		if ("String".equals(type)) {
			editor.putString(key, (String) object);
		} else if ("Integer".equals(type)) {
			editor.putInt(key, (Integer) object);
		} else if ("Boolean".equals(type)) {
			editor.putBoolean(key, (Boolean) object);
		} else if ("Float".equals(type)) {
			editor.putFloat(key, (Float) object);
		} else if ("Long".equals(type)) {
			editor.putLong(key, (Long) object);
		}

		editor.commit();
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context, String key,
			Object defaultObject) {
		String type = defaultObject.getClass().getSimpleName();
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);

		if ("String".equals(type)) {
			return sp.getString(key, (String) defaultObject);
		} else if ("Integer".equals(type)) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if ("Boolean".equals(type)) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if ("Float".equals(type)) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if ("Long".equals(type)) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return null;
	}

	/**
	 * 清除数据
	 * 
	 * @param context
	 * @return
	 */
	public static void clearAll(Context context) {

		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();// 获取编辑器
		// 登录成功后先清除原来的数据
		editor.clear();
		editor.commit();

	}
	
	/**
	 * 保存数据
	 * 
	 * @param context
	 * @return
	 */
	public static void savaUser(Context context,LoginBean bean,String password) {
		
		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();// 获取编辑器
		

	//	editor.putString("account_id", accountBean.getId()+"");
//		editor.putString("money", accountBean.getMoney()+"");
//		editor.putString("integral", accountBean.getIntegral()+"");
//		editor.putString("customer_id", customerBean.getId()+"");
//		editor.putString("name", customerBean.getName());
//
//		editor.putString("face", customerBean.getFace().replaceAll(" ", ""));
		editor.putBoolean("islogin", true);//判断是否登录
//		editor.putBoolean("existsPaypwd", customerBean.isExistsPaypwd());//判断是否登录
       	editor.putString("phone", bean.getCellPhone());// 保存手机号
		editor.putString("name", bean.getUserName());// 用户名字
		editor.putString("password", password);// 用户名字
		editor.putString("realname", bean.getRealName());// 实名认证名字
		editor.putString("bankcardno", bean.getBankCardNo());// 卡号
		editor.putString("password", password);// 用户名字
		editor.putString("images", bean.getImages());// head
	//	editor.putLong("systime", System.currentTimeMillis());// head
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setGroupingUsed(false);

		Logger.d(nf.format(bean.getTotal1())+"total1");
		editor.putString("total1",  nf.format(bean.getTotal1())+"");//

		editor.putString("usableAmount", nf.format(bean.getUsableAmount())+"");//

		Logger.d(nf.format(bean.getUsableAmount())+"usableAmount");
		editor.putString("token", bean.getToken());//

		editor.putBoolean("tPerson", bean.isTPerson());//
		editor.putBoolean("payPwd", bean.isPayPwd());//
		editor.putBoolean("email", bean.isEmail());//
		editor.putBoolean("tBankCardlist", bean.isTBankCardlist());//

		editor.commit();
		
	}

	/**
	 * 保存数据
	 *
	 * @param context
	 * @return
	 */
	public static void savaUser2(Context context, CenterIndexBean bean) {

		SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();// 获取编辑器


		editor.putBoolean("islogin", true);//判断是否登录
		editor.putString("name", bean.getUserName());// 用户名字
		editor.putString("images", bean.getImages());// head
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		Logger.d(nf.format(bean.getTotal1())+"total1");
		editor.putString("total1",  nf.format(bean.getTotal1())+"");//
		editor.putString("total2",  nf.format(bean.getTotal2())+"");//
		editor.putString("usableAmount", nf.format(bean.getUsableAmount())+"");//
		editor.putBoolean("tPerson", bean.isTPerson());//
		editor.putBoolean("payPwd", bean.isPayPwd());//
		editor.putBoolean("email", bean.isEmail());//
		editor.putBoolean("tBankCardlist", bean.isTBankCardlist());//

		editor.commit();

	}


	public static String getUserName(Context context) {

		return (String) SharedPreferencesUtils.getParam(context, "phone", "") ;

	}

	public static String getPassword(Context context) {
		return (String) SharedPreferencesUtils.getParam(context, "password", "") ;


	}

	public static boolean getIsRealName(Context context) {
		return (Boolean) SharedPreferencesUtils.getParam(context, "tPerson", false) ;


	}

	public static boolean getIsBank(Context context) {
		return (Boolean) SharedPreferencesUtils.getParam(context, "tBankCardlist", false) ;
	}

	public static void setIsBank(Context context,Boolean b) {
		SharedPreferencesUtils.setParam(context, "tBankCardlist", b) ;
	}

	public static void setIsLogin(Context context,boolean isLogin) {
		 SharedPreferencesUtils.setParam(context, "islogin", false) ;
	}

}
