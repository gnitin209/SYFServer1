package com.mindtree.sellyourfurniture.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostFurnitureDTO {
	private int furnitureId;
	private String furnitureType;
	private String furnitureMaterial;
	private double furniturePrice;
	private String furnitureShippingType;
	private double furnitureShippingCharge;
	private String furnitureDescription;
	private int furnitureQuantity;
	private int userId;
	private String userEmail;
	private MultipartFile file;
	private String furnitureName;
	public PostFurnitureDTO() {
		super();
	}
	public PostFurnitureDTO(int furnitureId, String furnitureType, String furnitureMaterial, double furniturePrice,
			String furnitureShippingType, double furnitureShippingCharge, String furnitureDescription,
			int furnitureQuantity, int userId, String userEmail, MultipartFile file, String furnitureName) {
		super();
		this.furnitureId = furnitureId;
		this.furnitureType = furnitureType;
		this.furnitureMaterial = furnitureMaterial;
		this.furniturePrice = furniturePrice;
		this.furnitureShippingType = furnitureShippingType;
		this.furnitureShippingCharge = furnitureShippingCharge;
		this.furnitureDescription = furnitureDescription;
		this.furnitureQuantity = furnitureQuantity;
		this.userId = userId;
		this.userEmail = userEmail;
		this.file = file;
		this.furnitureName = furnitureName;
	}
	public int getFurnitureId() {
		return furnitureId;
	}
	public void setFurnitureId(int furnitureId) {
		this.furnitureId = furnitureId;
	}
	public String getFurnitureType() {
		return furnitureType;
	}
	public void setFurnitureType(String furnitureType) {
		this.furnitureType = furnitureType;
	}
	public String getFurnitureMaterial() {
		return furnitureMaterial;
	}
	public void setFurnitureMaterial(String furnitureMaterial) {
		this.furnitureMaterial = furnitureMaterial;
	}
	public double getFurniturePrice() {
		return furniturePrice;
	}
	public void setFurniturePrice(double furniturePrice) {
		this.furniturePrice = furniturePrice;
	}
	public String getFurnitureShippingType() {
		return furnitureShippingType;
	}
	public void setFurnitureShippingType(String furnitureShippingType) {
		this.furnitureShippingType = furnitureShippingType;
	}
	public double getFurnitureShippingCharge() {
		return furnitureShippingCharge;
	}
	public void setFurnitureShippingCharge(double furnitureShippingCharge) {
		this.furnitureShippingCharge = furnitureShippingCharge;
	}
	public String getFurnitureDescription() {
		return furnitureDescription;
	}
	public void setFurnitureDescription(String furnitureDescription) {
		this.furnitureDescription = furnitureDescription;
	}
	public int getFurnitureQuantity() {
		return furnitureQuantity;
	}
	public void setFurnitureQuantity(int furnitureQuantity) {
		this.furnitureQuantity = furnitureQuantity;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFurnitureName() {
		return furnitureName;
	}
	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}
	
	
		
}
