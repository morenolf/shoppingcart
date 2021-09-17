package com.lucasmoreno.shoppingcart.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductShoppingKeyDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ShoppingCartDto;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;

@Service
public interface ShoppingCartService {

	public Long createShoppingCart(Long userIdentification);

	public void removeShoppingCart(Long shoppingCartId);

	public ShoppingCartDto addProductToShoppingCart(ProductShoppingKeyDto productShoppingKeyDto);

	public ShoppingCartDto removeProductToShoppingCart(Long productId, Long shoppingCartId);

	public List<Product> retrieveMoreExpensiveProductsByUser(Long userIdentification);

	public Optional<ShoppingCart> findByShopingCartById(Long shoppingCartId);

}
