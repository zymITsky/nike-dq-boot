
package com.nike.app.dq.boot.data.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nike.app.dq.boot.data.entity.QryStrExecResult;
import com.nike.app.dq.boot.data.entity.RuleQsCase;
import com.nike.app.dq.boot.data.entity.RuleQsCaseRunHistory;
import com.nike.app.dq.boot.data.mapper.RuleQsCaseMapper;

@Repository("ruleQsCaseDao")
public class RuleQsCaseDao {

	@Autowired
	private RuleQsCaseMapper ruleCaseQsMapper = null;

	public List<RuleQsCase> findAll() {
		return ruleCaseQsMapper.findAll();
	}
	
	public RuleQsCase findById(int id) {
		return ruleCaseQsMapper.findById(id);
	}

	public RuleQsCase findByRuleCaseName(String ruleCaseName) {
		return ruleCaseQsMapper.findByRuleCaseName(ruleCaseName);
	}

	public void updateOne(RuleQsCase rqsc) {
		ruleCaseQsMapper.updateOne(rqsc);
	}

	public QryStrExecResult execQueryString(String queryString) {
		return ruleCaseQsMapper.execQueryString(queryString);
	}

	public void insertHistoryOne(RuleQsCaseRunHistory rh) {
		ruleCaseQsMapper.insertHistoryOne(rh);
	}

	public void insertOne(RuleQsCase rqsc) {
		ruleCaseQsMapper.insertOne(rqsc);
	}

	public void deleteByRuleCaseId(int ruleCaseId) {
		ruleCaseQsMapper.deleteByRuleCaseId(ruleCaseId);
	}

	public void deleteRunHistoryByCaseId(int caseId) {
		ruleCaseQsMapper.deleteRunHistoryByCaseId(caseId);
	}
}