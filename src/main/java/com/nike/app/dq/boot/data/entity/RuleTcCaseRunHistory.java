
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.nike.app.dq.boot.util.format.SimpleFormater;

public class RuleTcCaseRunHistory implements Serializable {

	private static final long serialVersionUID = 313374776242573553L;

	private int ruleCaseRunId = 0;
	private int ruleCaseId = 0;
	private int ruleCaseRunRows = 0;
	private Date ruleCaseRunStartDate = null;
	private Date ruleCaseRunEndDate = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunStartDateStr = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunEndDateStr = null;

	public RuleTcCaseRunHistory() {
		
	}

	public int getRuleCaseRunId() {
		return ruleCaseRunId;
	}

	public void setRuleCaseRunId(int ruleCaseRunId) {
		this.ruleCaseRunId = ruleCaseRunId;
	}

	public int getRuleCaseId() {
		return ruleCaseId;
	}

	public void setRuleCaseId(int ruleCaseId) {
		this.ruleCaseId = ruleCaseId;
	}

	public int getRuleCaseRunRows() {
		return ruleCaseRunRows;
	}

	public void setRuleCaseRunRows(int ruleCaseRunRows) {
		this.ruleCaseRunRows = ruleCaseRunRows;
	}

	public Date getRuleCaseRunStartDate() {
		return ruleCaseRunStartDate;
	}

	public void setRuleCaseRunStartDate(Date ruleCaseRunStartDate) {
		this.ruleCaseRunStartDate = ruleCaseRunStartDate;
	}

	public Date getRuleCaseRunEndDate() {
		return ruleCaseRunEndDate;
	}

	public void setRuleCaseRunEndDate(Date ruleCaseRunEndDate) {
		this.ruleCaseRunEndDate = ruleCaseRunEndDate;
	}

	public String getRuleCaseRunStartDateStr() {
		return SimpleFormater.simpleDatetimeFormate(ruleCaseRunStartDate);
	}

	public void setRuleCaseRunStartDateStr(String ruleCaseRunStartDateStr) {
		this.ruleCaseRunStartDateStr = ruleCaseRunStartDateStr;
	}

	public String getRuleCaseRunEndDateStr() {
		return SimpleFormater.simpleDatetimeFormate(ruleCaseRunEndDate);
	}

	public void setRuleCaseRunEndDateStr(String ruleCaseRunEndDateStr) {
		this.ruleCaseRunEndDateStr = ruleCaseRunEndDateStr;
	}
}