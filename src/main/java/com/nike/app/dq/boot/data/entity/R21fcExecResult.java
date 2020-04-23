
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.Date;

public class R21fcExecResult implements Serializable {

	private static final long serialVersionUID = 1312916181521116686L;

	private Date execResult = null;

	public R21fcExecResult() {

	}

	public Date getExecResult() {
		return execResult;
	}

	public void setExecResult(Date execResult) {
		this.execResult = execResult;
	}
}