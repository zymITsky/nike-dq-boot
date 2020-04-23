
package com.nike.app.dq.boot.data.service;

import java.io.OutputStream;
import java.util.List;

import com.nike.app.dq.boot.data.entity.RuleR21fcCase;
import com.nike.app.dq.boot.web.mvc.bean.form.R21fcForm;

public interface RuleR21fcService {

	List<RuleR21fcCase> getAllR21fcRuleCases();
	void doDatatableActForRcR21fc(String requestBody);
	void removeRuleR21fcCase(int ruleCaseId);
	void runRcR21fcById(int id);
	void runRcR21fcByName(String name);
	void runRcR21fcBatch(String ids);
	boolean isRuleCaseNameDuplicate(String ruleCaseName);
	void addNewR21fc(R21fcForm form);
	void copyNewR21fc(int id);
	List<RuleR21fcCase> filterForR21fcRuleCases(String tgtConnName, String bizFunc, String caseOwner);
	void downloadRule21AsExcel(OutputStream os);
	List<String> getAllCaseOwnersAsStrings();
}