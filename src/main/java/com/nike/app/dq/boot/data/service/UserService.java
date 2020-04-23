
package com.nike.app.dq.boot.data.service;

import com.nike.app.dq.boot.data.entity.UserProfile;

public interface UserService {

	UserProfile getUserByName(String userName);
	UserProfile getUserById(int userId);
}