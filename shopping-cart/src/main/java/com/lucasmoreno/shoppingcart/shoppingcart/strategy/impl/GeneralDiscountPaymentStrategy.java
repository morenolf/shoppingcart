package com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl;

import java.util.List;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.PaymentStrategy;

public class GeneralDiscountPaymentStrategy implements PaymentStrategy {
	private static final double DISCOUNT = 500.0;

	@Override
	public Double calculateTotal(ShoppingCart shoppingCart) {
		List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
		Double serviceOrderTotal = 0.0;

		for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {
			serviceOrderTotal += (shoppingCartProduct.getProduct().getPrice()
					* shoppingCartProduct.getProductQuantity());

		}

		if (serviceOrderTotal >= DISCOUNT) {
			serviceOrderTotal -= DISCOUNT;
		} else {
			serviceOrderTotal = 0.0;
		}

		return serviceOrderTotal;
	}

}
