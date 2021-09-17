package com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl;

import java.util.List;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.PaymentStrategy;

/**
 * Strategy pattern default calculation for total.
 * 
 * @author Lucas
 *
 */
public class GeneralPaymentStrategy implements PaymentStrategy {

	/**
	 * Calculates total base on number of products and price. 
	 */
	@Override
	public Double calculateTotal(ShoppingCart shoppingCart) {

		List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();

		Double serviceOrderTotal = 0.0;
		for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {

			serviceOrderTotal += (shoppingCartProduct.getProduct().getPrice()
					* shoppingCartProduct.getProductQuantity());

		}

		return serviceOrderTotal;
	}

}
