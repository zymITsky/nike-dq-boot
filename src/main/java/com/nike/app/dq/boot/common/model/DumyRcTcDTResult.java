
package com.nike.app.dq.boot.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.entity.RuleTcCase;

public class DumyRcTcDTResult {

	@SuppressWarnings("unchecked")
	public static <T> Map<String, List<T>> dumyEmptyData() {
		Map<String, List<T>> result = new HashMap<String, List<T>>();
		DsConnection connection = new DsConnection(0, "", "", "", "", "", "", "", new Date());
		RuleTcCase ruleTcCase = new RuleTcCase(0, "", "", "", "", 0, 0.0, 0.0, "", "", new Date());
		ruleTcCase.setConnection(connection);
		List<RuleTcCase> data = new ArrayList<RuleTcCase>();
		data.add(ruleTcCase);
		result.put("data", (List<T>)data);
		result.put("options", (List<T>)(new ArrayList<String>()));
		result.put("files", (List<T>)(new ArrayList<String>()));
		return result;
	}
}