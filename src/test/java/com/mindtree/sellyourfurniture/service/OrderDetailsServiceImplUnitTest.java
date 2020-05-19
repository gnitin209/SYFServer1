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

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.entity.Cart;
import com.mindtree.sellyourfurniture.entity.CartItem;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.FurnitureImage;
import com.mindtree.sellyourfurniture.entity.OrderDetails;
import com.mindtree.sellyourfurniture.entity.OrderItem;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.NoCartException;
import com.mindtree.sellyourfurniture.exception.service.custom.QuantityIsLess;
import com.mindtree.sellyourfurniture.exception.service.custom.UserNotPresentException;
import com.mindtree.sellyourfurniture.repository.CartItemRepository;
import com.mindtree.sellyourfurniture.repository.CartRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.OrderDetailsRepository;
import com.mindtree.sellyourfurniture.repository.OrderItemRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.impl.OrderDetailsServiceImpl;

public class OrderDetailsServiceImplUnitTest {

	@Mock
	OrderDetailsRepository orderrepository;

	@InjectMocks
	private OrderDetailsServiceImpl orderService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CartRepository cartRepo;

	@Mock
	private OrderDetailsRepository orderDetailsRepo;

	@Mock
	OrderItemRepository orderitemrepository;
	@Mock
	CartItemRepository cartitemrepo;
	@Mock
	FurnitureRepository furnitureRepo;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void OrderDetailsTest() throws SellYourFurnitureApplicationException {
		User u = new User();
		u.setUserId(1);
		u.setUserEmail("harshadasugandhi4@gmail.com");

		Furniture furniture = new Furniture();

		furniture.setFurnitureId(1);
		furniture.setFurnitureQuantity(5);

		CartItem cartItem = new CartItem();
		cartItem.setCartItemId(1);
		cartItem.setFurniture(furniture);

		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem);

		Cart cart = new Cart();
		cart.setCartId(1);
		cart.setCartItem(cartList);

		OrderItem oredrItem = new OrderItem();
		List<OrderItem> oredrItemList = new ArrayList<OrderItem>();
		oredrItemList.add(oredrItem);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderId(1);
		orderDetails.setOrderItem(oredrItemList);
		orderDetails.setOrderUser(u);

		when(userRepository.findByUserEmail("harshadasugandhi4@gmail.com")).thenReturn(u);
		when(cartRepo.findByuserCart(u)).thenReturn(cart);
		when(furnitureRepo.findByFurnitureId(1)).thenReturn(furniture);
		when(orderrepository.findByOrderUser(u)).thenReturn(orderDetails);
		when(orderrepository.save(orderDetails)).thenReturn(orderDetails);
		when(orderrepository.findByOrderUser(u)).thenReturn(orderDetails);
		when(furnitureRepo.findByFurnitureId(1)).thenReturn(furniture);
		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		when(orderitemrepository.save(oredrItem)).thenReturn(oredrItem);
		when(furnitureRepo.findByFurnitureId(furniture.getFurnitureId())).thenReturn(furniture);
		when(furnitureRepo.save(furniture)).thenReturn(furniture);
		when(orderitemrepository.save(oredrItem)).thenReturn(oredrItem);
		String message = orderService.addOrderDetails(u.getUserEmail());
		assertEquals("successful", message);

		u.setUserEmail("harshada");
		try {
			orderService.addOrderDetails(u.getUserEmail());
		} catch (UserNotPresentException e) {

			assertEquals("user is not present", e.getMessage());

		}
		User u1=new User();
				u1.setUserEmail("harshada");
		u.setUserEmail("harshadasugandhi4@gmail.com");
		try {
			when(cartRepo.findByuserCart(u)).thenReturn(null);
			orderService.addOrderDetails("harshadasugandhi4@gmail.com");
		} catch (NoCartException e) {

			assertEquals("No cart found related to this user", e.getMessage());

		}
		u.setUserEmail("harshadasugandhi4@gmail.com");
		when(cartRepo.findByuserCart(u)).thenReturn(cart);
		cart.setCartId(1);
		cart.setCartItem(cartList);
		furniture.setFurnitureQuantity(0);
		try {
			
			orderService.addOrderDetails("harshadasugandhi4@gmail.com");
		} catch (QuantityIsLess e) 
		{
			assertEquals("quantity of " + furniture.getFurnitureName() + " is Zero", e.getMessage());

		}
		User user=new User();
		user.setUserEmail("harshadasugandhi4@gmail.com");
		furniture.setFurnitureQuantity(6);
		cart.setCartId(1);
		cart.setCartItem(cartList);

