package com.enduo.ndonline.bean;

public class InfoMsg {
	/**
	 * info: 输出提示信息;
	 */
	private String info;
	/**
	 * status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败,"o"表示中间状态,"j"表示session超时
	 */
	private String status;
	
	/**
	 * value: 值;
	 */
	private String value;
	/**
	 * 数据
	 */
	private Object datas;
	public InfoMsg() {
		
	}
	
	public InfoMsg(String info, String status) {
		super();
		this.info = info;
		this.status = status;
	}
	
	

	public InfoMsg(String info, String status, String value) {
		this(info,status);
		this.value = value;
	}
	
	public InfoMsg(String info, String status, Object datas) {
		this(info,status);
		this.datas = datas;
	}



	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}



	public Object getDatas() {
		return datas;
	}



	public void setDatas(Object datas) {
		this.datas = datas;
	}
	
}
