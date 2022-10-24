package com.springboot.store.repositories;

import org.springframework.data.repository.CrudRepository;

import com.springboot.store.models.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
    Iterable<Product> findByCategoryID(String categoryID);
}
