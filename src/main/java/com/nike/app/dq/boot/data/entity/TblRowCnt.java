
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;

public class TblRowCnt implements Serializable {

	private static final long serialVersionUID = -5341285537695188556L;

	private int rowCount = 0;

	public TblRowCnt() {

	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
}