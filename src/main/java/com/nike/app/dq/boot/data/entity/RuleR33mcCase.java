
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nike.app.dq.boot.util.format.SimpleFormater;
import com.nike.app.dq.boot.web.mvc.bean.form.R33mcRcActionForm;

public class RuleR33mcCase implements Serializable {

	private static final long serialVersionUID = -5360236495291265817L;

	private int ruleCaseId = 0;
	private String ruleCaseName = null;
	private String ruleCaseOwner = null;
	private String ruleCaseBusinessFunction = null;
	private String ruleCaseDescription = null;
	private int connectionSrcId = 0;
	private DsConnection connectionSrc = null;
	private String ruleCaseSourceTable = null;
	private String ruleCaseSourceField = null;
	private String ruleCaseSourceConditionField = null;
	private int connectionTgtId = 0;
	private DsConnection connectionTgt = null;
	private String ruleCaseTargetTable = null;
	private String ruleCaseTargetField = null;
	private String ruleCaseTargetConditionField = null;
	private String ruleCaseLastModifiedBy = null;
	private Date ruleCaseLastModifiedDate = null;
	private List<RuleR33mcCaseRunHistory> runHistories = new ArrayList<RuleR33mcCaseRunHistory>();
	@SuppressWarnings("unused")
	private String ruleCaseLastModifiedDateStr = null;
	private String ruleCaseSourceConnectionName = null;
	private String ruleCaseTargetConnectionName = null;
	@SuppressWarnings("unused")
	private String lastRunResult = null;
	@JsonProperty("DT_RowId")
	private String dt_rowid = null;

	public RuleR33mcCase() {
		
	}

