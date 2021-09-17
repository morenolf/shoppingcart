package com.lucasmoreno.shoppingcart.shoppingcart.dto;

public class ProductShoppingKeyDto {

	private Long productId;
	private Long shoppingcartId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getShoppingcartId() {
		return shoppingcartId;
	}

	public void setShoppingcartId(Long shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}

}
