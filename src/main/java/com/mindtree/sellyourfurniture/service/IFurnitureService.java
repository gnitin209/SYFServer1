package com.mindtree.sellyourfurniture.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.PostFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.ReviewDTO;
import com.mindtree.sellyourfurniture.dto.ViewProductDTO;
import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;

public interface IFurnitureService {
	public List<DisplayFurnitureDTO> getFurnituresBasedOnSearch(String text) throws SellYourFurnitureServiceException;

	public List<DisplayFurnitureDTO> getAllFurnitures() throws SellYourFurnitureServiceException;

	String addFurniture(PostFurnitureDTO furnitureDTO) throws SellYourFurnitureServiceException;

	String addImage(MultipartFile file) throws IOException;

	ViewProductDTO viewProduct(int furnitureId) throws SellYourFurnitureServiceException;


	//String addReview(ReviewDTO reviewDto);

}
