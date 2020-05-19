package com.mindtree.sellyourfurniture.service.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.service.SellYourFurnitureServiceException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidEmailException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidPassWordException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidPhoneNumberException;
import com.mindtree.sellyourfurniture.exception.service.custom.InvalidUserNameException;
import com.mindtree.sellyourfurniture.exception.service.custom.UserAlreadyExist;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.IUserService;
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	Pattern pattern;
	Matcher match;
	boolean result;
	@Autowired
	UserRepository userRepo;

	@Override
	public String addUser(User user) throws SellYourFurnitureServiceException {
		Optional<User> userExist = userRepo.findByuserEmail(user.getUserEmail());
		if (userExist.isPresent())
			throw new UserAlreadyExist("User already Exist.");
		// First name pattern checking.
		pattern = Pattern.compile("^(?=.*[a-zA-z])[a-zA-Z\\s]*$");
		match = pattern.matcher(user.getUserFirstName());
		result = match.matches();
		if (result == false) {
			throw new InvalidUserNameException("Name should  contains characters and spaces.");
		}
		// Last name pattern checking.
		pattern = Pattern.compile("^(?=.*[a-zA-z])[a-zA-Z\\s]*$");
		match = pattern.matcher(user.getUserLastName());
		result = match.matches();
		if (result == false) {
			throw new InvalidUserNameException("Name should  contains characters and spaces.");
		}

		// User password pattern checking.
		pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]){8,15}$");
		match = pattern.matcher(user.getUserPassword());
		result = match.matches();
		boolean result1=false;
		for(int i=0;i<user.getUserPassword().length();i++)
		{
			if(user.getUserPassword().charAt(i)==33||user.getUserPassword().charAt(i)==34||(user.getUserPassword().charAt(i)>=39&&user.getUserPassword().charAt(i)<=47)||(user.getUserPassword().charAt(i)>=35&&user.getUserPassword().charAt(i)<=38||(user.getUserPassword().charAt(i)>=58&&user.getUserPassword().charAt(i)<=64||(user.getUserPassword().charAt(i)>=91&&user.getUserPassword().charAt(i)<=96||(user.getUserPassword().charAt(i)>=123&&user.getUserPassword().charAt(i)<=126)))))
					{
				result1=true;
				break;
					}
		}
		if(user.getUserPassword().length()>=8&&user.getUserPassword().length()<=15);
			else
		{
			result1=false;
		}
		if (result==false&&result1==false||result==true&&result1==false) {
		
			throw new InvalidPassWordException("Password characters should contains atleast one Uppercase,lowercase,special character and a digit and should be of length b/w 8-15");
		}// User phone number validation checking.
		pattern = Pattern.compile(("^[1-9][0-9]{9}"));
		match = pattern.matcher(user.getUserPhoneNumber());
		result = match.matches();
		if (result == false) {
			throw new InvalidPhoneNumberException("Phone number should be of ten digits and should  contains digits only.");
		}
//		 User email validation checking.
		pattern = Pattern.compile("^[a-z0-9](\\.?[a-z0-9])+@[a-z0-9.-]+\\.[a-z]{2,4}$", Pattern.CASE_INSENSITIVE);
		match = pattern.matcher(user.getUserEmail());
		result = match.matches();
		if (result == false) {
			throw new InvalidEmailException("Email is not valid.");

		}
		user.setUserPassword(user.getUserPassword());
		userRepo.save(user);
		return "User Succesfully Signed Up";
	}

	@Override
    public String loginUser(String userEmail, String userPassword) throws SellYourFurnitureServiceException {
       
    User user=userRepo.findByUserEmailAndUserPassword(userEmail, userPassword).orElse(null);
    if (user == null)
    {
    	System.out.println("something");
        return "false";
    }



     if(user.getUserPassword().matches(userPassword)) 
     {
         return user.getUserFirstName();
    
    }
     {
    	 return "false";
    }

}


	@Override
	public String signupWithGmail(User user) throws SellYourFurnitureServiceException{
		Optional<User> userExist = userRepo.findByuserEmail(user.getUserEmail());
		if (userExist.isPresent())
			throw new UserAlreadyExist("User already Exist.");

		userRepo.save(user);
		return "User Succesfully Signed Up";
	}

	@Override
	public boolean login(String userEmail) {
		User user = userRepo.findByUserEmail(userEmail);
		if (user != null) {
			return true;
		}

		return false;
	}

}
