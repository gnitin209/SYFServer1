package com.mindtree.sellyourfurniture.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mindtree.sellyourfurniture.controller.AddressController;
import com.mindtree.sellyourfurniture.dto.AddressDto;
import com.mindtree.sellyourfurniture.entity.Address;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.repository.AddressRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.impl.AddressServiceImpl;

public class AddressServiceImplUnitTest {
	 @InjectMocks
	 	AddressServiceImpl addressService;
	 

	  
	    @Mock
	    private UserRepository userRepository;
	    @Mock
	    private AddressRepository addressrepo;

	 

	    @BeforeEach
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	
	@Test
		public void addAddressTest() throws SellYourFurnitureApplicationException {
		AddressDto addressdto=new AddressDto();
		Address addres=new Address();
		
		addressdto.setAddressStreet("st.no. 1");
		addressdto.setAddressCity("jammu");
		addressdto.setAddressDistrict("jammu");
		addressdto.setAddressPincode("12123");
		addressdto.setAddressState("jammu and kashmir");
		addressdto.setAddressCountry("India");
		addressdto.setUserEmail("p@gmail.com");
		addres.setAddressId(1);
		addres.setAddressStreet(addressdto.getAddressStreet());
		addres.setAddressCity(addressdto.getAddressCity());
		addres.setAddressDistrict(addressdto.getAddressDistrict());
		addres.setAddressPincode(addressdto.getAddressPincode());
		addres.setAddressState(addressdto.getAddressState());
		addres.setAddressCountry(addressdto.getAddressCountry());
			User user=new User();
			user.setUserId(1);
			user.setUserEmail("p@gmail.com");
			//when(userRepository.save(user)).thenReturn(user);
			Optional<User>user1=Optional.of(user);
			when(userRepository.findByuserEmail("p@gmail.com")).thenReturn(user1);
			System.out.println(user);
			addres.setUserAddress(user);
			System.out.println(addres);
		when(addressrepo.save(addres)).thenReturn(addres);
			String response=addressService.addAddress(addressdto);
			assertEquals("saved", response);
		}
	
}
