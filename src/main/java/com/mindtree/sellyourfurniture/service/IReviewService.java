package com.mindtree.sellyourfurniture.service;

import com.mindtree.sellyourfurniture.dto.ReviewDTO;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;

public interface IReviewService {
	
//	boolean checkReview(int furnitureId, String userEmail);
	
	String addReview(ReviewDTO review)throws SellYourFurnitureServiceException;
//	
//	List<DisplayReviewDTO> displayReview(int furnitureId) throws SellYourFurnitureServiceException;
//	
//	float averageRating(int furnitureId) throws SellYourFurnitureServiceException;
	
	String updateReview(ReviewDTO reviewDto) throws SellYourFurnitureServiceException;

}
