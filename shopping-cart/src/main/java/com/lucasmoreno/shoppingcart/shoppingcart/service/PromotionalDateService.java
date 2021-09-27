package com.lucasmoreno.shoppingcart.shoppingcart.service;

import org.springframework.stereotype.Service;

@Service
public interface PromotionalDateService {

	void setPromotionalDate(boolean promotionalDate);

	boolean validatePromotionalDate();

}
