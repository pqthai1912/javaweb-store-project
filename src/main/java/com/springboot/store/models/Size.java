package com.springboot.store.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Size implements Comparable<Size> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id") //  tên đại diện khi liên kết 2 bảng và đặt ở bảng mới
	private Product product;	
	private String value;
		
	public Size() {}
	
	public Size(String value, Product product) {
		this.value = value;
		this.product = product;
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int compareTo(Size s) {
		return this.value.compareTo(s.getValue());		
	}
	
	
	
}
