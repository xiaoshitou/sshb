package com.yyb.manager.pageModel;
import java.util.ArrayList;
import java.util.List;
/**
 * 存放easyUI--DataGrid控件 必须的参数
 * @author yyb
 *
 */
public class DataGrid {

	private Long total = 0L;
	private List rows = new ArrayList();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
