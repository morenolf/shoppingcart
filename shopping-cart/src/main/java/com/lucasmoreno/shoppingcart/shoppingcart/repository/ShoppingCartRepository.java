package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.User;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	List<ShoppingCart> findByUserUserIdAndStatus(Long userId, boolean status);
}
