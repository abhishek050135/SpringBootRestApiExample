package com.websystique.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springboot.dao.UserServiceDao;
import com.websystique.springboot.entity.CountryDetails;
import com.websystique.springboot.entity.UserAddress;
import com.websystique.springboot.entity.UserInformation;
import com.websystique.springboot.model.User;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserServiceDao userServiceDao;
	
	/*
	 * static{ users= populateDummyUsers(); }
	 */

	
	public List<UserInformation> findAllUsers() {
		return userServiceDao.findAllUsers();
		//return users;
	}
	
	public UserInformation findById(int id) {
		return userServiceDao.findById(id);
		/*
		 * for(User user : users){ if(user.getId() == id){ return user; } } return null;
		 */
	}
	
	public UserInformation findByName(String name) {
		return userServiceDao.findByName(name);
		/*
		 * for(User user : users){ if(user.getName().equalsIgnoreCase(name)){ return
		 * user; } } return null;
		 */
	}
	
	public void saveUser(User user) {
		
		//BUSINESS LOGIC
		CountryDetails countrydetailsEntity = new CountryDetails();
		countrydetailsEntity.setCountryName(user.getCountry());
		
		
		UserAddress useraddressEntity = new UserAddress();
		useraddressEntity.setStreet(user.getStreet());
		useraddressEntity.setCity(user.getCity());
		useraddressEntity.setCountrydetails(countrydetailsEntity);
		
		UserInformation userinformationEntity = new UserInformation();
		userinformationEntity.setName(user.getName());
		userinformationEntity.setAge(user.getAge());
		userinformationEntity.setSalary(user.getSalary());
		userinformationEntity.setUseraddress(useraddressEntity);
		
		userServiceDao.saveUser(userinformationEntity);
		
		/*
		 * user.setId(counter.incrementAndGet()); users.add(user);
		 */
	}

	public void updateUser(UserInformation user) {
		userServiceDao.updateUser(user);
		/*
		 * int index = users.indexOf(user); users.set(index, user);
		 */
	}

	public void deleteUserById(int id) {
		userServiceDao.deleteUserById(id);
		/*
		 * for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) { User
		 * user = iterator.next(); if (user.getId() == id) { iterator.remove(); } }
		 */
	}

	public boolean isUserExist(User user) {
		return userServiceDao.isUserExist(user);
	}
	
	public void deleteAllUsers(){
		userServiceDao.deleteAllUsers();
	}

	/*
	 * private static List<User> populateDummyUsers(){ List<User> users = new
	 * ArrayList<User>(); users.add(new User(counter.incrementAndGet(),"Sam",30,
	 * 70000)); users.add(new User(counter.incrementAndGet(),"Tom",40, 50000));
	 * users.add(new User(counter.incrementAndGet(),"Jerome",45, 30000));
	 * users.add(new User(counter.incrementAndGet(),"Silvia",50, 40000)); return
	 * users; }
	 */
}
