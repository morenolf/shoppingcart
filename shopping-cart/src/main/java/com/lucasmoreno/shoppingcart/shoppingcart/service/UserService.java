package com.lucasmoreno.shoppingcart.shoppingcart.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.lucasmoreno.shoppingcart.shoppingcart.model.User;

@Service
public interface UserService {

	Optional<User> findFirstByUserIdentification(Long userIdentification);

}
