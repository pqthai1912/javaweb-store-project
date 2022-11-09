package com.springboot.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.store.other.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
