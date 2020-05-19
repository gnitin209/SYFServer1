package com.mindtree.sellyourfurniture.service;


import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;

import com.mindtree.sellyourfurniture.dto.DisplayReviewDTO;

import com.mindtree.sellyourfurniture.dto.PostFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.ViewProductDTO;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.FurnitureImage;

import com.mindtree.sellyourfurniture.entity.OrderDetails;
import com.mindtree.sellyourfurniture.entity.OrderItem;
import com.mindtree.sellyourfurniture.entity.Review;

import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.NoResultsFurnitureException;
import com.mindtree.sellyourfurniture.repository.FurnitureImageRepository;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.OrderItemRepository;
import com.mindtree.sellyourfurniture.repository.ReviewRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.impl.FurnitureServiceImpl;
import com.sun.org.apache.bcel.internal.generic.FDIV;

public class FurnitureServiceImplUnitTest {
	@InjectMocks
	private FurnitureServiceImpl furnitureService;

	@Mock
	private FurnitureRepository furnitureRepo;

	@Mock

	private UserRepository userRepository;

	@Mock
	private OrderItemRepository orderItemRepo;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private FurnitureImageRepository furnitureImageRepo;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test

	public void getAllFurnituresWithException() throws SellYourFurnitureServiceException {
		List<DisplayFurnitureDTO> furnitureDto = new ArrayList<DisplayFurnitureDTO>();
		List<FurnitureImage> img = new ArrayList<FurnitureImage>();
		Furniture f = new Furniture();
		f.setFurnitureId(1);
		List<Furniture> flist = new ArrayList<Furniture>();
		flist.add(f);
		try {
		when(furnitureRepo.findTop8ByOrderByFurnitureIdDesc()).thenReturn(flist);
		List<Furniture> furniture = furnitureRepo.findTop8ByOrderByFurnitureIdDesc();
		assertThat(flist).isEqualTo(furniture);
		when(furnitureImageRepo.findByFurnitureImage(f)).thenReturn(img);
		List<FurnitureImage> furnitureImg = furnitureImageRepo.findByFurnitureImage(f);
		assertThat(img).isEqualTo(furnitureImg);
		when(furnitureService.getAllFurnitures()).thenReturn(furnitureDto);
		List<DisplayFurnitureDTO> furnitureDto1 = furnitureService.getAllFurnitures();
		assertEquals(furnitureDto,furnitureDto1);
		}catch (NoResultsFurnitureException e) {
			assertEquals("No furnitures are available right now",e.getMessage());
		}
	}
	@Test
	public void getAllFurnitures() throws SellYourFurnitureServiceException {
		List<DisplayFurnitureDTO> furnitureDto = new ArrayList<DisplayFurnitureDTO>();
		List<FurnitureImage> img = new ArrayList<FurnitureImage>();
		Furniture f = new Furniture(1, "sofa", "wood", "sofa", 34560.0, "free", 0.0, "Wooden sofa", 4, null, img, null, null, null);
		FurnitureImage image = new FurnitureImage(1, "test data".getBytes(), f);
		img.add(image);
		List<Furniture> flist = new ArrayList<Furniture>();
		flist.add(f);
		f.setFurnitureImages(img);
		when(furnitureRepo.findTop8ByOrderByFurnitureIdDesc()).thenReturn(flist);
		List<Furniture> furniture = furnitureRepo.findTop8ByOrderByFurnitureIdDesc();
		assertThat(flist).isEqualTo(furniture);
		when(furnitureImageRepo.findByFurnitureImage(f)).thenReturn(img);
		List<FurnitureImage> furnitureImg = furnitureImageRepo.findByFurnitureImage(f);
		assertThat(img).isEqualTo(furnitureImg);
		when(furnitureService.getAllFurnitures()).thenReturn(furnitureDto);
		List<DisplayFurnitureDTO> furnitureDto1 = furnitureService.getAllFurnitures();
		assertEquals(furnitureDto,furnitureDto1);		
	}
		
//		@Test
//		public void viewProduct() throws SellYourFurnitureServiceException {
//			List<FurnitureImage> img = new ArrayList<FurnitureImage>();
//			List<Review> rlist = new ArrayList<Review>();
//			List<OrderItem> orders = new ArrayList<OrderItem>();
//			OrderItem order = new OrderItem();
//			Furniture f = new Furniture(1, "sofa", "wood", "sofa", 34560.0, "free", 0.0, "Wooden sofa", 4, null, img, rlist, null, orders);
//			FurnitureImage image = new FurnitureImage(1, "test data".getBytes(), f);
//			img.add(image);
//			f.setFurnitureImages(img);
//			Review r = new Review(1, 4, "", f, null);
//			rlist.add(r);
//			f.setReview(rlist);
//			List<Furniture> flist = new ArrayList<Furniture>();
//			flist.add(f);
//			OrderDetails orderDetils = new OrderDetails();
//			User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, flist, null, null,
//					null);
//			//r.setUserRating(new User(2, "abc", "abc", "abcd@gmail.com", "Athira@97", "9876543210", null, null, null, rlist, null));
//			orderDetils.setOrderUser(u);
//			orderDetils.setOrderId(1);
//			List<String> users = new ArrayList<String>();
//			users.add("abc@gmail.com");
//			order.setOrderItemId(1);
//			order.setFurniture(f);
//			order.setQuantity(1);
//			order.setOrder(orderDetils);
//			orders.add(order);
//			DisplayReviewDTO reviewDto = new DisplayReviewDTO(1, "athira", "ca", 3, "good", "abc@gmail.com");
//			List <DisplayReviewDTO> reviewlist = new ArrayList<DisplayReviewDTO>();
//			reviewlist.add(reviewDto);
//			ViewProductDTO product =new ViewProductDTO(1, "sofa", "sofa", "wood", 2345.0, "free", 0.0, "Wooden sofa", null, reviewlist, users, null, 4.3f);
//			
//			when(furnitureRepo.findByFurnitureId(1)).thenReturn(f);
//			Furniture furniture = furnitureRepo.findByFurnitureId(1);
//			assertThat(f).isEqualTo(furniture);
//			when(orderItemRepo.findByFurniture(f)).thenReturn(orders);
//			List<OrderItem> orderlist = orderItemRepo.findByFurniture(f);
//			assertThat(orderlist).isEqualTo(orders);
//			when(furnitureImageRepo.findByFurnitureImage(f)).thenReturn(img);
//			List<FurnitureImage> furnitureImg = furnitureImageRepo.findByFurnitureImage(f);
//			assertThat(img).isEqualTo(furnitureImg);
//			when(reviewRepo.findByFurnitureRating(furniture)).thenReturn(rlist);
//			List<Review> review = reviewRepo.findByFurnitureRating(furniture);
//			assertThat(review).isEqualTo(rlist);
//			when(furnitureService.viewProduct(1)).thenReturn(product);
//			ViewProductDTO v = furnitureService.viewProduct(1);
//			assertEquals(v,product);
//	}
	
	

