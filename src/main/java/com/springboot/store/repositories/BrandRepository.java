package com.springboot.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.store.models.Brand;
import com.springboot.store.models.User;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	Brand findBrandById(Long id);
	Brand findBrandByProductId(Long id);

}
