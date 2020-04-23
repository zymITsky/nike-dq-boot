
package com.nike.app.dq.boot.data.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nike.app.dq.boot.data.dao.DsConnectionDao;
import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.service.ConnectionService;

@Service("connectionService")
public class ConnectionServiceImpl implements ConnectionService {

	@Autowired
	private DsConnectionDao dsConnectionDao = null;

	@Transactional(readOnly=true)
	@Override
	public List<String> getAllConnectionsAsStrings() {
		List<DsConnection> conns = dsConnectionDao.findAll();
		List<String> result = conns.stream().map(conn -> (conn.getConnectionName())).collect(Collectors.toList());
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> getAllConnectionsAsDropdownList() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<DsConnection> conns = dsConnectionDao.findAll();
		result = conns.stream().map((conn) -> {
			Map<String, Object> hm = new HashMap<String, Object>();
			hm.put("connectionId", conn.getConnectionId());
			hm.put("connectionName", conn.getConnectionName());
			return hm;
		}).collect(Collectors.toList());
		return result;
	}
}