	@Test

	public void addFurnitureTestPositive() throws SellYourFurnitureServiceException {

		PostFurnitureDTO p = new PostFurnitureDTO(1, "sofa", "wodden", 23000, "free", 0, "comfortable sitting", 12, 1,
				"harshadasugandhi4@gmail.com", null, "Sofa");

		String message = "";
		User u = new User();
		u.setUserId(1);

		when(userRepository.findByuserEmail("harshadasugandhi4@gmail.com")).thenReturn(Optional.of(u));

		Furniture furniture = new Furniture();
		furniture.setFurnitureId(p.getFurnitureId());
		furniture.setFurnitureDescription(p.getFurnitureDescription());
		furniture.setFurnitureMaterial(p.getFurnitureMaterial());
		furniture.setFurnitureName(p.getFurnitureName());
		furniture.setFurnitureQuantity(p.getFurnitureQuantity());
		furniture.setFurniturePrice(p.getFurniturePrice());
		furniture.setFurnitureShippingCharge(p.getFurnitureShippingCharge());
		furniture.setFurnitureShippingType(p.getFurnitureShippingType());
		furniture.setUser(u);
		furniture.setFurnitureType(p.getFurnitureType());

		when(furnitureRepo.save(furniture)).thenReturn(furniture);

		message = furnitureService.addFurniture(p);
		assertEquals("Furniture Adevertisement Posted", message);

		p = new PostFurnitureDTO(1, "sofa123", "wodden", 23000, "free", 0, "comfortable sitting", 12, 1,
				"harshadasugandhi4@gmail.com", null, "Sofa");

		try {
			message = furnitureService.addFurniture(p);
		} catch (SellYourFurnitureServiceException e) {
			assertEquals(" type should  contains characters and spaces.", e.getMessage());
		}
		p = new PostFurnitureDTO(1, "sofa", "wodden", 23000, "free", 0, "comfortable sitting", 12, 1,
				"harshadasugandhi4gmail.com", null, "Sofa");

		try {
			message = furnitureService.addFurniture(p);
		} catch (SellYourFurnitureServiceException e) {
			assertEquals("Please Sign up First", e.getMessage());
		}
		p = new PostFurnitureDTO(1, "sofa", "wodden", 23000, "free", 0, "comfortable sitting", 0, 1,
				"harshadasugandhi4@gmail.com", null, "Sofa");

		try {
			message = furnitureService.addFurniture(p);
		} catch (SellYourFurnitureServiceException e) {
			assertEquals("Quntity should be greater than 0", e.getMessage());
		}
		p = new PostFurnitureDTO(1, "sofa", "wodden", 0, "free", 0, "comfortable sitting", 1, 1,
				"harshadasugandhi4@gmail.com", null, "Sofa");

		try {
			message = furnitureService.addFurniture(p);
		} catch (SellYourFurnitureServiceException e) {
			assertEquals("Price  should be greater than 0 .", e.getMessage());
		}
		p = new PostFurnitureDTO(1, "sofa", "wodden", 1, "Paid", 0, "comfortable sitting", 1, 1,
				"harshadasugandhi4@gmail.com", null, "Sofa");

		try {
			message = furnitureService.addFurniture(p);
		} catch (SellYourFurnitureServiceException e) {
			assertEquals("Shipping charge  should be  greater than  0.", e.getMessage());
		}

	}

