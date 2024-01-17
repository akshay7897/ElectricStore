package com.ap.electric.service;

import java.util.List;

import com.ap.electric.helper.UserRequest;
import com.ap.electric.helper.UserResponse;

public interface UserService {
	
	
	UserResponse createUser(UserRequest userRequest);
	
	UserResponse updateUser(UserRequest userRequest,String userId);
	
	void deleteUser(String userId);
	
	UserResponse getUserById(String userId);
	
	UserResponse getUserByEmail(String email);
	
	List<UserResponse> getAllUsers();
	
	List<UserResponse> getByKeyword(String keyword);

}
