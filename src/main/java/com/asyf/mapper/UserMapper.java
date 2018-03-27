package com.asyf.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asyf.model.User;


public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(User id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	List<User> getAllUser(Map<String, String> param);

	Integer countUser(Map<String, String> param);

	List<User> listAllUser();

	List<User> findAllUser(HashMap<String, String> stringStringHashMap);
}