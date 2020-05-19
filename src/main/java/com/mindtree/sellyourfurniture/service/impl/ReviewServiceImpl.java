package com.mindtree.sellyourfurniture.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.mindtree.sellyourfurniture.service.IReviewService;

@Service

@Transactional
public class ReviewServiceImpl implements IReviewService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	FurnitureRepository furnitureRepo;
	
	@Autowired
	ReviewRepository reviewRepo;

	@Override
	public String addReview(ReviewDTO reviewDto) throws SellYourFurnitureServiceException {
		User user = userRepo.findByUserEmail(reviewDto.getUserEmail());
		if(user == null)
			throw new UserNotSignedUpException("Please Sign up First");
		Furniture furniture = furnitureRepo.findByFurnitureId(reviewDto.getFurnitureId());
		if(furniture == null)
			throw new FurnitureNotFoundException("No such furniture found");
		if(reviewRepo.findByFurnitureRatingAndUserRating(furniture, user) != null)
			throw new MultipleReviewsByOneUserException("A user can only give one review for one furniture");
		if(reviewDto.getRating()<1)
			throw new InvalidRatingException("Rating should be greater than 0");
		Review review = new Review();
		review.setUserRating(user);
		review.setFurnitureRating(furniture);
		review.setRating(reviewDto.getRating());
		review.setFeedback(reviewDto.getFeedback());
		reviewRepo.save(review);
		return "Your review is added";
	}


	@Override
	public String updateReview(ReviewDTO reviewDto) throws SellYourFurnitureServiceException {
		Furniture furniture = furnitureRepo.findByFurnitureId(reviewDto.getFurnitureId());
		User user = userRepo.findByUserEmail(reviewDto.getUserEmail());
		Review review = reviewRepo.findByFurnitureRatingAndUserRating(furniture, user);
		if(review == null)
			throw new NoReviewFoundException("No such review is exist");
		review.setRating(reviewDto.getRating());
		review.setFeedback(reviewDto.getFeedback());
		reviewRepo.save(review);
		return "Review updated";
		
	}
  }
