package com.springboot.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.ecommerce.ecom.app.domain.Article;
//import com.ecommerce.ecom.app.service.ArticleService;

@Controller
public class HomeController {
		
//	@Autowired
//	private ArticleService articleService;
//	
	
	@RequestMapping("/")
	public String index(Model model) {		
//		List<Article> articles = articleService.findFirstArticles();
//		model.addAttribute("articles", articles);
		return "index";
	}

	
}
