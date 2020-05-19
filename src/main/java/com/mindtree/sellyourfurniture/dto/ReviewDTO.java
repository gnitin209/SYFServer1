package com.mindtree.sellyourfurniture.dto;

public class ReviewDTO {
	
	private int rating;
	private String feedback;
	private int furnitureId;
	private String userEmail;
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewDTO(int rating, String feedback, int furnitureId, String userEmail) {
		super();
		this.rating = rating;
		this.feedback = feedback;
		this.furnitureId = furnitureId;
		this.userEmail = userEmail;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getFurnitureId() {
		return furnitureId;
	}
	public void setFurnitureId(int furnitureId) {
		this.furnitureId = furnitureId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	

}
