package com.mindtree.sellyourfurniture.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.sellyourfurniture.dto.CartFurnitureDTO;
import com.mindtree.sellyourfurniture.entity.Cart;
import com.mindtree.sellyourfurniture.entity.CartItem;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.repository.CartItemRepository;
import com.mindtree.sellyourfurniture.repository.CartRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.impl.CartServiceImpl;
import com.mysql.fabric.xmlrpc.base.Array;

public class CartServiceImplUnitTest {
	@InjectMocks
	private CartServiceImpl cartService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private CartRepository cartRepo;
	@Mock
	private UserRepository userRepo;
	@Mock
	private FurnitureRepository furnitureRepo;
	@Mock
	private CartItemRepository cartItemRepo;

	@Test
	public void addToCartUnitTest() {
		CartFurnitureDTO cartDTO = new CartFurnitureDTO(true, "anshu.rubul@gmail.com", 1);
		Optional<User> user = userRepo.findByuserEmail(cartDTO.getUserEmail());

		if (!user.isPresent()) {
			String message1 = cartService.addToCart(cartDTO);
			assertEquals("User not present", message1);
		}
		User newUser = new User();
		newUser.setUserEmail("anshu.rubul@gmail.com");
		Optional<User> optionalUser = Optional.of(newUser);
		CartItem cartItem = new CartItem();

		Furniture furniture = new Furniture();
		furniture.setFurnitureId(1);
		when(userRepo.save(newUser)).thenReturn(newUser);

		when(userRepo.findByuserEmail(newUser.getUserEmail())).thenReturn(optionalUser);
		when(furnitureRepo.findByFurnitureId(cartDTO.getFurnitureId())).thenReturn(furniture);
		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);
		String message = cartService.addToCart(cartDTO);
		assertEquals("Your Product has been added to cart successfully!!", message);

	}

	@Test
	public void addToCartFurnitureUnitTest() {
		CartFurnitureDTO cartDTO = new CartFurnitureDTO(true, "anshu.rubul@gmail.com", 1);
		User newUser = new User();
		newUser.setUserEmail(cartDTO.getUserEmail());
		Optional<User> userOptional = Optional.of(newUser);
		when(userRepo.save(newUser)).thenReturn(newUser);
		when(userRepo.findByuserEmail(newUser.getUserEmail())).thenReturn(userOptional);
		Furniture furniture = new Furniture();
		furniture.setFurnitureId(1);
		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		when(furnitureRepo.findByFurnitureId(cartDTO.getFurnitureId())).thenReturn(furniture);
		Cart cart = new Cart();
		cart.setUserCart(newUser);
		when(cartRepo.findByUserCart(userOptional.get())).thenReturn(Optional.of(cart));
		CartItem cartItem = new CartItem();
//		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);

		when(cartItemRepo.findByFurnitureAndCart(furniture, cart)).thenReturn(Optional.empty());

		CartItem cartItemNew = new CartItem();
		cartItemNew.setCart(cart);
		cartItemNew.setFurniture(furniture);
		cartItemNew.setQuantity(1);
		when(cartItemRepo.save(cartItemNew)).thenReturn(cartItemNew);
		String message = cartService.addToCart(cartDTO);
		assertEquals("Furniture added to old user", message);

	}

	@Test
	public void addToCartFurnitureDecreaseUnitTest() {
		CartFurnitureDTO cartDTO = new CartFurnitureDTO(false, "anshu.rubul@gmail.com", 1);
		User newUser = new User();
		newUser.setUserEmail(cartDTO.getUserEmail());
		Optional<User> userOptional = Optional.of(newUser);
		when(userRepo.save(newUser)).thenReturn(newUser);
		when(userRepo.findByuserEmail(newUser.getUserEmail())).thenReturn(userOptional);
		Furniture furniture = new Furniture();
		furniture.setFurnitureId(1);
		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		when(furnitureRepo.findByFurnitureId(cartDTO.getFurnitureId())).thenReturn(furniture);
		Cart cart = new Cart();
		cart.setUserCart(newUser);
		when(cartRepo.findByUserCart(userOptional.get())).thenReturn(Optional.of(cart));
		CartItem cartItem = new CartItem();
//		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);

		when(cartItemRepo.findByFurnitureAndCart(furniture, cart)).thenReturn(Optional.of(cartItem));

		CartItem cartItemNew = new CartItem();
		cartItemNew.setCart(cart);
		cartItemNew.setFurniture(furniture);
		cartItemNew.setQuantity(1);
		when(cartItemRepo.save(cartItemNew)).thenReturn(cartItemNew);
		String message = cartService.addToCart(cartDTO);
		assertEquals("Furniture quantity decreased", message);

	}

	@Test
	public void addToCartFurnitureIncreaseUnitTest() {
		CartFurnitureDTO cartDTO = new CartFurnitureDTO(true, "anshu.rubul@gmail.com", 1);
		User newUser = new User();
		newUser.setUserEmail(cartDTO.getUserEmail());
		Optional<User> userOptional = Optional.of(newUser);
		when(userRepo.save(newUser)).thenReturn(newUser);
		when(userRepo.findByuserEmail(newUser.getUserEmail())).thenReturn(userOptional);
		Furniture furniture = new Furniture();
		furniture.setFurnitureId(1);
		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		when(furnitureRepo.findByFurnitureId(cartDTO.getFurnitureId())).thenReturn(furniture);
		Cart cart = new Cart();
		cart.setUserCart(newUser);
		when(cartRepo.findByUserCart(userOptional.get())).thenReturn(Optional.of(cart));
		CartItem cartItem = new CartItem();
//		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);

		when(cartItemRepo.findByFurnitureAndCart(furniture, cart)).thenReturn(Optional.of(cartItem));

		CartItem cartItemNew = new CartItem();
		cartItemNew.setCart(cart);
		cartItemNew.setFurniture(furniture);
		cartItemNew.setQuantity(1);

		when(cartItemRepo.save(cartItemNew)).thenReturn(cartItemNew);
		String message = cartService.addToCart(cartDTO);
		assertEquals("Furniture quantity increased", message);

	}
	
	@Test
	public void deleteFurnitureFromCartUnitTest() {
		CartFurnitureDTO cartDTO = new CartFurnitureDTO(true, "anshu.rubul@gmail.com", 1);
		User newUser = new User();
		newUser.setUserEmail(cartDTO.getUserEmail());
		Optional<User> userOptional = Optional.of(newUser);
		when(userRepo.save(newUser)).thenReturn(newUser);
		when(userRepo.findByuserEmail(newUser.getUserEmail())).thenReturn(userOptional);
		Furniture furniture = new Furniture();
		furniture.setFurnitureId(1);
		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		when(furnitureRepo.findByFurnitureId(cartDTO.getFurnitureId())).thenReturn(furniture);
		Cart cart = new Cart();
		cart.setUserCart(newUser);
		when(cartRepo.findByUserCart(userOptional.get())).thenReturn(Optional.of(cart));
		CartItem cartItem = new CartItem();
//		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);

		when(cartItemRepo.findByFurnitureAndCart(furniture, cart)).thenReturn(Optional.of(cartItem));

		CartItem cartItemNew = new CartItem();
		cartItemNew.setCart(cart);
		cartItemNew.setFurniture(furniture);
		cartItemNew.setQuantity(1);
		when(cartItemRepo.save(cartItemNew)).thenReturn(cartItemNew);

//		String message=cartService.deleteFurnitureFromCart(cartDTO);
//		assertEquals("Furniture has been removed from the cart", message);
	}
	
	@Test
	public void getFurnitureIdsOfCartTest() {
		String email="anshu.rubul@gmail.com";
		User user=new User();
		user.setUserEmail(email);
//		when(userRepo.save(user)).thenReturn(user);
		when(userRepo.findByuserEmail(email)).thenReturn(Optional.of(user));
		Cart cart=new Cart();
		cart.setUserCart(user);
//		when(cartRepo.save(cart)).thenReturn(cart);
		when(cartRepo.findByUserCart(user)).thenReturn(Optional.of(cart));
		Furniture furniture=new Furniture();
		furniture.setFurnitureId(1);
//		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		CartItem cartItem=new CartItem();
		cartItem.setFurniture(furniture);
		cartItem.setCart(cart);
//		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);
		List<CartItem> cartItems=new ArrayList<CartItem>();
		cartItems.add(cartItem);
		when(cartItemRepo.findByCart(cart)).thenReturn(Optional.of(cartItems));
		List<Integer> allFunirureIds=new ArrayList<Integer>();
		allFunirureIds.add(1);
		List<Integer> results=cartService.getFurnitureIdsOfCart(email);
		assertEquals(allFunirureIds, results);
	}
	
	
}
