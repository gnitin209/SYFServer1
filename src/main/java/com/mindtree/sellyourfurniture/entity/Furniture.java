package com.mindtree.sellyourfurniture.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "furniture")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "furnitureId")
public class Furniture implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int furnitureId;
	private String furnitureName;
	private String furnitureType;
	private String furnitureMaterial;
	private double furniturePrice;
	private String furnitureShippingType;
	private double furnitureShippingCharge;
	@Column(length = 1000)
	private String furnitureDescription;
	private int furnitureQuantity;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "furnitureImage")
	private List<FurnitureImage> furnitureImages;

	@OneToMany(mappedBy = "furnitureRating")
	private List<Review> review;

	@OneToMany(mappedBy = "furniture")
	private List<CartItem> cartFurnitureItem;

	@OneToMany(mappedBy = "furniture")
	private List<OrderItem> orderItem;

	public Furniture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Furniture(int furnitureId, String furnitureName, String furnitureType, String furnitureMaterial,
			double furniturePrice, String furnitureShippingType, double furnitureShippingCharge,
			String furnitureDescription, int furnitureQuantity, User user, List<FurnitureImage> furnitureImages,

			List<Review> review, List<CartItem> cartFurnitureItem, List<OrderItem> orderItem) {

		super();
		this.furnitureId = furnitureId;
		this.furnitureName = furnitureName;
		this.furnitureType = furnitureType;
		this.furnitureMaterial = furnitureMaterial;
		this.furniturePrice = furniturePrice;
		this.furnitureShippingType = furnitureShippingType;
		this.furnitureShippingCharge = furnitureShippingCharge;
		this.furnitureDescription = furnitureDescription;
		this.furnitureQuantity = furnitureQuantity;
		this.user = user;
		this.furnitureImages = furnitureImages;
		this.review = review;
		this.cartFurnitureItem = cartFurnitureItem;
		this.orderItem = orderItem;
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

	public int getFurnitureQuantity() {
		return furnitureQuantity;
	}

	public void setFurnitureQuantity(int furnitureQuantity) {
		this.furnitureQuantity = furnitureQuantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<FurnitureImage> getFurnitureImages() {
		return furnitureImages;
	}

	public void setFurnitureImages(List<FurnitureImage> furnitureImages) {
		this.furnitureImages = furnitureImages;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public List<CartItem> getCartFurnitureItem() {
		return cartFurnitureItem;
	}

	public void setCartFurnitureItem(List<CartItem> cartFurnitureItem) {
		this.cartFurnitureItem = cartFurnitureItem;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	@Override
	public String toString() {
		return "Furniture [furnitureName=" + furnitureName + ", furnitureType=" + furnitureType + ", furnitureMaterial="
				+ furnitureMaterial + ", furniturePrice=" + furniturePrice + ", furnitureShippingType="
				+ furnitureShippingType + "]";
	}

	




	

}