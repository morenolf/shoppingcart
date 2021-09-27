package com.lucasmoreno.shoppingcart.shoppingcart.service.impl;

import org.springframework.stereotype.Component;

import com.lucasmoreno.shoppingcart.shoppingcart.service.PromotionalDateService;

@Component
public class PromotionalDateServiceImpl implements PromotionalDateService{

	private boolean promotionalDate;
	
	@Override
	public boolean validatePromotionalDate() {
		return promotionalDate;
	}
	
	@Override
	public void setPromotionalDate(boolean promotionalDate) {
		this.promotionalDate = promotionalDate;
	}

}
