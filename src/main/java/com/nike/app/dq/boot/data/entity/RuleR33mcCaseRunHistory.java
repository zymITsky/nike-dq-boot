
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.nike.app.dq.boot.util.format.SimpleFormater;

public class RuleR33mcCaseRunHistory implements Serializable {

	private static final long serialVersionUID = 2883211294238579163L;

	private int ruleCaseRunId = 0;
	private int ruleCaseId = 0;
	private String ruleCaseRunResult = null;
	private long ruleCaseSourceMeasureValue = 0L;
	private long ruleCaseTargetMeasureValue = 0L;
	private int ruleCaseDiffPcnt = 0;
	private int ruleCaseTolerance = 0;
	private Date ruleCaseRunStartDate = null;
	private Date ruleCaseRunEndDate = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunStartDateStr = null;
	@SuppressWarnings("unused")
	private String ruleCaseRunEndDateStr = null;

	public RuleR33mcCaseRunHistory() {
		
	}

	public RuleR33mcCaseRunHistory(int ruleCaseId, String ruleCaseRunResult, long ruleCaseSourceMeasureValue, long ruleCaseTargetMeasureValue, int ruleCaseDiffPcnt, int ruleCaseTolerance, Date ruleCaseRunStartDate, Date ruleCaseRunEndDate) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseRunResult = ruleCaseRunResult;
		this.ruleCaseSourceMeasureValue = ruleCaseSourceMeasureValue;
		this.ruleCaseTargetMeasureValue = ruleCaseTargetMeasureValue;
		this.ruleCaseDiffPcnt = ruleCaseDiffPcnt;
		this.ruleCaseTolerance = ruleCaseTolerance;
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

	public long getRuleCaseSourceMeasureValue() {
		return ruleCaseSourceMeasureValue;
	}

	public void setRuleCaseSourceMeasureValue(long ruleCaseSourceMeasureValue) {
		this.ruleCaseSourceMeasureValue = ruleCaseSourceMeasureValue;
	}

	public long getRuleCaseTargetMeasureValue() {
		return ruleCaseTargetMeasureValue;
	}

	public void setRuleCaseTargetMeasureValue(long ruleCaseTargetMeasureValue) {
		this.ruleCaseTargetMeasureValue = ruleCaseTargetMeasureValue;
	}

	public int getRuleCaseDiffPcnt() {
		return ruleCaseDiffPcnt;
	}

	public void setRuleCaseDiffPcnt(int ruleCaseDiffPcnt) {
		this.ruleCaseDiffPcnt = ruleCaseDiffPcnt;
	}

	public int getRuleCaseTolerance() {
		return ruleCaseTolerance;
	}

	public void setRuleCaseTolerance(int ruleCaseTolerance) {
		this.ruleCaseTolerance = ruleCaseTolerance;
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