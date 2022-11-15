package com.springboot.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.store.models.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {

}
