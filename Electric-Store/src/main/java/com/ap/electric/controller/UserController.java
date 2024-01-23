package com.ap.electric.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ap.electric.helper.ApiResponse;
import com.ap.electric.helper.UserRequest;
import com.ap.electric.helper.UserResponse;
import com.ap.electric.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/v1/createuser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
		UserResponse createUser = userService.createUser(userRequest);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);

	}

	@PutMapping(value = "/v1/{userId}/updateuser", consumes = {"application/json","application/xml"}, produces = "application/json")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest, @PathVariable String userId) {
		UserResponse updateUser = userService.updateUser(userRequest, userId);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);

	}

	@DeleteMapping(value = "/v1/{userId}/deleteuser")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User Deleted ..!!", Boolean.TRUE) ,HttpStatus.OK);

	}

	@GetMapping(value = "/v1/getuser/{userId}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
		UserResponse user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@GetMapping(value = "/v1/getuserbyemail/{email}")
	public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
		UserResponse user = userService.getUserByEmail(email);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@GetMapping(value = "/v1/contaningname/{name}")
	public ResponseEntity<List<UserResponse>> getUserByName(@PathVariable String name) {
		List<UserResponse> userList = userService.getByKeyword(name);
		return new ResponseEntity<>(userList, HttpStatus.OK);

	}
	
	@GetMapping(value = "/v1/allusers")
	public ResponseEntity<List<UserResponse>> getAllUsers() {

		List<UserResponse> allUsers = userService.getAllUsers();
		
		return new ResponseEntity<>(allUsers, HttpStatus.OK);

	}
	
	

}
