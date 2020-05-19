package com.mindtree.sellyourfurniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.sellyourfurniture.dto.AddressDto;
import com.mindtree.sellyourfurniture.service.IAddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*")
public class AddressController {
	@Autowired
	IAddressService addressService;

	@PostMapping(value = "/addAddress")
	public
	ResponseEntity<?> addAddress(@RequestBody AddressDto address) {
		return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.ACCEPTED);
	}

//	@GetMapping(value="/getAddress")
//	ResponseEntity<?> addFurniture(@RequestParam  String userEmail) {
//		return new ResponseEntity<>(addressService.getAddress(userEmail), HttpStatus.ACCEPTED);
//	}
}