package com.lucasmoreno.shoppingcart.shoppingcart.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductShoppingKeyDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ShoppingCartDto;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.model.User;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ProductRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ShoppingCartRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.service.PromotionalDateService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.ShoppingCartService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.UserService;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.GeneralDiscountPaymentStrategy;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.GeneralPaymentStrategy;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.PercentageDiscountPaymentStrategy;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.VipDiscountPaymentStrategy;

@Component
public class ShoppingCartImpl implements ShoppingCartService {

	private static final long MAX_PRODUCTS = 10L;
	private static final long MIN_PRODUCTS = 10L;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PromotionalDateService promotionalDateService;

	@Override
	public ShoppingCartDto removeProductToShoppingCart(Long productId, Long shoppingCartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> retrieveMoreExpensiveProductsByUser(Long userIdentification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShoppingCart> findByShopingCartById(Long shoppingCartId) {
		return shoppingCartRepository.findById(shoppingCartId);
	}

	@Override
	public Long createShoppingCart(Long userIdentification) {
		List<User> user = this.userService.validUserByUserIdentification(userIdentification);
		if (user.isEmpty()) {
			// TODO analyze how to reply when the user doesn't exist.
			return null;
		}

		List<ShoppingCart> shoppingCartList = this.shoppingCartRepository
				.findByUserUserIdAndStatus(user.get(0).getUserId(), true);

		ShoppingCart shoppingCart;
		if (shoppingCartList.isEmpty()) {
			shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user.get(0));
			shoppingCart.setStatus(true);
			shoppingCartRepository.save(shoppingCart);
		} else {
			shoppingCart = shoppingCartList.get(0);
		}

		return shoppingCart.getShoppingCartId();
	}

	@Override
	public void removeShoppingCart(Long shoppingCartId) {
		Optional<ShoppingCart> shoppingCartList = this.shoppingCartRepository.findById(shoppingCartId);
		// TODO verify if it's possible to change this and avoid finding the cart first.
		if (shoppingCartList.isPresent()) {
			shoppingCartRepository.deleteById(shoppingCartId);
		}

	}

	@Override
	public ShoppingCartDto addProductToShoppingCart(ProductShoppingKeyDto productShoppingKeyDto) {
		return this.generateShoppingCartDto(this.generateUpdatedShoppingCart(productShoppingKeyDto));
	}

	private ShoppingCart generateUpdatedShoppingCart(ProductShoppingKeyDto productShoppingKeyDto) {
		Optional<ShoppingCart> shoppingCartList = this.shoppingCartRepository
				.findById(productShoppingKeyDto.getShoppingcartId());
		ShoppingCart ShoppingCart;
		ShoppingCartProduct shoppingCartProduct;
		if (shoppingCartList.isPresent()) {
			ShoppingCart = shoppingCartList.get();
			List<ShoppingCartProduct> shoppingCartProducts = ShoppingCart.getShoppingCartProducts();
			shoppingCartProduct = ShoppingCart.getShoppingCartProducts().stream()
					.filter(shoppingCartProduct1 -> shoppingCartProduct1.getProduct().getProductId()
							.equals(productShoppingKeyDto.getProductId()))
					.findAny().orElse(null);

			if (shoppingCartProducts.isEmpty()) {
				Product product = this.productRepository.getById(productShoppingKeyDto.getProductId());
				shoppingCartProduct.setProduct(product);
				shoppingCartProduct.setProductQuantity(1L);
				shoppingCartProduct.setShoppingCartProductId(productShoppingKeyDto.getShoppingcartId());

			} else {
				shoppingCartProduct.setProductQuantity(shoppingCartProduct.getProductQuantity() + 1);
			}

			shoppingCartProducts.add(shoppingCartProduct);
			ShoppingCart.setShoppingCartProducts(shoppingCartProducts);
		} else {
			// TODO create flow to inform that the shopping cart doesn't exist.
			return null;
		}
		return ShoppingCart;
	}

	private ShoppingCartDto generateShoppingCartDto(ShoppingCart shoppingCart) {

		ShoppingCartDto shoppingCartDto = new ShoppingCartDto(shoppingCart);

		shoppingCartDto.setTotal(this.setShoppingCartStrategy(shoppingCart));

		return shoppingCartDto;

	}

	private double setShoppingCartStrategy(ShoppingCart shoppingCart) {

		Long totalProducts = 0L;
		double totalPrice = 0.0;

		if (totalProducts == MAX_PRODUCTS) {
			totalPrice = shoppingCart.calculateTotalPrice(new PercentageDiscountPaymentStrategy());
		} else if (totalProducts > MIN_PRODUCTS) {
			if (this.promotionalDateService.validate()) {
				totalPrice = shoppingCart.calculateTotalPrice(new GeneralDiscountPaymentStrategy());
			} else if (shoppingCart.getUser().isVip()) {
				totalPrice = shoppingCart.calculateTotalPrice(new VipDiscountPaymentStrategy());
			} else {
				totalPrice = shoppingCart.calculateTotalPrice(new GeneralPaymentStrategy());
			}
		}

		return totalPrice;
	}

}
