package com.enduo.ndonline.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class InterestBean implements Serializable {

    private StateBean state;
    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

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
    public static class DataBean implements Serializable{
        private String content;
        private String createTime;
        private String id;
        private String src;
        private String title;
        private String srcImgPath;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSrcImgPath() {
            return srcImgPath;
        }

        public void setSrcImgPath(String srcImgPath) {
            this.srcImgPath = srcImgPath;
        }
    }

    @Override
    public String toString() {
        return "InterestBean{" +
                "state=" + state +
                '}';
    }
}
