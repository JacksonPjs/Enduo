package com.enduo.ndonline.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ShareBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"cellPhone":"18622222222","createTime":1449799376000,"isInvest":6,"isRecharge":0},{"cellPhone":"18633333333","createTime":1449800023000,"isInvest":0,"isRecharge":0}]
     * page : {"endRow":2,"firstPage":true,"hasNextPage":false,"hasPrePage":false,"lastPage":true,"limit":100,"nextPage":1,"offset":0,"page":1,"prePage":1,"slider":[1],"startRow":1,"totalCount":2,"totalPages":1}
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
         * cellPhone : 18622222222
         * createTime : 1449799376000
         * isInvest : 6
         * isRecharge : 0
         */

        private String cellPhone;
        private long createTime;
        private double isInvest;
        private double isRecharge;

        public String getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(String cellPhone) {
            this.cellPhone = cellPhone;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getIsInvest() {
            return isInvest;
        }

        public void setIsInvest(double isInvest) {
            this.isInvest = isInvest;
        }

        public double getIsRecharge() {
            return isRecharge;
        }

        public void setIsRecharge(double isRecharge) {
            this.isRecharge = isRecharge;
        }
    }
}
