package com.enduo.ndonline.utils;

import com.enduo.ndonline.bean.Bank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 * value="ICBC" title="工商银行">
 value="ABC" title="中国农业银行">
 value="BOC" title="中国银行">


 value="CCB" title="中国建设银行">


 value="CMBCHINA" title="招商银行">


 value="SPDB" title="上海浦东发展银行">

 value="CEB" title="中国光大银行">
 value="BOCO" title="交通银行">

 value="PINGANBANK" title="平安银行">

 value="HXB" title="华夏银行">

 value="CIB" title="兴业银行">
 value="ECITIC" title="中信银行">

 value="POST" title="中国邮政储蓄银行">

 value="CMBC" title="中国民生银行">

 value="GDB" title="广发银行">

 value="BCCB" title="北京银行">

 value="SHB" title="上海银行">
 */

public class BankList {

    public static List<Bank> getBankList(){
        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank("0102","中国工商银行"));
        banks.add(new Bank("0103","中国农业银行"));
        banks.add(new Bank("0104","中国银行"));
        banks.add(new Bank("0105","中国建设银行"));
        banks.add(new Bank("4031000","北京银行"));
        banks.add(new Bank("0301","交通银行"));
        banks.add(new Bank("0302","中信银行"));
        banks.add(new Bank("0303","中国光大银行"));
        banks.add(new Bank("0304","华夏银行"));
        banks.add(new Bank("0305","中国民生银行"));
        banks.add(new Bank("0306","广东发展银行"));
        banks.add(new Bank("4012900","上海银行"));
        banks.add(new Bank("0308","招商银行"));
        banks.add(new Bank("0309","兴业银行"));
        banks.add(new Bank("0310","上海浦东发展银行"));
        banks.add(new Bank("0403","中国邮政储蓄银行"));
        return banks;
    }






}
