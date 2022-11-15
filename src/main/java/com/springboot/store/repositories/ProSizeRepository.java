package com.springboot.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.store.models.Size;


@Repository
public interface ProSizeRepository extends JpaRepository<Size, Long> {

}
