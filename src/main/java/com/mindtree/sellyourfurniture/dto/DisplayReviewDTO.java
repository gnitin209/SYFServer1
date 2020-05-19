package com.mindtree.sellyourfurniture.dto;

public class DisplayReviewDTO {
	
	private int reviewId;
	private String userFirstName;
	private String userLastName;
	private int rating;
	private String feedback;
	private String userEmail;
	
	public DisplayReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DisplayReviewDTO(int reviewId, String userFirstName, String userLastName, int rating, String feedback,
			String userEmail) {
		super();
		this.reviewId = reviewId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.rating = rating;
		this.feedback = feedback;
		this.userEmail = userEmail;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	

}
