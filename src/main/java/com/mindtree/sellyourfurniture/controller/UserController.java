package com.mindtree.sellyourfurniture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IUserService;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	IUserService userService;
	@PostMapping("/adduser")
	public ResponseEntity<?> signup(@RequestBody User user) throws SellYourFurnitureApplicationException
	{
		
		return new ResponseEntity<>(userService.addUser(user),HttpStatus.ACCEPTED);
	}

	@GetMapping("/login")
    public ResponseEntity<?>loginUser(@RequestParam String userEmail, @RequestParam String userPassword) throws SellYourFurnitureApplicationException {
       String message=userService.loginUser(userEmail,userPassword);
        System.out.println(message);
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);
    }
 

	@PostMapping("/addgmailuser")
	public ResponseEntity<?> signupWithGmail(@RequestBody User user) throws SellYourFurnitureApplicationException
	{
		return new ResponseEntity<>(userService.signupWithGmail(user),HttpStatus.ACCEPTED);
	}
	@GetMapping("/loginGmail")
    public ResponseEntity<Boolean>login(@RequestParam String userEmail) throws SellYourFurnitureApplicationException {
     boolean message=userService.login(userEmail);
        System.out.println(message);
        return new ResponseEntity<Boolean>(message,HttpStatus.ACCEPTED);
    }
	
	

}
