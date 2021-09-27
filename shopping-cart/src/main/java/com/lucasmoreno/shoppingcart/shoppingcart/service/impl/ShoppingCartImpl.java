package com.lucasmoreno.shoppingcart.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ProductShoppingKeyDto;
import com.lucasmoreno.shoppingcart.shoppingcart.dto.ShoppingCartDto;
import com.lucasmoreno.shoppingcart.shoppingcart.model.Product;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCart;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartProduct;
import com.lucasmoreno.shoppingcart.shoppingcart.model.ShoppingCartStatusType;
import com.lucasmoreno.shoppingcart.shoppingcart.model.User;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ProductRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ShoppingCartProductRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.repository.ShoppingCartRepository;
import com.lucasmoreno.shoppingcart.shoppingcart.service.PromotionalDateService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.ShoppingCartService;
import com.lucasmoreno.shoppingcart.shoppingcart.service.UserService;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.GeneralDiscountPaymentStrategy;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.GeneralPaymentStrategy;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.PercentageDiscountPaymentStrategy;
import com.lucasmoreno.shoppingcart.shoppingcart.strategy.impl.VipDiscountPaymentStrategy;

/**
 * Implementation of a Shopping Cart. Shopping Cart for this service will
 * persist the shopping cart if it's pending (only one per user) or the products
 * have been purchased. Allows to create, delete shopping carts. Add or remove
 * products from shopping cart. Calculate more expensive products purchased by
 * an User.
 * 
 * @author Lucas
 *
 */
@Component
public class ShoppingCartImpl implements ShoppingCartService {

	private static final Long MAX_PRODUCTS = 10L;
	private static final Long MIN_PRODUCTS = 10L;
	private static final Long NUMBER_OF_PRODUCTS = null;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private ShoppingCartProductRepository shoppingCartProductRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PromotionalDateService promotionalDateService;

	/**
	 * Retrieve more expensive products for a User purchase history base on a fix
	 * number of products.
	 * 
	 * @param User identification.
	 * @return List of ProductDto.
	 */
	@Override
	public List<ProductDto> retrieveMoreExpensiveProductsByUser(Long userIdentification) {
		User user = this.userService.findFirstByUserIdentification(userIdentification).orElse(null);
		if (user == null) {
			// TODO analyze how to reply when the user doesn't exist.
			return null;
		}
		List<ShoppingCart> shoppingCartList = this.shoppingCartRepository
				.findAllByUserUserIdAndShoppingCartStatus(user.getUserId(), ShoppingCartStatusType.PAID);

		return this.createProductsDto(this.filterProductsByPrice(NUMBER_OF_PRODUCTS, shoppingCartList));
	}

	/**
	 * Generates the Product Dto base on Product list.
	 * 
	 * @param List of Products.
	 * @return List of ProductDto.
	 */
	private List<ProductDto> createProductsDto(List<Product> products) {
		List<ProductDto> productsDto = new ArrayList<>();
		if (!products.isEmpty()) {
			for (Product product : products) {
				productsDto.add(new ProductDto(product));
			}
		}
		return productsDto;
	}

	/**
	 * Generate a Product list with a specific number of more expensive products
	 * base on their price. It will return the first currencies if the price is the
	 * same.
	 * 
	 * @param number   of products to be retrieve.
	 * @param Shopping cart list to retrieve products.
	 * @return List of ProductDto.
	 */
	private List<Product> filterProductsByPrice(Long numberOfProducts, List<ShoppingCart> shoppingCartList) {
		List<Product> products = new ArrayList<>();
		List<Product> productsReturn = new ArrayList<>();
		// TODO replace with flat map?
		for (ShoppingCart shoppingCart : shoppingCartList) {
			shoppingCart.getShoppingCartProducts();
			for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getShoppingCartProducts()) {
				if (!products.contains(shoppingCartProduct.getProduct())) {
					products.add(shoppingCartProduct.getProduct());
				}
			}
		}

		for (int i = 0; i < numberOfProducts; i++) {
			Product product;
			try {
				product = products.stream().max(Comparator.comparing(Product::getPrice))
						.orElseThrow(() -> new Exception("Error while processing max product price"));
			} catch (Exception e) {
				return new ArrayList<>();
			}
			productsReturn.add(product);
			products.remove(product);
		}

