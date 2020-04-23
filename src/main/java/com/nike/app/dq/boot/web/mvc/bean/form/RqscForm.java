
package com.nike.app.dq.boot.web.mvc.bean.form;

public class RqscForm {

	private String txtRuleCaseName = null;
	private String txtRuleCaseDescription = null;
	private String txtRuleCaseSql = null;
	private String selRuleCaseConnection = null;
	private String selRuleCaseSeverity = null;
	private String createdBy = null;

	public RqscForm() {
		
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

	public String getTxtRuleCaseSql() {
		return txtRuleCaseSql;
	}

	public void setTxtRuleCaseSql(String txtRuleCaseSql) {
		this.txtRuleCaseSql = txtRuleCaseSql;
	}

	public String getSelRuleCaseConnection() {
		return selRuleCaseConnection;
	}

	public void setSelRuleCaseConnection(String selRuleCaseConnection) {
		this.selRuleCaseConnection = selRuleCaseConnection;
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