package com.mindtree.sellyourfurniture.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mindtree.sellyourfurniture.dto.DisplayFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.PostFurnitureDTO;
import com.mindtree.sellyourfurniture.dto.ViewProductDTO;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IFurnitureService;

@RestController
@RequestMapping("/furniture")

@CrossOrigin(origins = "*")

public class FurnitureController {


	@Autowired
	 private IFurnitureService furnitureService;


	@GetMapping("/findfurniture")
	public ResponseEntity<List<DisplayFurnitureDTO>> findFurnitureFromText(@RequestParam("text") String text)
			throws SellYourFurnitureApplicationException {
		return new ResponseEntity<>(furnitureService.getFurnituresBasedOnSearch(text), HttpStatus.OK);
	}


	@PostMapping(value="/addFurniture")

	ResponseEntity<String> addFurniture(@RequestBody PostFurnitureDTO furnitureDTO) throws SellYourFurnitureApplicationException {
		return new ResponseEntity<>(furnitureService.addFurniture(furnitureDTO), HttpStatus.ACCEPTED);
	}


	   @PostMapping("/savefile")
	ResponseEntity<String> addFurnitureImage(@RequestParam("file") MultipartFile file) throws IOException {
		return new ResponseEntity<>(furnitureService.addImage(file), HttpStatus.ACCEPTED);

	}

	@GetMapping("/displayFurniture")
	public ResponseEntity<List<DisplayFurnitureDTO>> getAllFurnitures() throws SellYourFurnitureApplicationException {
		return new ResponseEntity<>(furnitureService.getAllFurnitures(), HttpStatus.OK);
	}
	
	@GetMapping("/viewFurniture")
	public ResponseEntity<ViewProductDTO> viewProduct(@RequestParam("furnitureId") int furnitureId) throws SellYourFurnitureApplicationException {
		return new ResponseEntity<>(furnitureService.viewProduct(furnitureId), HttpStatus.OK);
	}

	


}
