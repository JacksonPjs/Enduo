package com.enduo.ndonline.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class TouziBean {

    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"annualRate":12,"borrowId":236,"borrowStatus":3,"borrowTitle":"月月赚000","cellPhone":"13811111111","deadline":3,"deadlineType":2,"id":180,"interestBearingTime":1,"investAmount":100,"investTime":1483428202000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103152322334979712","peroids":0,"realAmount":100,"realName":"å°¤é\u0092¢æ\u0098\u008e","repayList":[],"repayStatus":0,"repayType":1,"result":3,"transferAmount":0,"userName":"13811111111"},{"annualRate":12,"borrowId":236,"borrowStatus":3,"borrowTitle":"月月赚000","cellPhone":"13811111111","deadline":3,"deadlineType":2,"id":178,"interestBearingTime":1,"investAmount":100,"investTime":1483427691000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103151451663874825","peroids":0,"realAmount":100,"realName":"å°¤é\u0092¢æ\u0098\u008e","repayList":[],"repayStatus":0,"repayType":1,"result":3,"transferAmount":0,"userName":"13811111111"},{"annualRate":12,"borrowId":236,"borrowStatus":3,"borrowTitle":"月月赚000","cellPhone":"13811111111","deadline":3,"deadlineType":2,"id":177,"interestBearingTime":1,"investAmount":100,"investTime":1483427663000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103151423714357220","peroids":0,"realAmount":100,"realName":"å°¤é\u0092¢æ\u0098\u008e","repayList":[],"repayStatus":0,"repayType":1,"result":3,"transferAmount":0,"userName":"13811111111"}]
     * page : {"endRow":3,"firstPage":true,"hasNextPage":false,"hasPrePage":false,"lastPage":true,"limit":10,"nextPage":1,"offset":0,"page":1,"prePage":1,"slider":[1],"startRow":1,"totalCount":3,"totalPages":1}
     */

    private StateBean state;
    private PageBean page;
    private List<DataBean> data;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public static class PageBean {
        /**
         * endRow : 3
         * firstPage : true
         * hasNextPage : false
         * hasPrePage : false
         * lastPage : true
         * limit : 10
         * nextPage : 1
         * offset : 0
         * page : 1
         * prePage : 1
         * slider : [1]
         * startRow : 1
         * totalCount : 3
         * totalPages : 1
         */

        private int endRow;
        private boolean firstPage;
        private boolean hasNextPage;
        private boolean hasPrePage;
        private boolean lastPage;
        private int limit;
        private int nextPage;
        private int offset;
        private int page;
        private int prePage;
        private int startRow;
        private int totalCount;
        private int totalPages;
        private List<Integer> slider;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPrePage() {
            return hasPrePage;
        }

        public void setHasPrePage(boolean hasPrePage) {
            this.hasPrePage = hasPrePage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<Integer> getSlider() {
            return slider;
        }

        public void setSlider(List<Integer> slider) {
            this.slider = slider;
        }
    }

    public static class DataBean {
        /**
         * annualRate : 12.0
         * borrowId : 236
         * borrowStatus : 3
         * borrowTitle : 月月赚000
         * cellPhone : 13811111111
         * deadline : 3
         * deadlineType : 2
         * id : 180
         * interestBearingTime : 1
         * investAmount : 100.0
         * investTime : 1483428202000
         * investWay : 1
         * investorId : 605
         * isClaim : 0
         * numOfPeroids : 0
         * ordDate : 1483372800000
         * ordId : 20170103152322334979712
         * peroids : 0
         * realAmount : 100.0
         * realName : å°¤é¢æ
         * repayList : []
         * repayStatus : 0
         * repayType : 1
         * result : 3
         * transferAmount : 0.0
         * userName : 13811111111
         */

        private double annualRate;
        private int borrowId;
        private int borrowStatus;
        private String borrowTitle;
        private String cellPhone;
        private int deadline;
        private int deadlineType;
        private int id;
        private int interestBearingTime;
        private double investAmount;
        private long investTime;
        private int investWay;
        private int investorId;
        private int isClaim;
        private int numOfPeroids;
        private long ordDate;
        private String ordId;
        private int peroids;
        private double realAmount;
        private String realName;
        private int repayStatus;
        private int repayType;
        private int result;
        private double transferAmount;
        private String userName;
        private List<?> repayList;

        public double getAnnualRate() {
            return annualRate;
        }

        public void setAnnualRate(double annualRate) {
            this.annualRate = annualRate;
        }

        public int getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(int borrowId) {
            this.borrowId = borrowId;
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

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
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

        public double getInvestAmount() {
            return investAmount;
        }

        public void setInvestAmount(double investAmount) {
            this.investAmount = investAmount;
        }

        public long getInvestTime() {
            return investTime;
        }

        public void setInvestTime(long investTime) {
            this.investTime = investTime;
        }

        public int getInvestWay() {
            return investWay;
        }

        public void setInvestWay(int investWay) {
            this.investWay = investWay;
        }

        public int getInvestorId() {
            return investorId;
        }

        public void setInvestorId(int investorId) {
            this.investorId = investorId;
        }

        public int getIsClaim() {
            return isClaim;
        }

        public void setIsClaim(int isClaim) {
            this.isClaim = isClaim;
        }

        public int getNumOfPeroids() {
            return numOfPeroids;
        }

        public void setNumOfPeroids(int numOfPeroids) {
            this.numOfPeroids = numOfPeroids;
        }

        public long getOrdDate() {
            return ordDate;
        }

        public void setOrdDate(long ordDate) {
            this.ordDate = ordDate;
        }

        public String getOrdId() {
            return ordId;
        }

        public void setOrdId(String ordId) {
            this.ordId = ordId;
        }

        public int getPeroids() {
            return peroids;
        }

        public void setPeroids(int peroids) {
            this.peroids = peroids;
        }

        public double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(double realAmount) {
            this.realAmount = realAmount;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getRepayStatus() {
            return repayStatus;
        }

        public void setRepayStatus(int repayStatus) {
            this.repayStatus = repayStatus;
        }

        public int getRepayType() {
            return repayType;
        }

        public void setRepayType(int repayType) {
            this.repayType = repayType;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public double getTransferAmount() {
            return transferAmount;
        }

        public void setTransferAmount(double transferAmount) {
            this.transferAmount = transferAmount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public List<?> getRepayList() {
            return repayList;
        }

        public void setRepayList(List<?> repayList) {
            this.repayList = repayList;
        }
    }
}
