package com.springboot.store.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.store.models.CartItem;
import com.springboot.store.models.Order;
import com.springboot.store.models.Payment;
import com.springboot.store.models.Product;
import com.springboot.store.models.Shipping;
import com.springboot.store.models.ShoppingCart;
import com.springboot.store.models.User;
import com.springboot.store.repositories.CartItemRepository;
import com.springboot.store.repositories.OrderRepository;
import com.springboot.store.repositories.ProductRepository;
import com.springboot.store.services.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	ProductRepository productRepository;
			
	@Override
	@Transactional
	@CacheEvict(value = "itemcount", allEntries = true)
	public synchronized Order createOrder(ShoppingCart shoppingCart, Shipping shipping, Payment payment, User user) {
		Order order = new Order();
		order.setUser(user);
		order.setPayment(payment);
		order.setShipping(shipping);
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shipping.setOrder(order);
		payment.setOrder(order);			
		LocalDate today = LocalDate.now();
		LocalDate estimatedDeliveryDate = today.plusDays(5);				
		order.setOrderDate(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setShippingDate(Date.from(estimatedDeliveryDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		order.setOrderStatus("Đang xử lý"); // set trạng thái
		
		order = orderRepository.save(order);
		
		List<CartItem> cartItems = shoppingCart.getCartItems();
		for (CartItem item : cartItems) {
			Product product = item.getProduct();
			product.decreaseStock(item.getQty());
			productRepository.save(product);
			item.setOrder(order);
			cartItemRepository.save(item);
		}		
		return order;	
	}
	
	@Override
	public Order findOrderWithDetails(Long id) {
		return orderRepository.findEagerById(id);
	}	
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}

	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}
	
	public Order findOrderById(Long id) {
		return orderRepository.findOrderById(id);
	}
	
	// cập nhật trạng thái
	@Override
	public void updateStatus(Order order, Integer approved) {
		if (approved == 0) {
			orderRepository.deleteById(order.getId());
		} else {
			order.setOrderStatus("Đang vận chuyển");
			orderRepository.save(order);
		}
	}

}
