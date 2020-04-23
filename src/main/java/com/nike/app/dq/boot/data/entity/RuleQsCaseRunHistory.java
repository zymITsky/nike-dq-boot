
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.nike.app.dq.boot.util.format.SimpleFormater;

public class RuleQsCaseRunHistory implements Serializable {

	private static final long serialVersionUID = -8880965492673564492L;

	private int ruleCaseRunId = 0;
	private int ruleCaseId = 0;
	private int ruleCaseRunResult = 0;
	private Date ruleCaseRunStartDate = null;
	private Date ruleCaseRunEndDate = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunStartDateStr = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunEndDateStr = null;

	public RuleQsCaseRunHistory() {
		
	}

	public RuleQsCaseRunHistory(int ruleCaseId, int ruleCaseRunResult, Date ruleCaseRunStartDate, Date ruleCaseRunEndDate) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseRunResult = ruleCaseRunResult;
		this.ruleCaseRunStartDate = ruleCaseRunStartDate;
		this.ruleCaseRunEndDate = ruleCaseRunEndDate;
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

	public int getRuleCaseRunResult() {
		return ruleCaseRunResult;
	}

	public void setRuleCaseRunResult(int ruleCaseRunRows) {
		this.ruleCaseRunResult = ruleCaseRunRows;
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