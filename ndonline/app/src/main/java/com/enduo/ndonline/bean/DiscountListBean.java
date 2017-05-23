package com.enduo.ndonline.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/9.
 */

public class DiscountListBean {
    private StateBean state;
    private ArrayList<DisData> data;

    public ArrayList<DisData> getData() {
        return data;
    }

    public void setData(ArrayList<DisData> data) {
        this.data = data;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
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

    public static class DisData {
        private Double couponAmount;
        private int couponStatus;
        private int couponType;
        private long expirationDate;

        public Double getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(Double couponAmount) {
            this.couponAmount = couponAmount;
        }

        public int getCouponStatus() {
            return couponStatus;
        }

        public void setCouponStatus(int couponStatus) {
            this.couponStatus = couponStatus;
        }

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

        public long getExpirationDate() {
            return expirationDate;
        }

        public void setExpirationDate(long expirationDate) {
            this.expirationDate = expirationDate;
        }
    }
}
