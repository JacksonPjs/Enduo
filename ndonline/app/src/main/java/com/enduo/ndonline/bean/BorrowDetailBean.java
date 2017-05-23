package com.enduo.ndonline.bean;

import java.io.Serializable;

/**
 *   项目详情
 */

public class BorrowDetailBean implements Serializable{

    /**
     * state : {"info":"取值成功","status":"0"}
     * data : {"annualRate":10,"auditTime":1480901550000,"borrowAmount":100000,"borrowNo":"3333333333","borrowStatus":5,"borrowTitle":"333333333","borrowType":1,"borrowerId":544,"clockAmount":-100000,"collateralInfos":"<p>gergerg<\/p>","createId":1,"createTime":1480901243000,"deadline":3,"deadlineType":2,"firstAuditId":1,"fullAuditId":1,"fullTime":1480901502000,"hasBorrowAmount":100000,"id":228,"interestBearingTime":1,"introductionInfos":"<p>gregergre<\/p>","investStartTime":1480901340000,"isClaim":0,"maxInvestAmount":0,"minInvestAmount":100,"ordId":"17020161205092735528301286","personBorrowerId":7,"raisingPeriod":1,"remainTime":0,"repayDate":1488729600000,"repayType":2,"riskControlInfos":"<p>gregerge<\/p>","riskrank":1}
     */

    private StateBean state;
    private DataBean tBorrowModel;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public DataBean getData() {
        return tBorrowModel;
    }

    public void setData(DataBean data) {
        this.tBorrowModel = data;
    }

    public static class StateBean implements Serializable{
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

    public static class DataBean implements Serializable{
        /**
         * annualRate : 10.0
         * auditTime : 1480901550000
         * borrowAmount : 100000.0
         * borrowNo : 3333333333
         * borrowStatus : 5
         * borrowTitle : 333333333
         * borrowType : 1
         * borrowerId : 544
         * clockAmount : -100000.0
         * collateralInfos : <p>gergerg</p>
         * createId : 1
         * createTime : 1480901243000
         * deadline : 3
         * deadlineType : 2
         * firstAuditId : 1
         * fullAuditId : 1
         * fullTime : 1480901502000
         * hasBorrowAmount : 100000.0
         * id : 228
         * interestBearingTime : 1
         * introductionInfos : <p>gregergre</p>
         * investStartTime : 1480901340000
         * isClaim : 0
         * maxInvestAmount : 0.0
         * minInvestAmount : 100.0
         * ordId : 17020161205092735528301286
         * personBorrowerId : 7
         * raisingPeriod : 1
         * remainTime : 0
         * repayDate : 1488729600000
         * repayType : 2
         * riskControlInfos : <p>gregerge</p>
         * riskrank : 1
         */

        private double annualRate;
      //  private long auditTime;
        private double borrowAmount;
        private String borrowNo;
        private int borrowStatus;
        private String borrowTitle;
        private int borrowType;
    //    private int borrowerId;
        private double clockAmount;
        private String collateralInfos;
   //     private int createId;
        private long createTime;
        private int deadline;
        private int deadlineType;
    //    private int firstAuditId;
    //    private int fullAuditId;
    //    private long fullTime;
        private double hasBorrowAmount;
        private int id;
        private int interestBearingTime;
        private String introductionInfos;
        private long investStartTime;
     //   private int isClaim;
        private double maxInvestAmount;
        private double minInvestAmount;
     //   private String ordId;
     //   private int personBorrowerId;
     //   private int raisingPeriod;
     //   private int remainTime;
    //    private long repayDate;
        private int repayType;
        private String riskControlInfos;
        private int remainTime;
     //   private int riskrank;

        public int getRemainTime() {
            return remainTime;
        }

        public void setRemainTime(int remainTime) {
            this.remainTime = remainTime;
        }

        public double getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(double annualRate) {
            this.annualRate = annualRate;
        }

//        public long getAuditTime() {
//            return auditTime;
//        }
//
//        public void setAuditTime(long auditTime) {
//            this.auditTime = auditTime;
//        }

