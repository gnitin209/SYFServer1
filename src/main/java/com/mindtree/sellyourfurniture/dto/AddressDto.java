package com.mindtree.sellyourfurniture.dto;

public class AddressDto {
	private String addressStreet;
	private String addressCity;
	private String addressDistrict;
	private String addressState;
	private String addressCountry;
	private String addressPincode;
	private String userEmail;
	public AddressDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AddressDto(String addressStreet, String addressCity, String addressDistrict, String addressState,
			String addressCountry, String addressPincode, String userEmail) {
		super();
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.addressDistrict = addressDistrict;
		this.addressState = addressState;
		this.addressCountry = addressCountry;
		this.addressPincode = addressPincode;
		this.userEmail = userEmail;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressDistrict() {
		return addressDistrict;
	}
	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	public String getAddressCountry() {
		return addressCountry;
	}
	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}
	public String getAddressPincode() {
		return addressPincode;
	}
	public void setAddressPincode(String addressPincode) {
		this.addressPincode = addressPincode;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	
	
}