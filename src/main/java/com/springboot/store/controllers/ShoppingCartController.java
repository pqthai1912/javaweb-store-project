package com.springboot.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.store.models.Product;
import com.springboot.store.models.CartItem;
import com.springboot.store.models.ShoppingCart;
import com.springboot.store.models.User;
import com.springboot.store.services.ProductService;
import com.springboot.store.services.ShoppingCartService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
		
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Authentication authentication) {		
		User user = (User) authentication.getPrincipal();		
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);		
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		model.addAttribute("shoppingCart", shoppingCart);		
		model.addAttribute("shoppingCartItemNumber",shoppingCartService.getItemsNumber(user)); // show số lượng trong giỏ hàng
		return "cart";
	}

	//thêm vào giỏ hàng
	@RequestMapping("/add-item")
	public String addItem(@ModelAttribute("product") Product product, @RequestParam("qty") String qty,
						  @RequestParam("size") String size, RedirectAttributes attributes, Model model, Authentication authentication) {
		product = productService.findProductById(product.getId());				
		if (!product.hasStock(Integer.parseInt(qty))) {
			attributes.addFlashAttribute("notEnoughStock", true); // khi sl sản phẩm hk đủ so với số lượng khách chọn
			return "redirect:/product-detail?id="+product.getId();
		}		
		User user = (User) authentication.getPrincipal();		
		shoppingCartService.addProductToShoppingCart(product, user, Integer.parseInt(qty), size);
		attributes.addFlashAttribute("addProductSuccess", true);
		return "redirect:/product-detail?id="+product.getId();
	}
	
	// cập nhật giỏ hàng
	@RequestMapping("/update-item")
	public String updateItemQuantity(@RequestParam("id") Long cartItemId,
									 @RequestParam("qty") Integer qty, Model model) {		
		CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}
		return "redirect:/shopping-cart/cart";
	}
	
	// xóa sản phẩm khỏi giỏ hàng
	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Long id) {		
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));		
		return "redirect:/shopping-cart/cart";
	} 
}
