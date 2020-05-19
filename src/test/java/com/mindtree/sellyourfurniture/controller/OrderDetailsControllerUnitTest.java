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

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IOrderDetailsService;

public class OrderDetailsControllerUnitTest {
	@InjectMocks
	private OrderDetailsController OrderDetailsController;
	@Mock
	IOrderDetailsService orderService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

@Test
public void OrderDetailsUserTest() throws SellYourFurnitureApplicationException {
	when(orderService.addOrderDetails("p@gmail.com")).thenReturn("Successfully ordered");
	ResponseEntity<?> response = OrderDetailsController.addOrderDetails("p@gmail.com");
	assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	assertEquals("Successfully ordered", response.getBody());
}

@Test
public void getOrderDetailsTest() throws SellYourFurnitureApplicationException
{
	List<DisplayFurnitureDTO> listFurniture=new ArrayList<DisplayFurnitureDTO>();

	when(orderService.getOrderDeatils("harshadasugandhi4@gmail.com")).thenReturn(listFurniture);
	ResponseEntity<?> response = OrderDetailsController.getOrderDetails("harshadasugandhi4@gmail.com");
	assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
	}


}
