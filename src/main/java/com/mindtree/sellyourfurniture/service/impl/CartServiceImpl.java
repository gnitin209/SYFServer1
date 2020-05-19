package com.mindtree.sellyourfurniture.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.sellyourfurniture.dto.CartDisplayDTO;
import com.mindtree.sellyourfurniture.dto.CartFurnitureDTO;
import com.mindtree.sellyourfurniture.entity.Cart;
import com.mindtree.sellyourfurniture.entity.CartItem;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.FurnitureImage;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.repository.CartItemRepository;
import com.mindtree.sellyourfurniture.repository.CartRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureImageRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.ICartService;

@Service
@Transactional
public class CartServiceImpl implements ICartService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private FurnitureRepository furnitureRepo;
	@Autowired
	private CartItemRepository cartItemRepo;
	@Autowired
	private FurnitureImageRepository furnitureImageRepo;
	
	
	
	
	

	@Override
	public String addToCart(CartFurnitureDTO cartFurniture) {
		Optional<User> user=userRepo.findByuserEmail(cartFurniture.getUserEmail());
		if(!user.isPresent()) {
			return "User not present";
		}
		Optional<Cart> cart=cartRepo.findByUserCart(user.get());
		if(!cart.isPresent()) {
			Cart currentCart=new Cart();
			currentCart.setUserCart(user.get());
			cartRepo.save(currentCart);
			CartItem cartItem=new CartItem();
			cartItem.setCart(currentCart);
			cartItem.setQuantity(1);
			cartItem.setFurniture(furnitureRepo.findByFurnitureId(cartFurniture.getFurnitureId()));
			cartItemRepo.save(cartItem);
		}
		else {
			Optional<CartItem> cartItem=cartItemRepo.findByFurnitureAndCart(furnitureRepo.findByFurnitureId(cartFurniture.getFurnitureId()), cart.get());
			if(!cartItem.isPresent()) {
				CartItem cartItemNew=new CartItem();
				cartItemNew.setCart(cart.get());
				cartItemNew.setQuantity(1);
				cartItemNew.setFurniture(furnitureRepo.findByFurnitureId(cartFurniture.getFurnitureId()));
				cartItemRepo.save(cartItemNew);
				return "Furniture added to old user";
			}
			else {
				if(cartFurniture.getAdd())
				{
					CartItem cartCurrent=cartItem.get();
					cartCurrent.setQuantity(cartCurrent.getQuantity()+1);
					cartItemRepo.save(cartCurrent);
					return "Furniture quantity increased";
				}
				else
				{
					CartItem cartCurrent=cartItem.get();
					cartCurrent.setQuantity(cartCurrent.getQuantity()-1);
					cartItemRepo.save(cartCurrent);
					return "Furniture quantity decreased";
				}
				
			}
		}
		
		return "Your Product has been added to cart successfully!!";
	}

	@Override
	public List<CartDisplayDTO> getCartDetails(String userEmail) {
		Optional<User> userCurrent=userRepo.findByuserEmail(userEmail);
		Optional<Cart> cartCurrent=cartRepo.findByUserCart(userCurrent.get());
		List<CartDisplayDTO> results=new ArrayList<CartDisplayDTO>();
		List<CartItem> allCartItems=cartItemRepo.findByCart(cartCurrent.get()).get();
		for(CartItem cartItem : allCartItems) {
			int furnitureId=cartItem.getFurniture().getFurnitureId();
			CartDisplayDTO cartDisplay=new CartDisplayDTO();
			List<FurnitureImage> furnitureImages=furnitureImageRepo.findByFurnitureImage(furnitureRepo.findByFurnitureId(furnitureId));
			cartDisplay.setFurnitureDTO(FurnitureServiceImpl.decompressBytes(furnitureImages.get(0).getImageContent()));
			cartDisplay.setFurnitureId(furnitureRepo.findByFurnitureId(furnitureId).getFurnitureId());
			cartDisplay.setFurnitureName(furnitureRepo.findByFurnitureId(furnitureId).getFurnitureName());
			cartDisplay.setFurnitureLimit(furnitureRepo.findByFurnitureId(furnitureId).getFurnitureQuantity());
			cartDisplay.setFurnitureQuanitity(cartItem.getQuantity());
			cartDisplay.setFurniturePrice(furnitureRepo.findByFurnitureId(furnitureId).getFurniturePrice());
			cartDisplay.setFurnitureType(furnitureRepo.findByFurnitureId(furnitureId).getFurnitureType());
			results.add(cartDisplay);
		}
		return results;
	}

	@Override
	public String deleteFurnitureFromCart(CartFurnitureDTO cartFurniture) {
		Optional<Furniture> furniture=furnitureRepo.findById(cartFurniture.getFurnitureId());
		Optional<Cart> cart=cartRepo.findByUserCart(userRepo.findByUserEmail(cartFurniture.getUserEmail()));
		Optional<CartItem> cartCurrent=cartItemRepo.findByFurnitureAndCart(furniture.get(), cart.get());
		cartItemRepo.delete(cartCurrent.get());
		return "Furniture has been removed from the cart";
	}

	@Override
	public List<Integer> getFurnitureIdsOfCart(String userEmail) {
		Optional<User> userCurrent=userRepo.findByuserEmail(userEmail);
		Optional<Cart> cartCurrent = cartRepo.findByUserCart(userCurrent.get());
		List<CartItem> allCartItems=cartItemRepo.findByCart(cartCurrent.get()).get();
		List<Integer> allFurnitureIds=new ArrayList<Integer>();
		for(CartItem cartItem : allCartItems) {
			allFurnitureIds.add(cartItem.getFurniture().getFurnitureId());
		}
		return allFurnitureIds;
	}
	
}
