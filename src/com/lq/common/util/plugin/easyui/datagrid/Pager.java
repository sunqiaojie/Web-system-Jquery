package com.lq.common.util.plugin.easyui.datagrid;

import java.io.Serializable;

public class Pager implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 当前页 */
	private int page = 1;
	
	/** 每页记录数 */
	private int rows = 10;
	
	/** 总数 */
	private int total = 0;
	
	/** 排序字段 */
	private String sort = "";
	
	/** 排序方式 */
	private String order = "";
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
