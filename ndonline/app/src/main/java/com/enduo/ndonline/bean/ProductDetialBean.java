package com.enduo.ndonline.bean;

import java.util.List;

/**
 *   产品详情
 * Created by Administrator on 2016/12/30.
 */

public class ProductDetialBean {


    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"attrName":"gregeg","attrPath":"upload/images/about-us-06.jpg","attrType":1,"borrowId":228,"createTime":1480901243000,"id":32}]
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
         * attrName : gregeg
         * attrPath : upload/images/about-us-06.jpg
         * attrType : 1
         * borrowId : 228
         * createTime : 1480901243000
         * id : 32
         */

        private String attrName;
        private String attrPath;
        private int attrType;
        private int borrowId;
        private long createTime;
        private int id;

        public String getAttrName() {
            return attrName;
        }

        public void setAttrName(String attrName) {
            this.attrName = attrName;
        }

        public String getAttrPath() {
            return attrPath;
        }

        public void setAttrPath(String attrPath) {
            this.attrPath = attrPath;
        }

        public int getAttrType() {
            return attrType;
        }

        public void setAttrType(int attrType) {
            this.attrType = attrType;
        }

        public int getBorrowId() {
            return borrowId;
        }

        public void setBorrowId(int borrowId) {
            this.borrowId = borrowId;
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
    }
}
