package com.enduo.ndonline.bean;

import java.util.List;

/**
 * 投资记录
 */

public class InvestmentBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":198,"investAmount":1000,"investTime":1483492314000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483459200000,"ordId":"20170104091154645899193","peroids":0,"realAmount":1000,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":197,"investAmount":1000,"investTime":1483439508000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103183148593131598","peroids":0,"realAmount":1000,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":196,"investAmount":100,"investTime":1483439043000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103182403233719626","peroids":0,"realAmount":100,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":195,"investAmount":100,"investTime":1483438965000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103182245241667560","peroids":0,"realAmount":100,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":194,"investAmount":100,"investTime":1483438959000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103182239797266305","peroids":0,"realAmount":100,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":236,"borrowStatus":0,"cellPhone":"13651433115","deadline":0,"deadlineType":0,"id":193,"investAmount":100,"investTime":1483438896000,"investWay":1,"investorId":569,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103182136792805114","peroids":0,"realAmount":100,"realName":"陈阳金","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13651433115"},{"borrowId":236,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":192,"investAmount":1300,"investTime":1483438850000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103182050270975875","peroids":0,"realAmount":1300,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":191,"investAmount":230,"investTime":1483438074000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103180754962582802","peroids":0,"realAmount":230,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":239,"borrowStatus":0,"cellPhone":"13811111111","deadline":0,"deadlineType":0,"id":190,"investAmount":5000,"investTime":1483437804000,"investWay":1,"investorId":605,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103180324528754267","peroids":0,"realAmount":5000,"realName":"汪灶声","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13811111111"},{"borrowId":236,"borrowStatus":0,"cellPhone":"13651433115","deadline":0,"deadlineType":0,"id":189,"investAmount":100,"investTime":1483435955000,"investWay":1,"investorId":569,"isClaim":0,"numOfPeroids":0,"ordDate":1483372800000,"ordId":"20170103173235389932443","peroids":0,"realAmount":100,"realName":"陈阳金","repayStatus":0,"repayType":0,"result":3,"transferAmount":0,"userName":"13651433115"}]
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
         * borrowId : 239
         * borrowStatus : 0
         * cellPhone : 13811111111
         * deadline : 0
         * deadlineType : 0
         * id : 198
         * investAmount : 1000.0
         * investTime : 1483492314000
         * investWay : 1
         * investorId : 605
         * isClaim : 0
         * numOfPeroids : 0
         * ordDate : 1483459200000
         * ordId : 20170104091154645899193
         * peroids : 0
         * realAmount : 1000.0
         * realName : 汪灶声
         * repayStatus : 0
         * repayType : 0
         * result : 3
         * transferAmount : 0.0
         * userName : 13811111111
         */


        private double investAmount;
        private long investTime;

        private String userName;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
