package com.mindtree.sellyourfurniture.service;



import java.util.List;

import com.mindtree.sellyourfurniture.dto.CartDisplayDTO;
import com.mindtree.sellyourfurniture.dto.CartFurnitureDTO;



public interface ICartService {
	public String addToCart(CartFurnitureDTO cartFurniture); 
	public List<CartDisplayDTO> getCartDetails(String userEmail);
	public String deleteFurnitureFromCart(CartFurnitureDTO cartFurniture);
	public List<Integer> getFurnitureIdsOfCart(String userEmail);

}
