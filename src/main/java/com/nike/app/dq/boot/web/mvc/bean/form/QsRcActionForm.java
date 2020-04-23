
package com.nike.app.dq.boot.web.mvc.bean.form;

@UrlEncodedForm("QS_RC_ACTION_FORM")
public class QsRcActionForm implements UrlEncodedFormBean {

	private String actionName = null;
	private int id = 0;
	private String ruleCaseDescription = null;
	private String ruleCaseSql = null;
	private String ruleCaseSeverity = null;

	public QsRcActionForm() {
		
	}

	public int getId() {
		return id;
	}

	@UrlEncodedFormFieldMapping("id")
	public void setId(int id) {
		this.id = id;
	}

	public String getRuleCaseDescription() {
		return ruleCaseDescription;
	}

	@UrlEncodedFormFieldMapping("ruleCaseDescription")
	public void setRuleCaseDescription(String ruleCaseDescription) {
		this.ruleCaseDescription = ruleCaseDescription;
	}

	public String getRuleCaseSql() {
		return ruleCaseSql;
	}

	@UrlEncodedFormFieldMapping("ruleCaseSql")
	public void setRuleCaseSql(String ruleCaseSql) {
		this.ruleCaseSql = ruleCaseSql;
	}

	public String getRuleCaseSeverity() {
		return ruleCaseSeverity;
	}

	@UrlEncodedFormFieldMapping("ruleCaseSeverity")
	public void setRuleCaseSeverity(String ruleCaseSeverity) {
		this.ruleCaseSeverity = ruleCaseSeverity;
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