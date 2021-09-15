package com.lucasmoreno.shoppingcart.shoppingcart.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long productId;

	@Column(name = "name")
	String product;
		    
	@Column(name = "last_name")
    String productQuantity;
	
	@Column(name = "vip")
    boolean vip;
	
}
