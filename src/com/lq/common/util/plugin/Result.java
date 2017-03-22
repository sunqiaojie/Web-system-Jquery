package com.lq.common.util.plugin;

import java.io.Serializable;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String status;//状态值
	private Object data;//数据
	private String msg;//消息
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
