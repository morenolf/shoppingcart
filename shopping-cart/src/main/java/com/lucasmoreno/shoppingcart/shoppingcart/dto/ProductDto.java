package com.lucasmoreno.shoppingcart.shoppingcart.dto;

import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;

public class ProductDto {
	
	private Long productId;
	private String description;
	private Double price;

	public ProductDto(Product product) {

		this.productId = product.getProductId();
		this.description = product.getDescription();
		this.price = product.getPrice();
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

}
