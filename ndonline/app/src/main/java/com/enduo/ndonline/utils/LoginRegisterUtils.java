package com.enduo.ndonline.utils;

import android.widget.EditText;

import com.pvj.xlibrary.utils.StringUtil;

import java.util.regex.Pattern;

/**
 *     注册和登陆 验证 输入判断
 * Created by Administrator on 2016/12/30.
 */

public class LoginRegisterUtils {

    public static  boolean isPhone(EditText editText){
        String p = editText.getText().toString();
        return StringUtil.isMobile(p) ;
    }

    public static boolean isNullOrEmpty(EditText editText){
        String str = editText.getText().toString();

        return str == null || str.trim().length() == 0;
    }

    public static boolean isPassWord(EditText editText){
        String str = editText.getText().toString();

      return   isPassWord(str);
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
     * 判断str1和str2是否相同
     *
     * @param str11 str1
     * @param str21 str2
     * @return true or false
     */
    public static boolean equals(EditText str11, EditText str21) {
        String str1 = str11.getText().toString();
        String str2 = str21.getText().toString();
        return str1 == str2 || str1 != null && str1.equals(str2);
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
}
