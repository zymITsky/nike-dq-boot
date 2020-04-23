
package com.nike.app.dq.boot.data.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nike.app.dq.boot.common.model.JsonCjLabelYModel;
import com.nike.app.dq.boot.data.dao.DsConnectionDao;
import com.nike.app.dq.boot.data.dao.RuleTcCaseDao;
import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.RuleTcCase;
import com.nike.app.dq.boot.data.entity.RuleTcCaseRunHistory;
import com.nike.app.dq.boot.data.entity.TblRowCnt;
import com.nike.app.dq.boot.data.service.RuleTcService;
import com.nike.app.dq.boot.util.convert.UrlEncodedFormFactory;
import com.nike.app.dq.boot.web.mvc.bean.form.RtccForm;
import com.nike.app.dq.boot.web.mvc.bean.form.TcRcActionForm;

@Service("ruleTcService")
public class RuleTcServiceImpl implements RuleTcService {

	@Autowired
	private RuleTcCaseDao ruleTcCaseDao = null;

	@Autowired
	private DsConnectionDao dsConnectionDao = null;

	@Transactional(readOnly=true)
	@Override
	public List<RuleTcCase> getAllTcRuleCases() {
		return ruleTcCaseDao.findAll();
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void doDatatableActForRcTc(String requestBody) {
		TcRcActionForm tcRcActionForm = UrlEncodedFormFactory.getInstance().generateForTcRcActionForm(requestBody);
//		TcRcActionForm tcRcActionForm = null;
//		try {
//			tcRcActionForm = (TcRcActionForm)UrlEncodedFormFactory.getInstance().generate(WebAppConstant.URL_ENCODED_TC_RC_ACTION_FORM, requestBody);
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
		if (tcRcActionForm.getActionName().equals("edit")) {
			RuleTcCase rtcc = ruleTcCaseDao.findById(tcRcActionForm.getId());
			rtcc.toSyn(tcRcActionForm);
			ruleTcCaseDao.updateOne(rtcc.getRuleCaseId(), rtcc.getRuleCaseDescription(), rtcc.getRuleCaseOriginalTableSize(), rtcc.getRuleCaseRowsGapGt(), rtcc.getRuleCaseRowsGapLt(), rtcc.getRuleCaseSeverity());
		} else if (tcRcActionForm.getActionName().equals("remove")) {
			removeRuleTableCountCase(tcRcActionForm.getId());
		}
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void runRcTcById(int id) {
		Date startedDate = new Date();
		RuleTcCase ruleTcCase = ruleTcCaseDao.findById(id);
		TblRowCnt count = ruleTcCaseDao.getTableRowCount(ruleTcCase.getRuleCaseTargetTable());
		Date endDate = new Date();
		ruleTcCaseDao.insertHistoryOne(id, count.getRowCount(), startedDate, endDate);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void runRcTcByName(String name) {
		RuleTcCase rtcc = ruleTcCaseDao.findByRuleCaseName(name);
		runRcTcById(rtcc.getRuleCaseId());
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void runRcTcBatch(String ids) {
		if (ids.equals("all")) {
			List<Integer> arr_ids = ruleTcCaseDao.findAll().stream().map((rtcc) -> rtcc.getRuleCaseId()).collect(Collectors.toList());
			for (int id : arr_ids) {
				runRcTcById(id);
			}
		} else {
			for (String id : ids.split("-")) {
				runRcTcById(Integer.valueOf(id));
			}
		}
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, List<JsonCjLabelYModel>> loadLastTcRcRunHistoryForChart() {
		Map<String, List<JsonCjLabelYModel>> result = new HashMap<String, List<JsonCjLabelYModel>>();
		List<RuleTcCase> ruleTcCases = ruleTcCaseDao.findAll();
		List<JsonCjLabelYModel> origList = ruleTcCases.stream().map((rtcc) -> new JsonCjLabelYModel(rtcc.getRuleCaseName(), rtcc.getRuleCaseOriginalTableSize())).collect(Collectors.toList());
		List<JsonCjLabelYModel> currList = ruleTcCases.stream()
			.map((rtcc) -> new JsonCjLabelYModel(rtcc.getRuleCaseName(),
			rtcc.getRunHistories().get(rtcc.getRunHistories().size() - 1).getRuleCaseRunRows()))
			.collect(Collectors.toList());
		List<JsonCjLabelYModel> expcList = ruleTcCases.stream()
			.map((rtcc) -> new JsonCjLabelYModel(rtcc.getRuleCaseName(), 
			(rtcc.getRunHistories().get(rtcc.getRunHistories().size() - 1).getRuleCaseRunRows() < rtcc.getRuleCaseOriginalTableSize())
			? ((int)(rtcc.getRuleCaseOriginalTableSize()*(100.0 - rtcc.getRuleCaseRowsGapLt())/100.0)) 
			: ((int)(rtcc.getRuleCaseOriginalTableSize()*(100.0 + rtcc.getRuleCaseRowsGapGt())/100.0))))
			.collect(Collectors.toList());
		result.put("origList", origList);
		result.put("currList", currList);
		result.put("expcList", expcList);
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public List<JsonCjLabelYModel> loadTcConnUsedForChart() {
		List<JsonCjLabelYModel> result = new ArrayList<JsonCjLabelYModel>();
		List<RuleTcCase> ruleTcCases = ruleTcCaseDao.findAll();
		List<String> connections = ruleTcCases.stream().map((rtcc) -> rtcc.getConnection().getConnectionName()).collect(Collectors.toList());
		Map<String, Long> connNameCount = connections.stream().collect(Collectors.groupingBy(elem -> elem, Collectors.counting()));
		connNameCount.forEach((name, count) -> {
			result.add(new JsonCjLabelYModel(name, count));
		});
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public List<JsonCjLabelYModel> loadTcCreatedByForChart() {
		List<JsonCjLabelYModel> result = new ArrayList<JsonCjLabelYModel>();
		List<RuleTcCase> ruleTcCases = ruleTcCaseDao.findAll();
		List<String> createdBys = ruleTcCases.stream().map((rtcc) -> rtcc.getRuleCaseCreatedBy()).collect(Collectors.toList());
		Map<String, Long> nameCount = createdBys.stream().collect(Collectors.groupingBy(elem -> elem, Collectors.counting()));
		nameCount.forEach((name, count) -> {
			result.add(new JsonCjLabelYModel(name, count));
		});
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public List<JsonCjLabelYModel> loadTcCaseSeverityForChart() {
		List<JsonCjLabelYModel> result = new ArrayList<JsonCjLabelYModel>();
		List<RuleTcCase> ruleTcCases = ruleTcCaseDao.findAll();
		List<String> severitys = ruleTcCases.stream().map((rtcc) -> rtcc.getRuleCaseSeverity()).collect(Collectors.toList());
		Map<String, Long> nameCount = severitys.stream().collect(Collectors.groupingBy(elem -> elem, Collectors.counting()));
		nameCount.forEach((name, count) -> {
			result.add(new JsonCjLabelYModel(name, count));
		});
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isRuleCaseNameDuplicate(String ruleCaseName) {
		RuleTcCase rtcc = ruleTcCaseDao.findByRuleCaseName(ruleCaseName);
		return (rtcc != null) ? true : false;
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isTableExist(String table) {
		boolean result = true;
		try {
			ruleTcCaseDao.getTableRowCount(table);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void addNewRtcc(RtccForm rtccForm) {
		DsConnection conn = dsConnectionDao.findByConnectionName(rtccForm.getSelRuleCaseConnection());
		RuleTcCase rtcc = new RuleTcCase();
		rtcc.setRuleCaseName(rtccForm.getTxtRuleCaseName());
		rtcc.setRuleCaseDescription(rtccForm.getTxtRuleCaseDescription());
		rtcc.setRuleCaseTargetDb(rtccForm.getTxtRuleCaseTargetDb());
		rtcc.setRuleCaseTargetTable(rtccForm.getTxtRuleCaseTargetTable());
		rtcc.setConnectionId(conn.getConnectionId());
		rtcc.setRuleCaseOriginalTableSize(rtccForm.getTxtRuleCaseOrigTblSize());
		rtcc.setRuleCaseRowsGapGt(rtccForm.getTxtRuleCaseRowGapGt());
		rtcc.setRuleCaseRowsGapLt(rtccForm.getTxtRuleCaseRowGapLt());
		rtcc.setRuleCaseSeverity(rtccForm.getSelRuleCaseSeverity());
		rtcc.setRuleCaseCreatedBy(rtccForm.getCreatedBy());
		ruleTcCaseDao.insertOne(rtcc);
		runRcTcByName(rtccForm.getTxtRuleCaseName());
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, List<JsonCjLabelYModel>> loadLastTcRcRunHistoryAvgForChart() {
		Map<String, List<JsonCjLabelYModel>> result = new HashMap<String, List<JsonCjLabelYModel>>();
		List<RuleTcCase> ruleTcCases = ruleTcCaseDao.findAll();
		List<JsonCjLabelYModel> origList = ruleTcCases.stream().map((rtcc) -> new JsonCjLabelYModel(rtcc.getRuleCaseName(), rtcc.getRuleCaseOriginalTableSize())).collect(Collectors.toList());
		List<JsonCjLabelYModel> currAvgList = ruleTcCases.stream()
			.map((rtcc) -> new JsonCjLabelYModel(rtcc.getRuleCaseName(), rtcc.getRunHistories().stream().mapToInt((rh) -> rh.getRuleCaseRunRows()).average().getAsDouble()))
			.collect(Collectors.toList());
		List<JsonCjLabelYModel> expcList = ruleTcCases.stream()
			.map((rtcc) -> new JsonCjLabelYModel(rtcc.getRuleCaseName(), 
			(rtcc.getRunHistories().get(rtcc.getRunHistories().size() - 1).getRuleCaseRunRows() < rtcc.getRuleCaseOriginalTableSize())
			? ((int)(rtcc.getRuleCaseOriginalTableSize()*(100.0 - rtcc.getRuleCaseRowsGapLt())/100.0)) 
			: ((int)(rtcc.getRuleCaseOriginalTableSize()*(100.0 + rtcc.getRuleCaseRowsGapGt())/100.0))))
			.collect(Collectors.toList());
		result.put("origList", origList);
		result.put("currAvgList", currAvgList);
		result.put("expcList", expcList);
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public List<JsonCjLabelYModel> loadTcHistoryFailureCountForChart() {
		List<JsonCjLabelYModel> result = new ArrayList<JsonCjLabelYModel>();
		List<RuleTcCase> ruleTcCases = ruleTcCaseDao.findAll();
		for (RuleTcCase rtcc : ruleTcCases) {
			List<RuleTcCaseRunHistory> histories = rtcc.getRunHistories();
			List<RuleTcCaseRunHistory> filteredhistories = histories.stream().filter(
				(rh) -> (rh.getRuleCaseRunRows() < rtcc.getRuleCaseOriginalTableSize())
				? (((rtcc.getRuleCaseOriginalTableSize() - rh.getRuleCaseRunRows()) > (int)((double)rtcc.getRuleCaseOriginalTableSize()*rtcc.getRuleCaseRowsGapLt()/100.0)) ? true : false)
				: (((rh.getRuleCaseRunRows() - rtcc.getRuleCaseOriginalTableSize()) > (int)((double)rtcc.getRuleCaseOriginalTableSize()*rtcc.getRuleCaseRowsGapGt()/100.0)) ? true : false))
				.collect(Collectors.toList());
			result.add(new JsonCjLabelYModel(rtcc.getRuleCaseName(), filteredhistories.size()));
		}
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public Map<String, List<JsonCjLabelYModel>> loadTcRcsRunHistoryAllForChart(String name) {
		Map<String, List<JsonCjLabelYModel>> result = new HashMap<String, List<JsonCjLabelYModel>>();
		RuleTcCase rtcc = ruleTcCaseDao.findByRuleCaseName(name);
		List<JsonCjLabelYModel> actList = rtcc.getRunHistories().stream().map((rh) -> new JsonCjLabelYModel(rh.getRuleCaseRunStartDateStr(), rh.getRuleCaseRunRows())).collect(Collectors.toList());
//		List<JsonCjLabelYModel> expList = rtcc.getRunHistories().stream().map(
//			(rh) -> new JsonCjLabelYModel(rh.getRuleCaseRunStartDateStr(),
//			(rh.getRuleCaseRunRows() < rh.getRuleCaseRunRows())
//			? (int)(rtcc.getRuleCaseOriginalTableSize()*(100.0 - rtcc.getRuleCaseRowsGapLt())/100.0)
//			: (int)(rtcc.getRuleCaseOriginalTableSize()*(100.0 + rtcc.getRuleCaseRowsGapGt())/100.0)))
//			.collect(Collectors.toList());
		List<JsonCjLabelYModel> expList = rtcc.getRunHistories().stream().map((rh) -> new JsonCjLabelYModel(rh.getRuleCaseRunStartDateStr(), rtcc.getRuleCaseOriginalTableSize())).collect(Collectors.toList());
		result.put("actList", actList);
		result.put("expList", expList);
		return result;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void removeRuleTableCountCase(int ruleCaseId) {
		ruleTcCaseDao.deleteRunHistoryByCaseId(ruleCaseId);
		ruleTcCaseDao.deleteByRuleCaseId(ruleCaseId);
	}
}