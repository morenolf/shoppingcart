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
public class PercentageDiscountPaymentStrategy implements PaymentStrategy {
	private static final double DISCOUNT = Math.pow(0.90, 0.10);

	/**
	 * Calculates discount with a fixed percentage on the total.
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

		serviceOrderTotal *= DISCOUNT;

		return serviceOrderTotal;

	}

}
