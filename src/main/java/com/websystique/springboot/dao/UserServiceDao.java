package com.websystique.springboot.dao;

import java.util.List;

import com.websystique.springboot.entity.UserInformation;
import com.websystique.springboot.model.User;

public interface UserServiceDao {
	
	UserInformation findById(int id);
	UserInformation findByName(String name);
	void saveUser(UserInformation user);
	void updateUser(UserInformation user);
	void deleteUserById(int id);
	List<UserInformation> findAllUsers();
	void deleteAllUsers();
	boolean isUserExist(User user);

}
