package com.mindtree.sellyourfurniture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="review")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "reviewId")
public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewId;
	
	private int rating;
	
	@Column(length=1000) 
	private String feedback;
	
	@ManyToOne
	private Furniture furnitureRating;
	
	@ManyToOne
	private User userRating;

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewId, int rating, String feedback, Furniture furnitureRating, User userRating) {
		super();
		this.reviewId = reviewId;
		this.rating = rating;
		this.feedback = feedback;
		this.furnitureRating = furnitureRating;
		this.userRating = userRating;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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

	public Furniture getFurnitureRating() {
		return furnitureRating;
	}

	public void setFurnitureRating(Furniture furnitureRating) {
		this.furnitureRating = furnitureRating;
	}

	public User getUserRating() {
		return userRating;
	}

	public void setUserRating(User userRating) {
		this.userRating = userRating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", rating=" + rating + ", feedback=" + feedback + ", furnitureRating="
				+ furnitureRating + ", userRating=" + userRating + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((furnitureRating == null) ? 0 : furnitureRating.hashCode());
		result = prime * result + rating;
		result = prime * result + reviewId;
		result = prime * result + ((userRating == null) ? 0 : userRating.hashCode());
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
		Review other = (Review) obj;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (furnitureRating == null) {
			if (other.furnitureRating != null)
				return false;
		} else if (!furnitureRating.equals(other.furnitureRating))
			return false;
		if (rating != other.rating)
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (userRating == null) {
			if (other.userRating != null)
				return false;
		} else if (!userRating.equals(other.userRating))
			return false;
		return true;
	}
	

}
