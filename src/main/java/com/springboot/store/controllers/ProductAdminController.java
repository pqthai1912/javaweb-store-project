package com.springboot.store.controllers;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.store.filters.ProductFilterForm;
import com.springboot.store.models.Brand;
import com.springboot.store.models.Category;
import com.springboot.store.models.Product;
import com.springboot.store.models.Size;
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
	
	@Autowired
	ServletContext app;
	
	@PostMapping("/product/add-product")
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResults,
			  @ModelAttribute("productName") String productName, @ModelAttribute("productPrice") double productPrice, 
			  @ModelAttribute("productStock") Integer productStock, @ModelAttribute("productDesc") String productDesc, @ModelAttribute("productBrand") String productBrand,
			  @ModelAttribute("productCategory") String productCategory, @ModelAttribute("productSize") String productSize, @ModelAttribute("photo") MultipartFile photo,
			  @ModelAttribute("brand") Brand brand, @ModelAttribute("category") Category category, @ModelAttribute("size") Size size,
			  RedirectAttributes redirectAttributes, Model model) {
		
		boolean invalidFields = false;
		if (bindingResults.hasErrors()) {
			return "redirect:/product/add-product";
		}		
		if (productService.findByTitle(productName) != null ) {
			redirectAttributes.addFlashAttribute("productNameExists", "Sản phẩm này đã tồn tại.");
			invalidFields = true;
		}
		if (!photo.getContentType().equals("image/jpeg") ) {
			redirectAttributes.addFlashAttribute("fileTypeNotAllowed", "Ảnh phải có định dạng jpg.");
			invalidFields = true;
		}
		if (invalidFields) {
			return "redirect:/product/add-product";
		}		
		
		String path = app.getRealPath("/");
		path = path.substring(0, path.length() - 7);
		path = path + "resources\\static\\image\\product\\pictures\\";
		
		String fullFileName = photo.getOriginalFilename();
		String fileName = fullFileName.substring(0, fullFileName.lastIndexOf('.'));
		
		try {
			photo.transferTo(Path.of(path + fullFileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		product = productService.createProduct(productName, productPrice, productStock, productDesc, fileName);
		brand = new Brand(productBrand, product);
		productService.saveBrand(brand);
		category = new Category(productCategory, product);
		productService.saveCategory(category);
		size = new Size(productSize, product);
		productService.saveSize(size);
		//TODO Popup xác nhận thêm sản phẩm; thêm trường brand, size,...; validation; popup hiển thị thểm sản phẩm thành công
		return "redirect:/product/add-product";
	}
	
	@GetMapping("/product/product-detail")
	public String productDetail(@PathParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		model.addAttribute("product", product);
		return "productDetailAdmin";
	}
	
	@GetMapping("/product/edit-product")
	public String editProductPage(@PathParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);
		Brand brand = productService.findBrandByProductId(id);
		Category category = productService.findCategoryByProductId(id);
		model.addAttribute("product", product);
		model.addAttribute("brand", brand);
		model.addAttribute("category", category);
		return "editProductAdmin";
	}
	
	@PostMapping("/product/edit-product")
	public String editProduct(
			  @ModelAttribute("id") Long id, @ModelAttribute("currentProductName") String currentProductName,@ModelAttribute("productName") String productName, @ModelAttribute("productPrice") double productPrice, 
			  @ModelAttribute("productStock") Integer productStock, @ModelAttribute("productBrand") String productBrand,
			  @ModelAttribute("productCategory") String productCategory, @ModelAttribute("productDesc") String productDesc, 
			  RedirectAttributes redirectAttributes) {
		
		boolean invalidFields = false;	
		if ((productService.findByTitle(productName) != null ) && (currentProductName.compareTo(productName) != 0 ) ) {
			redirectAttributes.addFlashAttribute("productNameExists", "Sản phẩm này đã tồn tại.");
			
			invalidFields = true;
		} 
		if (invalidFields) {
			return "redirect:/product/edit-product?id=" + id;
		}		
		
		Product product = productService.findProductById(id);
		Brand brand = productService.findBrandByProductId(id);
		Category category = productService.findCategoryByProductId(id);
		
		product.setTitle(productName);
		product.setPrice(productPrice);
		product.setStock(productStock);
		product.setDescription(productDesc);
		brand.setName(productBrand);
		brand.setProduct(product);
		category.setName(productCategory);
		category.setProduct(product);
		
		
		productService.saveBrand(brand);
		productService.saveCategory(category);
		productService.saveProduct(product);
		
		return "redirect:/product/product-detail?id=" + id;
//TODO brand, category
	}
	
	@GetMapping("/product/delete-product")
	public String deleteProduct(@PathParam("id") Long id, Model model) {
//		Product product = productService.findProductById(id);
		productService.deleteProductById(id);
		return "redirect:/product/product-list";
	}
	
	
	

}
