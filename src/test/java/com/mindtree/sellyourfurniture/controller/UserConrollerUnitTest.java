package com.mindtree.sellyourfurniture.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IUserService;

public class UserConrollerUnitTest {

	@InjectMocks
    private UserController userController;
    @Mock
    IUserService userService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    User user=new User();
  
    @Test
    public void userSignUpTest() throws SellYourFurnitureApplicationException {
   user.setUserFirstName("Nitin");
   user.setUserLastName("");
   user.setUserEmail("gnitin2312@gmail.com");
   user.setUserPassword("itin12");
   user.setUserPhoneNumber("8572008762");
    	when(userService.addUser(user)).thenReturn("true");
        ResponseEntity<?> response=userController.signup(user);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("true", response.getBody());
}
    @Test
    public void signupUserFromSocialTest() throws SellYourFurnitureApplicationException {
        User uld = new User();
        uld.setUserFirstName("nitin");
        uld.setUserLastName("garg");
        uld.setUserEmail("gnitin2312@gmsil.com");
        when(userService.signupWithGmail(uld)).thenReturn("Succesfull");
        ResponseEntity<?> response=userController.signupWithGmail(uld);
        //System.out.println(response.getBody());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Succesfull", response.getBody());
    }
    @Test
	public void loginUserTest() throws SellYourFurnitureApplicationException {
		when(userService.loginUser("p@gmail.com", "123")).thenReturn("Successfully login");
		ResponseEntity<?> response = userController.loginUser("p@gmail.com", "123");
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("Successfully login", response.getBody());
	}

	@Test
	public void loginUserTestInvalidCredentials() throws SellYourFurnitureApplicationException {
		when(userService.loginUser("h@gmail.com", "987")).thenReturn("Invalid login");
		ResponseEntity<?> response = userController.loginUser("h@gmail.com", "987");
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("Invalid login", response.getBody());
	}

	@Test
	public void loginTest() throws SellYourFurnitureApplicationException {

		when(userService.login("prakashsingh143.ps5@gmail.com")).thenReturn(true);
		ResponseEntity<?> response = userController.login("prakashsingh143.ps5@gmail.com");
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(true, response.getBody());
	}

	@Test
	public void loginTestInvalid() throws SellYourFurnitureApplicationException {

		when(userService.login("prakashsingh.@gmail.com")).thenReturn(false);
		ResponseEntity<?> response = userController.login("prakashsingh.@gmail.com");
		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals(false, response.getBody());
	}
}