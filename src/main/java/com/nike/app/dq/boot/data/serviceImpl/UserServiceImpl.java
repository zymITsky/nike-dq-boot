
package com.nike.app.dq.boot.data.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nike.app.dq.boot.data.dao.UserDao;
import com.nike.app.dq.boot.data.entity.UserProfile;
import com.nike.app.dq.boot.data.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao = null;

	@Transactional(readOnly=true)
	@Override
	public UserProfile getUserByName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Transactional(readOnly=true)
	@Override
	public UserProfile getUserById(int userId) {
		return userDao.findByUserId(userId);
	}
}