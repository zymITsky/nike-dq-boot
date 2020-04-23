
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;

public class QryStrExecResult implements Serializable {

	private static final long serialVersionUID = 7952082187848340853L;

	private int execResult = 0;

	public QryStrExecResult() {

	}

	public int getExecResult() {
		return execResult;
	}

	public void setExecResult(int execResult) {
		this.execResult = execResult;
	}
}