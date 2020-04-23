
package com.nike.app.dq.boot.data.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nike.app.dq.boot.data.dao.DsConnectionDao;
import com.nike.app.dq.boot.data.dao.RuleQsCaseDao;
import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.QryStrExecResult;
import com.nike.app.dq.boot.data.entity.RuleQsCase;
import com.nike.app.dq.boot.data.entity.RuleQsCaseRunHistory;
import com.nike.app.dq.boot.data.service.RuleQsService;
import com.nike.app.dq.boot.util.convert.UrlEncodedFormFactory;
import com.nike.app.dq.boot.web.mvc.bean.form.QsRcActionForm;
import com.nike.app.dq.boot.web.mvc.bean.form.RqscForm;

@Service("ruleQsService")
public class RuleQsServiceImpl implements RuleQsService {

	@Autowired
	private RuleQsCaseDao ruleQsCaseDao = null;

	@Autowired
	private DsConnectionDao dsConnectionDao = null;

	@Transactional(readOnly=true)
	@Override
	public List<RuleQsCase> getAllTcRuleCases() {
		return ruleQsCaseDao.findAll();
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void doDatatableActForRcQs(String requestBody) {
		QsRcActionForm qsRcActionForm = UrlEncodedFormFactory.getInstance().generateForQsRcActionForm(requestBody);
		if (qsRcActionForm.getActionName().equals("edit")) {
			RuleQsCase rqsc = ruleQsCaseDao.findById(qsRcActionForm.getId());
			rqsc.toSyn(qsRcActionForm);
			ruleQsCaseDao.updateOne(rqsc);
		} else if (qsRcActionForm.getActionName().equals("remove")) {
			removeRuleQueryStringCase(qsRcActionForm.getId());
		}
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void removeRuleQueryStringCase(int ruleCaseId) {
		ruleQsCaseDao.deleteRunHistoryByCaseId(ruleCaseId);
		ruleQsCaseDao.deleteByRuleCaseId(ruleCaseId);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void runRcQsById(int id) {
		Date startedDate = new Date();
		RuleQsCase ruleQsCase = ruleQsCaseDao.findById(id);
		QryStrExecResult result = ruleQsCaseDao.execQueryString(ruleQsCase.getRuleCaseSql());
		Date endDate = new Date();
		RuleQsCaseRunHistory rh = new RuleQsCaseRunHistory(id, (result == null) ? 0 : result.getExecResult(), startedDate, endDate);
		ruleQsCaseDao.insertHistoryOne(rh);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void runRcQsByName(String name) {
		RuleQsCase rqsc = ruleQsCaseDao.findByRuleCaseName(name);
		runRcQsById(rqsc.getRuleCaseId());
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void runRcQsBatch(String ids) {
		if (ids.equals("all")) {
			List<Integer> arr_ids = ruleQsCaseDao.findAll().stream().map((rc) -> rc.getRuleCaseId()).collect(Collectors.toList());
			for (int id : arr_ids) {
				runRcQsById(id);
			}
		} else {
			for (String id : ids.split("-")) {
				runRcQsById(Integer.valueOf(id));
			}
		}
	}

	@Transactional(readOnly=true)
	@Override
	public boolean isRuleCaseNameDuplicate(String ruleCaseName) {
		RuleQsCase rqsc = ruleQsCaseDao.findByRuleCaseName(ruleCaseName);
		return (rqsc != null) ? true : false;
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
	@Override
	public void addNewRqsc(RqscForm rqscForm) {
		DsConnection conn = dsConnectionDao.findByConnectionName(rqscForm.getSelRuleCaseConnection());
		RuleQsCase rqsc = new RuleQsCase();
		rqsc.setRuleCaseName(rqscForm.getTxtRuleCaseName());
		rqsc.setRuleCaseDescription(rqscForm.getTxtRuleCaseDescription());
		rqsc.setRuleCaseSql(rqscForm.getTxtRuleCaseSql());
		rqsc.setConnectionId(conn.getConnectionId());
		rqsc.setRuleCaseSeverity(rqscForm.getSelRuleCaseSeverity());
		rqsc.setRuleCaseCreatedBy(rqscForm.getCreatedBy());
		ruleQsCaseDao.insertOne(rqsc);
		runRcQsByName(rqscForm.getTxtRuleCaseName());
	}
}