        public double getBorrowAmount() {
            return borrowAmount;
        }

        public void setBorrowAmount(double borrowAmount) {
            this.borrowAmount = borrowAmount;
        }

        public String getBorrowNo() {
            return borrowNo;
        }

        public void setBorrowNo(String borrowNo) {
            this.borrowNo = borrowNo;
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

//        public int getBorrowerId() {
//            return borrowerId;
//        }
//
//        public void setBorrowerId(int borrowerId) {
//            this.borrowerId = borrowerId;
//        }

        public double getClockAmount() {
            return clockAmount;
        }

        public void setClockAmount(double clockAmount) {
            this.clockAmount = clockAmount;
        }

        public String getCollateralInfos() {
            return collateralInfos;
        }

        public void setCollateralInfos(String collateralInfos) {
            this.collateralInfos = collateralInfos;
        }

//        public int getCreateId() {
//            return createId;
//        }
//
//        public void setCreateId(int createId) {
//            this.createId = createId;
//        }

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

//        public int getFirstAuditId() {
//            return firstAuditId;
//        }
//
//        public void setFirstAuditId(int firstAuditId) {
//            this.firstAuditId = firstAuditId;
//        }

//        public int getFullAuditId() {
//            return fullAuditId;
//        }
//
//        public void setFullAuditId(int fullAuditId) {
//            this.fullAuditId = fullAuditId;
//        }

//        public long getFullTime() {
//            return fullTime;
//        }
//
//        public void setFullTime(long fullTime) {
//            this.fullTime = fullTime;
//        }

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

        public int getInterestBearingTime() {
            return interestBearingTime;
        }

        public void setInterestBearingTime(int interestBearingTime) {
            this.interestBearingTime = interestBearingTime;
        }

        public String getIntroductionInfos() {
            return introductionInfos;
        }

        public void setIntroductionInfos(String introductionInfos) {
            this.introductionInfos = introductionInfos;
        }

        public long getInvestStartTime() {
            return investStartTime;
        }

        public void setInvestStartTime(long investStartTime) {
            this.investStartTime = investStartTime;
        }

//        public int getIsClaim() {
//            return isClaim;
//        }
//
//        public void setIsClaim(int isClaim) {
//            this.isClaim = isClaim;
//        }

        public double getMaxInvestAmount() {
            return maxInvestAmount;
        }

        public void setMaxInvestAmount(double maxInvestAmount) {
            this.maxInvestAmount = maxInvestAmount;
        }

        public double getMinInvestAmount() {
            return minInvestAmount;
        }

        public void setMinInvestAmount(double minInvestAmount) {
            this.minInvestAmount = minInvestAmount;
        }

//        public String getOrdId() {
//            return ordId;
//        }
//
//        public void setOrdId(String ordId) {
//            this.ordId = ordId;
//        }

//        public int getPersonBorrowerId() {
//            return personBorrowerId;
//        }
//
//        public void setPersonBorrowerId(int personBorrowerId) {
//            this.personBorrowerId = personBorrowerId;
//        }

//        public int getRaisingPeriod() {
//            return raisingPeriod;
//        }
//
//        public void setRaisingPeriod(int raisingPeriod) {
//            this.raisingPeriod = raisingPeriod;
//        }
//
//        public int getRemainTime() {
//            return remainTime;
//        }
//
//        public void setRemainTime(int remainTime) {
//            this.remainTime = remainTime;
//        }
//
//        public long getRepayDate() {
//            return repayDate;
//        }
//
//        public void setRepayDate(long repayDate) {
//            this.repayDate = repayDate;
//        }
//
        public int getRepayType() {
            return repayType;
        }

        public void setRepayType(int repayType) {
            this.repayType = repayType;
        }

        public String getRiskControlInfos() {
            return riskControlInfos;
        }

        public void setRiskControlInfos(String riskControlInfos) {
            this.riskControlInfos = riskControlInfos;
        }

//        public int getRiskrank() {
//            return riskrank;
//        }
//
//        public void setRiskrank(int riskrank) {
//            this.riskrank = riskrank;
//        }
    }
}
