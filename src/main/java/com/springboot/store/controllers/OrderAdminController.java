package com.springboot.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.store.models.CartItem;
import com.springboot.store.models.Order;
import com.springboot.store.services.OrderService;

@Controller
public class OrderAdminController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/product/order-list")
	public String orderList(Model model, Authentication authentication) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "orderListAdmin"; //xong
	}
	
	@RequestMapping(value = "/product/order-list", method = RequestMethod.POST)
	public String orderAccept(@RequestParam("orderId") Long orderId,
							@RequestParam("approved") Integer approved, Model model) {		
		Order order = orderService.findOrderById(orderId);

		orderService.updateStatus(order, approved); // thực hiên cập nhật dựa theo approved
		
		return "redirect:/product/order-list/";
	}
	
	@RequestMapping("/product/order-list/order-detail")
	public String orderDetail(@RequestParam("order") Long id, Model model) {
		Order order = orderService.findOrderWithDetails(id);
		model.addAttribute("order", order);
		return "orderDetails2"; //xong
	}
	
//	public String orderDetail(@RequestParam("order") Long id, Model model) {
//		Order order = orderService.findOrderWithDetails(id);
//		model.addAttribute("order", order);
//		return "orderListAdmin";
//	}
	

}
