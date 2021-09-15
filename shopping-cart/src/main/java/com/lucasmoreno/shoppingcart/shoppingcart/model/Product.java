package com.lucasmoreno.shoppingcart.shoppingcart.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

	@OneToMany(mappedBy = "product")
    Set<ShoppingCartProduct> shoppingCartProducts;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long productId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@Column(name = "currency")
	@Enumerated(EnumType.ORDINAL)
	private CurrencyType currency;

	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private ProductStatusType status;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}

	public ProductStatusType getStatus() {
		return status;
	}

	public void setStatus(ProductStatusType status) {
		this.status = status;
	}
}
