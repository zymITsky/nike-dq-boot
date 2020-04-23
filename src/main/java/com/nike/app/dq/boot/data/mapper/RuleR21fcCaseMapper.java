
package com.nike.app.dq.boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nike.app.dq.boot.data.entity.R21fcExecResult;
import com.nike.app.dq.boot.data.entity.RuleR21fcCase;
import com.nike.app.dq.boot.data.entity.RuleR21fcCaseRunHistory;

@Mapper
public interface RuleR21fcCaseMapper {

	List<RuleR21fcCase> findAll();
	RuleR21fcCase findById(@Param("id") int id);
	RuleR21fcCase findByRuleCaseName(@Param("ruleCaseName") String ruleCaseName);
	void updateOne(RuleR21fcCase rr21fcc);
	R21fcExecResult execQueryR21fcVerify(@Param("table") String table, @Param("field") String field, @Param("conditionField") String conditionField);
	void insertHistoryOne(RuleR21fcCaseRunHistory rh);
	void insertOne(RuleR21fcCase rr21fcc);
	void deleteByRuleCaseId(int ruleCaseId);
	void deleteRunHistoryByCaseId(int caseId);
}