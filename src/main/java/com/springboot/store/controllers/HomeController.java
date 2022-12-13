package com.springboot.store.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.store.models.Product;
import com.springboot.store.services.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String index(Model model) {
		int numberShowProducts = getRandomInteger(100, 1) % 2;
		List<Product> products = productService.findFirstProducts();
		model.addAttribute("products", products);
		model.addAttribute("numberShowProducts", numberShowProducts);
		return "index";
	}

	@RequestMapping("/dashboard")
	public String dashboard(Model model) {
		List<Product> products = productService.findFirstProducts();
		model.addAttribute("products", products);
		return "indexAdmin";
	}

	/* * returns random integer between minimum and maximum range */
	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

}
