
package com.nike.app.dq.boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nike.app.dq.boot.data.entity.R33mcExecResult;
import com.nike.app.dq.boot.data.entity.RuleR33mcCase;
import com.nike.app.dq.boot.data.entity.RuleR33mcCaseRunHistory;

@Mapper
public interface RuleR33mcCaseMapper {

	List<RuleR33mcCase> findAll();
	RuleR33mcCase findById(@Param("id") int id);
	RuleR33mcCase findByRuleCaseName(@Param("ruleCaseName") String ruleCaseName);
	void updateOne(RuleR33mcCase rr33mcc);
	R33mcExecResult execQueryR33mcVerify(@Param("table") String table, @Param("field") String field, @Param("conditionField") String conditionField);
	void insertHistoryOne(RuleR33mcCaseRunHistory rh);
	void insertOne(RuleR33mcCase rr21fcc);
	void deleteByRuleCaseId(int ruleCaseId);
	void deleteRunHistoryByCaseId(int caseId);
}