package com.lucasmoreno.shoppingcart.shoppingcart.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

	@OneToMany(mappedBy = "shopping_cart", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ShoppingCartProduct> shoppingCartProducts;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long shoppingCartId;

	@ManyToOne
	@Column(name = "user_id")
	private String userId;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name = "last_modified_timestamp")
	private Date lastModifiedTimestamp;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time_stamp")
	private Date creationtimestamp;

	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}

	public Date getCreationtimestamp() {
		return creationtimestamp;
	}

	public void setCreationtimestamp(Date creationtimestamp) {
		this.creationtimestamp = creationtimestamp;
	}

	
}
