package com.enduo.ndonline.bean;

/**
 * Created by Administrator on 2017/1/8.
 */

public class ChagerBean {

    /**
     * state : {"info":"请求支付成功","status":"66"}
     * data : https://mp.cspaying.com/aps/cloudplatform/payChannel/qpay/3c016b9529b967466bf1a6b61afe6646.html
     * orderId : 20170108145555747224528
     */

    private StateBean state;
    private String data;
    private String orderId;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static class StateBean {
        /**
         * info : 请求支付成功
         * status : 66
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
