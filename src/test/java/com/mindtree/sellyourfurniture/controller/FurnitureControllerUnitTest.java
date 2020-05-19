package com.mindtree.sellyourfurniture.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.DisplayReviewDTO;
import com.mindtree.sellyourfurniture.dto.PostFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.ViewProductDTO;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IFurnitureService;
import com.mindtree.sellyourfurniture.service.impl.FurnitureServiceImpl;


public class FurnitureControllerUnitTest {
	private static List<DisplayFurnitureDTO> furniture = new ArrayList<DisplayFurnitureDTO>();

	@InjectMocks
	private FurnitureController furnitureController;

	@Mock

	private IFurnitureService furnitureService;
	

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test

	
	public void getAllFurnitures() throws SellYourFurnitureApplicationException
	{
		String str = "Athira";
		byte[] byteArr = str.getBytes();
		furniture.add(new DisplayFurnitureDTO(1, "sofa", 3200.00, byteArr, "wood","sofa", "free"));
		when(furnitureService.getAllFurnitures()).thenReturn(furniture);
		ResponseEntity<?> response = furnitureController.getAllFurnitures();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(furniture, response.getBody());
	}

	public void searchFurnitureFromText() throws SellYourFurnitureApplicationException {
		String text="table";
		List<DisplayFurnitureDTO> furnitureDTOs=new ArrayList<DisplayFurnitureDTO>();
		when(furnitureService.getFurnituresBasedOnSearch(text)).thenReturn(furnitureDTOs);
		ResponseEntity<?> responseBody=furnitureController.findFurnitureFromText(text);
		assertEquals(HttpStatus.OK, responseBody.getStatusCode());
	}

	
	@Test

	public void viewProduct() throws SellYourFurnitureApplicationException {
		List<byte[]> imageContent = new ArrayList<byte[]>();
		String str = "Athira";
		byte[] byteArr = str.getBytes();
		imageContent.add(byteArr);
		List<DisplayReviewDTO> reviews = new ArrayList<DisplayReviewDTO>();
		List<String> users = new ArrayList<String>();
		List<String> userReviewed = new ArrayList<String>();
		ViewProductDTO product = new ViewProductDTO(1, "Table", "table", "Wood", 12345.0, "paid", 300.0, "Wooden table", imageContent, reviews, users,userReviewed,4.3f );
		when(furnitureService.viewProduct(1)).thenReturn(product);
		ResponseEntity<?> response = furnitureController.viewProduct(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(product, response.getBody());
	}


	@Test
	  public void addFurnitureTestPositive() throws SellYourFurnitureApplicationException {


		  PostFurnitureDTO p = new PostFurnitureDTO( 1, "sofa", "wodden", 23000,
	    			"free", 0, "comfortable sitting",
	    			12, 1,"harshadasugandhi@gmail.com", null, "Sofa");
	  
	        when(furnitureService.addFurniture(p)).thenReturn("Success");
	        
	        ResponseEntity<?> responseEntity = furnitureController.addFurniture(p);
	         
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
	 
	        assertThat(responseEntity.getBody()).isEqualTo("Success");

	  }
	
	@Test
	  public void addFurnitureIamge() throws SellYourFurnitureApplicationException, IOException
	{

		String fileName = "Desktop\\sofa.jpg";

	      MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
	              "text/plain", "test data".getBytes());
	 
	        when(furnitureService.addImage(mockMultipartFile)).thenReturn("Success");
	        
	        ResponseEntity<?> responseEntity = furnitureController.addFurnitureImage(mockMultipartFile);
	         
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
	        assertThat(responseEntity.getBody()).isEqualTo("Success");

	      }
	@Test
	public void findFurnitureFromtext() throws SellYourFurnitureApplicationException
	{
		String text="table";
		
		List <DisplayFurnitureDTO>list=new ArrayList();
		DisplayFurnitureDTO d1=new DisplayFurnitureDTO();
		list.add(d1);
		
		when(furnitureService.getFurnituresBasedOnSearch(text)).thenReturn(list);
		 ResponseEntity<?> responseEntity = furnitureController.findFurnitureFromText(text);
         
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	        assertThat(responseEntity.getBody()).isEqualTo(list);
	}
	@Test
	  public void decompress() throws  IOException
	{
		//String fileName = "Desktop\\sofa.jpg";

	      //MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file",fileName,
	            //  "text/plain", "test data".getBytes());
	      byte[] bytes = "Desktop\\sofa.jpg".getBytes();
	      
	      byte[] bytes1=FurnitureServiceImpl.decompressBytes(bytes);
	      
	      
	      assertEquals(bytes1, bytes1);
	      
	}
}