		//when(cartRepo.findByuserCart(u)).thenReturn(cart);

			when(orderrepository.findByOrderUser(user)).thenReturn(null);

			String m=orderService.addOrderDetails("harshadasugandhi4@gmail.com");
	
			assertEquals("successful",m);

		


	}@Test
    public void getOrderDeatilsTest() throws SellYourFurnitureApplicationException
    {
        User user= new User();
        OrderDetails order=new OrderDetails();
        user.setUserEmail("harshadasugandhi4@gmail.com");
        order.setOrderUser(user);
        FurnitureImage furnitureImages=new FurnitureImage();
        furnitureImages.setImageContent("harshada".getBytes());
        List <FurnitureImage>array= new ArrayList<FurnitureImage>();
        Furniture furniture=new Furniture();
        array.add(furnitureImages);
        furniture.setFurnitureImages(array);
       
        OrderItem o=new OrderItem();
        o.setOrder(order);   
        o.setFurniture(furniture);
       
        List <OrderItem> listo=new ArrayList<OrderItem>();
        listo.add(o);
        order.setOrderItem(listo);
        o.setFurniture(furniture);
        Optional<OrderDetails>  order1=Optional.of(order);
        		
        List<DisplayFurnitureDTO> listFurniture=new ArrayList<DisplayFurnitureDTO>();
        List<Furniture> listOfFurniture=new ArrayList<Furniture>();
        when(userRepository.findByUserEmail("harshadasugandhi4@gmail.com")).thenReturn(user);
        when(orderrepository.findByorderUser(user)).thenReturn(order1);
        
        when(orderrepository.findByOrderUser(user)).thenReturn(order);
        listFurniture=orderService.getOrderDeatils(user.getUserEmail());
        assertEquals(listFurniture, listFurniture);
    }

	@Test
    public void getOrderDeatilsExceptionTest() throws SellYourFurnitureApplicationException
    {
		try {
		    
        User user= new User();
        OrderDetails order=new OrderDetails();
        user.setUserEmail("harshadasugandhi4@gmail.com");
        order.setOrderUser(user);
        FurnitureImage furnitureImages=new FurnitureImage();
        furnitureImages.setImageContent("harshada".getBytes());
        List <FurnitureImage>array= new ArrayList<FurnitureImage>();
        Furniture furniture=new Furniture();
        array.add(furnitureImages);
        furniture.setFurnitureImages(array);
       
        OrderItem o=new OrderItem();
        o.setOrder(order);   
//        o.setFurniture(null);
//       
        List <OrderItem> listo=new ArrayList<OrderItem>();
        listo.add(o);
        order.setOrderItem(listo);
        o.setFurniture(furniture);
        List<DisplayFurnitureDTO> listFurniture=new ArrayList<DisplayFurnitureDTO>();
        List<Furniture> listOfFurniture=new ArrayList<Furniture>();
    
        when(userRepository.findByUserEmail("harshadasugandhi4@gmail.com")).thenReturn(user);
        when(orderrepository.findByOrderUser(user)).thenReturn(order);
        listFurniture=orderService.getOrderDeatils(user.getUserEmail());
        assertEquals(listFurniture, listFurniture);
    }catch (SellYourFurnitureServiceException e) {
		// TODO: handle exception
    	assertEquals("furniture not found", e.getMessage());
	}
        
    }
}
