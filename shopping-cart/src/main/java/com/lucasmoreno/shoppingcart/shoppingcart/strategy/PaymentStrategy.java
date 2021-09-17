package com.lucasmoreno.shoppingcart.shoppingcart.strategy;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;

public interface PaymentStrategy {
	
	Double calculateTotal(ShoppingCart shoppingCart);

}
