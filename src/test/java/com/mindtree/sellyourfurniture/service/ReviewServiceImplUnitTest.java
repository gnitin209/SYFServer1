package com.mindtree.sellyourfurniture.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.sellyourfurniture.dto.ReviewDTO;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.Review;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.FurnitureNotFoundException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidRatingException;
import com.mindtree.sellyourfurniture.exception.service.custom.MultipleReviewsByOneUserException;
import com.mindtree.sellyourfurniture.exception.service.custom.NoReviewFoundException;
import com.mindtree.sellyourfurniture.exception.service.custom.UserNotSignedUpException;
import com.mindtree.sellyourfurniture.repository.FurnitureRepository;
import com.mindtree.sellyourfurniture.repository.ReviewRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.impl.ReviewServiceImpl;

public class ReviewServiceImplUnitTest {

	@InjectMocks
	private ReviewServiceImpl reviewService;
	@Mock

	private ReviewRepository reviewRepo;

	@Mock
	private FurnitureRepository furnitureRepo;

	@Mock
	private UserRepository userRepo;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addReview() throws SellYourFurnitureServiceException {
		Furniture f = new Furniture();
		Review r = new Review();
		List<Review> rlist = new ArrayList<Review>();
		List<Furniture> flist = new ArrayList<Furniture>();
		ReviewDTO reviewDto = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
		User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, flist, null, rlist,
				null);

		f.setFurnitureId(1);
		r.setReviewId(1);
		flist.add(f);
		rlist.add(r);
		when(userRepo.findByUserEmail("abc@gmail.com")).thenReturn(u);
		User user = userRepo.findByUserEmail("abc@gmail.com");
		assertThat(u).isEqualTo(user);
		when(furnitureRepo.findByFurnitureId(1)).thenReturn(f);
		Furniture furniture = furnitureRepo.findByFurnitureId(1);
		assertThat(f).isEqualTo(furniture);
		when(reviewRepo.findByFurnitureRatingAndUserRating(f, u)).thenReturn(null);
		Review review = reviewRepo.findByFurnitureRatingAndUserRating(f, u);
		assertThat(review).isEqualTo(null);

