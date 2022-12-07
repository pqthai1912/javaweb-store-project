package com.springboot.store.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.store.filters.ProductFilterForm;
import com.springboot.store.models.Product;
import com.springboot.store.models.User;
import com.springboot.store.services.ProductService;
import com.springboot.store.type.SortFilter;

@Controller
public class ProductAdminController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/product/product-list")
	public String productList(@ModelAttribute("filters") ProductFilterForm filters, Model model) {
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
		return "productListAdmin";
	}
	
	@GetMapping("/product/add-product")
	public String addProductPage() {
		return "addProductAdmin";
	}
	
	@PostMapping("/product/add-product")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResults,
			  @ModelAttribute("productName") String productName, @ModelAttribute("productPrice") Integer productPrice, 
			  @ModelAttribute("productStock") Integer productStock, RedirectAttributes redirectAttributes, Model model) {
//		model.addAttribute("email", product.getEmail());
//		model.addAttribute("username", product.getUsername());	
		
		boolean invalidFields = false;
		if (bindingResults.hasErrors()) {
			return "redirect:/product/add-product";
		}		
//		if (userService.findByUsername(user.getUsername()) != null) {
//			redirectAttributes.addFlashAttribute("usernameExists", true);
//			invalidFields = true;
//		}
//		if (userService.findByEmail(user.getEmail()) != null) {
//			redirectAttributes.addFlashAttribute("emailExists", true);
//			invalidFields = true;
//		}	
		if (invalidFields) {
			return "redirect:/product/add-product";
		}		
		
		product = productService.createProduct(productName, productPrice, productStock);	
		//TODO Popup xác nhận thêm sản phẩm; thêm trường brand, size,...; validation; popup hiển thị thểm sản phẩm thành công
		return "redirect:/product/add-product";
	}
	
	
	
	

}
