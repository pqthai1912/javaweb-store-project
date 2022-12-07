package com.springboot.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.store.models.Product;
import com.springboot.store.services.ProductService;


@Controller
public class HomeController {
		
	@Autowired
	private ProductService  productService;
	
	
	@RequestMapping("/")
	public String index(Model model) {		
		List<Product> products = productService.findFirstProducts();
		model.addAttribute("products", products);
//		return "indexAdmin"; Tạm thời, để dễ dàng kiểm tra pages cho admin
		return "index";
	}

	
}
