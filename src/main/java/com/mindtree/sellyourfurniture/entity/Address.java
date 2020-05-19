package com.mindtree.sellyourfurniture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "address")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String addressStreet;
	private String addressCity;
	private String addressDistrict;
	private String addressState;
	private String addressCountry;

	private String addressPincode;

	@ManyToOne
	private User userAddress;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(int addressId, String addressStreet, String addressCity, String addressDistrict, String addressState,
			String addressCountry, String addressPincode, User userAddress) {
		super();
		this.addressId = addressId;
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
		this.addressDistrict = addressDistrict;
		this.addressState = addressState;
		this.addressCountry = addressCountry;
		this.addressPincode = addressPincode;
		this.userAddress = userAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressCity == null) ? 0 : addressCity.hashCode());
		result = prime * result + ((addressCountry == null) ? 0 : addressCountry.hashCode());
		result = prime * result + ((addressDistrict == null) ? 0 : addressDistrict.hashCode());
		result = prime * result + addressId;
		result = prime * result + ((addressPincode == null) ? 0 : addressPincode.hashCode());
		result = prime * result + ((addressState == null) ? 0 : addressState.hashCode());
		result = prime * result + ((addressStreet == null) ? 0 : addressStreet.hashCode());
		result = prime * result + ((userAddress == null) ? 0 : userAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressCity == null) {
			if (other.addressCity != null)
				return false;
		} else if (!addressCity.equals(other.addressCity))
			return false;
		if (addressCountry == null) {
			if (other.addressCountry != null)
				return false;
		} else if (!addressCountry.equals(other.addressCountry))
			return false;
		if (addressDistrict == null) {
			if (other.addressDistrict != null)
				return false;
		} else if (!addressDistrict.equals(other.addressDistrict))
			return false;
		if (addressId != other.addressId)
			return false;
		if (addressPincode == null) {
			if (other.addressPincode != null)
				return false;
		} else if (!addressPincode.equals(other.addressPincode))
			return false;
		if (addressState == null) {
			if (other.addressState != null)
				return false;
		} else if (!addressState.equals(other.addressState))
			return false;
		if (addressStreet == null) {
			if (other.addressStreet != null)
				return false;
		} else if (!addressStreet.equals(other.addressStreet))
			return false;
		if (userAddress == null) {
			if (other.userAddress != null)
				return false;
		} else if (!userAddress.equals(other.userAddress))
			return false;
		return true;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public User getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(User userAddress) {
		this.userAddress = userAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressStreet=" + addressStreet + ", addressCity=" + addressCity
				+ ", addressDistrict=" + addressDistrict + ", addressState=" + addressState + ", addressCountry="
				+ addressCountry + ", addressPincode=" + addressPincode + ", userAddress=" + userAddress + "]";
	}
	
	

}
