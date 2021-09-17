package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByIdentification(Long userIdentification);

}
