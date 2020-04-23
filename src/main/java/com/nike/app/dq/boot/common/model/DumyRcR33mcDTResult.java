
package com.nike.app.dq.boot.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.RuleR33mcCase;

public class DumyRcR33mcDTResult {

	public static <T> Map<String, List<T>> dumyEmptySuccessData() {
		return dumyEmptyData(true, null);
	}

	public static <T> Map<String, List<T>> dumyEmptyFailureData(String msg) {
		return dumyEmptyData(false, msg);
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, List<T>> dumyEmptyData(boolean flag, String msg) {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		DsConnection connection = new DsConnection(0, "", "", "", "", "", "", "", new Date());
		RuleR33mcCase ruleCase = null;
		if (flag) {
			ruleCase = new RuleR33mcCase(0, "", "", "", "", "", "", "", "", "", "", "", new Date());
		} else {
			ruleCase = new RuleR33mcCase(-1, msg, "", "", "", "", "", "", "", "", "", "", new Date());
		}
		ruleCase.setConnectionSrc(connection);
		ruleCase.setConnectionTgt(connection);
		List<RuleR33mcCase> data = new ArrayList<RuleR33mcCase>();
		data.add(ruleCase);
		result.put("data", (List<T>)data);
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}
}