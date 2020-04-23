
package com.nike.app.dq.boot.data.serviceImpl;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nike.app.dq.boot.common.constant.Rule33Excel;
import com.nike.app.dq.boot.common.constant.WebAppConstant;
import com.nike.app.dq.boot.data.dao.DsConnectionDao;
import com.nike.app.dq.boot.data.dao.RuleR33mcCaseDao;
import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.R33mcExecResult;
import com.nike.app.dq.boot.data.entity.RuleR33mcCase;
import com.nike.app.dq.boot.data.entity.RuleR33mcCaseRunHistory;
import com.nike.app.dq.boot.data.entity.UserProfile;
import com.nike.app.dq.boot.data.service.RuleR33mcService;
import com.nike.app.dq.boot.util.convert.UrlEncodedFormFactory;
import com.nike.app.dq.boot.util.excel.Rule33ExcelModel;
import com.nike.app.dq.boot.web.mvc.bean.form.R33mcForm;
import com.nike.app.dq.boot.web.mvc.bean.form.R33mcRcActionForm;

import jxl.write.WritableSheet;

@Service("ruleR33mcService")
public class RuleR33mcServiceImpl implements RuleR33mcService {

	@Autowired
	private RuleR33mcCaseDao ruleR33mcCaseDao = null;

	@Autowired
	private DsConnectionDao dsConnectionDao = null;

	@Autowired
	private HttpSession session = null;

