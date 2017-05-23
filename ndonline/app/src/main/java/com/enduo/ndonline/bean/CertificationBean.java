package com.enduo.ndonline.bean;

/**
 *   实名信息
 * Created by Administrator on 2017/1/4.
 */

public class CertificationBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * tPerson : {"cardNo":"410104197611119836","createTime":1483446245000,"id":472,"isAuth":1,"realName":"汪灶声","remark":"app端默认成功","sex":1,"userId":605}
     */

    private StateBean state;
    private TPersonBean tPerson;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public TPersonBean getTPerson() {
        return tPerson;
    }

    public void setTPerson(TPersonBean tPerson) {
        this.tPerson = tPerson;
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

    public static class TPersonBean {
        /**
         * cardNo : 410104197611119836
         * createTime : 1483446245000
         * id : 472
         * isAuth : 1
         * realName : 汪灶声
         * remark : app端默认成功
         * sex : 1
         * userId : 605
         */

        private String cardNo;
        private long createTime;
        private int id;
        private int isAuth;
        private String realName;
        private String remark;
        private int sex;
        private int userId;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsAuth() {
            return isAuth;
        }

        public void setIsAuth(int isAuth) {
            this.isAuth = isAuth;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
