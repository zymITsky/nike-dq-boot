
package com.nike.app.dq.boot.web.mvc.bean.form;

@UrlEncodedForm("TC_RC_ACTION_FORM")
public class TcRcActionForm implements UrlEncodedFormBean {

	private String actionName = null;
	private int id = 0;
	private String ruleCaseDescription = null;
	private String ruleCaseOriginalTableSize = null;
	private String ruleCaseRowsGapGt = null;
	private String ruleCaseRowsGapLt = null;
	private String ruleCaseSeverity = null;

	public TcRcActionForm() {
		
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

	public String getRuleCaseOriginalTableSize() {
		return ruleCaseOriginalTableSize;
	}

	@UrlEncodedFormFieldMapping("ruleCaseOriginalTableSize")
	public void setRuleCaseOriginalTableSize(String ruleCaseOriginalTableSize) {
		this.ruleCaseOriginalTableSize = ruleCaseOriginalTableSize;
	}

	public String getRuleCaseRowsGapGt() {
		return ruleCaseRowsGapGt;
	}

	@UrlEncodedFormFieldMapping("ruleCaseRowsGapGt")
	public void setRuleCaseRowsGapGt(String ruleCaseRowsGapGt) {
		this.ruleCaseRowsGapGt = ruleCaseRowsGapGt;
	}

	public String getRuleCaseRowsGapLt() {
		return ruleCaseRowsGapLt;
	}

	@UrlEncodedFormFieldMapping("ruleCaseRowsGapLt")
	public void setRuleCaseRowsGapLt(String ruleCaseRowsGapLt) {
		this.ruleCaseRowsGapLt = ruleCaseRowsGapLt;
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