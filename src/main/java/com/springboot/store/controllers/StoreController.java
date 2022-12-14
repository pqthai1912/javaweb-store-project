package com.springboot.store.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.store.models.Category;
import com.springboot.store.models.Product;
import com.springboot.store.filters.ProductFilterForm;
import com.springboot.store.services.ProductService;
import com.springboot.store.type.SortFilter;

@Controller
public class StoreController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") ProductFilterForm filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());	
		Page<Product> pageresult = productService.findProductsByCriteria(PageRequest.of(pagenumber, 8, sortFilter.getSortType()), 
																filters.getPricelow(), filters.getPricehigh(), 
																filters.getSize(), filters.getCategory(), filters.getBrand(), filters.getSearch());	
		model.addAttribute("allCategories", productService.getAllCategories());
		model.addAttribute("allBrands", productService.getAllBrands());
		model.addAttribute("allSizes", productService.getAllSizes());
		model.addAttribute("products", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 8);
		return "store";
	}
	
	
	@RequestMapping("/product-detail")
	public String productDetail(@PathParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addProductSuccess", model.asMap().get("addProductSuccess"));
		
		String name = null;
		for(Category category : product.getCategories()) {
			name = category.getName();
			break; // l???y 1 th??? lo???i
		}
		model.addAttribute("productsByCategory", productService.findProductsByCategory(name, product.getId())); // get by category for product
		
		return "productDetail"; //tr??? v??? trang chi ti???t s???n ph???m
	}
	

}
