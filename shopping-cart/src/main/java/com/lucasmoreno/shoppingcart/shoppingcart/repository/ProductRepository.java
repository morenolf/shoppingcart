package com.lucasmoreno.shoppingcart.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
