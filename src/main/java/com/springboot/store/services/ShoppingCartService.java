package com.springboot.store.services;

import com.springboot.store.models.Product;
import com.springboot.store.models.CartItem;
import com.springboot.store.models.ShoppingCart;
import com.springboot.store.models.User;


public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addProductToShoppingCart(Product article, User user, int qty, String size);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
	
}
