package com.mindtree.sellyourfurniture.dto;

import java.util.List;

public class ViewProductDTO {
	
	private int furnitureId;
	private String furnitureName;
	private String furnitureType;
	private String furnitureMaterial;
	private double furniturePrice;
	private String furnitureShippingType;
	private double furnitureShippingCharge;
	private String furnitureDescription;
	private List<byte[]> imageContent;
	private List<DisplayReviewDTO> reviews;
	private List<String> users;
	private List<String> userReviewed;
	private float avgRating;
	public ViewProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ViewProductDTO(int furnitureId, String furnitureName, String furnitureType, String furnitureMaterial,
			double furniturePrice, String furnitureShippingType, double furnitureShippingCharge,
			String furnitureDescription, List<byte[]> imageContent, List<DisplayReviewDTO> reviews, List<String> users,
			List<String> userReviewed, float avgRating) {
		super();
		this.furnitureId = furnitureId;
		this.furnitureName = furnitureName;
		this.furnitureType = furnitureType;
		this.furnitureMaterial = furnitureMaterial;
		this.furniturePrice = furniturePrice;
		this.furnitureShippingType = furnitureShippingType;
		this.furnitureShippingCharge = furnitureShippingCharge;
		this.furnitureDescription = furnitureDescription;
		this.imageContent = imageContent;
		this.reviews = reviews;
		this.users = users;
		this.userReviewed = userReviewed;
		this.avgRating = avgRating;
	}
	public int getFurnitureId() {
		return furnitureId;
	}
	public void setFurnitureId(int furnitureId) {
		this.furnitureId = furnitureId;
	}
	public String getFurnitureName() {
		return furnitureName;
	}
	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
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
	public List<byte[]> getImageContent() {
		return imageContent;
	}
	public void setImageContent(List<byte[]> imageContent) {
		this.imageContent = imageContent;
	}
	public List<DisplayReviewDTO> getReviews() {
		return reviews;
	}
	public void setReviews(List<DisplayReviewDTO> reviews) {
		this.reviews = reviews;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public List<String> getUserReviewed() {
		return userReviewed;
	}
	public void setUserReviewed(List<String> userReviewed) {
		this.userReviewed = userReviewed;
	}
	public float getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	

}
