package com.enduo.ndonline.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public class BiaoBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"annualRate":10,"borrowAmount":100000,"borrowStatus":3,"borrowTitle":"ooioioioioi","borrowType":1,"createTime":1483068079000,"deadline":2,"deadlineType":2,"hasBorrowAmount":0,"id":233,"investStartTime":1483069500000,"mayCast":100000,"minInvestAmount":100,"progress":0,"raisingPeriod":3,"remainTime":0,"repayType":1},{"annualRate":12,"borrowAmount":200000,"borrowStatus":5,"borrowTitle":"测试test","borrowType":1,"createTime":1481881941000,"deadline":2,"deadlineType":2,"fullTime":1482128616000,"hasBorrowAmount":200000,"id":229,"investStartTime":1481882340000,"mayCast":0,"minInvestAmount":100,"progress":1,"raisingPeriod":6,"remainTime":0,"repayDate":1487520000000,"repayType":2},{"annualRate":10,"borrowAmount":100000,"borrowStatus":5,"borrowTitle":"333333333","borrowType":1,"createTime":1480901243000,"deadline":3,"deadlineType":2,"fullTime":1480901502000,"hasBorrowAmount":100000,"id":228,"investStartTime":1480901340000,"mayCast":0,"minInvestAmount":100,"progress":1,"raisingPeriod":1,"remainTime":0,"repayDate":1488729600000,"repayType":2},{"annualRate":10,"borrowAmount":100000,"borrowStatus":6,"borrowTitle":"1111111","borrowType":1,"createTime":1480900620000,"deadline":2,"deadlineType":2,"fullTime":1480900825000,"hasBorrowAmount":100000,"id":227,"investStartTime":1480900740000,"mayCast":0,"minInvestAmount":100,"progress":1,"raisingPeriod":3,"remainTime":0,"repayDate":1486310400000,"repayType":1},{"annualRate":10,"borrowAmount":100000,"borrowStatus":6,"borrowTitle":"89757","borrowType":7,"createTime":1479110823000,"deadline":200,"deadlineType":1,"fullTime":1479110984000,"hasBorrowAmount":100000,"id":226,"investStartTime":1479110940000,"mayCast":0,"minInvestAmount":100,"progress":1,"raisingPeriod":1,"remainTime":0,"repayDate":1496419200000,"repayType":1},{"annualRate":10,"borrowAmount":100000,"borrowStatus":6,"borrowTitle":"测试债权转让","borrowType":1,"createTime":1478588990000,"deadline":250,"deadlineType":1,"fullTime":1478589707000,"hasBorrowAmount":100000,"id":225,"investStartTime":1478589300000,"mayCast":0,"minInvestAmount":100,"progress":1,"raisingPeriod":7,"remainTime":0,"repayDate":1500220800000,"repayType":1},{"annualRate":15,"borrowAmount":10000,"borrowStatus":9,"borrowTitle":"2016ttt","borrowType":1,"createTime":1455690061000,"deadline":60,"deadlineType":1,"hasBorrowAmount":2866,"id":222,"investStartTime":1455690300000,"mayCast":7134,"minInvestAmount":100,"progress":0.2866,"raisingPeriod":3,"remainTime":0,"repayType":1},{"annualRate":12,"borrowAmount":1000000,"borrowStatus":9,"borrowTitle":"test2","borrowType":1,"createTime":1452331113000,"deadline":3,"deadlineType":2,"hasBorrowAmount":0,"id":216,"investStartTime":1453346880000,"mayCast":1000000,"minInvestAmount":500,"progress":0,"raisingPeriod":3,"remainTime":0,"repayType":3},{"annualRate":13,"borrowAmount":100000,"borrowStatus":9,"borrowTitle":"test3","borrowType":1,"createTime":1452331166000,"deadline":2,"deadlineType":2,"hasBorrowAmount":0,"id":217,"investStartTime":1453346760000,"mayCast":100000,"minInvestAmount":50,"progress":0,"raisingPeriod":5,"remainTime":0,"repayType":1},{"annualRate":15,"borrowAmount":10000,"borrowStatus":9,"borrowTitle":"yuanTest201602","borrowType":1,"createTime":1453260539000,"deadline":1,"deadlineType":2,"fullTime":1453260830000,"hasBorrowAmount":10000,"id":221,"investStartTime":1453260660000,"mayCast":0,"minInvestAmount":100,"progress":1,"raisingPeriod":1,"remainTime":0,"repayDate":1455984000000,"repayType":1}]
     * page : {"endRow":10,"firstPage":true,"hasNextPage":true,"hasPrePage":false,"lastPage":false,"limit":10,"nextPage":2,"offset":0,"page":1,"prePage":1,"slider":[1,2,3],"startRow":1,"totalCount":21,"totalPages":3}
     */

    private StateBean state;

    private List<DataBean> data;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }



    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
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


    public static class DataBean {
        /**
         * annualRate : 10.0
         * borrowAmount : 100000.0
         * borrowStatus : 3
         * borrowTitle : ooioioioioi
         * borrowType : 1
         * createTime : 1483068079000
         * deadline : 2
         * deadlineType : 2
         * hasBorrowAmount : 0.0
         * id : 233
         * investStartTime : 1483069500000
         * mayCast : 100000.0
         * minInvestAmount : 100.0
         * progress : 0.0
         * raisingPeriod : 3
         * remainTime : 0
         * repayType : 1
         * fullTime : 1482128616000
         * repayDate : 1487520000000
         */

        private double annualRate;
        private double borrowAmount;
        private int borrowStatus;
        private String borrowTitle;
        private int borrowType;
        private long createTime;
        private int deadline;
        private int deadlineType;
        private double hasBorrowAmount;
        private int id;
        private long investStartTime;
   //     private double mayCast;
        private double minInvestAmount;
     //   private double progress;
   //     private int raisingPeriod;
   //     private int remainTime;
    //    private int repayType;
    //    private long fullTime;
    //    private long repayDate;

        public double getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(double annualRate) {
            this.annualRate = annualRate;
        }

        public double getBorrowAmount() {
            return borrowAmount;
        }

        public void setBorrowAmount(double borrowAmount) {
            this.borrowAmount = borrowAmount;
        }

        public int getBorrowStatus() {
            return borrowStatus;
        }

        public void setBorrowStatus(int borrowStatus) {
            this.borrowStatus = borrowStatus;
        }

        public String getBorrowTitle() {
            return borrowTitle;
        }

        public void setBorrowTitle(String borrowTitle) {
            this.borrowTitle = borrowTitle;
        }

        public int getBorrowType() {
            return borrowType;
        }

        public void setBorrowType(int borrowType) {
            this.borrowType = borrowType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDeadline() {
            return deadline;
        }

        public void setDeadline(int deadline) {
            this.deadline = deadline;
        }

        public int getDeadlineType() {
            return deadlineType;
        }

        public void setDeadlineType(int deadlineType) {
            this.deadlineType = deadlineType;
        }

        public double getHasBorrowAmount() {
            return hasBorrowAmount;
        }

        public void setHasBorrowAmount(double hasBorrowAmount) {
            this.hasBorrowAmount = hasBorrowAmount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getInvestStartTime() {
            return investStartTime;
        }

        public void setInvestStartTime(long investStartTime) {
            this.investStartTime = investStartTime;
        }

//        public double getMayCast() {
//            return mayCast;
//        }
//
//        public void setMayCast(double mayCast) {
//            this.mayCast = mayCast;
//        }

        public double getMinInvestAmount() {
            return minInvestAmount;
        }

        public void setMinInvestAmount(double minInvestAmount) {
            this.minInvestAmount = minInvestAmount;
        }

//        public double getProgress() {
//            return progress;
//        }
//
//        public void setProgress(double progress) {
//            this.progress = progress;
//        }

//        public int getRaisingPeriod() {
//            return raisingPeriod;
//        }
//
//        public void setRaisingPeriod(int raisingPeriod) {
//            this.raisingPeriod = raisingPeriod;
//        }

//        public int getRemainTime() {
//            return remainTime;
//        }
//
//        public void setRemainTime(int remainTime) {
//            this.remainTime = remainTime;
//        }
//
//        public int getRepayType() {
//            return repayType;
//        }
//
//        public void setRepayType(int repayType) {
//            this.repayType = repayType;
//        }

//        public long getFullTime() {
//            return fullTime;
//        }
//
//        public void setFullTime(long fullTime) {
//            this.fullTime = fullTime;
//        }
//
//        public long getRepayDate() {
//            return repayDate;
//        }
//
//        public void setRepayDate(long repayDate) {
//            this.repayDate = repayDate;
//        }
    }
}
