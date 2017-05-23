package com.pvj.xlibrary.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * 字符串的处理类
 */
public class StringUtil {
    /**
     * 判断是否为null或空值
     *
     * @param str String
     * @return true or false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断str1和str2是否相同
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equals(String str1, String str2) {
        return str1 == str2 || str1 != null && str1.equals(str2);
    }

    /**
     * 判断str1和str2是否相同(不区分大小写)
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 != null && str1.equalsIgnoreCase(str2);
    }

    /**
     * 判断字符串str1是否包含字符串str2
     *
     * @param str1 源字符串
     * @param str2 指定字符串
     * @return true源字符串包含指定字符串，false源字符串不包含指定字符串
     */
    public static boolean contains(String str1, String str2) {
        return str1 != null && str1.contains(str2);
    }

    /**
     * 判断字符串是否为空，为空则返回一个空值，不为空则返回原字符串
     *
     * @param str 待判断字符串
     * @return 判断后的字符串
     */
    public static String getString(String str) {
        return str == null ? "" : str;
    }

    /**
     * 正则验证
     *
     * @param qString 待验证字符串
     * @param regx    正则，为空则使用默认正则
     * @return false表示验证通过 返回true表示不通过
     */
    public static boolean hasCrossScriptRisk(String qString, String regx) {
        if ("".equals(regx)) {
            regx = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§";
        }
        if (qString != null) {
            qString = qString.trim();
            Pattern p = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(qString);
            boolean bl = m.find();
            return bl;
        }
        return false;
    }

    /**
     * 字符数组转字符串
     *
     * @param join
     * @param strAry
     * @return
     */
    public static String join(String join, String[] strAry) {
        if (strAry == null)
            return "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strAry.length; i++) {
            if (StringUtil.isNullOrEmpty(strAry[i]))
                continue;
            if (i == (strAry.length - 1)) {
                sb.append(strAry[i]);
            } else {
                sb.append(strAry[i]).append(join);
            }
        }

        return new String(sb);
    }

    /**
     * 将文本复制到剪贴板
     *
     * @param text
     */
    public static void copyTextToClipBoard(String text, Context context) {
        try {
            ClipboardManager clipboard = (ClipboardManager) context
                    .getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(ClipData.newPlainText(null, text));
        } catch (Exception e) {
            Log.i("ctrl+c", "将文本复制到剪贴板失败！");
            e.printStackTrace();
        }
    }
    
    /**
     * 可以是纯数字，也可以是纯字母，也可以是数字+字母,6-16 位
     * @param str
     * @return
     */
    public static boolean isPassWord(String str) {
    	if (str==null) {
			return false ;
		}
    	if(Pattern.matches("^[0-9a-zA-Z]{6,16}", str)){
    	return true ;
    	}
    	return false;
    	}
   
    /**
     * 验证昵称
     * 
     * @param str
     * @return
     */
    public static boolean isNimane(String str) {
    	if (str==null) {
    		return false ;
    	}

    	if (str.contains(" ")) {
    		return false ;
		}
    	if ( str.length()>12) {
    		return false ;
		}
    	
    	
    	return true;
    }
    
    /**
     * 是否是6位
     * @param str
     * @return
     */
    public static boolean is6Word(String str) {
    	if (str==null) {
    		return false ;
    	}
    	if(Pattern.matches("\\d{6}", str)){
    		return true ;
    	}
    	return false;
    }
    
    /** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) {
        if (isNullOrEmpty(str)){
         return false ;
        }

        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][2,3,4,5,6,7,8,9][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
}

