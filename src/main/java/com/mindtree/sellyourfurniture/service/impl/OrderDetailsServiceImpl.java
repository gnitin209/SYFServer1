package com.mindtree.sellyourfurniture.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.entity.Cart;
import com.mindtree.sellyourfurniture.entity.CartItem;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.OrderDetails;
import com.mindtree.sellyourfurniture.entity.OrderItem;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.FurnitureNotFoundException;
import com.mindtree.sellyourfurniture.exception.service.custom.NoCartException;
import com.mindtree.sellyourfurniture.exception.service.custom.QuantityIsLess;
import com.mindtree.sellyourfurniture.exception.service.custom.UserNotPresentException;
import com.mindtree.sellyourfurniture.repository.CartItemRepository;
import com.mindtree.sellyourfurniture.repository.CartRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.OrderDetailsRepository;
import com.mindtree.sellyourfurniture.repository.OrderItemRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.IOrderDetailsService;

@Service
@Transactional
public class OrderDetailsServiceImpl implements IOrderDetailsService {
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderDetailsRepository orderrepository;
	@Autowired
	OrderItemRepository orderitemrepository;
	@Autowired
	CartItemRepository cartitemrepo;
	@Autowired
	FurnitureRepository furnitureRepo;
	

	@Override
	public String addOrderDetails(String userEmail) throws SellYourFurnitureServiceException {
		User userr = userRepository.findByUserEmail(userEmail);
		if (userr == null) {
			throw new UserNotPresentException("user is not present");
		}

		Cart cart = cartRepository.findByuserCart(userr);
		if (cart == null) {
			throw new NoCartException("No cart found related to this user");
		}
		List<CartItem> cartlist = cart.getCartItem();
		for (CartItem carti : cartlist) {
			Furniture f = furnitureRepo.findByFurnitureId(carti.getFurniture().getFurnitureId());
			if (f.getFurnitureQuantity() <= 0) {
				throw new QuantityIsLess("quantity of " + f.getFurnitureName() + " is Zero");

			}

		}

		OrderDetails or = orderrepository.findByOrderUser(userr);
		if (or == null) {
			OrderDetails orderdetails = new OrderDetails();
			orderdetails.setOrderUser(userr);
			orderrepository.save(orderdetails);
			OrderDetails order = orderrepository.findByOrderUser(userr);

			List<CartItem> cartitemlist = cart.getCartItem();

			for (CartItem cartItem : cartitemlist) {
				OrderItem orderitem = new OrderItem();
				orderitem.setFurniture(cartItem.getFurniture());
				orderitem.setQuantity(cartItem.getQuantity());
				Furniture furniture = furnitureRepo.findByFurnitureId(cartItem.getFurniture().getFurnitureId());
				furniture.setFurnitureQuantity(furniture.getFurnitureQuantity() - cartItem.getQuantity());
				furnitureRepo.save(furniture);
				orderitem.setOrder(order);
				orderitemrepository.save(orderitem);
			}
		} else {

			List<CartItem> cartitemlist = cart.getCartItem();
			for (CartItem cartItem : cartitemlist) {
				OrderItem orderitem = new OrderItem();
				orderitem.setFurniture(cartItem.getFurniture());
				orderitem.setQuantity(cartItem.getQuantity());
				Furniture furniture = furnitureRepo.findByFurnitureId(cartItem.getFurniture().getFurnitureId());
				furniture.setFurnitureQuantity(furniture.getFurnitureQuantity() - cartItem.getQuantity());
				furnitureRepo.save(furniture);
				orderitem.setOrder(or);
				orderitemrepository.save(orderitem);
			}

		}
		cartitemrepo.deleteByCart(cart);
		cartRepository.deleteByuserCart(userr);

		return "successful";
	}
	
	 @Override
	    public List<DisplayFurnitureDTO> getOrderDeatils(String userEmail) throws SellYourFurnitureServiceException {
	        // TODO Auto-generated method stub
	    User user=userRepository.findByUserEmail(userEmail);
	    OrderDetails order1=orderrepository.findByorderUser(user).orElseThrow(()->new FurnitureNotFoundException("furniture not found"));
	    
	    OrderDetails order=orderrepository.findByOrderUser(user);
	    List<DisplayFurnitureDTO> listFurniture=new ArrayList<DisplayFurnitureDTO>();
	    List<Furniture> listOfFurniture=new ArrayList<Furniture>();
	 List <Integer> checkArray=new ArrayList<Integer>();
	    for(OrderItem o:order.getOrderItem())
	    {
	        Furniture furniture=o.getFurniture();
	        DisplayFurnitureDTO d=new DisplayFurnitureDTO();
	        d.setFurnitureId(furniture.getFurnitureId());
	        d.setFurnitureMaterial(furniture.getFurnitureMaterial());
	        d.setFurniturePrice(furniture.getFurniturePrice());
	        d.setFurnitureShipping(furniture.getFurnitureShippingType());
	        d.setFurnitureType(furniture.getFurnitureType());
	        d.setFurnitureType1(furniture.getFurnitureName());
	        d.setImageContent(FurnitureServiceImpl.decompressBytes(furniture.getFurnitureImages().get(0).getImageContent()));    
	        if(!checkArray.contains(furniture.getFurnitureId()))
	        {
	        	listFurniture.add(d);
	        	checkArray.add(furniture.getFurnitureId());
	        }
	        
	    }
	    
	    if(listFurniture.isEmpty())
	    	throw new FurnitureNotFoundException("furniture not found");
	        return listFurniture;
	    }

	 

	   

}
