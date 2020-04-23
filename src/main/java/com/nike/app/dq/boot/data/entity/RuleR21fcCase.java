
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nike.app.dq.boot.util.format.SimpleFormater;
import com.nike.app.dq.boot.web.mvc.bean.form.R21fcRcActionForm;

public class RuleR21fcCase implements Serializable {

	private static final long serialVersionUID = -2067499031603204655L;

	private int ruleCaseId = 0;
	private int connectionId = 0;
	private DsConnection connection = null;
	private String ruleCaseName = null;
	private String ruleCaseOwner = null;
	private String ruleCaseBusinessFunction = null;
	private String ruleCaseDescription = null;
	private String ruleCaseTargetTable = null;
	private String ruleCaseTargetField = null;
	private String ruleCaseTargetConditionField = null;
	private String ruleCaseLastModifiedBy = null;
	private Date ruleCaseLastModifiedDate = null;
	private List<RuleR21fcCaseRunHistory> runHistories = new ArrayList<RuleR21fcCaseRunHistory>();
	@SuppressWarnings("unused")
	private String ruleCaseLastModifiedDateStr = null;
	private String ruleCaseTargetConnectionName = null;
	@SuppressWarnings("unused")
	private String lastRunResult = null;
	@JsonProperty("DT_RowId")
	private String dt_rowid = null;

	public RuleR21fcCase() {
		
	}

