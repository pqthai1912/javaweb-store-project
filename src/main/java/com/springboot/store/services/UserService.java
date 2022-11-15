package com.springboot.store.services;


import java.util.List;

import com.springboot.store.models.User;


public interface UserService {
	
	User findById(Long id);
	
	User findByUsername(String username);
		
	User findByEmail(String email);
		
	void save(User user);
	
	User createUser(String username, String password, String email, List<String> roles);

}