		return products;

	}

	/**
	 * finds a cart on repository base on a shopping cart id.
	 * 
	 * @param Shopping cart id.
	 * @return ShoppingCart if exists.
	 */
	@Override
	public Optional<ShoppingCart> findByShopingCartById(Long shoppingCartId) {
		return shoppingCartRepository.findById(shoppingCartId);
	}

	/**
	 * Creates a new shopping cart if the user is valid. If the user has an already
	 * existing pending shopping cart, it will return it. This process will create a
	 * persistent shopping cart if doesn't exist.
	 * 
	 * @param User identification.
	 * @return shopping cart id
	 */
	@Override
	public Long createShoppingCart(Long userIdentification) {
		User user = this.userService.findFirstByUserIdentification(userIdentification).orElse(null);
		if (user == null) {
			// TODO analyze how to reply when the user doesn't exist.
			return null;
		}

		ShoppingCart shoppingCart = this.shoppingCartRepository
				.findFirstByUserUserIdAndShoppingCartStatus(user.getUserId(), ShoppingCartStatusType.PENDING)
				.orElseGet(() -> this.newShoppingCart(user));
		
		return shoppingCart.getShoppingCartId();
	}

	/**
	 * Creates a new shopping cart object.
	 * 
	 * @param user.
	 * @return Shopping Cart.
	 */
	private ShoppingCart newShoppingCart(User user) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		shoppingCart.setShoppingCartStatus(ShoppingCartStatusType.PENDING);
		shoppingCartRepository.save(shoppingCart);
		return shoppingCart;
	}

	/**
	 * Deletes a shopping cart from repository.
	 * 
	 * @param Shopping cart id.
	 */
	@Override
	public void removeShoppingCart(Long shoppingCartId) {
		Optional<ShoppingCart> shoppingCart = this.shoppingCartRepository.findById(shoppingCartId);
		if(shoppingCart.isPresent()) {
			shoppingCartRepository.deleteById(shoppingCartId);
		}
	}

	/**
	 * Adds a new product to a shopping cart and it will be persisted on the
	 * repository.
	 * 
	 * @param Shopping Cart and product key.
	 * @return Shopping Cart Dto.
	 */
	@Override
	public ShoppingCartDto addProductToShoppingCart(ProductShoppingKeyDto productShoppingKeyDto) {
		ShoppingCart shoppingCart = this.newProductInShoppingCart(productShoppingKeyDto);
		//this.shoppingCartRepository.save(shoppingCart);
		return this.generateShoppingCartDto(shoppingCart);
	}

	/**
	 * Adds a product to Shopping Cart and returns the same with the product or
	 * increments the quantity of that product.
	 * 
	 * @param Shopping Cart and product key.
	 * @return Shopping Cart Dto.
	 */
	private ShoppingCart newProductInShoppingCart(ProductShoppingKeyDto productShoppingKeyDto) {
		ShoppingCart shoppingCart = this.shoppingCartRepository.findById(productShoppingKeyDto.getShoppingCartId())
				.orElse(null);
		ShoppingCartProduct shoppingCartProduct;
		if (shoppingCart != null) {
			List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
			shoppingCartProduct = shoppingCart.getShoppingCartProducts().stream()
					.filter(shoppingCartProduct1 -> shoppingCartProduct1.getProduct().getProductId()
							.equals(productShoppingKeyDto.getProductId()))
					.findAny().orElse(null);

			if (shoppingCartProduct == null) {
				Product product = this.productRepository.findById(productShoppingKeyDto.getProductId()).orElse(null);
				shoppingCartProduct = new ShoppingCartProduct(product);
				shoppingCartProduct.setProduct(product);
				shoppingCartProduct.setProductQuantity(1L);
				shoppingCartProduct.setShoppingCartProductId(productShoppingKeyDto.getShoppingCartId());
			} else {
				shoppingCartProduct.setProductQuantity(shoppingCartProduct.getProductQuantity() + 1);
			}			
			shoppingCartProduct.setShoppingCart(shoppingCart);
			this.shoppingCartProductRepository.save(shoppingCartProduct);

			shoppingCartProducts.add(shoppingCartProduct);
			shoppingCart.setShoppingCartProducts(shoppingCartProducts);
		}
		return shoppingCart;
	}
	
	/**
	 * Removes a new product from a shopping cart and it will be persisted on the
	 * repository.
	 * 
	 * @param Shopping Cart and product key.
	 * @return Shopping Cart Dto.
	 */
	@Override
	public ShoppingCartDto removeProductFromShoppingCart(ProductShoppingKeyDto productShoppingKeyDto) {
		ShoppingCart shoppingCart = this.removeProductInShoppingCart(productShoppingKeyDto);
		//this.shoppingCartRepository.save(shoppingCart);
		return this.generateShoppingCartDto(shoppingCart);
	}

	/**
	 * Removes a product from Shopping Cart and returns the same without the product
	 * or with one less quantity on shopping cart product.
	 * 
	 * @param Shopping Cart and product key.
	 * @return Shopping Cart Dto.
	 */
	public ShoppingCart removeProductInShoppingCart(ProductShoppingKeyDto productShoppingKeyDto) {
		ShoppingCart shoppingCart = this.shoppingCartRepository.findById(productShoppingKeyDto.getShoppingCartId())
				.orElse(null);
		ShoppingCartProduct shoppingCartProduct;
		if (shoppingCart != null) {
			List<ShoppingCartProduct> shoppingCartProducts = shoppingCart.getShoppingCartProducts();
			// TODO what happens when the shopping cart doesn't have a list instantiated.
			// Null pointer?
			shoppingCartProduct = shoppingCart.getShoppingCartProducts().stream()
					.filter(shoppingCartProduct1 -> shoppingCartProduct1.getProduct().getProductId()
							.equals(productShoppingKeyDto.getProductId()))
					.findAny().orElse(null);

			if (shoppingCartProduct != null) {
				if (shoppingCartProduct.getProductQuantity() > 0) {
					shoppingCartProduct.setProductQuantity(shoppingCartProduct.getProductQuantity() - 1L);					
					shoppingCartProduct.setShoppingCart(shoppingCart);
					this.shoppingCartProductRepository.save(shoppingCartProduct);
					shoppingCartProducts.add(shoppingCartProduct);
				} else {
					this.shoppingCartProductRepository.delete(shoppingCartProduct);
					shoppingCartProducts.remove(shoppingCartProduct);
				}
			}

			shoppingCart.setShoppingCartProducts(shoppingCartProducts);
		}
		return shoppingCart;
	}



	/**
	 * Generates Shopping Cart Dto as response.
	 * 
	 * @param Shopping Cart
	 * @return Shopping Cart Dto
	 */
	private ShoppingCartDto generateShoppingCartDto(ShoppingCart shoppingCart) {

		ShoppingCartDto shoppingCartDto = new ShoppingCartDto(shoppingCart);

		shoppingCartDto.setTotal(this.setShoppingCartStrategy(shoppingCart));

		return shoppingCartDto;

	}

	/**
	 * Defines the strategy for the total calculation and discounts based on User
	 * type, promotional dates or number of products on the Shopping Cart. Then
	 * calculates the amount for the shopping cart.
	 * 
	 * @param Shopping Cart.
	 * @return Amount for the total shopping cart value base on strategies.
	 */

	private Double setShoppingCartStrategy(ShoppingCart shoppingCart) {

		Long totalProducts = shoppingCart.getShoppingCartProducts().stream()
				.mapToLong(ShoppingCartProduct::getProductQuantity).sum();
		Double totalPrice;

		if (totalProducts.equals(MAX_PRODUCTS)) {
			totalPrice = shoppingCart.calculateTotalPrice(new PercentageDiscountPaymentStrategy());
		} else if (Double.compare(totalProducts, MIN_PRODUCTS) <= 0) {
			if (shoppingCart.getUser().isVip()) {
				totalPrice = shoppingCart.calculateTotalPrice(new VipDiscountPaymentStrategy());
			} else if (this.promotionalDateService.validatePromotionalDate()) {
				totalPrice = shoppingCart.calculateTotalPrice(new GeneralDiscountPaymentStrategy());
			} else {
				totalPrice = shoppingCart.calculateTotalPrice(new GeneralPaymentStrategy());
			}
		} else {
			totalPrice = shoppingCart.calculateTotalPrice(new GeneralPaymentStrategy());
		}

		return totalPrice;
	}

}
