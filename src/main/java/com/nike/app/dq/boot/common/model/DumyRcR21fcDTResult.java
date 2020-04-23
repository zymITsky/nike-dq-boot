
package com.nike.app.dq.boot.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.RuleR21fcCase;

public class DumyRcR21fcDTResult {

	public static <T> Map<String, List<T>> dumyEmptySuccessData() {
		return dumyEmptyData(true, null);
	}

	public static <T> Map<String, List<T>> dumyEmptyFailureData(String errMsg) {
		return dumyEmptyData(false, errMsg);
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, List<T>> dumyEmptyData(boolean flag, String msg) {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		DsConnection connection = new DsConnection(0, "", "", "", "", "", "", "", new Date());
		RuleR21fcCase ruleCase = null;
		if (flag) {
			ruleCase = new RuleR21fcCase(0, "", "", "", "", "", "", "", "", new Date());
		} else {
			ruleCase = new RuleR21fcCase(-1, msg, "", "", "", "", "", "", "", new Date());
		}
		ruleCase.setConnection(connection);
		List<RuleR21fcCase> data = new ArrayList<RuleR21fcCase>();
		data.add(ruleCase);
		result.put("data", (List<T>)data);
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}
}