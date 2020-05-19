package com.mindtree.sellyourfurniture.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidEmailException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidPassWordException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidPhoneNumberException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidUserNameException;
import com.mindtree.sellyourfurniture.exception.service.custom.UserAlreadyExist;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.impl.UserServiceImpl;

public class UserServiceImpleUnitTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loginUserTest() throws SellYourFurnitureApplicationException {

		String userEmail = "";
		String userPassword = "";
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);

		String response = userService.loginUser(userEmail, userPassword);
		assertEquals("false", response);

	}

	@Test
	public void loginUserInvalidTest() throws SellYourFurnitureApplicationException {

		String userEmail = "";
		String userPassword = "";
		User user = new User();
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		userRepository.save(user);
		Optional<User> user1 = Optional.of(user);
		when(userRepository.findByUserEmailAndUserPassword(userEmail, userPassword)).thenReturn(user1);
		String response = userService.loginUser(user.getUserEmail(), user.getUserPassword());
		assertEquals(null, response);

	}

	@Test
	public void loginTest() throws SellYourFurnitureApplicationException {

		String userEmail = "prakashsgddggddgingh143.ps5@gmail.com";

		User user = new User();
		user.setUserEmail(userEmail);
		when(userRepository.findByUserEmail(userEmail)).thenReturn(user);
		boolean response = userService.login(userEmail);
		assertEquals(true, response);
	}

	@Test
	public void loginInvalidTest() throws SellYourFurnitureApplicationException {

		String userEmail = "prakashsgddggddgingh143.ps5@gmail.com";
		User user1 = null;
		when(userRepository.save(user1)).thenReturn(user1);
		when(userRepository.findByUserEmail(userEmail)).thenReturn(user1);
		System.out.println(user1);
		boolean response = userService.login(userEmail);
		System.out.println(response);
		assertEquals(false, response);
	}

	User user = new User();

	@Test
	public void userSignUpTest() throws SellYourFurnitureApplicationException {
		user.setUserFirstName("Nitin");
		user.setUserLastName("garg");
		user.setUserEmail("gnitin2312@gmail.com");
		user.setUserPassword("/Nitin12");
		user.setUserPhoneNumber("8572008762");
		Optional<User> user1 = Optional.empty();

		when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
		when(userRepository.save(user)).thenReturn(user);

		String response = userService.addUser(user);
		assertEquals("User Succesfully Signed Up", response);

	}

	@Test
	public void signupUserFromSocial() throws SellYourFurnitureApplicationException {
		User user = new User();
		user.setUserEmail("pra@q");
		user.setUserFirstName("pra");
		user.setUserLastName("jai");
		Optional<User> user1 = Optional.empty();
		when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
		when(userRepository.save(user)).thenReturn(user);
		String response = userService.signupWithGmail(user);
		assertEquals("User Succesfully Signed Up", response);

	}

	@Test
	public void signupUserException() throws SellYourFurnitureServiceException {
		try {
			user.setUserFirstName("Nitin");
			user.setUserLastName("garg");
			user.setUserEmail("gnitin2312@gmail.com");
			user.setUserPassword("/Nitin12");
			user.setUserPhoneNumber("8572008762");
			Optional<User> user1 = Optional.of(user);

			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);

			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);
		} catch (UserAlreadyExist e) {
			assertEquals("User already Exist.", e.getMessage());
		}

	}

	@Test
	public void signupSocialUserException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@q");
			user.setUserFirstName("pra");
			user.setUserLastName("jai");
			Optional<User> user1 = Optional.of(user);
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.signupWithGmail(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (UserAlreadyExist e) {

			assertEquals("User already Exist.", e.getMessage());

		}
	}

	@Test
	public void signupFirstNameException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@q");
			user.setUserFirstName("");
			user.setUserLastName("jai");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidUserNameException e) {

			assertEquals("Name should  contains characters and spaces.", e.getMessage());

		}
	}

	@Test
	public void signupLastNameException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@q");
			user.setUserFirstName("pj");
			user.setUserLastName("");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidUserNameException e) {

			assertEquals("Name should  contains characters and spaces.", e.getMessage());

		}
	}

	@Test
	public void signupPasswordException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@q");
			user.setUserFirstName("pj");
			user.setUserLastName("gh");
			user.setUserPassword("Nitin12 ");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidPassWordException e) {

			assertEquals(
					"Password characters should contains atleast one Uppercase,lowercase,special character and a digit and should be of length b/w 8-15",
					e.getMessage());

		}
	}

	@Test
	public void signupPasswordLengthException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@q");
			user.setUserFirstName("pj");
			user.setUserLastName("gh");
			user.setUserPassword("Ni12@");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidPassWordException e) {

			assertEquals(
					"Password characters should contains atleast one Uppercase,lowercase,special character and a digit and should be of length b/w 8-15",
					e.getMessage());

		}
	}

	@Test
	public void signupPassException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@gmail.com");
			user.setUserFirstName("pj");
			user.setUserLastName("gh");
			user.setUserPassword("Nitingarg@1234556667");
			user.setUserPhoneNumber("8572008762");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidPassWordException e) {

			assertEquals(
					"Password characters should contains atleast one Uppercase,lowercase,special character and a digit and should be of length b/w 8-15",
					e.getMessage());

		}
	}

	@Test
	public void signupNumberException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("prankur@gmail.com");
			user.setUserFirstName("pj");
			user.setUserLastName("gh");
			user.setUserPassword("Nitin@123");
			user.setUserPhoneNumber("8976544");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidPhoneNumberException e) {

			assertEquals("Phone number should be of ten digits and should  contains digits only.", e.getMessage());
		}
	}

	@Test
	public void signupEmailException() throws SellYourFurnitureServiceException {
		try {

			User user = new User();
			user.setUserEmail("pra@nkur@gmail.com");
			user.setUserFirstName("pj");
			user.setUserLastName("gh");
			user.setUserPassword("Nitin@123");
			user.setUserPhoneNumber("8572008762");
			Optional<User> user1 = Optional.empty();
			when(userRepository.findByuserEmail(user.getUserEmail())).thenReturn(user1);
			when(userRepository.save(user)).thenReturn(user);
			String response = userService.addUser(user);
			assertEquals("User Succesfully Signed Up", response);

		} catch (InvalidEmailException e) {

			assertEquals("Email is not valid.", e.getMessage());
		}
	}

}
