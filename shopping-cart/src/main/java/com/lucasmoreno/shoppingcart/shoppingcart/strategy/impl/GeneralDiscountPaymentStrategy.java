package com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl;

import java.util.List;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.PaymentStrategy;

/**
 * Strategy pattern for a discount based on a percentage.
 * 
 * @author Lucas
 *
 */
public class GeneralDiscountPaymentStrategy implements PaymentStrategy {
	private static final double DISCOUNT = 500.0;


	/**
	 * Calculates discount with a fixed amount and removes the price of the less expensive product on the shopping cart, if possible.
	 * @param Shopping Cart.
	 * @return Total with discount calculation.
	 */
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
