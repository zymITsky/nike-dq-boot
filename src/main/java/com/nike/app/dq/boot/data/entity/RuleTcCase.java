
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nike.app.dq.boot.util.format.SimpleFormater;
import com.nike.app.dq.boot.web.mvc.bean.form.TcRcActionForm;

public class RuleTcCase implements Serializable {

	private static final long serialVersionUID = 6899322065378355292L;

	private int ruleCaseId = 0;
	private int connectionId = 0;
	private DsConnection connection = null;
	private String ruleCaseName = null;
	private String ruleCaseDescription = null;
	private String ruleCaseTargetDb = null;
	private String ruleCaseTargetTable = null;
	private int ruleCaseOriginalTableSize = 0;
	private double ruleCaseRowsGapGt = 0.0;
	private double ruleCaseRowsGapLt = 0.0;
	private String ruleCaseSeverity = null;
	private String ruleCaseCreatedBy = null;
	private Date ruleCaseCreatedDatetime = null;
	private List<RuleTcCaseRunHistory> runHistories = new ArrayList<RuleTcCaseRunHistory>();
	@SuppressWarnings("unused")
	private String ruleCaseCreatedDateStr = null;
	@JsonProperty("DT_RowId")
	private String dt_rowid = null;

	public RuleTcCase() {
		
	}

	public RuleTcCase(int ruleCaseId, String ruleCaseName, String ruleCaseDescription, String ruleCaseTargetDb, String ruleCaseTargetTable, int ruleCaseOriginalTableSize, double ruleCaseRowsGapGt, double ruleCaseRowsGapLt, String ruleCaseSeverity, String ruleCaseCreatedBy, Date ruleCaseCreatedDatetime) {
		this.ruleCaseId = ruleCaseId;
		this.ruleCaseName = ruleCaseName;
		this.ruleCaseDescription = ruleCaseDescription;
		this.ruleCaseTargetDb = ruleCaseTargetDb;
		this.ruleCaseTargetTable = ruleCaseTargetTable;
		this.ruleCaseOriginalTableSize = ruleCaseOriginalTableSize;
		this.ruleCaseRowsGapGt = ruleCaseRowsGapGt;
		this.ruleCaseRowsGapLt = ruleCaseRowsGapLt;
		this.ruleCaseSeverity = ruleCaseSeverity;
		this.ruleCaseCreatedBy = ruleCaseCreatedBy;
		this.ruleCaseCreatedDatetime = ruleCaseCreatedDatetime;
	}

	public void toSyn(TcRcActionForm form) {
		this.ruleCaseDescription = (form.getRuleCaseDescription() != null && !form.getRuleCaseDescription().equals("")) ? form.getRuleCaseDescription() : this.ruleCaseDescription;
		this.ruleCaseOriginalTableSize = (form.getRuleCaseOriginalTableSize() != null && !form.getRuleCaseOriginalTableSize().equals("")) ? Integer.parseInt(form.getRuleCaseOriginalTableSize()) : this.ruleCaseOriginalTableSize;
		this.ruleCaseRowsGapGt = (form.getRuleCaseRowsGapGt() != null && !form.getRuleCaseRowsGapGt().equals("")) ? Double.parseDouble(form.getRuleCaseRowsGapGt()) : this.ruleCaseRowsGapGt;
		this.ruleCaseRowsGapLt = (form.getRuleCaseRowsGapLt() != null && !form.getRuleCaseRowsGapLt().equals("")) ? Double.parseDouble(form.getRuleCaseRowsGapLt()) : this.ruleCaseRowsGapLt;
		this.ruleCaseSeverity = (form.getRuleCaseSeverity() != null && !form.getRuleCaseSeverity().equals("")) ? form.getRuleCaseSeverity() : this.ruleCaseSeverity;
	}

	@Override
	public String toString() {
		return "RuleTcCase(ruleCaseId=" + ruleCaseId + ",connection=" + connection + ",ruleCaseName=" + ruleCaseName + ",ruleCaseDescription=" + ruleCaseDescription + ",ruleCaseTargetDb=" + ruleCaseTargetDb + ",ruleCaseTargetTable=" + ruleCaseTargetTable + ",ruleCaseOriginalTableSize=" + ruleCaseOriginalTableSize + ",ruleCaseRowsGapGt=" + ruleCaseRowsGapGt + ",ruleCaseRowsGapLt=" + ruleCaseRowsGapLt + ",ruleCaseSeverity=" + ruleCaseSeverity + ",ruleCaseCreatedBy=" + ruleCaseCreatedBy + ",ruleCaseCreatedDatetime=" + ruleCaseCreatedDatetime + ",runHistories=" + runHistories + ")";
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

	public String getRuleCaseTargetDb() {
		return ruleCaseTargetDb;
	}

	public void setRuleCaseTargetDb(String ruleCaseTargetDb) {
		this.ruleCaseTargetDb = ruleCaseTargetDb;
	}

	public String getRuleCaseTargetTable() {
		return ruleCaseTargetTable;
	}

	public void setRuleCaseTargetTable(String ruleCaseTargetTable) {
		this.ruleCaseTargetTable = ruleCaseTargetTable;
	}

	public int getRuleCaseOriginalTableSize() {
		return ruleCaseOriginalTableSize;
	}

	public void setRuleCaseOriginalTableSize(int ruleCaseOriginalTableSize) {
		this.ruleCaseOriginalTableSize = ruleCaseOriginalTableSize;
	}

	public double getRuleCaseRowsGapGt() {
		return ruleCaseRowsGapGt;
	}

	public void setRuleCaseRowsGapGt(double ruleCaseRowsGapGt) {
		this.ruleCaseRowsGapGt = ruleCaseRowsGapGt;
	}

	public double getRuleCaseRowsGapLt() {
		return ruleCaseRowsGapLt;
	}

	public void setRuleCaseRowsGapLt(double ruleCaseRowsGapLt) {
		this.ruleCaseRowsGapLt = ruleCaseRowsGapLt;
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

	public List<RuleTcCaseRunHistory> getRunHistories() {
		return runHistories;
	}

	public void setRunHistories(List<RuleTcCaseRunHistory> runHistories) {
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