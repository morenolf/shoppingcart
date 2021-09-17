package com.lucasmoreno.shoppingcart.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucasmoreno.shoppingcart.shoppingcart.model.User;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.UserRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> validUserByUserIdentification(Long userIdentification) {
		return this.userRepository.findByIdentification(userIdentification);
	}

}
