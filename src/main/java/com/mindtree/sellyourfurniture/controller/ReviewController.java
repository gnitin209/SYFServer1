package com.mindtree.sellyourfurniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.sellyourfurniture.dto.ReviewDTO;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IReviewService;

@RestController
@RequestMapping("/review")

@CrossOrigin(origins = "*")

public class ReviewController {
	
	@Autowired
	IReviewService reviewService;
	
	@PostMapping("/addReview")
	ResponseEntity<String> addReview(@RequestBody ReviewDTO reviewDto) throws SellYourFurnitureApplicationException {
		return new ResponseEntity<String>(reviewService.addReview(reviewDto), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/updateReview")
	ResponseEntity<String> updateReview(@RequestBody ReviewDTO reviewDto) throws SellYourFurnitureApplicationException {
		return new ResponseEntity<String>(reviewService.updateReview(reviewDto), HttpStatus.ACCEPTED);
	}
	
}