	@Test
	public void addFurnitureIamge() throws SellYourFurnitureApplicationException, IOException {

		PostFurnitureDTO p = new PostFurnitureDTO(1, "sofa", "wodden", 23000, "free", 0, "comfortable sitting", 12, 1,
				"harshadasugandhi@gmail.com", null, "Sofa");
		Furniture furniture = new Furniture();
		furniture.setFurnitureId(p.getFurnitureId());
		furniture.setFurnitureDescription(p.getFurnitureDescription());
		furniture.setFurnitureMaterial(p.getFurnitureMaterial());
		furniture.setFurnitureName(p.getFurnitureName());
		furniture.setFurnitureQuantity(p.getFurnitureQuantity());
		furniture.setFurniturePrice(p.getFurniturePrice());
		furniture.setFurnitureShippingCharge(p.getFurnitureShippingCharge());
		furniture.setFurnitureShippingType(p.getFurnitureShippingType());

		when(furnitureRepo.findTopByOrderByFurnitureIdDesc()).thenReturn(furniture);
		Furniture f = furnitureRepo.findTopByOrderByFurnitureIdDesc();

		FurnitureImage furnitureImage = new FurnitureImage();
		String fileName = "Desktop\\sofa.jpg";

		MockMultipartFile file = new MockMultipartFile("user-file", fileName, "text/plain", "test data".getBytes());

		furnitureImage.setImageContent(file.getBytes());

		furnitureImage.setFurnitureImage(f);

		when(furnitureImageRepo.save(furnitureImage)).thenReturn(furnitureImage);

		String message = furnitureService.addImage(file);
		assertEquals("Image Added", message);
	}
	
	@Test
	public void getFurnituresBasedOnSearch() throws SellYourFurnitureApplicationException
	{
		Furniture furniture=new Furniture();
		List <DisplayFurnitureDTO> list=new ArrayList<DisplayFurnitureDTO>();
		List <DisplayFurnitureDTO> list1=new ArrayList<DisplayFurnitureDTO>();

		List<Furniture> furnitures = new ArrayList<Furniture>();
		List<FurnitureImage> listFurnitureImageList=new ArrayList<FurnitureImage>();
		when(furnitureRepo.findAll()).thenReturn(furnitures);
		when(furnitureImageRepo.findByFurnitureImage(furniture)).thenReturn(listFurnitureImageList);
		try {
		List <DisplayFurnitureDTO>message = furnitureService.getFurnituresBasedOnSearch("wood");
		}catch(Exception e)
		{
			assertEquals("Your text does not match our results!", e.getMessage());

		}
		furniture.setFurnitureDescription("wood");
		furniture.setFurnitureName("wood");
		furniture.setFurnitureType("wood");
		furnitures.add(furniture);
		DisplayFurnitureDTO d1=new DisplayFurnitureDTO();
		d1.setFurnitureType("wood");
		list.add(d1);
		FurnitureImage i=new FurnitureImage();
		when(furnitureRepo.findAll()).thenReturn(furnitures);
		when(furnitureImageRepo.findByFurnitureImage(furniture)).thenReturn(listFurnitureImageList);
		
		when(furnitureImageRepo.findByFurnitureImage(furniture)).thenReturn(listFurnitureImageList);
	
		List <DisplayFurnitureDTO>message = furnitureService.getFurnituresBasedOnSearch("wood");
		assertEquals(list1,message);

	}
	
	
	

}
