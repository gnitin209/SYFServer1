package com.mindtree.sellyourfurniture.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.sellyourfurniture.dto.CartFurnitureDTO;
import com.mindtree.sellyourfurniture.service.ICartService;


public class CartControllerUnitTest {
	@InjectMocks
	private CartController cartController;
	
	@Mock
	private ICartService cartService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addFurnitureToCartUnitTest() {
		CartFurnitureDTO cartFurniture=new CartFurnitureDTO();
		cartFurniture.setUserEmail("anshu.rubul@gmail.com");
		cartFurniture.setAdd(true);
		cartFurniture.setFurnitureId(1);
		when(cartService.addToCart(cartFurniture)).thenReturn("Your Product has been added to cart successfully!!");
		ResponseEntity<?> responseBody=cartController.addFurnitureToCart(cartFurniture);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
	}
	
	@Test
	public void changeQuantityOfCartUnitTest() {
		CartFurnitureDTO cartFurniture=new CartFurnitureDTO();
		cartFurniture.setUserEmail("anshu.rubul@gmail.com");
		cartFurniture.setAdd(true);
		cartFurniture.setFurnitureId(1);
		//when(cartService.addToCart(cartFurniture)).thenReturn("Your Product has been added to cart successfully!!");
		ResponseEntity<?> responseBody=cartController.changeQuantityOfCart(cartFurniture);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		
	}
	
	@Test
	public void getFurnituresOfCartUnitTest() {
		String email="anshu.rubul@gmail.com";
		List<CartFurnitureDTO> cartFurnitures=new ArrayList<CartFurnitureDTO>();
		ResponseEntity<?> responseBody=cartController.getFurnituresOfCart(email);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		assertEquals(cartFurnitures, responseBody.getBody());
		
	}
	@Test
	public void deleteFurnitureOfCartUnitTest() {
		CartFurnitureDTO cartFurniture=new CartFurnitureDTO();
		cartFurniture.setUserEmail("anshu.rubul@gmail.com");
		cartFurniture.setAdd(true);
		cartFurniture.setFurnitureId(1);
		when(cartService.addToCart(cartFurniture)).thenReturn("Furniture has been removed from the cart");
		ResponseEntity<?> responseBody=cartController.deleteFurnitureOfCart(cartFurniture);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
		
	}
	@Test
	public void allFunitureIdOfCartUnitTest() {
		String email="anshu.rubul@gmail.com";
		List<Integer> furnitureIds=new ArrayList<Integer>();
		when(cartService.getFurnitureIdsOfCart(email)).thenReturn(furnitureIds);
		ResponseEntity<?> responseBody=cartController.allFunitureIdOfCart(email);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
	}
}
