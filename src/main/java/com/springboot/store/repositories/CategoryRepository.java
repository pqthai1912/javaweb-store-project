package com.springboot.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.store.models.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
