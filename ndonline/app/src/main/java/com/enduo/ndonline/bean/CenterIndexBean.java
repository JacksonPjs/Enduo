package com.enduo.ndonline.bean;

/**
 * 个人中心
 * Created by Administrator on 2017/1/5.
 */

public class CenterIndexBean {

    /**
     * state : {"info":"取值成功","status":"0"}
     * tPerson : true
     * payPwd : true
     * email : false
     * tBankCardlist : true
     * images : images/user/user-img-01.png
     * userName : 13811111111
     * usableAmount : 9999400.0
     * total1 : 1.0E7
     */

    private StateBean state;
    private boolean tPerson;
    private boolean payPwd;
    private boolean email;
    private boolean tBankCardlist;
    private String images;
    private String userName;
    private double usableAmount;
    private double total1;
    private double total2;

    public double getTotal2() {
        return total2;
    }

    public void setTotal2(double total2) {
        this.total2 = total2;
    }

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public boolean isTPerson() {
        return tPerson;
    }

    public void setTPerson(boolean tPerson) {
        this.tPerson = tPerson;
    }

    public boolean isPayPwd() {
        return payPwd;
    }

    public void setPayPwd(boolean payPwd) {
        this.payPwd = payPwd;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean isTBankCardlist() {
        return tBankCardlist;
    }

    public void setTBankCardlist(boolean tBankCardlist) {
        this.tBankCardlist = tBankCardlist;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(double usableAmount) {
        this.usableAmount = usableAmount;
    }

    public double getTotal1() {
        return total1;
    }

    public void setTotal1(double total1) {
        this.total1 = total1;
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
}