		String response = reviewService.addReview(reviewDto);
		assertEquals("Your review is added", response);
	}

	@Test
	public void addReviewWithNoUser() throws SellYourFurnitureServiceException {
		try {
		Furniture f = new Furniture();
		Review r = new Review();
		List<Review> rlist = new ArrayList<Review>();
		List<Furniture> flist = new ArrayList<Furniture>();
		ReviewDTO reviewDto = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
		f.setFurnitureId(1);
		r.setReviewId(1);
		flist.add(f);
		rlist.add(r);
		User u = null;
		when(userRepo.findByUserEmail("abc@gmail.com")).thenReturn(u);
		User user = userRepo.findByUserEmail("abc@gmail.com");
		assertThat(u).isEqualTo(user);
		String response = reviewService.addReview(reviewDto);
		assertEquals("Your review is added", response);
		}catch (UserNotSignedUpException e) {
			assertEquals("Please Sign up First", e.getMessage());
		}
	}


	
	
	@Test
	public void addReviewWithNoFurniture() throws SellYourFurnitureServiceException {
		try {
		Furniture f = null;
		Review r = new Review();
		List<Review> rlist = new ArrayList<Review>();
		ReviewDTO reviewDto = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
		User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, null, null, rlist,
				null);
		r.setReviewId(1);
		when(userRepo.findByUserEmail("abc@gmail.com")).thenReturn(u);
		User user = userRepo.findByUserEmail("abc@gmail.com");
		assertThat(u).isEqualTo(user);
		when(furnitureRepo.findByFurnitureId(1)).thenReturn(f);
		Furniture furniture = furnitureRepo.findByFurnitureId(1);
		assertThat(f).isEqualTo(furniture);
		String response = reviewService.addReview(reviewDto);
		assertEquals("Your review is added", response);
		}catch (FurnitureNotFoundException e) {
			assertEquals("No such furniture found", e.getMessage());
		}
	}
	
	@Test
	public void addReviewWithMultipleReviews() throws SellYourFurnitureServiceException {
		try {
		Furniture f = new Furniture();
		Review r = new Review();
		List<Review> rlist = new ArrayList<Review>();
		List<Furniture> flist = new ArrayList<Furniture>();
		ReviewDTO reviewDto = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
		User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, flist, null, rlist,
				null);

		f.setFurnitureId(1);
		r.setReviewId(1);
		flist.add(f);
		rlist.add(r);
		when(userRepo.findByUserEmail("abc@gmail.com")).thenReturn(u);
		User user = userRepo.findByUserEmail("abc@gmail.com");
		assertThat(u).isEqualTo(user);
		when(furnitureRepo.findByFurnitureId(1)).thenReturn(f);
		Furniture furniture = furnitureRepo.findByFurnitureId(1);
		assertThat(f).isEqualTo(furniture);
		when(reviewRepo.findByFurnitureRatingAndUserRating(f, u)).thenReturn(r);
		Review review = reviewRepo.findByFurnitureRatingAndUserRating(f, u);
		assertThat(review).isEqualTo(r);

		String response = reviewService.addReview(reviewDto);
		assertEquals("Your review is added", response);
		}catch (MultipleReviewsByOneUserException e) {
			assertEquals("A user can only give one review for one furniture", e.getMessage());
		}
	}
	
	@Test
	public void addReviewWithInvalidRating() throws SellYourFurnitureServiceException {
		try {
		Furniture f = new Furniture();
		Review r = new Review();
		List<Review> rlist = new ArrayList<Review>();
		List<Furniture> flist = new ArrayList<Furniture>();
		ReviewDTO reviewDto = new ReviewDTO(0, "great product", 1, "abc@gmail.com");
		User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, flist, null, rlist,
				null);

		f.setFurnitureId(1);
		r.setReviewId(1);
		flist.add(f);
		rlist.add(r);
		when(userRepo.findByUserEmail("abc@gmail.com")).thenReturn(u);
		User user = userRepo.findByUserEmail("abc@gmail.com");
		assertThat(u).isEqualTo(user);
		when(furnitureRepo.findByFurnitureId(1)).thenReturn(f);
		Furniture furniture = furnitureRepo.findByFurnitureId(1);
		assertThat(f).isEqualTo(furniture);
		when(reviewRepo.findByFurnitureRatingAndUserRating(f, u)).thenReturn(null);
		Review review = reviewRepo.findByFurnitureRatingAndUserRating(f, u);
		assertThat(review).isEqualTo(null);

		String response = reviewService.addReview(reviewDto);
		assertEquals("Your review is added", response);
		}catch (InvalidRatingException e) {
			assertEquals("Rating should be greater than 0", e.getMessage());
		}
	}
	
	@Test
	public void updateReview() throws SellYourFurnitureServiceException {
		try {
			Furniture f = new Furniture();
			List<Review> rlist = new ArrayList<Review>();
			List<Furniture> flist = new ArrayList<Furniture>();
			ReviewDTO reviewDto = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
			User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, flist, null, rlist,
					null);
			f.setFurnitureId(1);
			flist.add(f);
			Review review = reviewRepo.findByFurnitureRatingAndUserRating(f, u);
			assertThat(review).isEqualTo(null);
			String response = reviewService.updateReview(reviewDto);
			assertEquals("Review updated", response);
		} catch (NoReviewFoundException e) {
			assertEquals("No such review is exist", e.getMessage());
		}
	}
	
	@Test
	public void updateReviewWithValidCondition() throws SellYourFurnitureServiceException {
			Furniture f = new Furniture();
			List<Review> rlist = new ArrayList<Review>();
			List<Furniture> flist = new ArrayList<Furniture>();
			ReviewDTO reviewDto = new ReviewDTO(4, "great product", 1, "abc@gmail.com");
			User u = new User(1, "athira", "ca", "abc@gmail.com", "Athira@123", "9876543210", null, flist, null, rlist,
					null);
			f.setFurnitureId(1);
			flist.add(f);
			Review r1 = new Review(1, 4, "", f, u);
			rlist.add(r1);
			when(userRepo.findByUserEmail("abc@gmail.com")).thenReturn(u);
			User user = userRepo.findByUserEmail("abc@gmail.com");
			assertThat(u).isEqualTo(user);
			when(furnitureRepo.findByFurnitureId(1)).thenReturn(f);
			Furniture furniture = furnitureRepo.findByFurnitureId(1);
			assertThat(f).isEqualTo(furniture);
	
			when(reviewRepo.findByFurnitureRatingAndUserRating(furniture, user)).thenReturn(r1);
			Review review = reviewRepo.findByFurnitureRatingAndUserRating(furniture, user);
			assertThat(review).isEqualTo(r1);
			String response = reviewService.updateReview(reviewDto);
			assertEquals("Review updated", response);
		
	}


}
