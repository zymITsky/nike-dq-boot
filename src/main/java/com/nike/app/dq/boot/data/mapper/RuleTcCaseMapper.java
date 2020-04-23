
package com.nike.app.dq.boot.data.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nike.app.dq.boot.data.entity.RuleTcCase;
import com.nike.app.dq.boot.data.entity.TblRowCnt;

@Mapper
public interface RuleTcCaseMapper {

	List<RuleTcCase> findAll();
	RuleTcCase findById(@Param("id") int id);
	RuleTcCase findByRuleCaseName(@Param("ruleCaseName") String ruleCaseName);
	void updateOne(@Param("id") int id, @Param("ruleCaseDescription") String ruleCaseDescription, @Param("ruleCaseOriginalTableSize") int ruleCaseOriginalTableSize, @Param("ruleCaseRowsGapGt") double ruleCaseRowsGapGt, @Param("ruleCaseRowsGapLt") double ruleCaseRowsGapLt, @Param("ruleCaseSeverity") String ruleCaseSeverity);
	TblRowCnt getTableRowCount(@Param("tableName") String tableName);
	void insertHistoryOne(@Param("ruleCaseId") int ruleCaseId, @Param("ruleCaseRunRows") int ruleCaseRunRows, @Param("ruleCaseRunStartDate") Date ruleCaseRunStartDate, @Param("ruleCaseRunEndDate") Date ruleCaseRunEndDate);
	void insertOne(RuleTcCase rtcc);
	void deleteByRuleCaseId(int ruleCaseId);
	void deleteRunHistoryByCaseId(int caseId);
}