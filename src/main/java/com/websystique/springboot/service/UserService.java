package com.websystique.springboot.service;


import java.util.List;

import com.websystique.springboot.entity.UserInformation;
import com.websystique.springboot.model.User;


public interface UserService {
	
	UserInformation findById(int id);
	UserInformation findByName(String name);
	void saveUser(User user);
	void updateUser(UserInformation user);
	void deleteUserById(int id);
	List<UserInformation> findAllUsers();
	void deleteAllUsers();
	boolean isUserExist(User user);
	
}
