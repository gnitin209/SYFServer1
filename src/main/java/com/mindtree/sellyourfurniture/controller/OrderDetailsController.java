package com.mindtree.sellyourfurniture.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.service.IOrderDetailsService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*")
public class OrderDetailsController {
	@Autowired
	IOrderDetailsService orderservice;
	@GetMapping(value="/addorder")
	ResponseEntity<String> addOrderDetails(@RequestParam("userEmail")  String userEmail) throws SellYourFurnitureApplicationException  {
		return new ResponseEntity<>(orderservice.addOrderDetails(userEmail), HttpStatus.ACCEPTED);
	}
	

    @GetMapping(value = "/displayorder")
    public ResponseEntity<List<DisplayFurnitureDTO>> getOrderDetails(@RequestParam String userEmail) throws SellYourFurnitureApplicationException
    {	  

        return new ResponseEntity<>(orderservice.getOrderDeatils(userEmail),HttpStatus.ACCEPTED);
    }

}