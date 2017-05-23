package com.enduo.ndonline.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/3.
 */

public class CaseFlowBean {

    /**
     * state : {"info":"取值成功","status":"0"}
     * data : [{"createTime":1483439508000,"frozenAmount":7130,"fundMode":"投资冻结","fundType":6,"id":718,"inAmount":0,"operAmount":1000,"operType":2,"outAmount":1000,"remarks":"投资了项目[测试aaa]，冻结投资金额为1000.00元。","usableAmount":9992870,"userId":605},{"createTime":1483439043000,"frozenAmount":7030,"fundMode":"投资冻结","fundType":6,"id":717,"inAmount":0,"operAmount":100,"operType":2,"outAmount":100,"remarks":"投资了项目[测试aaa]，冻结投资金额为100.00元。","usableAmount":9992970,"userId":605},{"createTime":1483438965000,"frozenAmount":6930,"fundMode":"投资冻结","fundType":6,"id":716,"inAmount":0,"operAmount":100,"operType":2,"outAmount":100,"remarks":"投资了项目[测试aaa]，冻结投资金额为100.00元。","usableAmount":9993070,"userId":605},{"createTime":1483438959000,"frozenAmount":6830,"fundMode":"投资冻结","fundType":6,"id":715,"inAmount":0,"operAmount":100,"operType":2,"outAmount":100,"remarks":"投资了项目[测试aaa]，冻结投资金额为100.00元。","usableAmount":9993170,"userId":605},{"createTime":1483438850000,"frozenAmount":5530,"fundMode":"投资冻结","fundType":6,"id":713,"inAmount":0,"operAmount":1300,"operType":2,"outAmount":1300,"remarks":"投资了项目[月月赚000]，冻结投资金额为1300.00元。","usableAmount":9994470,"userId":605},{"createTime":1483438075000,"frozenAmount":5300,"fundMode":"投资冻结","fundType":6,"id":712,"inAmount":0,"operAmount":230,"operType":2,"outAmount":230,"remarks":"投资了项目[测试aaa]，冻结投资金额为230.00元。","usableAmount":9994700,"userId":605},{"createTime":1483437804000,"frozenAmount":300,"fundMode":"投资冻结","fundType":6,"id":711,"inAmount":0,"operAmount":5000,"operType":2,"outAmount":5000,"remarks":"投资了项目[测试aaa]，冻结投资金额为5000.00元。","usableAmount":9999700,"userId":605},{"createTime":1483428202000,"frozenAmount":200,"fundMode":"投资冻结","fundType":6,"id":701,"inAmount":0,"operAmount":100,"operType":2,"outAmount":100,"remarks":"投资了项目[月月赚000]，冻结投资金额为100.00元。","usableAmount":9999800,"userId":605},{"createTime":1483427691000,"frozenAmount":100,"fundMode":"投资冻结","fundType":6,"id":699,"inAmount":0,"operAmount":100,"operType":2,"outAmount":100,"remarks":"投资了项目[月月赚000]，冻结投资金额为100.00元。","usableAmount":9999900,"userId":605},{"createTime":1483427663000,"frozenAmount":0,"fundMode":"投资冻结","fundType":6,"id":698,"inAmount":0,"operAmount":100,"operType":2,"outAmount":100,"remarks":"投资了项目[月月赚000]，冻结投资金额为100.00元。","usableAmount":1.0E7,"userId":605}]
     * page : {"endRow":10,"firstPage":true,"hasNextPage":false,"hasPrePage":false,"lastPage":true,"limit":10,"nextPage":1,"offset":0,"page":1,"prePage":1,"slider":[1],"startRow":1,"totalCount":10,"totalPages":1}
     */

    private StateBean state;
    private PageBean page;
    private List<DataBean> data;

    public StateBean getState() {
        return state;
    }

    public void setState(StateBean state) {
        this.state = state;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public static class PageBean {
        /**
         * endRow : 10
         * firstPage : true
         * hasNextPage : false
         * hasPrePage : false
         * lastPage : true
         * limit : 10
         * nextPage : 1
         * offset : 0
         * page : 1
         * prePage : 1
         * slider : [1]
         * startRow : 1
         * totalCount : 10
         * totalPages : 1
         */

        private int endRow;
        private boolean firstPage;
        private boolean hasNextPage;
        private boolean hasPrePage;
        private boolean lastPage;
        private int limit;
        private int nextPage;
        private int offset;
        private int page;
        private int prePage;
        private int startRow;
        private int totalCount;
        private int totalPages;
        private List<Integer> slider;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPrePage() {
            return hasPrePage;
        }

        public void setHasPrePage(boolean hasPrePage) {
            this.hasPrePage = hasPrePage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<Integer> getSlider() {
            return slider;
        }

        public void setSlider(List<Integer> slider) {
            this.slider = slider;
        }
    }

    public static class DataBean {
        /**
         * createTime : 1483439508000
         * frozenAmount : 7130.0
         * fundMode : 投资冻结
         * fundType : 6
         * id : 718
         * inAmount : 0.0
         * operAmount : 1000.0
         * operType : 2
         * outAmount : 1000.0
         * remarks : 投资了项目[测试aaa]，冻结投资金额为1000.00元。
         * usableAmount : 9992870.0
         * userId : 605
         */

        private long createTime;
        private double frozenAmount;
        private String fundMode;
        private int fundType;
        private int id;
        private double inAmount;
        private double operAmount;
        private int operType;
        private double outAmount;
        private String remarks;
        private double usableAmount;
        private int userId;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public double getFrozenAmount() {
            return frozenAmount;
        }

        public void setFrozenAmount(double frozenAmount) {
            this.frozenAmount = frozenAmount;
        }

        public String getFundMode() {
            return fundMode;
        }

        public void setFundMode(String fundMode) {
            this.fundMode = fundMode;
        }

        public int getFundType() {
            return fundType;
        }

        public void setFundType(int fundType) {
            this.fundType = fundType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getInAmount() {
            return inAmount;
        }

        public void setInAmount(double inAmount) {
            this.inAmount = inAmount;
        }

        public double getOperAmount() {
            return operAmount;
        }

        public void setOperAmount(double operAmount) {
            this.operAmount = operAmount;
        }

        public int getOperType() {
            return operType;
        }

        public void setOperType(int operType) {
            this.operType = operType;
        }

        public double getOutAmount() {
            return outAmount;
        }

        public void setOutAmount(double outAmount) {
            this.outAmount = outAmount;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public double getUsableAmount() {
            return usableAmount;
        }

        public void setUsableAmount(double usableAmount) {
            this.usableAmount = usableAmount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
