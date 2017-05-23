package com.enduo.ndonline.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MyMessgeBean implements Serializable{

    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"createTime":1483515780000,"id":549,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":547,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":546,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":545,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":544,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":543,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":542,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":541,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":539,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569},{"createTime":1483515780000,"id":536,"isDelete":0,"messageContent":"您投资的项目[月月赚000]投标失败,解冻金额为100.00元。","messageTitle":"投标失败","messageType":1,"receiverId":569}]
     * page : {"endRow":10,"firstPage":true,"hasNextPage":true,"hasPrePage":false,"lastPage":false,"limit":10,"nextPage":2,"offset":0,"page":1,"prePage":1,"slider":[1,2,3,4,5],"startRow":1,"totalCount":43,"totalPages":5}
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

    public static class StateBean implements Serializable{
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



    public static class DataBean implements Serializable {
        /**
         * createTime : 1483515780000
         * id : 549
         * isDelete : 0
         * messageContent : 您投资的项目[月月赚000]投标失败,解冻金额为100.00元。
         * messageTitle : 投标失败
         * messageType : 1
         * receiverId : 569
         */

        private long createTime;
        private int id;
        private int isDelete;
        private String messageContent;
        private String messageTitle;
        private int messageType;
        private int receiverId;

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

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        public int getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(int receiverId) {
            this.receiverId = receiverId;
        }
    }
}
