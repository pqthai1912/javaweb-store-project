package com.springboot.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;

import com.springboot.store.models.Order;
import com.springboot.store.models.Payment;
import com.springboot.store.models.Shipping;
import com.springboot.store.models.ShoppingCart;
import com.springboot.store.models.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	Order findOrderById(Long id);
	
	List<Order> findAll();
	List<Order> findByUser(User user);
	
	
	void updateStatus(Order order, Integer approved);
	
	Order findOrderWithDetails(Long id);
}
