package com.enduo.ndonline.productlist;

import java.text.DecimalFormat;

/**
 *     标信息转换
 * Created by Administrator on 2016/12/30.
 */

public class T1changerString {

    /**
     *    进度计算
     * @param borrowStatus
     *  借款状态（1，申请中，2，初审通过，3，招标中，4，复审中，5，还款中，6，已还款，7，借款失败(初审)，
     *      8复审失败,9流标,10， 复审处理中,11 流标或复审不通过处理中）',
     * @return
     */
    public static int progress(int borrowStatus,double hasBorrowAmount ,double borrowAmount){
        switch (borrowStatus){
            case 3:
                return (int)((hasBorrowAmount/borrowAmount)*10000) ;
            case 4:
            case 5:
            case 6:
            case 10:
                return 10000 ;
            default:
                break;
        }
        return 0 ;

    }




    /**
     *    '计息时间（1：满标计息；2：次日计息；3：即时计息）',
     * @param interestBearingTime
     * @return
     */
    public static String t1chager(int interestBearingTime){
          switch (interestBearingTime){
              case 1:
                  return "满标计息" ;
              case 2:
                  return "次日计息" ;
              case 3:
                  return "即时计息" ;
              default:
                  break;
          }
        return "不存在" ;

    }




    /**
     *     `deadline` int(5) DEFAULT '0' COMMENT '借款期限',
     `deadlineType` int(1) DEFAULT '1' COMMENT '借款期限类型（1，为天，2为月）',
     * @param deadline
     * @return
     */
    public static String t2chager(int deadline ,int deadlineType){

        switch (deadlineType){
            case 1:
                return deadline+"天" ;
            case 2:
                return deadline+"个月" ;
            default:
                break;
        }
        return deadline+"?" ;

    }

    /**
     *      DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
            String s = decimalFormat.format(d);
     * @return
     */
    public static String t3chager(Double d){

        DecimalFormat decimalFormat = new DecimalFormat("###,###.00");
        String s = decimalFormat.format(d) ;
        if (s.equals(".00"))
            s = "0.00" ;
        return s;

    }
    public static String t4chager(int interestBearingTime){
        switch (interestBearingTime){
            case 1:
                return "一次性还款" ;
            case 2:
                return "按月付息,到期还本" ;
            case 3:
                return "等额还款" ;
            default:
                break;
        }
        return "？？还款" ;
    }

}
