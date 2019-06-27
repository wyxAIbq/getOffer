package com.library.util;

import java.util.List;
public class JqgridPageResp<T> {

	/**
	 * 总页数
	 */
	private Integer total = 0;
	
	/**
	 * 当前页
	 */
	private Integer page = 0;
	
	/**
	 * 本次查询总记录数
	 */
	private Integer recordsInteger = 0;

	/**
	 * 结果集
	 */
	private List<T> rows;
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total){
		this.total = total;
	}
	
	public Integer getPage(){
		return page;
	}
	
	public void setPage(Integer page){
		this.page = page;
	}
	
	public Integer getRecordsInteger() {
		return recordsInteger;
	}

	public void setRecordsInteger(Integer recordsInteger) {
		this.recordsInteger = recordsInteger;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
