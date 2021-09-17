package com.lucasmoreno.shoppingcart.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lucasmoreno.shoppingcart.shoppingcart.model.User;

@Service
public interface UserService {

	List<User> validUserByUserIdentification(Long userIdentification);

}
