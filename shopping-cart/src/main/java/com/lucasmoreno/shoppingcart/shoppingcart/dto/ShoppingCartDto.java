package com.lucasmoreno.shoppingcart.shoppingcart.dto;

import java.util.ArrayList;
import java.util.List;

import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;

public class ShoppingCartDto {

	private Long shoppingCartId;
	private List<ShoppingCartProductDto> shoppingCartProductsDtoList;
	private Double total;

	public ShoppingCartDto(ShoppingCart shoppingCart) {
		this.shoppingCartId = shoppingCart.getShoppingCartId();
		this.shoppingCartProductsDtoList = new ArrayList<>();
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

	public void setTotal(Double total) {
		this.total = total;
	}

}
