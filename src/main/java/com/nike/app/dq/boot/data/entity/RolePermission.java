
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;

public class RolePermission implements Serializable {

	private static final long serialVersionUID = 7078574021564101472L;

	private int userRolePermissionId = 0;
	private String permission = null;

	public RolePermission() {
		
	}

	public String toString() {
		return "Permission(userRolePermissionId=" + userRolePermissionId + ",permission=" + permission + ")";
	}

	public int getUserRolePermissionId() {
		return userRolePermissionId;
	}

	public void setUserRolePermissionId(int userRolePermissionId) {
		this.userRolePermissionId = userRolePermissionId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}