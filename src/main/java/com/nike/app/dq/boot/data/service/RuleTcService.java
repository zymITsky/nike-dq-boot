
package com.nike.app.dq.boot.data.service;

import java.util.List;
import java.util.Map;

import com.nike.app.dq.boot.common.model.JsonCjLabelYModel;
import com.nike.app.dq.boot.data.entity.RuleTcCase;
import com.nike.app.dq.boot.web.mvc.bean.form.RtccForm;

public interface RuleTcService {

	List<RuleTcCase> getAllTcRuleCases();
	Map<String, List<JsonCjLabelYModel>> loadLastTcRcRunHistoryForChart();
	void doDatatableActForRcTc(String requestBody);
	void runRcTcById(int id);
	void runRcTcByName(String name);
	void runRcTcBatch(String ids);
	List<JsonCjLabelYModel> loadTcConnUsedForChart();
	List<JsonCjLabelYModel> loadTcCreatedByForChart();
	List<JsonCjLabelYModel> loadTcCaseSeverityForChart();
	boolean isRuleCaseNameDuplicate(String ruleCaseName);
	boolean isTableExist(String table);
	void addNewRtcc(RtccForm rtccForm);
	Map<String, List<JsonCjLabelYModel>> loadLastTcRcRunHistoryAvgForChart();
	List<JsonCjLabelYModel> loadTcHistoryFailureCountForChart();
	Map<String, List<JsonCjLabelYModel>> loadTcRcsRunHistoryAllForChart(String name);
	void removeRuleTableCountCase(int ruleCaseId);
}