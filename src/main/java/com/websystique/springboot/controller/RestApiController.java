package com.websystique.springboot.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.websystique.springboot.entity.UserInformation;
import com.websystique.springboot.model.User;
import com.websystique.springboot.service.UserService;
import com.websystique.springboot.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://www.ru-rocker.com", allowedHeaders = "*")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	
	@RequestMapping(value ="/",produces = "application/json")
	public String getURLValue(HttpServletRequest request){
	    String test = request.getRequestURI();
	    return test;
	}
	// -------------------Retrieve All UserInformations---------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	@Profile(value = "Test")
	public ResponseEntity<List<UserInformation>> listAllUserInformations() {
		List<UserInformation> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UserInformation>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single UserInformation------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserInformation(@PathVariable("id") int id) throws URISyntaxException {
		logger.info("Fetching UserInformation with id {}", id);
		UserInformation user = userService.findById(id);
		/*
		 * HttpHeaders responseHeaders = new HttpHeaders();
		 * responseHeaders.set("Content-type", "XML/asdasd");
		 */
		
		if (user == null) {
			logger.error("UserInformation with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("UserInformation with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		//return new ResponseEntity<UserInformation>(user, responseHeaders, HttpStatus.OK);
		return new ResponseEntity<UserInformation>(user, HttpStatus.OK);
	}

	// -------------------Create a UserInformation-------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	@Profile(value = "Test")
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating UserInformation : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A UserInformation with name {} already exist", user.getName()); 
			return new ResponseEntity(new CustomErrorType("Unable to create. A UserInformation with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a UserInformation ------------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@Profile(value = "Test")
	public ResponseEntity<?> updateUserInformation(@PathVariable("id") int id, @RequestBody UserInformation user) {
		logger.info("Updating UserInformation with id {}", id);

		UserInformation currentUserInformation = userService.findById(id);

		if (currentUserInformation == null) {
			logger.error("Unable to update. UserInformation with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. UserInformation with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUserInformation.setName(user.getName());
		currentUserInformation.setAge(user.getAge());
		currentUserInformation.setSalary(user.getSalary());

		userService.updateUser(currentUserInformation);
		return new ResponseEntity<UserInformation>(currentUserInformation, HttpStatus.OK);
	}

	// ------------------- Delete a UserInformation-----------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@Profile(value = "Test")
	public ResponseEntity<?> deleteUserInformation(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting UserInformation with id {}", id);

		UserInformation user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. UserInformation with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. UserInformation with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<UserInformation>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All UserInformations-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	@Profile(value = "Test")
	public ResponseEntity<UserInformation> deleteAllUserInformations() {
		logger.info("Deleting All UserInformations");

		userService.deleteAllUsers();
		return new ResponseEntity<UserInformation>(HttpStatus.NO_CONTENT);
	}

}