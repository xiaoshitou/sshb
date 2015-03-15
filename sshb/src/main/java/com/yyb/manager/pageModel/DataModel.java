package com.yyb.manager.pageModel;

import javax.persistence.Transient;
/**
 * 封装页面List展示所需属性
 * @author yyb
 *
 */
public class DataModel {
	
	private int page;
	
	private int rows;
	
	private String sort;
	
	private String order;
	
	private String ids;

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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
}
