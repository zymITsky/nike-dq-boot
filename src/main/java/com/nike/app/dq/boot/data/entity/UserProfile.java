
package com.nike.app.dq.boot.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = -6698433029100509696L;

	private int userId = 0;
	private String userName = null;
	private String userPassword = null;
	private List<UserRole> roles = new ArrayList<UserRole>();

	public UserProfile() {
		
	}

	public String toString() {
		return "User(userId=" + userId + ",userName=" + userName + ",userPassword=" + userPassword + ",roles=" + roles + ")";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
}