
package com.nike.app.dq.boot.web.mvc.bean.form;

public class RtccForm {

	private String txtRuleCaseName = null;
	private String txtRuleCaseDescription = null;
	private String txtRuleCaseTargetDb = null;
	private String txtRuleCaseTargetTable = null;
	private String selRuleCaseConnection = null;
	private int txtRuleCaseOrigTblSize = 0;
	private double txtRuleCaseRowGapGt = 0.0;
	private double txtRuleCaseRowGapLt = 0.0;
	private String selRuleCaseSeverity = null;
	private String createdBy = null;

	public RtccForm() {
		
	}

	public String getTxtRuleCaseName() {
		return txtRuleCaseName;
	}

	public void setTxtRuleCaseName(String txtRuleCaseName) {
		this.txtRuleCaseName = txtRuleCaseName;
	}

	public String getTxtRuleCaseDescription() {
		return txtRuleCaseDescription;
	}

	public void setTxtRuleCaseDescription(String txtRuleCaseDescription) {
		this.txtRuleCaseDescription = txtRuleCaseDescription;
	}

	public String getTxtRuleCaseTargetDb() {
		return txtRuleCaseTargetDb;
	}

	public void setTxtRuleCaseTargetDb(String txtRuleCaseTargetDb) {
		this.txtRuleCaseTargetDb = txtRuleCaseTargetDb;
	}

	public String getTxtRuleCaseTargetTable() {
		return txtRuleCaseTargetTable;
	}

	public void setTxtRuleCaseTargetTable(String txtRuleCaseTargetTable) {
		this.txtRuleCaseTargetTable = txtRuleCaseTargetTable;
	}

	public String getSelRuleCaseConnection() {
		return selRuleCaseConnection;
	}

	public void setSelRuleCaseConnection(String selRuleCaseConnection) {
		this.selRuleCaseConnection = selRuleCaseConnection;
	}

	public int getTxtRuleCaseOrigTblSize() {
		return txtRuleCaseOrigTblSize;
	}

	public void setTxtRuleCaseOrigTblSize(int txtRuleCaseOrigTblSize) {
		this.txtRuleCaseOrigTblSize = txtRuleCaseOrigTblSize;
	}

	public double getTxtRuleCaseRowGapGt() {
		return txtRuleCaseRowGapGt;
	}

	public void setTxtRuleCaseRowGapGt(double txtRuleCaseRowGapGt) {
		this.txtRuleCaseRowGapGt = txtRuleCaseRowGapGt;
	}

	public double getTxtRuleCaseRowGapLt() {
		return txtRuleCaseRowGapLt;
	}

	public void setTxtRuleCaseRowGapLt(double txtRuleCaseRowGapLt) {
		this.txtRuleCaseRowGapLt = txtRuleCaseRowGapLt;
	}

	public String getSelRuleCaseSeverity() {
		return selRuleCaseSeverity;
	}

	public void setSelRuleCaseSeverity(String selRuleCaseSeverity) {
		this.selRuleCaseSeverity = selRuleCaseSeverity;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}