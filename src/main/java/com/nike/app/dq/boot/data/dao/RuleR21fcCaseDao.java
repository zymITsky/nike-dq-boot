
package com.nike.app.dq.boot.data.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nike.app.dq.boot.data.entity.R21fcExecResult;
import com.nike.app.dq.boot.data.entity.RuleR21fcCase;
import com.nike.app.dq.boot.data.entity.RuleR21fcCaseRunHistory;
import com.nike.app.dq.boot.data.mapper.RuleR21fcCaseMapper;

@Repository("ruleR21fcCaseDao")
public class RuleR21fcCaseDao {

	@Autowired
	private RuleR21fcCaseMapper ruleR21fcCaseMapper = null;

	public List<RuleR21fcCase> findAll() {
		return ruleR21fcCaseMapper.findAll();
	}

	public RuleR21fcCase findById(int id) {
		return ruleR21fcCaseMapper.findById(id);
	}

	public RuleR21fcCase findByRuleCaseName(String ruleCaseName) {
		return ruleR21fcCaseMapper.findByRuleCaseName(ruleCaseName);
	}

	public void updateOne(RuleR21fcCase rr21fcc) {
		ruleR21fcCaseMapper.updateOne(rr21fcc);
	}

	public R21fcExecResult execQueryR21fcVerify(String db_link, String db, String table, String field, String conditionField) {
		return ruleR21fcCaseMapper.execQueryR21fcVerify((!db_link.equalsIgnoreCase("LOCAL_HOST")) ? ("[" + db_link + "]." + db + ".dbo." + table) : (db + ".dbo." + table), field, conditionField);
	}

	public void insertHistoryOne(RuleR21fcCaseRunHistory rh) {
		ruleR21fcCaseMapper.insertHistoryOne(rh);
	}

	public void insertOne(RuleR21fcCase rr21fcc) {
		ruleR21fcCaseMapper.insertOne(rr21fcc);
	}

	public void deleteByRuleCaseId(int ruleCaseId) {
		ruleR21fcCaseMapper.deleteByRuleCaseId(ruleCaseId);
	}

	public void deleteRunHistoryByCaseId(int caseId) {
		ruleR21fcCaseMapper.deleteRunHistoryByCaseId(caseId);
	}
}