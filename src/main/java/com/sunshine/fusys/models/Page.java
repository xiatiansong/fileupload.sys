package com.sunshine.fusys.models;

import java.util.List;
import java.util.Map;

/**
 * 创建人：江智勇 <br>
 * 创建时间：2011-11-30 <br>
 * 功能描述：列表页面对象<br>
 */
public class Page extends BaseDTO {

	private static final long serialVersionUID = 2392597298951896149L;

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 总页数
	 */
	private int total;

	/**
	 * 页大小
	 */
	private int rows;

	/**
	 * 总条数
	 */
	private int records;

	/**
	 * 返回状态
	 */
	private int state;
	/**
	 * 数据列表
	 */
	private List<?> dataRows = null;
	private Map<?, ?> userdata = null;

	 

	public Map<?, ?> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<?, ?> userdata) {
		this.userdata = userdata;
	}

	public Page() {
	}

	/**
	 * @param page
	 *            当前页
	 * @param rows
	 *            页大小
	 * @param total
	 *            总页数
	 * @param records
	 *            总记录数
	 * @param dataRows
	 *            数据列表
	 */
	public Page(int page, int rows, int total, int records, List<?> dataRows ) {
		this.page = page;
		this.rows = rows;
		this.total = total;
		this.records = records;
		this.dataRows = dataRows;
	}

	
	public Page(int page, int rows, int total, int records, List<?> dataRows,Map<?, ?> userdata) {
		this.page = page;
		this.rows = rows;
		this.total = total;
		this.records = records;
		this.dataRows = dataRows;
		this.userdata = userdata;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<?> getDataRows() {
		return dataRows;
	}

	public void setDataRows(List<?> dataRows) {
		this.dataRows = dataRows;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public final int getState() {
		return state;
	}

	public final void setState(int state) {
		this.state = state;
	}
	
}
