package com.lucasmoreno.shoppingcart.shoppingcart.dto;

import com.lucasmoreno.shoppingcart.shoppingcart.model.CurrencyType;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;

public class ShoppingCartProductDto {

	private Long productId;
	private String description;
	private Double price;
	private CurrencyType currency;
	private Long quantity;

	public ShoppingCartProductDto(ShoppingCartProduct shoppingCartProduct) {

		this.productId = shoppingCartProduct.getProduct().getProductId();
		this.description = shoppingCartProduct.getProduct().getDescription();
		this.price = shoppingCartProduct.getProduct().getPrice();
		this.currency = shoppingCartProduct.getProduct().getCurrency();
		this.quantity = shoppingCartProduct.getProductQuantity();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
