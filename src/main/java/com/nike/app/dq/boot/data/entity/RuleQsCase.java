
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nike.app.dq.boot.util.format.SimpleFormater;
import com.nike.app.dq.boot.web.mvc.bean.form.QsRcActionForm;

public class RuleQsCase implements Serializable {

	private static final long serialVersionUID = -2067499031603204655L;

	private int ruleCaseId = 0;
	private int connectionId = 0;
	private DsConnection connection = null;
	private String ruleCaseName = null;
	private String ruleCaseDescription = null;
	private String ruleCaseSql = null;
	private String ruleCaseSeverity = null;
	private String ruleCaseCreatedBy = null;
	private Date ruleCaseCreatedDatetime = null;
	private List<RuleQsCaseRunHistory> runHistories = new ArrayList<RuleQsCaseRunHistory>();
	@SuppressWarnings("unused")
	private String ruleCaseCreatedDateStr = null;
	@JsonProperty("DT_RowId")
	private String dt_rowid = null;

	public RuleQsCase() {
		
	}

	public RuleQsCase(int ruleCaseId, String ruleCaseName, String ruleCaseDescription, String ruleCaseSql, String ruleCaseSeverity, String ruleCaseCreatedBy, Date ruleCaseCreatedDatetime) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseName = ruleCaseName;
		this.ruleCaseDescription = ruleCaseDescription;
		this.ruleCaseSql = ruleCaseSql;
		this.ruleCaseSeverity = ruleCaseSeverity;
		this.ruleCaseCreatedBy = ruleCaseCreatedBy;
		this.ruleCaseCreatedDatetime = ruleCaseCreatedDatetime;
	}

	public void toSyn(QsRcActionForm form) {
		this.ruleCaseDescription = (form.getRuleCaseDescription() != null && !form.getRuleCaseDescription().equals("")) ? form.getRuleCaseDescription() : this.ruleCaseDescription;
		this.ruleCaseSql = (form.getRuleCaseSql() != null && !form.getRuleCaseSql().equals("")) ? form.getRuleCaseSql() : this.ruleCaseSql;
		this.ruleCaseSeverity = (form.getRuleCaseSeverity() != null && !form.getRuleCaseSeverity().equals("")) ? form.getRuleCaseSeverity() : this.ruleCaseSeverity;
	}

	@Override
	public String toString() {
		return "RuleQsCase(ruleCaseId=" + ruleCaseId + ",connection=" + connection + ",ruleCaseName=" + ruleCaseName + ",ruleCaseDescription=" + ruleCaseDescription + ",ruleCaseSql=" + ruleCaseSql + ",ruleCaseSeverity=" + ruleCaseSeverity + ",ruleCaseCreatedBy=" + ruleCaseCreatedBy + ",ruleCaseCreatedDatetime=" + ruleCaseCreatedDatetime + ",runHistories=" + runHistories + ")";
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

	public String getRuleCaseDescription() {
		return ruleCaseDescription;
	}

	public void setRuleCaseDescription(String ruleCaseDescription) {
		this.ruleCaseDescription = ruleCaseDescription;
	}

	public String getRuleCaseSql() {
		return ruleCaseSql;
	}

	public void setRuleCaseSql(String ruleCaseSql) {
		this.ruleCaseSql = ruleCaseSql;
	}

	public String getRuleCaseSeverity() {
		return ruleCaseSeverity;
	}

	public void setRuleCaseSeverity(String ruleCaseSeverity) {
		this.ruleCaseSeverity = ruleCaseSeverity;
	}

	public String getRuleCaseCreatedBy() {
		return ruleCaseCreatedBy;
	}

	public void setRuleCaseCreatedBy(String ruleCaseCreatedBy) {
		this.ruleCaseCreatedBy = ruleCaseCreatedBy;
	}

	public Date getRuleCaseCreatedDatetime() {
		return ruleCaseCreatedDatetime;
	}

	public void setRuleCaseCreatedDatetime(Date ruleCaseCreatedDatetime) {
		this.ruleCaseCreatedDatetime = ruleCaseCreatedDatetime;
	}

	public String getRuleCaseCreatedDateStr() {
		return SimpleFormater.simpleFormate(ruleCaseCreatedDatetime);
	}

	public void setRuleCaseCreatedDateStr(String ruleCaseCreatedDateStr) {
		this.ruleCaseCreatedDateStr = ruleCaseCreatedDateStr;
	}

	public List<RuleQsCaseRunHistory> getRunHistories() {
		return runHistories;
	}

	public void setRunHistories(List<RuleQsCaseRunHistory> runHistories) {
		this.runHistories = runHistories;
	}

	@Transient
	public String getDt_rowid() {
		return "row_" + ruleCaseId;
	}

	public void setDt_rowid(String dt_rowid) {
		this.dt_rowid = dt_rowid;
	}
}