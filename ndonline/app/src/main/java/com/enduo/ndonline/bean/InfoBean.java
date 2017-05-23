package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2017/1/2.
 */

public class InfoBean {

    /**
     * state : {"info":"实名成功","status":"0"}
     */

    private StateBean state;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public static class StateBean {
        /**
         * info : 实名成功
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
