
package com.nike.app.dq.boot.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.RuleQsCase;

public class DumyRcQsDTResult {

	@SuppressWarnings("unchecked")
	public static <T> Map<String, List<T>> dumyEmptyData() {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		DsConnection connection = new DsConnection(0, "", "", "", "", "", "", "", new Date());
		RuleQsCase ruleQsCase = new RuleQsCase(0, "", "", "", "", "", new Date());
		ruleQsCase.setConnection(connection);
		List<RuleQsCase> data = new ArrayList<RuleQsCase>();
		data.add(ruleQsCase);
		result.put("data", (List<T>)data);
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}
}