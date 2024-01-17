package com.ap.electric.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ap.electric.entity.User;
import com.ap.electric.exception.UserException;
import com.ap.electric.helper.UserRequest;
import com.ap.electric.helper.UserResponse;
import com.ap.electric.repository.UserRepository;
import com.ap.electric.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	
	Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponse createUser(UserRequest userRequest) {
		User user = dtoToEntity(userRequest);
		user.setUserId(randomUserID());
		User userResponse = userRepository.save(user);
		return entityToDto(userResponse);
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest, String userId)  {
		User user=userRepository.findById(userId).orElseThrow(()->new UserException("user not found"));
	
		if(null!=userRequest.getName() && "".equals(userRequest.getName()))
			user.setName(userRequest.getName());
		
		if(null!=userRequest.getEmail() && "".equals(userRequest.getEmail()))
			user.setEmail(userRequest.getEmail());
		
		if(null!=userRequest.getPassword() && "".equals(userRequest.getPassword()))
			user.setPassword(userRequest.getPassword());
		
		if(null!=userRequest.getGender() && "".equals(userRequest.getGender()))
			user.setGender(userRequest.getGender());
		
		if(null!=userRequest.getAbout() && "".equals(userRequest.getAbout()))
			user.setAbout(userRequest.getAbout());
		
		return entityToDto(user);
	}

	@Override
	public void deleteUser(String userId) {
		
		boolean existsById = userRepository.existsById(userId);
		if(existsById) {
			userRepository.deleteById(userId);
		}
	}

	@Override
	public UserResponse getUserById(String userId) {
		User user=userRepository.findById(userId).orElseThrow(()->new UserException("user not found"));
		return entityToDto(user);
	}

	@Override
	public UserResponse getUserByEmail(String email) {
		User user=userRepository.findByEmail(email).orElseThrow(()->new UserException("User not found with email")); 
		return entityToDto(user);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		
		List<User> usersList = userRepository.findAll();
		List<UserResponse> userResponseList=new ArrayList<>();
		for(User user:usersList) {
			userResponseList.add(entityToDto(user));
		}
		return userResponseList;
	}

	@Override
	public  List<UserResponse> getByKeyword(String keyword) {
		List<User> users= userRepository.findByNameContaining(keyword).orElseThrow(()->new UserException("User not found with that name ...!!!"));
		List<UserResponse> userResponseList=new ArrayList<>();
		for(User user:users) {
			userResponseList.add(entityToDto(user));
		}
		return userResponseList;
	}
	
	
	private UserResponse entityToDto(User user) {
		return new UserResponse(user.getUserId(), user.getName(), user.getEmail(), user.getPassword(), user.getGender(),
				user.getAbout());
	}
	
	private User dtoToEntity(UserRequest userRequest) {
		User user=new User();
		user.setUserId(userRequest.getUserId());
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setGender(userRequest.getGender());
		return user;
	}
	
	private String randomUserID() {
		return UUID.randomUUID().toString();
	}

}
