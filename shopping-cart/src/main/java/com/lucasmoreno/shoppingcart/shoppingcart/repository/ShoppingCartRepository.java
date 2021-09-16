package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
	
	
}
