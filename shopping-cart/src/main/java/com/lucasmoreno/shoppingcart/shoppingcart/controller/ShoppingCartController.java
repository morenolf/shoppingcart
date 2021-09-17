package com.lucasmoreno.shoppingcart.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductShoppingKeyDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ShoppingCartDto;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ProductRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.service.ShoppingCartService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.UserService;

@RestController
@RequestMapping("shoppingcart")
public class ShoppingCartController {

	Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Creates a new shopping cart based on an user identification.
	 * 
	 * @param userIdentification
	 * @return Shopping Cart Id
	 * @throws Exception
	 */
	@GetMapping(value = "/newShoppingCart", produces = "application/json")
	@ResponseBody
	public Long createShoppingCart(@RequestBody Long userIdentification) {
		return shoppingCartService.createShoppingCart(userIdentification);
	}

	/**
	 * Deletes a shopping cart by id.
	 * 
	 * @param shoppingCartId
	 */

	@RequestMapping(value = "/removeShoppingCart", produces = "application/json")
	@ResponseBody
	public void removeShoppingCart(@RequestBody Long shoppingCartId) {
		shoppingCartService.removeShoppingCart(shoppingCartId);
	}

	/**
	 * Add a product to the shopping cart and returns the shopping cart Dto.
	 * 
	 * @param productShoppingKeyDto
	 * @return ShoppingCartDto with shopping cart id, status and product list.
	 */
	@RequestMapping(value = "/addProductToShoppingCart", consumes = "application/json")
	@ResponseBody
	public ShoppingCartDto addProductToShoppingCart(@RequestBody ProductShoppingKeyDto productShoppingKeyDto) {
		return shoppingCartService.addProductToShoppingCart(productShoppingKeyDto);
	}

	/**
	 * Removes a product from the shopping cart and returns the shopping cart Dto.
	 * 
	 * @param productShoppingKeyDto
	 * @return ShoppingCartDto with shopping cart id, status and product list.
	 */

	@RequestMapping(value = "/removeProductFromShoppingCart", consumes = "application/json")
	@ResponseBody
	public ShoppingCartDto removeProductFromShoppingCart(@RequestBody ProductShoppingKeyDto productShoppingKeyDto) {
		return shoppingCartService.removeProductFromShoppingCart(productShoppingKeyDto);
	}

	/**
	 * 
	 * Calculates the more expensive products for a User in his purchase history.
	 * 
	 * @param userIdentification
	 * @return List with ProductDto
	 */
	@RequestMapping(value = "/getMoreExpensiveProductsByUser", produces = "application/json")
	@ResponseBody
	public List<ProductDto> getMoreExpensiveProductsByUser(@RequestBody Long userIdentification) {
		return shoppingCartService.retrieveMoreExpensiveProductsByUser(userIdentification);
	}

	/**
	 * 
	 * Calculates the more expensive products for a User in his purchase history.
	 * 
	 * @param userIdentification
	 * @return List with ProductDto
	 */
	@RequestMapping(value = "/getProductById", produces = "application/json")
	@ResponseBody
	public ProductDto getProductById(@RequestBody Long productId) {
		Product product = this.productRepository.findById(productId).orElse(null);
		return new ProductDto(product);
	}
}