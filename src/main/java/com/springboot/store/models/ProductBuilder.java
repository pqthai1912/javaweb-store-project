package com.springboot.store.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductBuilder {

	private String title;
	private int stock;	
	private double price;
	private String picture;
	private List<String> sizes;
	private List<String> categories;
	private List<String> brands;
	
	public ProductBuilder() {
	}
	
	public ProductBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public ProductBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public ProductBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public ProductBuilder imageLink(String picture) {
		this.picture = picture;
		return this;
	}
	
	public ProductBuilder sizesAvailable(List<String> sizes) {
		this.sizes = sizes;
		return this;
	}
	
	public ProductBuilder ofCategories(List<String> categories) {
		this.categories = categories;
		return this;
	}
	
	public ProductBuilder ofBrand(List<String> brands) {
		this.brands = brands;
		return this;
	}
	
	public Product build() {
		Product product = new Product();
		product.setTitle(this.title);
		product.setPrice(this.price);
		product.setStock(this.stock);
		product.setPicture(this.picture);		
		
		if (this.sizes != null && !this.sizes.isEmpty()) {
			Set<Size> sizeElements = new HashSet<>();
			for (String val : this.sizes) {
				sizeElements.add(new Size(val,product));
			}	
			product.setSizes(sizeElements);
		}
		
		if (this.categories != null && !this.categories.isEmpty() ) {
			Set<Category> catElements = new HashSet<>();
			for (String val : this.categories) {
				catElements.add(new Category(val,product));
			}
			product.setCategories(catElements);
		}		
		if (this.brands != null && !this.brands.isEmpty() ) {
			Set<Brand> brandlements = new HashSet<>();
			for (String val : this.brands) {
				brandlements.add(new Brand(val,product));
			}
			product.setBrands(brandlements);
		}		
		
		
		return product;
	}
}
