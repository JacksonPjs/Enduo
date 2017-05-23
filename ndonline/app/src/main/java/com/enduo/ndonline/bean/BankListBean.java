package com.enduo.ndonline.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */

public class BankListBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"bankCardNo":"21313213212","bankCode":"ABC","bankName":"中国农业银行","cardStatus":2,"createTime":1483518558000,"id":19,"userId":605}]
     */

    private StateBean state;
    private List<DataBean> data;
    String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

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
         * bankCardNo : 21313213212
         * bankCode : ABC
         * bankName : 中国农业银行
         * cardStatus : 2
         * createTime : 1483518558000
         * id : 19
         * userId : 605
         */

        private String bankCardNo;
        private String bankCode;
        private String bankName;
        private int cardStatus;
        private long createTime;
        private String id;
        private int userId;

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getCardStatus() {
            return cardStatus;
        }

        public void setCardStatus(int cardStatus) {
            this.cardStatus = cardStatus;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
