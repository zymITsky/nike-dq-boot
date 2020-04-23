
package com.nike.app.dq.boot.data.service;

import java.io.OutputStream;
import java.util.List;

import com.nike.app.dq.boot.data.entity.RuleR33mcCase;
import com.nike.app.dq.boot.web.mvc.bean.form.R33mcForm;

public interface RuleR33mcService {

	List<RuleR33mcCase> getAllRuleR33mcCases();
	void doDatatableActForRcR33mc(String requestBody);
	void removeRuleR33mcCase(int ruleCaseId);
	void runRcR33mcById(int id);
	void runRcR33mcByName(String name);
	void runRcR33mcBatch(String ids);
	boolean isRuleCaseNameDuplicate(String ruleCaseName);
	void addNewR33mc(R33mcForm form);
	void copyNewR33mc(int id);
	List<RuleR33mcCase> filterForR33mcRuleCases(String srcConnName, String tgtConnName, String bizFunc, String caseOwner);
	void downloadRule33AsExcel(OutputStream os);
	List<String> getAllCaseOwnersAsStrings();
}