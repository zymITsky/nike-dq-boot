
package com.nike.app.dq.boot.data.service;

import java.util.List;
import java.util.Map;

public interface ConnectionService {

	List<String> getAllConnectionsAsStrings();
	List<Map<String, Object>> getAllConnectionsAsDropdownList();
}