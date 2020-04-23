
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.nike.app.dq.boot.util.format.SimpleFormater;

public class RuleR21fcCaseRunHistory implements Serializable {

	private static final long serialVersionUID = -8880965492673564492L;

	private int ruleCaseRunId = 0;
	private int ruleCaseId = 0;
	private String ruleCaseRunResult = null;
	private String ruleCaseTargetMeasureValue = null;
	private int ruleCaseTolerance = 0;
	private int ruleCaseDifference = 0;
	private Date ruleCaseRunStartDate = null;
	private Date ruleCaseRunEndDate = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunStartDateStr = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunEndDateStr = null;

	public RuleR21fcCaseRunHistory() {
		
	}

	public RuleR21fcCaseRunHistory(int ruleCaseId, String ruleCaseRunResult, String ruleCaseTargetMeasureValue, int ruleCaseTolerance, int ruleCaseDifference, Date ruleCaseRunStartDate, Date ruleCaseRunEndDate) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseRunResult = ruleCaseRunResult;
		this.ruleCaseTargetMeasureValue = ruleCaseTargetMeasureValue;
		this.ruleCaseTolerance = ruleCaseTolerance;
		this.ruleCaseDifference = ruleCaseDifference;
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

	public String getRuleCaseRunResult() {
		return ruleCaseRunResult;
	}

	public void setRuleCaseRunResult(String ruleCaseRunRows) {
		this.ruleCaseRunResult = ruleCaseRunRows;
	}

	public String getRuleCaseTargetMeasureValue() {
		return ruleCaseTargetMeasureValue;
	}

	public void setRuleCaseTargetMeasureValue(String ruleCaseTargetMeasureValue) {
		this.ruleCaseTargetMeasureValue = ruleCaseTargetMeasureValue;
	}

	public int getRuleCaseTolerance() {
		return ruleCaseTolerance;
	}

	public void setRuleCaseTolerance(int ruleCaseTolerance) {
		this.ruleCaseTolerance = ruleCaseTolerance;
	}

	public int getRuleCaseDifference() {
		return ruleCaseDifference;
	}

	public void setRuleCaseDifference(int ruleCaseDifference) {
		this.ruleCaseDifference = ruleCaseDifference;
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