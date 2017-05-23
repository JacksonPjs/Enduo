package com.enduo.ndonline.utils;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/28.
 */
public class P2pUtils {
//    case -1:
//    incomeAmount = Arith.round(apr / 100 * period * investAmount, 2);
//    break;
//    case 0:
//    incomeAmount = Arith.round(apr / 12 / 100 * period * investAmount,
//            2);
//    break;
//    case 1:
//    incomeAmount = Arith.round(apr / 360 / 100 * period * investAmount,
//            2);

    /**
     *     按天算
     * @param investAmount  投资金额
     * @param apr   年化率
     * @param period  投资期限
     * @return   收益
     */
     public  static double  calculator(double investAmount,double apr,int period){
         return P2pUtils.round(apr / 360 / 100 * period * investAmount,
          2);
     }


    /**
     *    按月算
     * @param investAmount  投资金额
     * @param apr   年化率
     * @param period  投资期限
     * @return   收益
     */
    public  static double  calculator2(double investAmount,double apr,int period){
        return P2pUtils.round(apr / 12 / 100 * period * investAmount,
                2);
    }

    /**
     * 功能：提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        return b.setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

