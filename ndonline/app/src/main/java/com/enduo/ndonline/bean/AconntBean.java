package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2017/1/2.
 * 账户总览
 */

public class AconntBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * tAccount : {"createTime":1483350842000,"frozenAmount":9130,"id":601,"score":50,"usableAmount":9990870,"userId":605}
     * total1 : 1.0E7
     * total2 : 0
     * dsbj : 0
     * ydsy : 0
     * jujianfei : 0
     * feeAmount : 0
     * day1 : 0
     */

    private StateBean state;
    private TAccountBean tAccount;
    private double total1;
    private double total2;
    private double dsbj;
    private double ydsy;
    private double jujianfei;
    private double feeAmount;
    private double day1;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public TAccountBean getTAccount() {
        return tAccount;
    }

    public void setTAccount(TAccountBean tAccount) {
        this.tAccount = tAccount;
    }

    public double getTotal1() {
        return total1;
    }

    public void setTotal1(double total1) {
        this.total1 = total1;
    }

    public double getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }

    public double getDsbj() {
        return dsbj;
    }

    public void setDsbj(int dsbj) {
        this.dsbj = dsbj;
    }

    public double getYdsy() {
        return ydsy;
    }

    public void setYdsy(int ydsy) {
        this.ydsy = ydsy;
    }

    public double getJujianfei() {
        return jujianfei;
    }

    public void setJujianfei(int jujianfei) {
        this.jujianfei = jujianfei;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(int feeAmount) {
        this.feeAmount = feeAmount;
    }

    public double getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
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
    }

    public static class TAccountBean {
        /**
         * createTime : 1483350842000
         * frozenAmount : 9130.0
         * id : 601
         * score : 50
         * usableAmount : 9990870.0
         * userId : 605
         */

        private long createTime;
        private double frozenAmount;
        private int id;
        private int score;
        private double usableAmount;
        private int userId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getFrozenAmount() {
            return frozenAmount;
        }

        public void setFrozenAmount(double frozenAmount) {
            this.frozenAmount = frozenAmount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public double getUsableAmount() {
            return usableAmount;
        }

        public void setUsableAmount(double usableAmount) {
            this.usableAmount = usableAmount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
