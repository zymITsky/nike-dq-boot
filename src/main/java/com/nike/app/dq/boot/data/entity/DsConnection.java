
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.Date;

public class DsConnection implements Serializable {

	private static final long serialVersionUID = -162211460773161074L;

	private int connectionId = 0;
	private String connectionName = null;
	private String connectionDescription = null;
	private String connectionServerType = null;
	private String connectionServerHost = null;
	private String connectionDbLink = null;
	private String connectionDbSchema = null;
	private String connectionCreatedBy = null;
	private Date connectionCreatedDatetime = null;

	public DsConnection() {
		
	}

	public DsConnection(int connectionId, String connectionName, String connectionDescription, String connectionServerType, String connectionServerHost, String connectionDbLink, String connectionDbSchema, String connectionCreatedBy, Date connectionCreatedDatetime) {
		this.connectionId = connectionId;
		this.connectionName = connectionName;
		this.connectionDescription = connectionDescription;
		this.connectionServerType = connectionServerType;
		this.connectionServerHost = connectionServerHost;
		this.connectionDbLink = connectionDbLink;
		this.connectionDbSchema = connectionDbSchema;
		this.connectionCreatedBy = connectionCreatedBy;
		this.connectionCreatedDatetime = connectionCreatedDatetime;
	}

	public String toString() {
		return "DsConnection(connectionId=" + connectionId + ",connectionName=" + connectionName + ",connectionDescription=" + connectionDescription + ",connectionServerType=" + connectionServerType + ",connectionServerHost=" + connectionServerHost + ",connectionDbLink=" + connectionDbLink + ",connectionDbSchema=" + connectionDbSchema + ",connectionCreatedBy=" + connectionCreatedBy + ",connectionCreatedDatetime=" + connectionCreatedDatetime + ")";
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getConnectionDescription() {
		return connectionDescription;
	}

	public void setConnectionDescription(String connectionDescription) {
		this.connectionDescription = connectionDescription;
	}

	public String getConnectionServerType() {
		return connectionServerType;
	}

	public void setConnectionServerType(String connectionServerType) {
		this.connectionServerType = connectionServerType;
	}

	public String getConnectionServerHost() {
		return connectionServerHost;
	}

	public void setConnectionServerHost(String connectionServerHost) {
		this.connectionServerHost = connectionServerHost;
	}

	public String getConnectionDbLink() {
		return connectionDbLink;
	}

	public void setConnectionDbLink(String connectionDbLink) {
		this.connectionDbLink = connectionDbLink;
	}

	public String getConnectionDbSchema() {
		return connectionDbSchema;
	}

	public void setConnectionDbSchema(String connectionDbSchema) {
		this.connectionDbSchema = connectionDbSchema;
	}

	public String getConnectionCreatedBy() {
		return connectionCreatedBy;
	}

	public void setConnectionCreatedBy(String connectionCreatedBy) {
		this.connectionCreatedBy = connectionCreatedBy;
	}

	public Date getConnectionCreatedDatetime() {
		return connectionCreatedDatetime;
	}

	public void setConnectionCreatedDatetime(Date connectionCreatedDatetime) {
		this.connectionCreatedDatetime = connectionCreatedDatetime;
	}
}