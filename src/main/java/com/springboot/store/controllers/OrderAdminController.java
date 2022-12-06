package com.springboot.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderAdminController {
	
	@RequestMapping("/order-list")
	public String addProduct() {
		return "orderListAdmin";
	}
	
	
	
	

}
