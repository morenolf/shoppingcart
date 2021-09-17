package com.lucasmoreno.shoppingcart.shoppingcart.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucasmoreno.shoppingcart.shoppingcart.model.User;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.UserRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.service.UserService;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Validates if a user exist on the repository.
	 * @param User identification
	 * @return User if exist.
	 */
	@Override
	public Optional<User> findFirstByUserIdentification(Long userIdentification) {
		return this.userRepository.findFirstByIdentification(userIdentification);
	}
	

}
