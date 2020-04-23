
package com.nike.app.dq.boot.data.serviceImpl;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nike.app.dq.boot.common.constant.Rule21Excel;
import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.data.dao.DsConnectionDao;
import com.nike.app.dq.boot.data.dao.RuleR21fcCaseDao;
import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.R21fcExecResult;
import com.nike.app.dq.boot.data.entity.RuleR21fcCase;
import com.nike.app.dq.boot.data.entity.RuleR21fcCaseRunHistory;
import com.nike.app.dq.boot.data.entity.UserProfile;
import com.nike.app.dq.boot.data.service.RuleR21fcService;
import com.nike.app.dq.boot.util.convert.UrlEncodedFormFactory;
import com.nike.app.dq.boot.util.excel.Rule21ExcelModel;
import com.nike.app.dq.boot.util.format.SimpleFormater;
import com.nike.app.dq.boot.web.mvc.bean.form.R21fcForm;
import com.nike.app.dq.boot.web.mvc.bean.form.R21fcRcActionForm;

import jxl.write.WritableSheet;

@Service("ruleR21fcService")
public class RuleR21fcServiceImpl implements RuleR21fcService {

	@Autowired
	private RuleR21fcCaseDao ruleR21fcCaseDao = null;

	@Autowired
	private DsConnectionDao dsConnectionDao = null;

	@Autowired
	private HttpSession session = null;

