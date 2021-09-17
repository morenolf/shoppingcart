package com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.PaymentStrategy;


/**
 * Strategy pattern for a discount for VIP users.
 * 
 * @author Lucas
 *
 */

public class VipDiscountPaymentStrategy implements PaymentStrategy {
	private static final double DISCOUNT = 700.0;

	/**
	 * Calculates discount with a fixed amount and removes the price of the less expensive product on the shopping cart, if possible.
	 * @param Shopping Cart.
	 * @return Total with discount calculation.
	 */
	@Override
	public Double calculateTotal(ShoppingCart shoppingCart) {
		List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();

		List<Product> products = shoppingCartProducts.stream().map(ShoppingCartProduct::getProduct)
				.collect(Collectors.toList());

		Product product = products.stream().min(Comparator.comparing(Product::getPrice)).orElse(null);

		Double serviceOrderTotal = 0.0;

		for (ShoppingCartProduct shoppingCartProduct : shoppingCartProducts) {

			if (product != null && shoppingCartProduct.getProduct().getProductId().equals(product.getProductId())) {
				serviceOrderTotal += (shoppingCartProduct.getProduct().getPrice()
						* shoppingCartProduct.getProductQuantity() - 1);
			} else {
				serviceOrderTotal += (shoppingCartProduct.getProduct().getPrice()
						* shoppingCartProduct.getProductQuantity());
			}
		}

		if (serviceOrderTotal >= DISCOUNT) {
			serviceOrderTotal -= DISCOUNT;
		} else {
			serviceOrderTotal = 0.0;
		}

		return serviceOrderTotal;
	}

}
