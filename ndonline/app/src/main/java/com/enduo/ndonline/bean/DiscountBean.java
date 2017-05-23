package com.enduo.ndonline.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class DiscountBean {

    private ArrayList<UserCoupon> userCoupon;
    private ArrayList<UserCouponJx> userCouponJx;
    private ArrayList<UserCouponGz> userCouponGz;

    private StateBean state;


    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }


    public ArrayList<UserCoupon> getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(ArrayList<UserCoupon> userCoupon) {
        this.userCoupon = userCoupon;
    }

    public ArrayList<UserCouponJx> getUserCouponJx() {
        return userCouponJx;
    }

    public void setUserCouponJx(ArrayList<UserCouponJx> userCouponJx) {
        this.userCouponJx = userCouponJx;
    }

    public ArrayList<UserCouponGz> getUserCouponGz() {
        return userCouponGz;
    }

    public void setUserCouponGz(ArrayList<UserCouponGz> userCouponGz) {
        this.userCouponGz = userCouponGz;
    }

    public static class StateBean {
        /**
         * info : 取值成功
         * status : 0
         */

        private String info;
        private int status;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "StateBean{" +
                    "info='" + info + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    public static class UserCoupon {
        /**
         * 返回结果	userCoupon	代金券
         * `id` bigint(18) NOT NULL AUTO_INCREMENT,
         * `userId` bigint(11) DEFAULT NULL COMMENT '用户id',
         * `couponNum` varchar(50) DEFAULT NULL COMMENT '券号',
         * `couponName` varchar(30) DEFAULT NULL COMMENT '礼券名称',
         * `couponAmount` decimal(18,2) DEFAULT NULL COMMENT '礼券金额',
         * `createTime` datetime DEFAULT NULL COMMENT '创建时间',
         * `couponRemarks` varchar(80) DEFAULT NULL COMMENT '备注',
         * `couponType` int(2) DEFAULT '1' COMMENT '礼券类型（1.注册即送1888现金券 2:投资红包3：提现券4：现金券（代金券））',
         * `couponStatus` int(2) DEFAULT NULL COMMENT '礼券状态（1，未领取，2，未使用，3，已使用，4，未领取过期，5未使用过期，6处理中）',
         * `expirationDate` datetime DEFAULT NULL COMMENT '过期时间',
         * PRIMARY KEY (`id`)
         */

        private String couponAmount;
        private String couponName;
        private String couponRemarks;
        private int couponStatus;
        private int couponType;
        private long createTime;
        private long expirationDate;
        private int id;
        private int userId;

        public String getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(String couponAmount) {
            this.couponAmount = couponAmount;
        }

        public int getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(int couponStatus) {
            this.couponStatus = couponStatus;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getCouponRemarks() {
            return couponRemarks;
        }

        public void setCouponRemarks(String couponRemarks) {
            this.couponRemarks = couponRemarks;
        }

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(long expirationDate) {
            this.expirationDate = expirationDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "UserCoupon{" +
                    "couponAmount='" + couponAmount + '\'' +
                    ", couponName='" + couponName + '\'' +
                    ", couponRemarks='" + couponRemarks + '\'' +
                    ", couponStatus=" + couponStatus +
                    ", couponType=" + couponType +
                    ", createTime=" + createTime +
                    ", expirationDate=" + expirationDate +
                    ", id=" + id +
                    ", userId=" + userId +
                    '}';
        }
    }


    public static class UserCouponJx {
        /*返回结果	userCouponJx	加息券
        * `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `userId` bigint(11) DEFAULT NULL COMMENT '用户id',
  `interestticket` decimal(5,2) DEFAULT NULL COMMENT '加息券年化',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `interestRemarks` varchar(80) DEFAULT NULL COMMENT '备注',
  `interestStatus` int(2) DEFAULT '2' COMMENT '礼券状态（1，未领取，2，未使用，3，已使用，4，未领取过期，5未使用过期，6处理中）',
  `expirationDate` datetime DEFAULT NULL COMMENT '过期时间',
  `interestNum` varchar(50) DEFAULT NULL COMMENT '券号',
  PRIMARY KEY (`id`)
        * */
        private long createTime;
        private long expirationDate;
        private int id;
        private int interestStatus;
        private float interestticket;
        private int userId;
        private String interestRemarks;

        public int getUserId() {
            return userId;
        }

        public int getInterestStatus() {
            return interestStatus;
        }

        public float getInterestticket() {
            return interestticket;
        }

        public void setInterestticket(float interestticket) {
            this.interestticket = interestticket;
        }

        public void setInterestStatus(int interestStatus) {
            this.interestStatus = interestStatus;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getInterestRemarks() {
            return interestRemarks;
        }

        public void setInterestRemarks(String interestRemarks) {
            this.interestRemarks = interestRemarks;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(long expirationDate) {
            this.expirationDate = expirationDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "UserCouponJx{" +
                    "createTime=" + createTime +
                    ", expirationDate=" + expirationDate +
                    ", id=" + id +
                    ", interestStatus=" + interestStatus +
                    ", interestticket=" + interestticket +
                    ", userId=" + userId +
                    ", interestRemarks='" + interestRemarks + '\'' +
                    '}';
        }
    }

    public static class UserCouponGz {
        /*返回结果	userCouponGz	使用规则
        * `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `interestQuota` decimal(10,2) DEFAULT NULL COMMENT '道具额度',
  `useMinMoney` decimal(18,2) DEFAULT NULL COMMENT '使用最小限额',
  `useMaxMoney` decimal(18,2) DEFAULT NULL COMMENT '使用最大限额',
  `label` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '标签（T1 T2。。等）',
  `propType` int(2) DEFAULT '0' COMMENT '道具种类（0：加息券 1：现金券 2：投资红包 3：提现券 ）',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
        * */
        private  int id;
        private  float interestQuota;
        private  String label;
        private  int propType;
        private  int useMaxMoney;
        private  int useMinMoney;
        private  long updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public float getInterestQuota() {
            return interestQuota;
        }

        public void setInterestQuota(float interestQuota) {
            this.interestQuota = interestQuota;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getPropType() {
            return propType;
        }

        public void setPropType(int propType) {
            this.propType = propType;
        }

        public int getUseMaxMoney() {
            return useMaxMoney;
        }

        public void setUseMaxMoney(int useMaxMoney) {
            this.useMaxMoney = useMaxMoney;
        }

        public int getUseMinMoney() {
            return useMinMoney;
        }

        public void setUseMinMoney(int useMinMoney) {
            this.useMinMoney = useMinMoney;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "UserCouponGz{" +
                    "id=" + id +
                    ", interestQuota=" + interestQuota +
                    ", label='" + label + '\'' +
                    ", propType=" + propType +
                    ", useMaxMoney=" + useMaxMoney +
                    ", useMinMoney=" + useMinMoney +
                    ", updateTime=" + updateTime +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DiscountBean{" +
                "userCoupon=" + userCoupon +
                ", userCouponJx=" + userCouponJx +
                ", userCouponGz=" + userCouponGz +
                ", state=" + state +
                '}';
    }
}
