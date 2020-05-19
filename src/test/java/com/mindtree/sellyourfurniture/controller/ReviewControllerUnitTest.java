package com.mindtree.sellyourfurniture.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindtree.sellyourfurniture.dto.ReviewDTO;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IReviewService;

public class ReviewControllerUnitTest {
	
	@InjectMocks
	private ReviewController reviewController;

	@Mock

	private IReviewService reviewService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	static ReviewDTO review = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
	
	@Test

	public void addReview() throws SellYourFurnitureApplicationException {
		when(reviewService.addReview(review)).thenReturn("review added");
		ResponseEntity<String> response = reviewController.addReview(review);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("review added", response.getBody());
	}
	
	@Test

	public void updateReview() throws SellYourFurnitureApplicationException {
		when(reviewService.updateReview(review)).thenReturn("review updated");
		ResponseEntity<String> response = reviewController.updateReview(review);
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("review updated", response.getBody());
	}
	
	
	
}
