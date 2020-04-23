
package com.nike.app.dq.boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nike.app.dq.boot.data.entity.QryStrExecResult;
import com.nike.app.dq.boot.data.entity.RuleQsCase;
import com.nike.app.dq.boot.data.entity.RuleQsCaseRunHistory;

@Mapper
public interface RuleQsCaseMapper {

	List<RuleQsCase> findAll();
	RuleQsCase findById(@Param("id") int id);
	RuleQsCase findByRuleCaseName(@Param("ruleCaseName") String ruleCaseName);
	void updateOne(RuleQsCase rqsc);
	QryStrExecResult execQueryString(@Param("queryString") String queryString);
	void insertHistoryOne(RuleQsCaseRunHistory rh);
	void insertOne(RuleQsCase rqsc);
	void deleteByRuleCaseId(int ruleCaseId);
	void deleteRunHistoryByCaseId(int caseId);
}