	public RuleR21fcCase(int ruleCaseId, String ruleCaseName, String ruleCaseOwner, String ruleCaseBusinessFunction, String ruleCaseDescription, String ruleCaseTargetTable, String ruleCaseTargetField, String ruleCaseTargetConditionField, String ruleCaseLastModifiedBy, Date ruleCaseLastModifiedDate) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseName = ruleCaseName;
		this.ruleCaseOwner = ruleCaseOwner;
		this.ruleCaseBusinessFunction = ruleCaseBusinessFunction;
		this.ruleCaseDescription = ruleCaseDescription;
		this.ruleCaseTargetTable = ruleCaseTargetTable;
		this.ruleCaseTargetField = ruleCaseTargetField;
		this.ruleCaseTargetConditionField = ruleCaseTargetConditionField;
		this.ruleCaseLastModifiedBy = ruleCaseLastModifiedBy;
		this.ruleCaseLastModifiedDate = ruleCaseLastModifiedDate;
	}

	public void toSyn(R21fcRcActionForm form) {
		this.ruleCaseName = (form.getRuleCaseName() != null && !form.getRuleCaseName().equals("")) ? form.getRuleCaseName() : this.ruleCaseName;
		this.ruleCaseOwner = (form.getRuleCaseOwner() != null && !form.getRuleCaseOwner().equals("")) ? form.getRuleCaseOwner().toUpperCase() : this.ruleCaseOwner;
		this.ruleCaseBusinessFunction = (form.getRuleCaseBusinessFunction() != null && !form.getRuleCaseBusinessFunction().equals("")) ? form.getRuleCaseBusinessFunction() : this.ruleCaseBusinessFunction;
		this.ruleCaseDescription = (form.getRuleCaseDescription() != null && !form.getRuleCaseDescription().equals("")) ? form.getRuleCaseDescription() : this.ruleCaseDescription;
		this.ruleCaseTargetTable = (form.getRuleCaseTargetTable() != null && !form.getRuleCaseTargetTable().equals("")) ? form.getRuleCaseTargetTable() : this.ruleCaseTargetTable;
		this.ruleCaseTargetField = (form.getRuleCaseTargetField() != null && !form.getRuleCaseTargetField().equals("")) ? form.getRuleCaseTargetField() : this.ruleCaseTargetField;
		this.ruleCaseTargetConditionField = form.getRuleCaseTargetConditionField();
		this.ruleCaseTargetConnectionName = (form.getRuleCaseTargetConnectionName() != null && !form.getRuleCaseTargetConnectionName().equals("")) ? form.getRuleCaseTargetConnectionName() : this.ruleCaseTargetConnectionName;
	}

	@Override
	public String toString() {
		return "RuleR21fcCase(ruleCaseId=" + ruleCaseId + ",connection=" + connection + ",ruleCaseName=" + ruleCaseName + ",ruleCaseOwner=" + ruleCaseOwner + ",ruleCaseBusinessFunction=" + ruleCaseBusinessFunction + ",ruleCaseDescription=" + ruleCaseDescription + ",ruleCaseTargetTable=" + ruleCaseTargetTable + ",ruleCaseTargetField=" + ruleCaseTargetField + ",ruleCaseTargetConditionField=" + ruleCaseTargetConditionField + ",ruleCaseLastModifiedBy=" + ruleCaseLastModifiedBy + ",ruleCaseLastModifiedDate=" + ruleCaseLastModifiedDate + ",runHistories=" + runHistories + ")";
	}

	public int getRuleCaseId() {
		return ruleCaseId;
	}

	public void setRuleCaseId(int ruleCaseId) {
		this.ruleCaseId = ruleCaseId;
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public DsConnection getConnection() {
		return connection;
	}

	public void setConnection(DsConnection connection) {
		this.connection = connection;
	}

	public String getRuleCaseName() {
		return ruleCaseName;
	}

	public void setRuleCaseName(String ruleCaseName) {
		this.ruleCaseName = ruleCaseName;
	}

	public String getRuleCaseOwner() {
		return ruleCaseOwner;
	}

	public void setRuleCaseOwner(String ruleCaseOwner) {
		this.ruleCaseOwner = ruleCaseOwner;
	}

	public String getRuleCaseBusinessFunction() {
		return ruleCaseBusinessFunction;
	}

	public void setRuleCaseBusinessFunction(String ruleCaseBusinessFunction) {
		this.ruleCaseBusinessFunction = ruleCaseBusinessFunction;
	}

	public String getRuleCaseDescription() {
		return ruleCaseDescription;
	}

	public void setRuleCaseDescription(String ruleCaseDescription) {
		this.ruleCaseDescription = ruleCaseDescription;
	}

	public String getRuleCaseTargetTable() {
		return ruleCaseTargetTable;
	}

	public void setRuleCaseTargetTable(String ruleCaseTargetTable) {
		this.ruleCaseTargetTable = ruleCaseTargetTable;
	}

	public String getRuleCaseTargetField() {
		return ruleCaseTargetField;
	}

	public void setRuleCaseTargetField(String ruleCaseTargetField) {
		this.ruleCaseTargetField = ruleCaseTargetField;
	}

	public String getRuleCaseTargetConditionField() {
		return ruleCaseTargetConditionField;
	}

	public void setRuleCaseTargetConditionField(String ruleCaseTargetConditionField) {
		this.ruleCaseTargetConditionField = ruleCaseTargetConditionField;
	}

	public String getRuleCaseLastModifiedBy() {
		return ruleCaseLastModifiedBy;
	}

	public void setRuleCaseLastModifiedBy(String ruleCaseLastModifiedBy) {
		this.ruleCaseLastModifiedBy = ruleCaseLastModifiedBy;
	}

	public Date getRuleCaseLastModifiedDate() {
		return ruleCaseLastModifiedDate;
	}

	public void setRuleCaseLastModifiedDate(Date ruleCaseLastModifiedDate) {
		this.ruleCaseLastModifiedDate = ruleCaseLastModifiedDate;
	}

	public String getRuleCaseLastModifiedDateStr() {
		return SimpleFormater.simpleDatetimeFormate(ruleCaseLastModifiedDate);
	}

	public void setRuleCaseLastModifiedDateStr(String ruleCaseLastModifiedDateStr) {
		this.ruleCaseLastModifiedDateStr = ruleCaseLastModifiedDateStr;
	}

	public List<RuleR21fcCaseRunHistory> getRunHistories() {
		return runHistories;
	}

	public void setRunHistories(List<RuleR21fcCaseRunHistory> runHistories) {
		this.runHistories = runHistories;
	}

	public String getRuleCaseTargetConnectionName() {
		return connection.getConnectionName();
	}

	public void setRuleCaseTargetConnectionName(String ruleCaseTargetConnectionName) {
		this.ruleCaseTargetConnectionName = ruleCaseTargetConnectionName;
	}

	public String getLastRunResult() {
		return (runHistories.size() != 0) ? runHistories.get(0).getRuleCaseRunResult() : "PASSED";
	}

	public void setLastRunResult(String lastRunResult) {
		this.lastRunResult = lastRunResult;
	}

	@Transient
	public String getDt_rowid() {
		return "row_" + ruleCaseId;
	}

	public void setDt_rowid(String dt_rowid) {
		this.dt_rowid = dt_rowid;
	}
}