package com.springboot.store.services;

import java.util.List;

import com.springboot.store.models.Order;
import com.springboot.store.models.Payment;
import com.springboot.store.models.Shipping;
import com.springboot.store.models.ShoppingCart;
import com.springboot.store.models.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
