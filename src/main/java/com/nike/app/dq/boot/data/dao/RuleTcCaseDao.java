
package com.nike.app.dq.boot.data.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nike.app.dq.boot.data.entity.RuleTcCase;
import com.nike.app.dq.boot.data.entity.TblRowCnt;
import com.nike.app.dq.boot.data.mapper.RuleTcCaseMapper;

@Repository("ruleTcCaseDao")
public class RuleTcCaseDao {

	@Autowired
	private RuleTcCaseMapper ruleCaseTcMapper = null;

	public List<RuleTcCase> findAll() {
		return ruleCaseTcMapper.findAll();
	}
	
	public RuleTcCase findById(int id) {
		return ruleCaseTcMapper.findById(id);
	}

	public RuleTcCase findByRuleCaseName(String ruleCaseName) {
		return ruleCaseTcMapper.findByRuleCaseName(ruleCaseName);
	}

	public void updateOne(int id, String ruleCaseDescription, int ruleCaseOriginalTableSize, double ruleCaseRowsGapGt, double ruleCaseRowsGapLt, String ruleCaseSeverity) {
		ruleCaseTcMapper.updateOne(id, ruleCaseDescription, ruleCaseOriginalTableSize, ruleCaseRowsGapGt, ruleCaseRowsGapLt, ruleCaseSeverity);
	}

	public TblRowCnt getTableRowCount(String tableName) {
		return ruleCaseTcMapper.getTableRowCount(tableName);
	}

	public void insertHistoryOne(int ruleCaseId, int ruleCaseRunRows, Date ruleCaseRunStartDate, Date ruleCaseRunEndDate) {
		ruleCaseTcMapper.insertHistoryOne(ruleCaseId, ruleCaseRunRows, ruleCaseRunStartDate, ruleCaseRunEndDate);
	}

	public void insertOne(RuleTcCase rtcc) {
		ruleCaseTcMapper.insertOne(rtcc);
	}

	public void deleteByRuleCaseId(int ruleCaseId) {
		ruleCaseTcMapper.deleteByRuleCaseId(ruleCaseId);
	}

	public void deleteRunHistoryByCaseId(int caseId) {
		ruleCaseTcMapper.deleteRunHistoryByCaseId(caseId);
	}
}