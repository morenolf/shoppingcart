package com.lucasmoreno.shoppingcart.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductShoppingKeyDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ShoppingCartDto;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.model.User;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ProductRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.UserRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.service.PromotionalDateService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.ShoppingCartService;

@RestController
@RequestMapping("shoppingcart")
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingCartController {

	Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private PromotionalDateService promotionalDateService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UserRepository userRepostory;
	
	/**
	 * Retrieves all promotional dates avialable.
	 * 
	 * @return List<Long>
	 */
	@RequestMapping(value = "/setPromotionalDates", produces = "application/json")
	@ResponseBody
	public void setPromotionalDates(@RequestBody boolean promotionalDate) {
		this.promotionalDateService.setPromotionalDate(promotionalDate);
	}
	
	/**
	 * Retrieves all possible products.
	 * 
	 * @return List<Product>
	 */
	@RequestMapping(value = "/listAllProducts", produces = "application/json")
	@ResponseBody
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Validates that the user logged in is valid.
	 * 
	 * @param userId
	 * @return User
	 */
	@RequestMapping(value = "/validateUser", produces = "application/json")
	@ResponseBody
	public User validateUser(@RequestBody Long userId) {
		return userRepostory.findById(userId).orElse(null);
	}
	
	/**
	 * Creates a new shopping cart based on an user identification.
	 * 
	 * @param Retrieves list of users 
	 * @return List<User> 
	 */
	@RequestMapping(value = "/getUsers", produces = "application/json")
	@ResponseBody
	public List<User> getUsers() {
		return userRepostory.findAll();
	}
	
	
	/**
	 * Creates a new shopping cart based on an user identification.
	 * 
	 * @param userIdentification
	 * @return Shopping Cart Id
	 */
	@RequestMapping(value = "/newShoppingCart", produces = "application/json")
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
	@PostMapping(value = "/addProductToShoppingCart")
	public ShoppingCartDto addProductToShoppingCart(@RequestBody ProductShoppingKeyDto productShoppingKeyDto) {
		return shoppingCartService.addProductToShoppingCart(productShoppingKeyDto);
	}

	/**
	 * Removes a product from the shopping cart and returns the shopping cart Dto.
	 * 
	 * @param productShoppingKeyDto
	 * @return ShoppingCartDto with shopping cart id, status and product list.
	 */

	@PostMapping(value = "/removeProductFromShoppingCart")
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