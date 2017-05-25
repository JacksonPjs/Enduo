package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2016/12/31.
 */

public class LoginBean {

    /**
     * state : {"info":"取值成功","status":"0"}
     * tPerson : false
     * payPwd : false
     * email : false
     * tBankCardlist : false
     * images : images/user/user-img-01.png
     * token : eyJhbGciOiJIUzUxMiJ9.eyJKV1RfQk9EWSI6IntcImFwcEtleVwiOlwieWdfcHJvZHVjdF9wY1wiLFwiYXBwU2VjcmV0XCI6XCIzOTgyMmVkM2ZlZWZhNzFjMDUzNzNmMjRmYTU2NjgyNlwiLFwiYXBwVmVyc2lvblwiOlwiMS4wLjBcIixcImNlbGxQaG9uZVwiOlwiMTM4MTExMTExMTFcIixcImRldmljZUlkXCI6XCJwY1wiLFwiZXhwaXJlXCI6MCxcInN5c3RlbVNvdXJjZVwiOlwicGNcIixcInN5c3RlbVR5cGVcIjpcIjQ1NDE2Mzg1MzU0MzVcIixcInRpbWVzdGFtcFwiOlwiMTQ4MzM1Mjk2NjQ3OFwiLFwidG9rZW5JZFwiOlwiMTM4MTExMTExMTFcIixcInVzZXJJZFwiOjYwNSxcInZlcnNpb25cIjpcIjEuMC4wXCJ9In0.HGAt9Ku9eeMaEveuYeCJE3WI7J_QMqdpGw0-54hVYB3GS_-i5ZZ0SVXXHG-MqAzELqbWQwhlmjgVajxbZsZEIw
     * userName : 13811111111
     * cellPhone : 13811111111
     * usableAmount : 100000.0
     * total1 : 100000.0
     */

    private StateBean state;
    private boolean tPerson;
    private boolean payPwd;
    private boolean email;
    private boolean tBankCardlist;
    private String images;
    private String token;
    private String userName;
    private String cellPhone;
    private String realName;
    private String bankCardNo;
    private double usableAmount;
    private double total1;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
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
