package com.lucasmoreno.shoppingcart.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "shopping_cart_products")
public class ShoppingCartProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long shoppingCartProductId;

	@ManyToOne
	Product product;

	@Column(name = "quantity")
	Long productQuantity;

	public Long getShoppingCartProductId() {
		return shoppingCartProductId;
	}

	public void setShoppingCartProductId(Long shoppingCartProductId) {
		this.shoppingCartProductId = shoppingCartProductId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Long productQuantity) {
		this.productQuantity = productQuantity;
	}

}
