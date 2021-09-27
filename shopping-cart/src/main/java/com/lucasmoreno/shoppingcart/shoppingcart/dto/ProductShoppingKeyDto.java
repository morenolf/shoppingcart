package com.lucasmoreno.shoppingcart.shoppingcart.dto;

public class ProductShoppingKeyDto {

	private Long productId;
	private Long shoppingCartId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

}
