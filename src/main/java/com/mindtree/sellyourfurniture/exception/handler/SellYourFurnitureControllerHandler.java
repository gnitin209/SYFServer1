package com.mindtree.sellyourfurniture.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.sellyourfurniture.dto.ErrorResponse;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;

@RestControllerAdvice
public class SellYourFurnitureControllerHandler {
	@ExceptionHandler(SellYourFurnitureApplicationException.class)
	public ResponseEntity<ErrorResponse> errorHandler(Exception e) {
		return new ResponseEntity<>(new ErrorResponse(e.getMessage(), e.getCause()),
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
