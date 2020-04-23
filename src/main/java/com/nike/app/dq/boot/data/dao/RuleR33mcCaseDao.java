
package com.nike.app.dq.boot.data.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nike.app.dq.boot.data.entity.R33mcExecResult;
import com.nike.app.dq.boot.data.entity.RuleR33mcCase;
import com.nike.app.dq.boot.data.entity.RuleR33mcCaseRunHistory;
import com.nike.app.dq.boot.data.mapper.RuleR33mcCaseMapper;

@Repository("ruleR33mcCaseDao")
public class RuleR33mcCaseDao {

	@Autowired
	private RuleR33mcCaseMapper ruleR33mcCaseMapper = null;

	public List<RuleR33mcCase> findAll() {
		return ruleR33mcCaseMapper.findAll();
	}

	public RuleR33mcCase findById(int id) {
		return ruleR33mcCaseMapper.findById(id);
	}

	public RuleR33mcCase findByRuleCaseName(String ruleCaseName) {
		return ruleR33mcCaseMapper.findByRuleCaseName(ruleCaseName);
	}

	public void updateOne(RuleR33mcCase rr21fcc) {
		ruleR33mcCaseMapper.updateOne(rr21fcc);
	}

	public R33mcExecResult execQueryR33mcVerify(String db_link, String db, String table, String field, String conditionField) {
		return ruleR33mcCaseMapper.execQueryR33mcVerify((!db_link.equalsIgnoreCase("LOCAL_HOST")) ? ("[" + db_link + "]." + db + ".dbo." + table) : (db + ".dbo." + table), field, conditionField);
	}

	public void insertHistoryOne(RuleR33mcCaseRunHistory rh) {
		ruleR33mcCaseMapper.insertHistoryOne(rh);
	}

	public void insertOne(RuleR33mcCase rr21fcc) {
		ruleR33mcCaseMapper.insertOne(rr21fcc);
	}

	public void deleteByRuleCaseId(int ruleCaseId) {
		ruleR33mcCaseMapper.deleteByRuleCaseId(ruleCaseId);
	}

	public void deleteRunHistoryByCaseId(int caseId) {
		ruleR33mcCaseMapper.deleteRunHistoryByCaseId(caseId);
	}
}