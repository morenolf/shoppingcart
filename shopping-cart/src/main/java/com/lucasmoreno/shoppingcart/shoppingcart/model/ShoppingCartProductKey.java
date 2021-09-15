package com.lucasmoreno.shoppingcart.shoppingcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShoppingCartProductKey implements Serializable {
	
	    @Column(name = "shopping_cart_id")
	    Long shoppingCartId;

	    @Column(name = "product_id")
	    Long productId;

		public Long getShoppingCartId() {
			return shoppingCartId;
		}

		public void setShoppingCartId(Long shoppingCartId) {
			this.shoppingCartId = shoppingCartId;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}
}
