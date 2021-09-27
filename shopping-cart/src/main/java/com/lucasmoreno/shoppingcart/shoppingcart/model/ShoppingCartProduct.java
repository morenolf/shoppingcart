package com.lucasmoreno.shoppingcart.shoppingcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;
	
	public ShoppingCartProduct() {
		super();
	}
	public ShoppingCartProduct(Product product) {
		this.product = product;
		this.productQuantity = 1L;
	}

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
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
}
