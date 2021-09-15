package com.lucasmoreno.shoppingcart.shoppingcart.model;

public enum CurrencyType {

	USD(1), ARG(2);
	
	private int currency;
	 
	private CurrencyType(int currency) {
		this.currency = currency;
	}
 
	public int getProductStatus() {
		return currency;
	}
}
