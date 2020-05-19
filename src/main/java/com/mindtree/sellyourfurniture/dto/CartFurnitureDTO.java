package com.mindtree.sellyourfurniture.dto;

public class CartFurnitureDTO {
	private String userEmail;
	private boolean add;
	private int furnitureId;
	public CartFurnitureDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartFurnitureDTO(boolean add, String userEmail, int furnitureId) {
		super();
		this.add=add;
		this.userEmail = userEmail;
		this.furnitureId = furnitureId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getFurnitureId() {
		return furnitureId;
	}
	public void setFurnitureId(int furnitureId) {
		this.furnitureId = furnitureId;
	}
	public boolean getAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	@Override
	public String toString() {
		return "CartFurnitureDTO [userEmail=" + userEmail + ", add=" + add + ", furnitureId=" + furnitureId + "]";
	}
	
	

}