	@Transactional(readOnly=true)
	@Override
	public List<RuleR21fcCase> getAllR21fcRuleCases() {
		return ruleR21fcCaseDao.findAll();
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void doDatatableActForRcR21fc(String requestBody) {
		R21fcRcActionForm actionForm = UrlEncodedFormFactory.getInstance().generateForR21fcRcActionForm(requestBody);
		if (actionForm.getActionName().equals("edit")) {
			RuleR21fcCase rc = ruleR21fcCaseDao.findById(actionForm.getId());
			DsConnection conn = dsConnectionDao.findByConnectionName(actionForm.getRuleCaseTargetConnectionName());
			rc.setConnection(conn);
			rc.setRuleCaseLastModifiedDate(new Date());
			UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
			rc.setRuleCaseLastModifiedBy(user.getUserName().toUpperCase());
			rc.toSyn(actionForm);
			ruleR21fcCaseDao.updateOne(rc);
			runRcR21fcById(actionForm.getId());
		} else if (actionForm.getActionName().equals("remove")) {
			removeRuleR21fcCase(actionForm.getId());
		}
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void removeRuleR21fcCase(int ruleCaseId) {
		ruleR21fcCaseDao.deleteRunHistoryByCaseId(ruleCaseId);
		ruleR21fcCaseDao.deleteByRuleCaseId(ruleCaseId);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void runRcR21fcById(int id) {
		Date startedDate = new Date();
		RuleR21fcCase ruleCase = ruleR21fcCaseDao.findById(id);
		R21fcExecResult result = ruleR21fcCaseDao.execQueryR21fcVerify(ruleCase.getConnection().getConnectionDbLink(), ruleCase.getConnection().getConnectionDbSchema(), ruleCase.getRuleCaseTargetTable(), ruleCase.getRuleCaseTargetField(), ruleCase.getRuleCaseTargetConditionField());
		boolean isPassed = false;
		if (result != null) {
			Calendar c1 = Calendar.getInstance();
			c1.roll(Calendar.DATE, -2);
			isPassed = c1.getTime().after(result.getExecResult()) ? false : true;
		}
		String strResultDate = SimpleFormater.simpleDatetimeFormate(result.getExecResult());
		long day_diff =  Math.abs(startedDate.getTime() - result.getExecResult().getTime());
		int days = (int)(day_diff/(1000*60*60*24));
		Date endDate = new Date();
		RuleR21fcCaseRunHistory rh = new RuleR21fcCaseRunHistory(id, (isPassed) ? "PASSED" : "FAILED", strResultDate, 2, days, startedDate, endDate);
		ruleR21fcCaseDao.insertHistoryOne(rh);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void runRcR21fcByName(String name) {
		RuleR21fcCase rc = ruleR21fcCaseDao.findByRuleCaseName(name);
		runRcR21fcById(rc.getRuleCaseId());
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void runRcR21fcBatch(String ids) {
		if (ids.equals("all")) {
			List<Integer> arr_ids = ruleR21fcCaseDao.findAll().stream().map((rc) -> rc.getRuleCaseId()).collect(Collectors.toList());
			for (int id : arr_ids) {
				runRcR21fcById(id);
			}
		} else {
			for (String id : ids.split("-")) {
				runRcR21fcById(Integer.valueOf(id));
			}
		}
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isRuleCaseNameDuplicate(String ruleCaseName) {
		RuleR21fcCase rc = ruleR21fcCaseDao.findByRuleCaseName(ruleCaseName);
		return (rc != null) ? true : false;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void addNewR21fc(R21fcForm form) {
		UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
		String connectionName = form.getSelRuleCaseConnection();
		DsConnection conn = dsConnectionDao.findByConnectionName(connectionName);
		RuleR21fcCase rc = new RuleR21fcCase();
		rc.setRuleCaseName(form.getTxtRuleCaseName());
		rc.setRuleCaseOwner(form.getTxtRuleCaseOwner().toUpperCase());
		rc.setRuleCaseBusinessFunction(form.getTxtRuleCaseBusinessFunction());
		rc.setRuleCaseDescription(form.getTxtRuleCaseDescription());
		rc.setRuleCaseTargetTable(form.getTxtRuleCaseTargetTable());
		rc.setRuleCaseTargetField(form.getTxtRuleCaseTargetField());
		rc.setRuleCaseTargetConditionField(form.getTxtRuleCaseTargetConditionField());
		rc.setRuleCaseLastModifiedBy(user.getUserName().toUpperCase());
		rc.setRuleCaseLastModifiedDate(new Date());
		rc.setConnectionId(conn.getConnectionId());
		ruleR21fcCaseDao.insertOne(rc);
		runRcR21fcByName(form.getTxtRuleCaseName());
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void copyNewR21fc(int id) {
		UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
		RuleR21fcCase r21c = ruleR21fcCaseDao.findById(id);
		RuleR21fcCase rc = new RuleR21fcCase();
		rc.setRuleCaseName("Copy of " + r21c.getRuleCaseName());
		rc.setRuleCaseOwner(r21c.getRuleCaseOwner().toUpperCase());
		rc.setRuleCaseBusinessFunction(r21c.getRuleCaseBusinessFunction());
		rc.setRuleCaseDescription("Copy of " + r21c.getRuleCaseDescription());
		rc.setRuleCaseTargetTable(r21c.getRuleCaseTargetTable());
		rc.setRuleCaseTargetField(r21c.getRuleCaseTargetField());
		rc.setRuleCaseTargetConditionField(r21c.getRuleCaseTargetConditionField());
		rc.setRuleCaseLastModifiedBy(user.getUserName().toUpperCase());
		rc.setRuleCaseLastModifiedDate(new Date());
		rc.setConnectionId(r21c.getConnection().getConnectionId());
		ruleR21fcCaseDao.insertOne(rc);
		runRcR21fcByName(rc.getRuleCaseName());
	}

	@Transactional(readOnly=true)
	@Override
	public List<RuleR21fcCase> filterForR21fcRuleCases(String tgtConnName, String bizFunc, String caseOwner) {
		List<RuleR21fcCase> list = ruleR21fcCaseDao.findAll();
		if (!tgtConnName.equals("-")) {
			List<String> tgtConnNames = Arrays.asList(tgtConnName.split(","));
			list = list.stream().filter((rc) -> tgtConnNames.contains(rc.getConnection().getConnectionName())).collect(Collectors.toList());
		}
		if (!bizFunc.equals("-")) {
			List<String> bizFuncs = Arrays.asList(bizFunc.split(","));
			list = list.stream().filter((rc) -> bizFuncs.contains(rc.getRuleCaseBusinessFunction())).collect(Collectors.toList());
		}
		if (!caseOwner.equals("-")) {
			List<String> caseOwners = Arrays.asList(caseOwner.split(","));
			list = list.stream().filter((rc) -> caseOwners.contains(rc.getRuleCaseOwner().toUpperCase())).collect(Collectors.toList());
		}
		return list;
	}

	@Transactional(readOnly=true)
	@Override
	public void downloadRule21AsExcel(OutputStream os) {
		Rule21ExcelModel rule21ExcelModel = new Rule21ExcelModel(os);
		// create an Excel sheet
		WritableSheet excelSheet = rule21ExcelModel.createExcelWritableSheet(Rule21Excel.NAME_OF_RULE21_STEP_01_XLS_SHEET, Rule21Excel.IDX_XLS_SHEET_OF_RULE21_STEP_01);
		// set column width
		rule21ExcelModel.setColumnsWidth(excelSheet, Rule21Excel.ARR_COLS_WIDTH);
		// define the cell format for table field head
		rule21ExcelModel.buildForTableFieldHead(excelSheet);
		// fetch the data which be used for rendering
		List<RuleR21fcCase> dataList = ruleR21fcCaseDao.findAll();
		// write all the records into excel
		rule21ExcelModel.buildForTableBodyRows(excelSheet, dataList);
		rule21ExcelModel.writeAndClose();
	}

	@Transactional(readOnly=true)
	@Override
	public List<String> getAllCaseOwnersAsStrings() {
		List<RuleR21fcCase> rcs = ruleR21fcCaseDao.findAll();
		List<String> resList = rcs.stream().map((rc) -> rc.getRuleCaseOwner().toUpperCase()).distinct().collect(Collectors.toList());
		return resList;
	}
}