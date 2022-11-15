package com.springboot.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.store.models.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