	public RuleR33mcCase(int ruleCaseId, String ruleCaseName, String ruleCaseOwner, String ruleCaseBusinessFunction, String ruleCaseDescription, String ruleCaseSourceTable, String ruleCaseSourceField, String ruleCaseSourceConditionField, String ruleCaseTargetTable, String ruleCaseTargetField, String ruleCaseTargetConditionField, String ruleCaseLastModifiedBy, Date ruleCaseLastModifiedDate) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseName = ruleCaseName;
		this.ruleCaseOwner = ruleCaseOwner;
		this.ruleCaseBusinessFunction = ruleCaseBusinessFunction;
		this.ruleCaseDescription = ruleCaseDescription;
		this.ruleCaseSourceTable = ruleCaseSourceTable;
		this.ruleCaseSourceField = ruleCaseSourceField;
		this.ruleCaseSourceConditionField = ruleCaseSourceConditionField;
		this.ruleCaseTargetTable = ruleCaseTargetTable;
		this.ruleCaseTargetField = ruleCaseTargetField;
		this.ruleCaseTargetConditionField = ruleCaseTargetConditionField;
		this.ruleCaseLastModifiedBy = ruleCaseLastModifiedBy;
		this.ruleCaseLastModifiedDate = ruleCaseLastModifiedDate;
	}

	public void toSyn(R33mcRcActionForm form) {
		this.ruleCaseName = (form.getRuleCaseName() != null && !form.getRuleCaseName().equals("")) ? form.getRuleCaseName() : this.ruleCaseName;
		this.ruleCaseOwner = (form.getRuleCaseOwner() != null && !form.getRuleCaseOwner().equals("")) ? form.getRuleCaseOwner() : this.ruleCaseOwner;
		this.ruleCaseBusinessFunction = (form.getRuleCaseBusinessFunction() != null && !form.getRuleCaseBusinessFunction().equals("")) ? form.getRuleCaseBusinessFunction() : this.ruleCaseBusinessFunction;
		this.ruleCaseDescription = (form.getRuleCaseDescription() != null && !form.getRuleCaseDescription().equals("")) ? form.getRuleCaseDescription() : this.ruleCaseDescription;
		this.ruleCaseSourceTable = (form.getRuleCaseSourceTable() != null && !form.getRuleCaseSourceTable().equals("")) ? form.getRuleCaseSourceTable() : this.ruleCaseSourceTable;
		this.ruleCaseSourceField = (form.getRuleCaseSourceField() != null && !form.getRuleCaseSourceField().equals("")) ? form.getRuleCaseSourceField() : this.ruleCaseSourceField;
		this.ruleCaseSourceConditionField = form.getRuleCaseSourceConditionField();
		this.ruleCaseTargetTable = (form.getRuleCaseTargetTable() != null && !form.getRuleCaseTargetTable().equals("")) ? form.getRuleCaseTargetTable() : this.ruleCaseTargetTable;
		this.ruleCaseTargetField = (form.getRuleCaseTargetField() != null && !form.getRuleCaseTargetField().equals("")) ? form.getRuleCaseTargetField() : this.ruleCaseTargetField;
		this.ruleCaseTargetConditionField = form.getRuleCaseTargetConditionField();
		this.ruleCaseSourceConnectionName = (form.getRuleCaseSourceConnectionName() != null && !form.getRuleCaseSourceConnectionName().equals("")) ? form.getRuleCaseSourceConnectionName() : this.ruleCaseSourceConnectionName;
		this.ruleCaseTargetConnectionName = (form.getRuleCaseTargetConnectionName() != null && !form.getRuleCaseTargetConnectionName().equals("")) ? form.getRuleCaseTargetConnectionName() : this.ruleCaseTargetConnectionName;
	}

	@Override
	public String toString() {
		return "RuleR33mcCase(ruleCaseId=" + ruleCaseId + ",ruleCaseName=" + ruleCaseName + ",ruleCaseOwner=" + ruleCaseOwner + ",ruleCaseBusinessFunction=" + ruleCaseBusinessFunction + ",ruleCaseDescription=" + ruleCaseDescription + ",connectionSrc=" + connectionSrc + ",ruleCaseSourceTable=" + ruleCaseSourceTable + ",ruleCaseSourceField=" + ruleCaseSourceField + ",ruleCaseSourceConditionField=" + ruleCaseSourceConditionField + ",connectionTgt=" + connectionTgt + ",ruleCaseTargetTable=" + ruleCaseTargetTable + ",ruleCaseTargetField=" + ruleCaseTargetField + ",ruleCaseTargetConditionField=" + ruleCaseTargetConditionField + ",ruleCaseLastModifiedBy=" + ruleCaseLastModifiedBy + ",ruleCaseLastModifiedDate=" + ruleCaseLastModifiedDate + ",runHistories=" + runHistories + ")";
	}

	public int getRuleCaseId() {
		return ruleCaseId;
	}

	public void setRuleCaseId(int ruleCaseId) {
		this.ruleCaseId = ruleCaseId;
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

	public int getConnectionSrcId() {
		return connectionSrcId;
	}

	public void setConnectionSrcId(int connectionSrcId) {
		this.connectionSrcId = connectionSrcId;
	}

	public DsConnection getConnectionSrc() {
		return connectionSrc;
	}

	public void setConnectionSrc(DsConnection connectionSrc) {
		this.connectionSrc = connectionSrc;
	}

	public String getRuleCaseSourceTable() {
		return ruleCaseSourceTable;
	}

	public void setRuleCaseSourceTable(String ruleCaseSourceTable) {
		this.ruleCaseSourceTable = ruleCaseSourceTable;
	}

	public String getRuleCaseSourceField() {
		return ruleCaseSourceField;
	}

	public void setRuleCaseSourceField(String ruleCaseSourceField) {
		this.ruleCaseSourceField = ruleCaseSourceField;
	}

	public String getRuleCaseSourceConditionField() {
		return ruleCaseSourceConditionField;
	}

	public void setRuleCaseSourceConditionField(String ruleCaseSourceConditionField) {
		this.ruleCaseSourceConditionField = ruleCaseSourceConditionField;
	}

	public int getConnectionTgtId() {
		return connectionTgtId;
	}

	public void setConnectionTgtId(int connectionTgtId) {
		this.connectionTgtId = connectionTgtId;
	}

	public DsConnection getConnectionTgt() {
		return connectionTgt;
	}

	public void setConnectionTgt(DsConnection connectionTgt) {
		this.connectionTgt = connectionTgt;
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

	public List<RuleR33mcCaseRunHistory> getRunHistories() {
		return runHistories;
	}

	public void setRunHistories(List<RuleR33mcCaseRunHistory> runHistories) {
		this.runHistories = runHistories;
	}

	public String getRuleCaseSourceConnectionName() {
		return connectionSrc.getConnectionName();
	}

	public void setRuleCaseSourceConnectionName(String ruleCaseSourceConnectionName) {
		this.ruleCaseSourceConnectionName = ruleCaseSourceConnectionName;
	}

	public String getRuleCaseTargetConnectionName() {
		return connectionTgt.getConnectionName();
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