package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2017/1/6.
 */

public class WithdrawBean {

    /**
     * state : {"info":"初始化成功","status":"0"}
     * data1 : {"createTime":1483350842000,"frozenAmount":8801,"id":601,"score":50,"usableAmount":9991199,"userId":605}
     * data2 : {"bankCardNo":"21313213212","bankCode":"ABC","bankName":"中国农业银行","cardStatus":2,"createTime":1483518558000,"id":19,"userId":605}
     */

    private StateBean state;
    private Data1Bean data1;
    private Data2Bean data2;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public Data1Bean getData1() {
        return data1;
    }

    public void setData1(Data1Bean data1) {
        this.data1 = data1;
    }

    public Data2Bean getData2() {
        return data2;
    }

    public void setData2(Data2Bean data2) {
        this.data2 = data2;
    }
    String realName;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public static class StateBean {
        /**
         * info : 初始化成功
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

    public static class Data1Bean {

        private String usableAmount;

        public String getUsableAmount() {
            return usableAmount;
        }

        public void setUsableAmount(String usableAmount) {
            this.usableAmount = usableAmount;
        }

    }

    public static class Data2Bean {

        private String bankCardNo;

        private String bankName;
        int id;
        String bankCode;

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

    }
}
