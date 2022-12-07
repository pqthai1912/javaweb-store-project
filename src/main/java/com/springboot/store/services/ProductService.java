package com.springboot.store.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.store.models.Product;
import com.springboot.store.models.User;

public interface ProductService {
	List<Product> findAllProducts();
	
	Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<String> categories, List<String> brands, String search);
		
	List<Product> findFirstProducts();

	Product findProductById(Long id);
	
	Product saveProduct(Product product);
	
	Product createProduct(String productName, Integer price, Integer amount);

	void deleteProductById(Long id);
	
	List<String> getAllSizes();

	List<String> getAllCategories();

	List<String> getAllBrands();

}
