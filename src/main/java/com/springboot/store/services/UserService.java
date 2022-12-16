package com.springboot.store.services;


import java.util.List;

import com.springboot.store.models.User;


public interface UserService {
	
	User findById(Long id);
	// Thêm 1methhod 
	List < User > findAll();
	
	
	User findByUsername(String username);
		
	User findByEmail(String email);
		
	void save(User user);
	
	User createUser(String username, String password, String email, List<String> roles);
	//void deleteByUsername(String username);
	//void deleteUserById(Long id);
	//void deleteUserById(String id);
	void deleteUserById(long id);
	

}