	@Transactional(readOnly=true)
	@Override
	public List<RuleR33mcCase> getAllRuleR33mcCases() {
		return ruleR33mcCaseDao.findAll();
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void doDatatableActForRcR33mc(String requestBody) {
		R33mcRcActionForm actionForm = UrlEncodedFormFactory.getInstance().generateForR33mcRcActionForm(requestBody);
		if (actionForm.getActionName().equals("edit")) {
			RuleR33mcCase rc = ruleR33mcCaseDao.findById(actionForm.getId());
			DsConnection connSrc = dsConnectionDao.findByConnectionName(actionForm.getRuleCaseSourceConnectionName());
			DsConnection connTgt = dsConnectionDao.findByConnectionName(actionForm.getRuleCaseTargetConnectionName());
			rc.setConnectionSrc(connSrc);
			rc.setConnectionTgt(connTgt);
			rc.setRuleCaseLastModifiedDate(new Date());
			UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
			rc.setRuleCaseLastModifiedBy(user.getUserName().toUpperCase());
			rc.toSyn(actionForm);
			ruleR33mcCaseDao.updateOne(rc);
			runRcR33mcById(actionForm.getId());
		} else if (actionForm.getActionName().equals("remove")) {
			removeRuleR33mcCase(actionForm.getId());
		}
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void removeRuleR33mcCase(int ruleCaseId) {
		ruleR33mcCaseDao.deleteRunHistoryByCaseId(ruleCaseId);
		ruleR33mcCaseDao.deleteByRuleCaseId(ruleCaseId);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void runRcR33mcById(int id) {
		Date startedDate = new Date();
		RuleR33mcCase ruleCase = ruleR33mcCaseDao.findById(id);
		R33mcExecResult resultSrc = ruleR33mcCaseDao.execQueryR33mcVerify(ruleCase.getConnectionSrc().getConnectionDbLink(), ruleCase.getConnectionSrc().getConnectionDbSchema(), ruleCase.getRuleCaseSourceTable(), ruleCase.getRuleCaseSourceField(), ruleCase.getRuleCaseSourceConditionField());
		R33mcExecResult resultTgt = ruleR33mcCaseDao.execQueryR33mcVerify(ruleCase.getConnectionTgt().getConnectionDbLink(), ruleCase.getConnectionTgt().getConnectionDbSchema(), ruleCase.getRuleCaseTargetTable(), ruleCase.getRuleCaseTargetField(), ruleCase.getRuleCaseTargetConditionField());
		long srcNum = (resultSrc != null) ? resultSrc.getExecResult() : 0L;
		long tgtNum = (resultTgt != null) ? resultTgt.getExecResult() : 0L;
		int diffPcnt = (srcNum == 0L && tgtNum == 0L) ? 0 : (int)(Math.abs(tgtNum - srcNum)*100/Math.max(tgtNum, srcNum));
		Date endedDate = new Date();
		RuleR33mcCaseRunHistory rh = new RuleR33mcCaseRunHistory(id, (diffPcnt <= 50) ? "PASSED" : "FAILED", srcNum, tgtNum, diffPcnt, 50, startedDate, endedDate);
		ruleR33mcCaseDao.insertHistoryOne(rh);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void runRcR33mcByName(String name) {
		RuleR33mcCase rc = ruleR33mcCaseDao.findByRuleCaseName(name);
		runRcR33mcById(rc.getRuleCaseId());
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void runRcR33mcBatch(String ids) {
		if (ids.equals("all")) {
			List<Integer> arr_ids = ruleR33mcCaseDao.findAll().stream().map((rc) -> rc.getRuleCaseId()).collect(Collectors.toList());
			for (int id : arr_ids) {
				runRcR33mcById(id);
			}
		} else {
			for (String id : ids.split("-")) {
				runRcR33mcById(Integer.valueOf(id));
			}
		}
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isRuleCaseNameDuplicate(String ruleCaseName) {
		RuleR33mcCase rc = ruleR33mcCaseDao.findByRuleCaseName(ruleCaseName);
		return (rc != null) ? true : false;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void addNewR33mc(R33mcForm form) {
		UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
		DsConnection connSrc = dsConnectionDao.findByConnectionName(form.getSelRuleCaseConnectionSrc());
		DsConnection connTgt = dsConnectionDao.findByConnectionName(form.getSelRuleCaseConnectionTgt());
		RuleR33mcCase rc = new RuleR33mcCase();
		rc.setRuleCaseName(form.getTxtRuleCaseName());
		rc.setRuleCaseOwner(form.getTxtRuleCaseOwner().toUpperCase());
		rc.setRuleCaseBusinessFunction(form.getTxtRuleCaseBusinessFunction());
		rc.setRuleCaseDescription(form.getTxtRuleCaseDescription());
		rc.setRuleCaseSourceTable(form.getTxtRuleCaseSourceTable());
		rc.setRuleCaseSourceField(form.getTxtRuleCaseSourceField());
		rc.setRuleCaseSourceConditionField(form.getTxtRuleCaseSourceConditionField());
		rc.setRuleCaseTargetTable(form.getTxtRuleCaseTargetTable());
		rc.setRuleCaseTargetField(form.getTxtRuleCaseTargetField());
		rc.setRuleCaseTargetConditionField(form.getTxtRuleCaseTargetConditionField());
		rc.setRuleCaseLastModifiedBy(user.getUserName().toUpperCase());
		rc.setConnectionSrcId(connSrc.getConnectionId());
		rc.setConnectionTgtId(connTgt.getConnectionId());
		ruleR33mcCaseDao.insertOne(rc);
		runRcR33mcByName(form.getTxtRuleCaseName());
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public void copyNewR33mc(int id) {
		UserProfile user = (UserProfile)session.getAttribute(WebAppConstant.USER_CONFIG);
		RuleR33mcCase r33c = ruleR33mcCaseDao.findById(id);
		RuleR33mcCase rc = new RuleR33mcCase();
		rc.setRuleCaseName("Copy of " + r33c.getRuleCaseName());
		rc.setRuleCaseOwner(r33c.getRuleCaseOwner());
		rc.setRuleCaseBusinessFunction(r33c.getRuleCaseBusinessFunction());
		rc.setRuleCaseDescription("Copy of " + r33c.getRuleCaseDescription());
		rc.setRuleCaseSourceTable(r33c.getRuleCaseSourceTable());
		rc.setRuleCaseSourceField(r33c.getRuleCaseSourceField());
		rc.setRuleCaseSourceConditionField(r33c.getRuleCaseSourceConditionField());
		rc.setRuleCaseTargetTable(r33c.getRuleCaseTargetTable());
		rc.setRuleCaseTargetField(r33c.getRuleCaseTargetField());
		rc.setRuleCaseTargetConditionField(r33c.getRuleCaseTargetConditionField());
		rc.setRuleCaseLastModifiedBy(user.getUserName().toUpperCase());
		rc.setConnectionSrcId(r33c.getConnectionSrc().getConnectionId());
		rc.setConnectionTgtId(r33c.getConnectionTgt().getConnectionId());
		ruleR33mcCaseDao.insertOne(rc);
		runRcR33mcByName(rc.getRuleCaseName());
	}

	@Transactional(readOnly=true)
	@Override
	public List<RuleR33mcCase> filterForR33mcRuleCases(String srcConnName, String tgtConnName, String bizFunc, String caseOwner) {
		List<RuleR33mcCase> list = ruleR33mcCaseDao.findAll();
		if (!srcConnName.equals("-")) {
			List<String> srcConnNames = Arrays.asList(srcConnName.split(","));
			list = list.stream().filter((rc) -> srcConnNames.contains(rc.getConnectionSrc().getConnectionName())).collect(Collectors.toList());
		}
		if (!tgtConnName.equals("-")) {
			List<String> tgtConnNames = Arrays.asList(tgtConnName.split(","));
			list = list.stream().filter((rc) -> tgtConnNames.contains(rc.getConnectionTgt().getConnectionName())).collect(Collectors.toList());
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
	public void downloadRule33AsExcel(OutputStream os) {
		Rule33ExcelModel rule33ExcelModel = new Rule33ExcelModel(os);
		// create an Excel sheet
		WritableSheet excelSheet = rule33ExcelModel.createExcelWritableSheet(Rule33Excel.NAME_OF_RULE33_STEP_01_XLS_SHEET, Rule33Excel.IDX_XLS_SHEET_OF_RULE33_STEP_01);
		// set column width
		rule33ExcelModel.setColumnsWidth(excelSheet, Rule33Excel.ARR_COLS_WIDTH);
		// define the cell format for table field head
		rule33ExcelModel.buildForTableFieldHead(excelSheet);
		// fetch the data which be used for rendering
		List<RuleR33mcCase> dataList = ruleR33mcCaseDao.findAll();
		// write all the records into excel
		rule33ExcelModel.buildForTableBodyRows(excelSheet, dataList);
		rule33ExcelModel.writeAndClose();
	}

	@Transactional(readOnly=true)
	@Override
	public List<String> getAllCaseOwnersAsStrings() {
		List<RuleR33mcCase> rcs = ruleR33mcCaseDao.findAll();
		List<String> resList = rcs.stream().map((rc) -> rc.getRuleCaseOwner().toUpperCase()).distinct().collect(Collectors.toList());
		return resList;
	}
}