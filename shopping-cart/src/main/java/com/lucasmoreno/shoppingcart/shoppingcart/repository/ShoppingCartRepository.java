package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartStatusType;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	Optional<ShoppingCart> findFirstByUserUserIdAndShoppingCartStatus(Long userId, ShoppingCartStatusType status);
	List<ShoppingCart> findAllByUserUserIdAndShoppingCartStatus(Long userId, ShoppingCartStatusType status);
}
