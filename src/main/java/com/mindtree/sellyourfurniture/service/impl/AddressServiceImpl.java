package com.mindtree.sellyourfurniture.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.sellyourfurniture.dto.AddressDto;
import com.mindtree.sellyourfurniture.entity.Address;
import com.mindtree.sellyourfurniture.entity.User;
import com.mindtree.sellyourfurniture.repository.AddressRepository;
import com.mindtree.sellyourfurniture.repository.UserRepository;
import com.mindtree.sellyourfurniture.service.IAddressService;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;

	@Override
	public String addAddress(AddressDto address) {
		Address addres = new Address();
		addres.setAddressStreet(address.getAddressStreet());
		addres.setAddressCity(address.getAddressCity());
		addres.setAddressDistrict(address.getAddressDistrict());
		addres.setAddressState(address.getAddressState());
		addres.setAddressCountry(address.getAddressCountry());
		addres.setAddressPincode(address.getAddressPincode());
		User user = userRepository.findByuserEmail(address.getUserEmail()).orElse(null);
		addres.setUserAddress(user);
		addressRepository.save(addres);
		System.out.println("saved");
		return "saved";
	}

//	@Override
//	public List<AddressGetDto> getAddress(String userEmail) {
//		User user = userRepository.findByuserEmail(userEmail).orElse(null);
//		List<AddressGetDto> addresslist = new ArrayList<AddressGetDto>();
//		for (Address u : user.getUserAddress()) {
//			AddressGetDto addressdto = new AddressGetDto();
//			addressdto.setAddressStreet(u.getAddressStreet());
//			addressdto.setAddressCity(u.getAddressCity());
//			addressdto.setAddressDistrict(u.getAddressDistrict());
//			addressdto.setAddressPincode(u.getAddressPincode());
//			addressdto.setAddressState(u.getAddressState());
//			addressdto.setAddressCountry(u.getAddressCountry());
//			addresslist.add(addressdto);
//		}
//
//		return addresslist;
//	}

}

