package com.mindtree.sellyourfurniture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="furnitureimage")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "furnitureimageId")
public class FurnitureImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int furnitureimageId;
	@Lob
	private byte[] imageContent;
	
	@ManyToOne
	private Furniture furnitureImage;

	public FurnitureImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FurnitureImage(int furnitureimageId, byte[] imageContent, Furniture furnitureImage) {
		super();
		this.furnitureimageId = furnitureimageId;
		this.imageContent = imageContent;
		this.furnitureImage = furnitureImage;
	}

	public int getFurnitureimageId() {
		return furnitureimageId;
	}

	public void setFurnitureimageId(int furnitureimageId) {
		this.furnitureimageId = furnitureimageId;
	}

	public byte[] getImageContent() {
		return imageContent;
	}

	public void setImageContent(byte[] imageContent) {
		this.imageContent = imageContent;
	}

	public Furniture getFurnitureImage() {
		return furnitureImage;
	}

	public void setFurnitureImage(Furniture furnitureImage) {
		this.furnitureImage = furnitureImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FurnitureImage [furnitureimageId=" + furnitureimageId + ", imageContent=" + imageContent
				+ ", furnitureImage=" + furnitureImage + "]";
	}
	

}
