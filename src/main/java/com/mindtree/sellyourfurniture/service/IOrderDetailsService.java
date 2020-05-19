
package com.mindtree.sellyourfurniture.service;

import java.util.List;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;

public interface IOrderDetailsService {

	String addOrderDetails(String userEmail) throws SellYourFurnitureServiceException;

	List<DisplayFurnitureDTO> getOrderDeatils(String userEmail) throws SellYourFurnitureServiceException;
	
	

}