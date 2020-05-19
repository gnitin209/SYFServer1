package com.mindtree.sellyourfurniture.service;


import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;

public interface IUserService {

	String addUser(User user) throws SellYourFurnitureServiceException;


	String loginUser(String userEmail, String userPassword) throws SellYourFurnitureServiceException;

	String signupWithGmail(User user) throws SellYourFurnitureServiceException;


	boolean login(String userEmail);



}
