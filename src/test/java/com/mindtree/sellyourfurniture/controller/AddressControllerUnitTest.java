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

import com.mindtree.sellyourfurniture.dto.AddressDto;
import com.mindtree.sellyourfurniture.exception.SellYourFurnitureApplicationException;
import com.mindtree.sellyourfurniture.service.IAddressService;

public class AddressControllerUnitTest {
	@InjectMocks
    private AddressController AddressController;
    @Mock
   IAddressService addressService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    AddressDto addressdto=new AddressDto();
    @Test
    public void addAddressTest() throws SellYourFurnitureApplicationException {
    	addressdto.setAddressStreet("st.no. 1");
 addressdto.setAddressCity("jammu");
 addressdto.setAddressDistrict("jammu");
 addressdto.setAddressPincode("12123");
 addressdto.setAddressState("jammu and kashmir");
 addressdto.setAddressCountry("India");
 addressdto.setUserEmail("p@gmail.com");

   
   
    	when(addressService.addAddress(addressdto)).thenReturn("saved");
        ResponseEntity<?> response=AddressController.addAddress(addressdto);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("saved", response.getBody());

}
}
