
package com.nike.app.dq.boot.data.service;

import java.util.List;

import com.nike.app.dq.boot.data.entity.RuleQsCase;
import com.nike.app.dq.boot.web.mvc.bean.form.RqscForm;

public interface RuleQsService {

	List<RuleQsCase> getAllTcRuleCases();
	void doDatatableActForRcQs(String requestBody);
	void removeRuleQueryStringCase(int ruleCaseId);
	void runRcQsById(int id);
	void runRcQsByName(String name);
	void runRcQsBatch(String ids);
	boolean isRuleCaseNameDuplicate(String ruleCaseName);
	void addNewRqsc(RqscForm rqscForm);
}