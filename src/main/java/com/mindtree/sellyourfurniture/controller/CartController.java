package com.mindtree.sellyourfurniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.sellyourfurniture.dto.CartFurnitureDTO;
import com.mindtree.sellyourfurniture.service.ICartService;

@RestController
@RequestMapping("/cart")

@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	private ICartService cartService;
	
	@PostMapping("/addfurniture")
	public ResponseEntity<?> addFurnitureToCart(@RequestBody CartFurnitureDTO cartFurniture){
		return new ResponseEntity<>(cartService.addToCart(cartFurniture),HttpStatus.OK);
	}
	@PostMapping("/changequantity")
	public ResponseEntity<?> changeQuantityOfCart(@RequestBody CartFurnitureDTO cartFurniture){
		return new ResponseEntity<>(cartService.addToCart(cartFurniture),HttpStatus.OK);
	}
	@GetMapping("/getfurniture")
	public ResponseEntity<?> getFurnituresOfCart(@RequestParam("email") String email){
		return new ResponseEntity<>(cartService.getCartDetails(email),HttpStatus.OK);
	}
	
	@PostMapping("/removecart")
	public ResponseEntity<?> deleteFurnitureOfCart(@RequestBody CartFurnitureDTO cartFurniture){
		return new ResponseEntity<>(cartService.deleteFurnitureFromCart(cartFurniture),HttpStatus.OK);
	}
	@GetMapping("/getfurnitureids")
	public ResponseEntity<?> allFunitureIdOfCart(@RequestParam("email") String email){
		return new ResponseEntity<>(cartService.getFurnitureIdsOfCart(email),HttpStatus.OK);
	}

}
