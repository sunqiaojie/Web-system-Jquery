package com.lq.common.util.plugin.easyui.datagrid;

import java.io.Serializable;
import java.util.List;

public class PageDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** 总记录数 */
	private long total;
	
	/** 记录 */
	private List<?> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	

}
