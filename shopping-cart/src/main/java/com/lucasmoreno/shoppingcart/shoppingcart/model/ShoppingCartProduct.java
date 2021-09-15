package com.lucasmoreno.shoppingcart.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity

@Table(name = "shopping_cart_products")
public class ShoppingCartProduct {

	@EmbeddedId
	ShoppingCartProductKey shoppingCartProductId;

	@ManyToOne
	@MapsId("shoppingCartId")
	@JoinColumn(name = "shopping_cart_id")
	ShoppingCart shoppingCart;

	@ManyToOne
	@MapsId("productId")
	@JoinColumn(name = "product_id")
	Product product;
		    
	@Column(name = "quantity")
    Long productQuantity;
	
}
