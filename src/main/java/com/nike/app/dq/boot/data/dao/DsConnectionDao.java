
package com.nike.app.dq.boot.data.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nike.app.dq.boot.data.entity.DsConnection;
import com.nike.app.dq.boot.data.mapper.DsConnectionMapper;

@Repository("dsConnectionDao")
public class DsConnectionDao {

	@Autowired
	private DsConnectionMapper dsConnectionMapper = null;

	public List<DsConnection> findAll() {
		return dsConnectionMapper.findAll();
	}

	public DsConnection findByConnectionName(String connectionName) {
		return dsConnectionMapper.findByConnectionName(connectionName);
	}

	public DsConnection findById(int connectionId) {
		return dsConnectionMapper.findById(connectionId);
	}
}