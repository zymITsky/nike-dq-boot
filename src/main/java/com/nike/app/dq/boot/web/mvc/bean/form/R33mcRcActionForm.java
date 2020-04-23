
package com.nike.app.dq.boot.web.mvc.bean.form;

@UrlEncodedForm("R33MC_RC_ACTION_FORM")
public class R33mcRcActionForm implements UrlEncodedFormBean {

	private String actionName = null;
	private int id = 0;
	private String ruleCaseName = null;
	private String ruleCaseOwner = null;
	private String ruleCaseBusinessFunction = null;
	private String ruleCaseDescription = null;
	private String ruleCaseSourceTable = null;
	private String ruleCaseSourceField = null;
	private String ruleCaseSourceConditionField = "";
	private String ruleCaseTargetTable = null;
	private String ruleCaseTargetField = null;
	private String ruleCaseTargetConditionField = "";
	private String ruleCaseSourceConnectionName = null;
	private String ruleCaseTargetConnectionName = null;

	public R33mcRcActionForm() {
		
	}

	public int getId() {
		return id;
	}

	@UrlEncodedFormFieldMapping("id")
	public void setId(int id) {
		this.id = id;
	}

	public String getRuleCaseName() {
		return ruleCaseName;
	}

	@UrlEncodedFormFieldMapping("ruleCaseName")
	public void setRuleCaseName(String ruleCaseName) {
		this.ruleCaseName = ruleCaseName;
	}

	public String getRuleCaseOwner() {
		return ruleCaseOwner;
	}

	@UrlEncodedFormFieldMapping("ruleCaseOwner")
	public void setRuleCaseOwner(String ruleCaseOwner) {
		this.ruleCaseOwner = ruleCaseOwner;
	}

	public String getRuleCaseBusinessFunction() {
		return ruleCaseBusinessFunction;
	}

	@UrlEncodedFormFieldMapping("ruleCaseBusinessFunction")
	public void setRuleCaseBusinessFunction(String ruleCaseBusinessFunction) {
		this.ruleCaseBusinessFunction = ruleCaseBusinessFunction;
	}

	public String getRuleCaseDescription() {
		return ruleCaseDescription;
	}

	@UrlEncodedFormFieldMapping("ruleCaseDescription")
	public void setRuleCaseDescription(String ruleCaseDescription) {
		this.ruleCaseDescription = ruleCaseDescription;
	}

	public String getRuleCaseSourceTable() {
		return ruleCaseSourceTable;
	}

	@UrlEncodedFormFieldMapping("ruleCaseSourceTable")
	public void setRuleCaseSourceTable(String ruleCaseSourceTable) {
		this.ruleCaseSourceTable = ruleCaseSourceTable;
	}

	public String getRuleCaseSourceField() {
		return ruleCaseSourceField;
	}

	@UrlEncodedFormFieldMapping("ruleCaseSourceField")
	public void setRuleCaseSourceField(String ruleCaseSourceField) {
		this.ruleCaseSourceField = ruleCaseSourceField;
	}

	public String getRuleCaseSourceConditionField() {
		return ruleCaseSourceConditionField;
	}

	@UrlEncodedFormFieldMapping("ruleCaseSourceConditionField")
	public void setRuleCaseSourceConditionField(String ruleCaseSourceConditionField) {
		this.ruleCaseSourceConditionField = ruleCaseSourceConditionField;
	}

	public String getRuleCaseTargetTable() {
		return ruleCaseTargetTable;
	}

	@UrlEncodedFormFieldMapping("ruleCaseTargetTable")
	public void setRuleCaseTargetTable(String ruleCaseTargetTable) {
		this.ruleCaseTargetTable = ruleCaseTargetTable;
	}

	public String getRuleCaseTargetField() {
		return ruleCaseTargetField;
	}

	@UrlEncodedFormFieldMapping("ruleCaseTargetField")
	public void setRuleCaseTargetField(String ruleCaseTargetField) {
		this.ruleCaseTargetField = ruleCaseTargetField;
	}

	public String getRuleCaseTargetConditionField() {
		return ruleCaseTargetConditionField;
	}

	@UrlEncodedFormFieldMapping("ruleCaseTargetConditionField")
	public void setRuleCaseTargetConditionField(String ruleCaseTargetConditionField) {
		this.ruleCaseTargetConditionField = ruleCaseTargetConditionField;
	}

	public String getRuleCaseSourceConnectionName() {
		return ruleCaseSourceConnectionName;
	}

	@UrlEncodedFormFieldMapping("ruleCaseSourceConnectionName")
	public void setRuleCaseSourceConnectionName(String ruleCaseSourceConnectionName) {
		this.ruleCaseSourceConnectionName = ruleCaseSourceConnectionName;
	}

	public String getRuleCaseTargetConnectionName() {
		return ruleCaseTargetConnectionName;
	}

	@UrlEncodedFormFieldMapping("ruleCaseTargetConnectionName")
	public void setRuleCaseTargetConnectionName(String ruleCaseTargetConnectionName) {
		this.ruleCaseTargetConnectionName = ruleCaseTargetConnectionName;
	}

	@Override
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Override
	public String getActionName() {
		return actionName;
	}
}