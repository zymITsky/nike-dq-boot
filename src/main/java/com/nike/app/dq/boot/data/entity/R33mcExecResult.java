
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;

public class R33mcExecResult implements Serializable {

	private static final long serialVersionUID = 3812623399261011588L;

	private long execResult = 0L;

	public R33mcExecResult() {

	}

	public long getExecResult() {
		return execResult;
	}

	public void setExecResult(long execResult) {
		this.execResult = execResult;
	}
}