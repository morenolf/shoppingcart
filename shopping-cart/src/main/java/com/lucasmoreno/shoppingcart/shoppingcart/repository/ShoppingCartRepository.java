package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;

public interface ShoppingCartRepository extends JpaRepository<Product, Long> {
	
	
}
