package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2016/12/30.
 */

public class IntroduceBean {

    /**
     * state : {"info":"取值成功","status":"0"}
     * data : <p>uyiuyhiuyhiuyhi</p>
     */

    private StateBean state;
    private String data;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
}
