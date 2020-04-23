
package com.nike.app.dq.boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nike.app.dq.boot.data.entity.DsConnection;

@Mapper
public interface DsConnectionMapper {

	List<DsConnection> findAll();
	DsConnection findByConnectionName(@Param("connectionName") String connectionName);
	DsConnection findById(@Param("connectionId") int connectionId);
}