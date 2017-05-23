package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2017/1/3.
 */

public class MsgBean {


    public MsgBean(String info, int status) {
        this.state.info = info;
        this.state.status = status;
        setState(state);
    }

    private StateBean state;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
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
