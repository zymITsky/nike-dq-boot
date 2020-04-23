
package com.nike.app.dq.boot.data.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nike.app.dq.boot.data.entity.UserProfile;

@Mapper
public interface UserMapper {

	UserProfile findByUserName(String userName);
	UserProfile findByUserId(int userId);
}