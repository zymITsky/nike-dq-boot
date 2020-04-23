
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserRole implements Serializable {

	private static final long serialVersionUID = -9082000524414928464L;

	private int userRoleId = 0;
	private String roleName = null;
	private List<RolePermission> permissions = new ArrayList<RolePermission>();

	public UserRole() {
		
	}

	public String toString() {
		return "Role(userRoleId=" + userRoleId + ",roleName=" + roleName + ",permissions=" + permissions + ")";
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<RolePermission> permissions) {
		this.permissions = permissions;
	}
}