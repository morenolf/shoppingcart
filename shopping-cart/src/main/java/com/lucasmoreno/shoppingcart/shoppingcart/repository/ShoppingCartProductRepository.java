package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProductKey;

public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, ShoppingCartProductKey>{

}
