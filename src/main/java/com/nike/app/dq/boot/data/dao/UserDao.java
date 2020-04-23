
package com.nike.app.dq.boot.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nike.app.dq.boot.data.entity.UserProfile;
import com.nike.app.dq.boot.data.mapper.UserMapper;

@Repository("userDao")
public class UserDao {

	@Autowired
	private UserMapper userMapper = null;

	public UserProfile findByUserName(String userName) {
		return userMapper.findByUserName(userName);
	}

	public UserProfile findByUserId(int userId) {
		return userMapper.findByUserId(userId);
	}
}