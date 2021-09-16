package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long>{

}
