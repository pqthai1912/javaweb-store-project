package com.springboot.store.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.store.models.Product;
import com.springboot.store.models.ProductBuilder;
import com.springboot.store.repositories.ProductRepository;
import com.springboot.store.repositories.ProductSpecification;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	 
	@Autowired
	private ProductRepository repository;
		 	
	@Before
	public void setUp() {
		Product product = new ProductBuilder()
				.withTitle("product1")
				.withPrice(50)
				.sizesAvailable(Arrays.asList("5.5","6.0"))
				.ofCategories(Arrays.asList("Đồng hồ thường"))
				.ofBrand(Arrays.asList("Casio"))
				.build();	
        entityManager.persist(product);            
        
        Product product2 = new ProductBuilder()
				.withTitle("product2")
				.withPrice(100)
				.sizesAvailable(Arrays.asList("6.0", "6.5"))
				.ofCategories(Arrays.asList("Đồng hồ trẻ em"))
				.ofBrand(Arrays.asList("Samsung"))
				.build();     
        entityManager.persist(product2);    
        
        Product product3 = new ProductBuilder()
				.withTitle("product3")
				.withPrice(200)
				.sizesAvailable(Arrays.asList("6.0", "5.0", "3.5"))
				.ofCategories(Arrays.asList("Đồng hồ thông minh"))
				.ofBrand(Arrays.asList("Apple"))
				.build();     
        entityManager.persist(product3); 
        
        Product product4 = new ProductBuilder()
				.withTitle("product4")
				.sizesAvailable(Arrays.asList("5.0", "5.5"))
				.ofBrand(Arrays.asList("Apple"))
				.withPrice(300)
				.build();     
        entityManager.persist(product4); 
        
        Product product5 = new ProductBuilder()
				.withTitle("product5")
				.withPrice(500)
				.ofBrand(Arrays.asList("Casio"))
				.build();     
        entityManager.persist(product5); 
	}
//		
	@Test
	public void should_find_all_distinct_sizes() {		
        assertThat(repository.findAllSizes()).hasSize(5).contains("5.5","6.0","6.5","3.5","5.0");        
	}
//	
	@Test
	public void should_find_all_distinct_brands() {		
        assertThat(repository.findAllBrands()).hasSize(3).contains("Samsung", "Apple", "Casio");        
	}
//	
	@Test
	public void should_find_all_distinct_categories() {		
        assertThat(repository.findAllCategories()).hasSize(3).contains("Đồng hồ thường", "Đồng hồ trẻ em", "Đồng hồ thông minh");	
	}
	//	
	@Test
	public void should_filter_products_between_prices() {
		int low = 100;
		int high = 300;
		List<Product> results = repository.findAll(ProductSpecification.filterBy(low, high, null, null, null, null));
		assertThat(results).hasSize(3).extracting("title").contains("product2", "product3", "product4");
	}
//	
	@Test
	public void should_filter_products_with_price_higher_than_number() {
		int low = 300;
		List<Product> results = repository.findAll(ProductSpecification.filterBy(low, null, null, null, null, null));
		assertThat(results).hasSize(2).extracting("title").contains("product4", "product5");
	}
//	
	@Test
	public void should_filter_products_with_price_lower_than_number() {
		int high = 200;
		List<Product> results = repository.findAll(ProductSpecification.filterBy(null, high, null, null, null, null));
		assertThat(results).hasSize(3).extracting("title").contains("product1", "product2", "product3");
	}
//	
	@Test
	public void should_filter_products_by_size() {
		List<Product> results = repository.findAll(ProductSpecification.filterBy(null, null, Arrays.asList("6.5", "5.0"), null, null, null));
		List<Product> results2 = repository.findAll(ProductSpecification.filterBy(null, null, Arrays.asList("6.0"), null, null, null));
		assertThat(results).hasSize(3).extracting("title").contains("product2", "product3", "product4");
		assertThat(results2).hasSize(3).extracting("title").contains("product1", "product2", "product3");
	}
//	
	@Test
	public void should_filter_products_by_category() {
		List<Product> results = repository.findAll(ProductSpecification.filterBy(null, null, null, Arrays.asList("Đồng hồ thường", "Đồng hồ trẻ em"), null, null));
		List<Product> results2 = repository.findAll(ProductSpecification.filterBy(null, null, null, Arrays.asList("Đồng hồ thông minh", "notARealCategory"), null, null));
		assertThat(results).hasSize(2).extracting("title").contains("product1", "product2");
		assertThat(results2).hasSize(1).extracting("title").contains("product3");
	}
//
	@Test
	public void should_find_all_if_all_filters_are_null() {
		List<Product> results = repository.findAll(ProductSpecification.filterBy(null, null, null, null, null, null));
		assertThat(results).hasSize(5).extracting("title").contains("product1", "product2", "product3", "product4", "product5");
	}
}
