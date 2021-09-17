package com.lucasmoreno.shoppingcart.shoppingcart.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;

public class ShoppingCartDto {

	private Long shoppingCartId;
	@Autowired
	private List<ShoppingCartProductDto> shoppingCartProductsDtoList;
	private double total;

	public ShoppingCartDto(ShoppingCart shoppingCart) {
		this.shoppingCartId = shoppingCart.getShoppingCartId();

		for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getShoppingCartProducts()) {
			shoppingCartProductsDtoList.add(new ShoppingCartProductDto(shoppingCartProduct));
		}
		this.setTotal(0.0);
	}

	public Long getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Long shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public List<ShoppingCartProductDto> getShoppingCartProductsDtoList() {
		return shoppingCartProductsDtoList;
	}

	public void setShoppingCartProductsDtoList(List<ShoppingCartProductDto> shoppingCartProductsDtoList) {
		this.shoppingCartProductsDtoList = shoppingCartProductsDtoList;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
