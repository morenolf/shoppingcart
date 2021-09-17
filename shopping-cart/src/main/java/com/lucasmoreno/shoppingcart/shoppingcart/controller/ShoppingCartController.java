package com.lucasmoreno.shoppingcart.shoppingcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductShoppingKeyDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ShoppingCartDto;
import com.lucasmoreno.shoppingcart.shoppingcart.service.ShoppingCartService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.UserService;

@RestController
@RequestMapping("shoppingcart")
public class ShoppingCartController {

	Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private UserService userService;

	@GetMapping(value = "/newShoppingCart")
	@ResponseBody
	public Long createShoppingCart(@RequestParam(value = "userIdentification") Long userIdentification)
			throws Exception {
		Long shoppingCart = shoppingCartService.createShoppingCart(userIdentification);
		return shoppingCart;
	}

	@RequestMapping(value = "/removeShoppingCart")
	@ResponseBody
	public void removeShoppingCart(@RequestParam(value = "shoppingCartId") Long shoppingCartId) throws Exception {
		shoppingCartService.removeShoppingCart(shoppingCartId);
	}

	@RequestMapping(value = "/addProductToShoppingCart", consumes = "application/json")
	@ResponseBody
	public ShoppingCartDto addProductToShoppingCart(
			@RequestParam(value = "shoppingCartId") ProductShoppingKeyDto productShoppingKeyDto) throws Exception {
		return shoppingCartService.addProductToShoppingCart(productShoppingKeyDto);
	}
}