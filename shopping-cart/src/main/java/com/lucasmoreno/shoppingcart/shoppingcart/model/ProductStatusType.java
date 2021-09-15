package com.lucasmoreno.shoppingcart.shoppingcart.model;

public enum ProductStatusType {

	ACTIVE(1),IN_ACTIVE(2);
	
	private int productStatus;
	 
	private ProductStatusType(int productStatus) {
		this.productStatus = productStatus;
	}
 
	public int getProductStatus() {
		return productStatus;
	}
}